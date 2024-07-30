package com.example.ITTools.Server.Models;

import com.example.ITTools.Region.Models.RegionModel;
import com.example.ITTools.Server.DTO.ServerDTO;
import com.example.ITTools.Service.Models.ServiceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "servers")
public class ServerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int idServer;

    @Column(nullable = false)
    @Getter @Setter
    private String serverName;

    @Column(nullable = false)
    @Getter @Setter
    private String description;

    @Column(nullable = false)
    @Getter @Setter
    private String ipServer;

    @Column(nullable = false)
    @Getter @Setter
    private String portServer;

    @Column(nullable = false)
    @Getter @Setter
    private String instance;

    @Column(nullable = false)
    @Getter @Setter
    private String serverDB;

    @Column(nullable = false)
    @Getter @Setter
    private String userLogin;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

    @Column(nullable = false)
    @Getter @Setter
    private String repeatPassword;

    @Column(nullable = false)
    @Getter @Setter
    private String dbFR;

    @Getter @Setter
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    @Getter @Setter
    private RegionModel region;

    @JsonIgnore
    @OneToMany(mappedBy = "server", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ServiceModel> services;

    // Convertir a DTO
    public ServerDTO toDTO() {
        ServerDTO dto = new ServerDTO();
        dto.setIdServer(this.idServer);
        dto.setServerName(this.serverName);
        dto.setDescription(this.description);
        dto.setIpServer(this.ipServer);
        dto.setPortServer(this.portServer);
        dto.setInstance(this.instance);
        dto.setServerDB(this.serverDB);
        dto.setUserLogin(this.userLogin);
        dto.setPassword(this.password);
        dto.setRepeatPassword(this.repeatPassword);
        dto.setDbFR(this.dbFR);
        dto.setStatus(this.status);
        dto.setRegionId(this.region != null ? this.region.getIdRegion() : null);
        return dto;
    }
}

