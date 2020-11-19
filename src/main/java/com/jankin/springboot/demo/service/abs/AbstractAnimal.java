package com.jankin.springboot.demo.service.abs;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author lyy
 * @date 2020/9/1 6:48 下午
 */
public abstract class AbstractAnimal {

    @Value("${zlh.ossTransfer.environment:dev}")
    protected String environment;

    protected String envDirName(){
        if ("dev".equals(environment) || "qa".equals(environment)) {
            return environment + "/";
        } else if ("online".equals(environment)) {
            return "";
        }
        return "other";
    }

    public abstract String name();


    public AbstractAnimal() {
        System.out.println("sssss");
    }

    public static void main(String[] args) {
        System.out.println(1>>1);
        System.out.println(1>>2);
        System.out.println(1>>3);
        System.out.println("---");
        System.out.println(2>>1);
        System.out.println(2>>2);
        System.out.println(2>>3);
        System.out.println("---");
        System.out.println(2>>>1);
        System.out.println(2>>>2);
        System.out.println(2>>>3);
        System.out.println("---");
        System.out.println(1>>>1);
        System.out.println(1>>>2);
        System.out.println(1>>>3);
        System.out.println(17>>>16);
    }

}
