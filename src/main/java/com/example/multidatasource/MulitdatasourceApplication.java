package com.example.multidatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableAspectJAutoProxy
@MapperScan(value = "com.example.multidatasource.mapper")
public class MulitdatasourceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MulitdatasourceApplication.class, args);
	}

}
