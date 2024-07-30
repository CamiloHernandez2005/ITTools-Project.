package com.example.ITTools.infrastructure.config.auth;

import com.example.ITTools.application.repos.AuthRepo;
import com.example.ITTools.application.usecases.LoginUseCaseImpl;
import com.example.ITTools.application.usecases.RegisterUseCaseImpl;
import com.example.ITTools.domain.ports.out.auth.AuthRepositoryPort;
import com.example.ITTools.infrastructure.adapters.jpa.user.repositories.JpaUserRepositoryAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthRepositoryConfig {

    @Bean
    public AuthRepo authRepo(
        @Qualifier("authRepositoryPort") AuthRepositoryPort authRepoPort,
        LoginUseCaseImpl loginUseCase,
        RegisterUseCaseImpl registerUseCase
        ) {
        
        return new AuthRepo(
            new RegisterUseCaseImpl(authRepoPort),
            new LoginUseCaseImpl(authRepoPort)
        );
    }

    @Bean
    public AuthRepositoryPort authRepositoryPort(JpaUserRepositoryAdapter jpaAuthRepositoryAdapter) {
         return jpaAuthRepositoryAdapter;
    }

    @Bean
    public LoginUseCaseImpl loginAuthUseCaseImpl(AuthRepositoryPort authRepositoryPort) {
        return new LoginUseCaseImpl(authRepositoryPort);
    }
    @Bean
    public RegisterUseCaseImpl registerAuthUseCaseImpl(AuthRepositoryPort authRepositoryPort) {
        return new RegisterUseCaseImpl(authRepositoryPort);
    }
}
