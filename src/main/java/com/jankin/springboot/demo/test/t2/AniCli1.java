package com.jankin.springboot.demo.test.t2;

import org.springframework.stereotype.Service;

@Service
public class AniCli1 extends AniAbstract{
    int b;

    public int getAVal(){
        return a;
    }

    public int getBVal(){
        return b;
    }


    public AniCli1() {
        System.out.println("---------");
    }
}