package com.example.ITTools.application.repos;

import com.example.ITTools.domain.ports.in.auth.dtos.LoginDTO;
import com.example.ITTools.domain.ports.in.auth.dtos.SaveUserDTO;
import com.example.ITTools.domain.ports.in.auth.repo.auth.LoginUserRepo;
import com.example.ITTools.domain.ports.in.auth.repo.auth.RegisterUserRepo;

public class AuthRepo implements IAuthRepo {

    private final RegisterUserRepo registerUserRepo;
    private final LoginUserRepo loginUserRepo;

    public AuthRepo(RegisterUserRepo registerUserRepo,
            LoginUserRepo loginUserRepo) {
        this.registerUserRepo = registerUserRepo;
        this.loginUserRepo = loginUserRepo;
    }

    @Override
    public void register(SaveUserDTO saveUser) {
        registerUserRepo.register(saveUser);
    }

    @Override
    public String login(LoginDTO loginDTO) throws Exception {
        return loginUserRepo.login(loginDTO);
    }


}
