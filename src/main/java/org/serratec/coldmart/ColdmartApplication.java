package org.serratec.coldmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableFeignClients
@SpringBootApplication
@EnableAsync // obrigado mateus o mundo te ama
public class ColdmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColdmartApplication.class, args);
	}

}
