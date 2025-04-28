package org.rtm.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class BeansConfigurations {
    @Bean
    public AuditorAware<String> auditorAware() {
        return Optional::empty;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:ValidationMessages_de",
                "classpath:ExceptionMessages_de"
        );
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
