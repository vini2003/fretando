package dev.vini2003.fretando.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("dev.vini2003.fretando.*")
@ComponentScan("dev.vini2003.fretando.*")
public class FretandoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FretandoApplication.class, args);
    }
}
