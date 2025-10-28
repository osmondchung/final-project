package com.bootcamp.demo.project_data_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.bootcamp.demo")
@EnableScheduling
public class ProjectDataProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectDataProviderApplication.class, args);
	}

}
