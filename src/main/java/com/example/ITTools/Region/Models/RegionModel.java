package com.example.ITTools.Region.Models;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name="Region")

public class RegionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegion;


    @Column(nullable = false)
    private String nameRegion;


    @Column(nullable = false)
    private String description;
    private int status; // 1: activo, 0: inactivo

    // Constructor por defecto
    public RegionModel() {}

    public RegionModel(Long idRegion, String nameRegion, String description) {
        this.idRegion = idRegion;
        this.nameRegion = nameRegion;
        this.description = description;
        this.status= status;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RegionModel(String nameRegion, String description) {
        this.nameRegion = nameRegion;
        this.description = description;
        this.status= status;
    }

    // Getters y Setters
    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
