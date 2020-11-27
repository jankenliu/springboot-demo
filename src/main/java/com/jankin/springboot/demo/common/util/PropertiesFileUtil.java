package com.jankin.springboot.demo.common.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * 属性文件读取工具
 *
 * @author lyy
 * @date 2018/3/29 9:13
 */
public class PropertiesFileUtil {
    // 当打开多个资源文件时，缓存资源文件
    private static HashMap<String, PropertiesFileUtil> configMap = new HashMap<>();
    // 打开文件时间，判断超时使用
    private Date loadTime = null;
    // 资源文件
    private  Properties properties;
    // 缓存时间
    private static final Integer TIME_OUT = 60 * 1000;

    // 私有构造方法，创建单例
    private PropertiesFileUtil(String resourceName) {
        this.loadTime = new Date();
        if (resourceName.contains(".yml")){
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(new ClassPathResource(resourceName));
            this.properties = yaml.getObject();
        }else if (resourceName.contains(".properties")){
            this.properties=new Properties();
            try {
                properties.load(PropertiesFileUtil.class.getResourceAsStream(resourceName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("该类型后缀的资源文件不支持,请检查后缀，resourceName:"+resourceName);
        }
    }

    public static synchronized PropertiesFileUtil getInstance(String resourceName) {
        PropertiesFileUtil conf = configMap.get(resourceName);
        if (null == conf) {
            conf = new PropertiesFileUtil(resourceName);
            configMap.put(resourceName, conf);
        }
        // 判断是否打开的资源文件是否超时1分钟
        if ((System.currentTimeMillis() - conf.getLoadTime().getTime()) > TIME_OUT) {
            conf = new PropertiesFileUtil(resourceName);
            configMap.put(resourceName, conf);
        }
        return conf;
    }

    // 根据key读取value
    public String get(String key) {
        try {
            return properties.getProperty(key);
        } catch (MissingResourceException e) {
            return "";
        }
    }

    // 根据key读取value(整形)
    public Integer getInt(String key) {
        try {
            String value = properties.getProperty(key);
            return Integer.parseInt(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    // 根据key读取value(布尔)
    public boolean getBool(String key) {
        try {
            String value = properties.getProperty(key);
            return "true".equals(value);
        } catch (MissingResourceException e) {
            return false;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public static void main(String[] args) {
        //获取yml文件属性值
        String yml_test=PropertiesFileUtil.getInstance("/config/test.yml").get("test.app");
        System.out.println("yml_test = " + yml_test);

        //获取properties文件属性值
        String properties_test=PropertiesFileUtil.getInstance("/config/application-dev.yml").get("application.server.port");
        System.out.println("properties_test = " + properties_test);
    }

}
