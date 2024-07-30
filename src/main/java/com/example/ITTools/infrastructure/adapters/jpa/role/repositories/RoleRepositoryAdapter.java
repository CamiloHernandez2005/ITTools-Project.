package com.example.ITTools.infrastructure.adapters.jpa.role.repositories;

import com.example.ITTools.domain.models.Role;
import com.example.ITTools.domain.ports.out.role.RoleRepositoryPort;
import com.example.ITTools.infrastructure.entities.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findByAuthority(String authority) {
        Optional<RoleEntity> roleEntity = jpaRoleRepository.findByAuthority(authority);
        return roleEntity.map(this::mapToDomain);
    }

    private Role mapToDomain(RoleEntity roleEntity) {
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setAuthority(roleEntity.getAuthority());
        return role;
    }
}
