package com.ddm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ddm.dao") //avoid adding @Mapper for every mybatis dao.
public class WebStubApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStubApplication.class, args);
	}
}
