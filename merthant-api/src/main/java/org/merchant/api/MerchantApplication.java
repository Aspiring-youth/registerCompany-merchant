package org.merchant.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(scanBasePackages = {"org.merchant.db", "org.merchant.api"})
@MapperScan("org.merchant.db.dao")
//@EnableTransactionManagement
@SpringBootApplication
//@EnableScheduling
public class MerchantApplication {
    public static void main(String[] args) {
        SpringApplication.run(MerchantApplication.class, args);
    }
}
