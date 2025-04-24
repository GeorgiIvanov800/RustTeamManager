package org.rtm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class BeansConfigurations {
    @Bean
    public AuditorAware<String> auditorAware() {
        return Optional::empty;
    }
}
