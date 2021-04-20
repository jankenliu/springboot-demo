package com.jankin.springboot.demo.test.t2;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author p_pyyliu
 * @date 2021/4/6 7:23 下午
 */
@Service
public class TAniService {

    @Resource
    private AniCli1 aniCli1;

    public void m2(){
        System.out.println("aniCli1.pig = " + aniCli1.pig.name());
    }
}
