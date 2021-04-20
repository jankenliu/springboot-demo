package com.jankin.springboot.demo;

import com.jankin.springboot.demo.common.util.SpringUtil;
import com.jankin.springboot.demo.service.impl.ShopServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.annotation.Order;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScan("com.jankin.springboot.demo.mapper")
//开启Feign
@EnableFeignClients
@Order(value = 3)
public class SpringbootDemoApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }
    


    @Override
    public void run(String... args) throws Exception {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2, 2, 1, TimeUnit.HOURS,new LinkedBlockingQueue<>());
        executor.execute(()->{
            while (true){
                try {

                    ShopServiceImpl bean = SpringUtil.getBean(ShopServiceImpl.class);
                    String name = bean.aniCli1.pig.name();
                    System.out.println("name = " + name);
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        //String name = shopService.aniCli1.pig.name();
        //System.out.println("name = " + name);
    }
}
