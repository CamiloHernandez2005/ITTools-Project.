package com.example.ITTools.infrastructure.adapters.jpa.user.repositories;


import com.example.ITTools.domain.ports.in.auth.dtos.LoginDTO;
import com.example.ITTools.domain.ports.in.auth.dtos.SaveUserDTO;
import com.example.ITTools.domain.ports.out.auth.AuthRepositoryPort;
import com.example.ITTools.infrastructure.adapters.jpa.role.repositories.JpaRoleRepository;
import com.example.ITTools.infrastructure.entities.RoleEntity;
import com.example.ITTools.infrastructure.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JpaUserRepositoryAdapter implements AuthRepositoryPort {

    private final JpaUserRepository userRepo;
    private final JpaRoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;


    public JpaUserRepositoryAdapter(JpaUserRepository userRepo, JpaRoleRepository roleRepo, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }


    @Override
    public void register(SaveUserDTO saveUserDTO) {
        if (userRepo.findByUsername(saveUserDTO.getEmail()).isPresent()){
            throw new UnsupportedOperationException(" email " + saveUserDTO.getEmail() + " exist");
        }
        UserEntity user = new UserEntity();
        if (saveUserDTO.getRoles().isEmpty()) {
           throw new UnsupportedOperationException(" role not present");

        } else {
            Set<RoleEntity> setRole = new HashSet<RoleEntity>();
            for (String role : saveUserDTO.getRoles()) {
                RoleEntity roleFind = roleRepo.findByAuthority(role).orElseThrow();
                setRole.add(roleFind);
            }
            user.setAuthorities(setRole);
        }
        user.setUsername(saveUserDTO.getEmail());
        String passwordString = passwordEncoder.encode(saveUserDTO.getPassword());
        user.setPassword(passwordString);
        user.setFullName(saveUserDTO.getFull_name()); // Asegúrate de que los nombres coincidan
        user.setCharge(saveUserDTO.getCharge());
        user.setArea(saveUserDTO.getArea());
        user.setPhone(saveUserDTO.getPhone());
        user.setStatus(true);
        userRepo.save(user);
    }


    @Override
    public String login(LoginDTO login) throws Exception  {
        UserEntity findUser = userRepo.findByUsername(login.getEmail()).orElseThrow();

        if  (!passwordEncoder.matches(login.getPassword(), findUser.getPassword())) {
            throw new Exception("wrong password or username");
        }

        Instant now = Instant.now();
        long expiry = 36000L;

        String roles = findUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(findUser.getUsername())
                .claim("scope", roles)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


}