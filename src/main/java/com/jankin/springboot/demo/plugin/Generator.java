package com.jankin.springboot.demo.plugin;

import com.jankin.springboot.demo.util.MybatisGeneratorUtil;
import com.jankin.springboot.demo.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * mysql逆向工程-代码生成类
 *
 * @author lyy
 * @date 2018/3/29 9:05
 */
public class Generator {
    /**
     *  根据命名规范，只修改此常量值即可
     */
    private final static String PACKAGE_NAME =  PropertiesFileUtil.getInstance("profiles/dev").get("generator.packageName");
    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("profiles/dev").get("generator.jdbc.driver");
    private static String JDBC_URL = PropertiesFileUtil.getInstance("profiles/dev").get("generator.jdbc.url");
    private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("profiles/dev").get("generator.jdbc.username");
    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("profiles/dev").get("generator.jdbc.password");
    /**
     *  需要insert后返回主键的表配置，key:表名,value:主键名
     */
    private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();

    /**
     * 自动代码生成
     * @param args
     */
    public static void main(String[] args) {
        try {
            MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, PACKAGE_NAME, LAST_INSERT_ID_TABLES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

