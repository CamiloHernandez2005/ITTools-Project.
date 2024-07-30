package com.example.ITTools.Region.Services;


import com.example.ITTools.Region.Models.RegionModel;
import com.example.ITTools.Region.Repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<RegionModel> getAllRegions() {
        return regionRepository.findAll();
    }

    public RegionModel createRegion(RegionModel region) {
        if (region.getNameRegion() == null || region.getDescription() == null) {
            throw new IllegalArgumentException("El nombre y la descripción de la región no pueden ser nulos.");
        }

        if (regionRepository.findByNameRegion(region.getNameRegion()).isPresent()) {
            throw new RuntimeException("La región ya existe: " + region.getNameRegion());
        }

        region.setStatus(1); // Al crear, la región está activa
        return regionRepository.save(region);
    }

    public List<RegionModel> getActiveRegions() {
        return regionRepository.findByStatus(1); // Asumiendo que tienes un método en el repositorio para esto
    }

    public Optional<RegionModel> getRegionById(Long idRegion) {
        return regionRepository.findById(idRegion);
    }

    public RegionModel updateRegion(Long idRegion, RegionModel regionDetails) {
        RegionModel region = regionRepository.findById(idRegion)
                .orElseThrow(() -> new RuntimeException("Region not found with id " + idRegion));

        if (regionDetails.getNameRegion() != null) {
            region.setNameRegion(regionDetails.getNameRegion());
        }
        if (regionDetails.getDescription() != null) {
            region.setDescription(regionDetails.getDescription());
        }

        return regionRepository.save(region);
    }

    public void updateRegionStatus(Long idRegion, int status) {
        Optional<RegionModel> region = regionRepository.findById(idRegion);
        if (region.isPresent()) {
            RegionModel updatedRegion = region.get();
            updatedRegion.setStatus(status);
            regionRepository.save(updatedRegion);
        }
    }
}
