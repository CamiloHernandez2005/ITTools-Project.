package com.example.ITTools.domain.ports.out.role;


import com.example.ITTools.domain.models.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
     Optional<Role> findByAuthority(String authority);
}
