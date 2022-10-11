package com.mentpeak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hzl
 * @data 2022年05月09日13:40
 */
@SpringBootApplication
//@MapperScan("com.mentpeak.website.mapper")
public class HydApplication {
    public static void main(String[] args) {
        SpringApplication.run(HydApplication.class,args);
    }
}
