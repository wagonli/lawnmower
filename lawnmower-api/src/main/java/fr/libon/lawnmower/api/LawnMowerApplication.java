package fr.libon.lawnmower.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "fr.libon.lawnmower")
public class LawnMowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawnMowerApplication.class, args);
    }
}
