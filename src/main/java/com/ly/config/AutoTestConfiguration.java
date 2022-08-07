package com.ly.config;

import com.ly.entity.A;
import com.ly.entity.B;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({B.class})
public class AutoTestConfiguration {

    @Bean
    public A a(){
        return new A();
    }
}
