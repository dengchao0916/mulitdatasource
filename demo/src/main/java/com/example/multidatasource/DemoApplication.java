package com.example.multidatasource;

import com.example.multidatasource.annotation.EnableMultiDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@MapperScan(value = "com.example.multidatasource.mapper")
@EnableMultiDataSource
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
	}

}
