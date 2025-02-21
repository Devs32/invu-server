package kr.co.devs32;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "kr.co.devs32")
public class InvuApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvuApplication.class, args);
    }
}
