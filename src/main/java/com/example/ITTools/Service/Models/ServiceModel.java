package com.example.ITTools.Service.Models;

import com.example.ITTools.Server.Models.ServerModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "services")
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int idService;
    @Getter @Setter
    @Column(nullable = false)
    private String serviceName;
    @Getter @Setter
    @Column(nullable = false)
    private String description;
    @Getter @Setter
    @Column(nullable = false)
    private String command;
    @Getter @Setter
    @Column(nullable = false)
    private String pathCommand;
    @Getter @Setter
    @Column(nullable = false)
    private String logFile;
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id", nullable = false)
    private ServerModel server;

    // Getters and setters

}

