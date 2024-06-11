package com.fayea.runnerz;

import com.fayea.runnerz.run.Location;
import com.fayea.runnerz.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Run run = new Run(
                    1,
                    "Run 1",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    10,
                    Location.OUTDOOR
            );
            log.info("Created Run: {}", run);

        };


    }
}


