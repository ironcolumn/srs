package com.srs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zzy
 */
@EntityScan ( basePackages = "com/srs/domain" )
@EnableJpaRepositories ( basePackages = "com.srs.dao" )
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@ServletComponentScan
@ComponentScan
@EnableScheduling
public class SrsApplication {

    public static void main ( String[] args ) {

        SpringApplication.run ( SrsApplication.class , args );
    }
}
