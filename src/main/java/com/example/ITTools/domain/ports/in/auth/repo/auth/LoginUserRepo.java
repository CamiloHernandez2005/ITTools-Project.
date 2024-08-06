package com.example.ITTools.domain.ports.in.auth.repo.auth;


import com.example.ITTools.domain.ports.in.auth.dtos.LoginDTO;
import com.example.ITTools.domain.ports.in.auth.dtos.SaveUserDTO;

public interface LoginUserRepo {
    String login(LoginDTO saveUser) throws Exception;
}
