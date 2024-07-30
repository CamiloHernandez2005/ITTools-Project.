package com.example.ITTools.application.usecases;


import com.example.ITTools.domain.ports.in.auth.dtos.SaveUserDTO;
import com.example.ITTools.domain.ports.in.auth.repo.auth.RegisterUserRepo;
import com.example.ITTools.domain.ports.out.auth.AuthRepositoryPort;

public class RegisterUseCaseImpl implements RegisterUserRepo {
        private final AuthRepositoryPort userRepositoryPort;

    public RegisterUseCaseImpl(AuthRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void register(SaveUserDTO saveUserDTO) {
        userRepositoryPort.register(saveUserDTO);
    }

}
