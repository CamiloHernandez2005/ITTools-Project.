package com.example.ITTools.Region.Models;


import jakarta.persistence.*;

@Entity
@Table(name="Region")
public class RegionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegion;

    private String nameRegion;
    private String description;

    // Constructor por defecto
    public RegionModel() {}

    public RegionModel(Long idRegion, String nameRegion, String description) {
        this.idRegion = idRegion;
        this.nameRegion = nameRegion;
        this.description = description;
    }

    public RegionModel(String nameRegion, String description) {
        this.nameRegion = nameRegion;
        this.description = description;
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
