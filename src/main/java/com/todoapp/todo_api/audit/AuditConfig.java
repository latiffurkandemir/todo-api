package com.todoapp.todo_api.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {

    @Bean
    public AuditorAwareImpl auditorAware() {
        return new AuditorAwareImpl();
    }
}
