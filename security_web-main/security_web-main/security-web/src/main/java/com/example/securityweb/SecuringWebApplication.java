package com.example.securityweb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.securityweb.model")
public class SecuringWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecuringWebApplication.class, args);
    }
}
