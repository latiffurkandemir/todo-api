package com.todoapp.todo_api.audit;

import com.todoapp.todo_api.users.AutenticationName;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(AutenticationName.isAuthenticationName());
    }
}
