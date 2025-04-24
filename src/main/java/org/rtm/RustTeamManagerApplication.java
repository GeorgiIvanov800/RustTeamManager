package org.rtm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RustTeamManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RustTeamManagerApplication.class, args);
    }

}
