package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MyApplication.class, args);
        /*String[] beanDefinitionNames = run.getBeanDefinitionNames();
        System.out.println("个数：" + beanDefinitionNames.length);
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }*/
        boolean exist = run.containsBean("a");
        System.out.println("是否存在a:"+exist);
    }
}
