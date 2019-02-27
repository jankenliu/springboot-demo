package com.jankin.springboot.demo.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 逆向工程-代码生成工具
 *
 * @author lyy
 * @date 2018/3/29 9:13
 */
public class MybatisGeneratorUtil {

    /**
     * jdbc驱动
     */
    private static String jdbcDriver;
    /**
     * jdbc地址
     */
    private static String jdbcUrl;
    /**
     * jdbc用户名
     */
    private static String jdbcUsername;
    /**
     * jdbc密码
     */
    private static String jdbcPassword;
    /**
     * 数据库
     */
    private static String database;
    /**
     * 逆向生成指向的基本包名地址
     */
    private static String packageName;

    /**
     * 最后插入表的主键值
     */
    private static Map<String, String> lastInsertIdTables;

    /**
     * generatorConfig模板路径
     */
    private static String generatorConfig_vm = "/mybatis/template/generatorConfig.vm";
    /**
     * Service模板路径
     */
    private static String service_vm = "/mybatis/template/Service.vm";
    /**
     * ServiceImpl模板路径
     */
    private static String serviceImpl_vm = "/mybatis/template/ServiceImpl.vm";
    /**
     * Controller模板路径
     */
    private static String controller_vm = "/mybatis/template/Controller.vm";
    /**
     * generatorConfigXml路径
     */
    private static String generatorConfigXml;


    /**
     * 根据模板生成generatorConfig.xml文件
     *
     * @param jdbcDriver   驱动路径
     * @param jdbcUrl      链接
     * @param jdbcUsername 帐号
     * @param jdbcPassword 密码
     * @param packageName  包名
     */
    public static void generator(String jdbcDriver, String jdbcUrl, String jdbcUsername, String jdbcPassword, String packageName, Map<String, String> lastInsertIdTables) throws Exception {
        MybatisGeneratorUtil.jdbcDriver = jdbcDriver;
        MybatisGeneratorUtil.jdbcUrl = jdbcUrl;
        MybatisGeneratorUtil.jdbcUsername = jdbcUsername;
        MybatisGeneratorUtil.jdbcPassword = jdbcPassword;
        MybatisGeneratorUtil.packageName = packageName;
        MybatisGeneratorUtil.lastInsertIdTables = lastInsertIdTables;

        Pattern pattern = Pattern.compile(":\\d{4}/(\\w{1,})\\?");
        Matcher matcher = pattern.matcher(jdbcUrl);
        if (matcher.find()) {
            database = matcher.group(1);
        }

        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
            service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
            serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");
            controller_vm = MybatisGeneratorUtil.class.getResource(controller_vm).getPath().replaceFirst("/", "");
        } else {
            generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
            service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath();
            serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath();
            controller_vm = MybatisGeneratorUtil.class.getResource(controller_vm).getPath();
        }
        generatorConfigXml = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/mybatis/generatorConfig.xml";

