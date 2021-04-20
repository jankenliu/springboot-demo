package com.jankin.springboot.demo.test.t2;

import com.jankin.springboot.demo.common.util.SpringUtil;
import com.jankin.springboot.demo.service.abs.Pig;

public abstract class AniAbstract  {
    int a;

    public Pig pig;

    //@Override
    public AniAbstract() {
        System.out.println("Ani--init1----");
        a=11;
        pig= SpringUtil.getBean(Pig.class);
    }
}
