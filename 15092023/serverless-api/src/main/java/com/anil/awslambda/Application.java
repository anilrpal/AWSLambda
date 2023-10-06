package com.anil.awslambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.anil.awslambda.controller.PingController;
import com.anil.awslambda.external.service.JsonPlaceholderService;


@SpringBootApplication
//// We use direct @Import instead of @ComponentScan to speed up cold starts
//// @ComponentScan(basePackages = "com.anil.awslambda.controller")
//@Import({ PingController.class })
@CrossOrigin(origins = "*")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	JsonPlaceholderService jsonPlaceholderService() {
	    WebClient client = WebClient.builder()
	            .baseUrl("https://jsonplaceholder.typicode.com")
	            .build();
	    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
	    return factory.createClient(JsonPlaceholderService.class);
	}
}