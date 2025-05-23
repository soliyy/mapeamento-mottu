package com.mottu.mapeamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MapeamentoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MapeamentoApplication.class, args);
	}
}