        //生成generatorConfig.xml文件
        List<Map<String, Object>> tables = generatorXml();
        //运行MybatisGenerator
        doMybatisGenerator();
        //生成Service层
        generatorService(tables);
        //生成Controller层
        //generatorController(tables);
    }

    private static List<Map<String, Object>> generatorXml() throws Exception {
        System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
        List<Map<String, Object>> tables = new ArrayList<>();
        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "';";

        VelocityContext context = new VelocityContext();
        Map<String, Object> table;

        // 查询所有表
        JdbcUtil jdbcUtil = new JdbcUtil(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
        List<Map> result = jdbcUtil.selectByParams(sql, null);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + ": " + result.get(i).get("TABLE_NAME"));
            table = new HashMap<>(2);
            table.put("table_name", result.get(i).get("TABLE_NAME"));
            table.put("model_name", StringUtil.lineToHump(ObjectUtils.toString(result.get(i).get("TABLE_NAME"))));
            tables.add(table);
        }
        jdbcUtil.release();

        context.put("tables", tables);
        context.put("generator_javaModelGenerator_targetPackage", packageName + ".model.po");
        //context.put("generator_sqlMapGenerator_targetPackage", packageName + ".mapper.xml");
        context.put("generator_javaClientGenerator_targetPackage", packageName + ".mapper");
        context.put("last_insert_id_tables", lastInsertIdTables);
        VelocityUtil.generate(generatorConfig_vm, generatorConfigXml, context);
        // 删除实体旧代码
        deleteDir(new File("src/main/java/" + packageName.replaceAll("\\.", "/") + "/model/po"));
        // 删除dao层旧代码(mapper、mapperXML)
        //deleteDir(new File("src/main/java/" + packageName.replaceAll("\\.", "/") + "/mapper/xml"));
        System.out.println("========== 结束生成generatorConfig.xml文件 ==========");
        return tables;
    }

    private static void doMybatisGenerator() throws Exception {
        System.out.println("========== 开始运行MybatisGenerator ==========");
        List<String> warnings = new ArrayList<>();
        File configFile = new File(generatorConfigXml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback dsc = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, dsc, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
        System.out.println("========== 结束运行MybatisGenerator ==========");
    }

    private static void generatorService(List<Map<String, Object>> tables) throws Exception {
        System.out.println("========== 开始生成Service ==========");
        String ctime = new SimpleDateFormat("yyyy-M-d").format(new Date());
        String servicePath = "src/main/java/" + packageName.replaceAll("\\.", "/") + "/service";
        String serviceImplPath = "src/main/java/" + packageName.replaceAll("\\.", "/") + "/service/impl";

        //检查service 与 serviceImpl 目录是否存在，不存在则主动创建
        if (!(checkDir(servicePath) && checkDir(serviceImplPath))) {
            return;
        }

        for (Map<String, Object> table : tables) {
            String model = StringUtil.lineToHump(ObjectUtils.toString(table.get("table_name")));
            // 生成service
            String service = servicePath + "/" + model + "Service.java";
            File serviceFile = new File(service);
            System.out.println("检测到 " + model + "Service.java" + " 文件存在为 ：" + serviceFile.exists());
            if (!serviceFile.exists()) {
                System.out.println("。。。 开始生成 " + model + "Service.java");
                VelocityContext context = new VelocityContext();
                context.put("package_name", packageName);
                context.put("model", model);
                context.put("ctime", ctime);
                VelocityUtil.generate(service_vm, service, context);
                System.out.println(service);
                System.out.println("。。。 生成 " + model + "Service.java 成功!");
            }
            // 生成serviceImpl
            String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
            File serviceImplFile = new File(serviceImpl);
            System.out.println("检测到 " + model + "ServiceImpl.java" + " 文件存在为 ：" + serviceImplFile.exists());
            if (!serviceImplFile.exists()) {
                System.out.println("。。。 开始生成 " + model + "ServiceImpl.java");
                VelocityContext context = new VelocityContext();
                context.put("package_name", packageName);
                context.put("model", model);
                context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
                context.put("ctime", ctime);
                VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
                System.out.println(serviceImpl);
                System.out.println("。。。 生成 " + model + "ServiceImpl.java 成功!");
            }
        }
        System.out.println("========== 结束生成Service ==========");
    }

    private static void generatorController(List<Map<String, Object>> tables) throws Exception {
        System.out.println("========== 开始生成Controller ==========");
        String ctime = new SimpleDateFormat("yyyy-M-d").format(new Date());
        String controllerPath = "src/main/java/" + packageName.replaceAll("\\.", "/") + "/controller";

        //检查controller 目录是否存在，不存在则主动创建
        if (!checkDir(controllerPath)) {
            return;
        }

        for (Map<String, Object> table : tables) {
            String model = StringUtil.lineToHump(ObjectUtils.toString(table.get("table_name")));
            String controller = controllerPath + "/" + model + "Controller.java";
            // 生成controller
            File controllerFile = new File(controller);
            System.out.println("检测到 " + model + "Controller.java" + " 文件存在为 ：" + controllerFile.exists());
            if (!controllerFile.exists()) {
                System.out.println("。。。 开始生成 " + model + "Controller.java");
                VelocityContext context = new VelocityContext();
                context.put("package_name", packageName);
                context.put("model", model);
                context.put("modelLowerCase", StringUtil.toLowerCaseFirstOne(model));
                context.put("ctime", ctime);
                VelocityUtil.generate(controller_vm, controller, context);
                System.out.println(controller);
                System.out.println("。。。 生成 " + model + "Controller.java 成功!");
            }
        }
        System.out.println("========== 结束生成Controller ==========");
    }

    /**
     * 递归删除非空文件夹
     *
     * @param dir 文件目录
     */
    private static void deleteDir(@NotNull File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (null != files) {
                for (File file : files) {
                    deleteDir(file);
                }
            }
        }
        boolean delete = dir.delete();
        if (!delete) {
            System.out.println("文件删除失败:" + dir);
        }
    }

    /**
     * 检查目录是否存在，不存在则自动生成一个
     *
     * @param dir 目录路径
     */
    private static boolean checkDir(String dir) {
        File outputServiceImplDirectory = new File(dir);
        boolean isExistsServiceImplDirectory = outputServiceImplDirectory.exists();
        System.out.println((isExistsServiceImplDirectory ? "已检测到" : "未检测到") + "目录 : " + dir);
        if (!isExistsServiceImplDirectory) {
            System.out.println("正在生成目录 --->>>" + dir);
            boolean mkdir = outputServiceImplDirectory.mkdir();
            if (mkdir) {
                System.out.println("生成目录成功！ --->>>" + dir);
                return true;
            } else {
                System.out.println("生成目录失败！ --->>>" + dir);
                return false;
            }
        }
        return true;
    }

}
