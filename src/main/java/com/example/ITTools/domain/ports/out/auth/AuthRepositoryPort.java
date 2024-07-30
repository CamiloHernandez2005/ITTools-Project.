package com.example.ITTools.domain.ports.out.auth;


import com.example.ITTools.domain.ports.in.auth.dtos.LoginDTO;
import com.example.ITTools.domain.ports.in.auth.dtos.SaveUserDTO;

public interface AuthRepositoryPort {
    public String login(LoginDTO login) throws Exception;
    void register(SaveUserDTO saveUserDTO);
}
