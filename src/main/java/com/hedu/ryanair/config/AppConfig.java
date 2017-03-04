package com.hedu.ryanair.config;

/**
 * Created by hedu on 4/03/17.
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hedu.ryanair")
public class AppConfig {

}