package com.example.ITTools.Region.Models;

import com.example.ITTools.Region.DTO.RegionDTO;
import com.example.ITTools.Server.Models.ServerModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "regions")
public class RegionModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idRegion;

    @Column(nullable = false)
    @Getter @Setter
    private String nameRegion;

    @Column(nullable = false)
    @Getter @Setter
    private String description;

    @Column(nullable = false)
    @Getter @Setter
    private int status;
    // 1: activo, 0: inactivo
   // Ignora la propiedad en la serialización
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ServerModel> servers;

    public RegionDTO toDTO() {
        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(this.idRegion);
        dto.setNameRegion(this.nameRegion);
        dto.setDescription(this.description);
        dto.setStatus(this.status);
        dto.setServers(this.servers != null ? this.servers.stream()
                .map(ServerModel::toDTO)
                .collect(Collectors.toSet()) : null);
        return dto;
    }

    // Getters and setters

}
