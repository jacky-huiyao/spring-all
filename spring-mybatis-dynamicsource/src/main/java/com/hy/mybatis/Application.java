package com.hy.mybatis;

import com.hy.mybatis.annotation.EnableDynamicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@EnableWebMvc
@EnableDynamicDataSource
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
