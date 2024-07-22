package com.example.ITTools.Region.Services;
import com.example.ITTools.Region.Exeption.RegionYaExisteException;
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

    // Obtener todas las regiones
    public List<RegionModel> getAllRegions() {
        return regionRepository.findAll();
    }

    // Crear una nueva región
    public RegionModel createRegion(RegionModel region) {
        if (regionRepository.findByNameRegion(region.getNameRegion()).isPresent()) {
            throw new RegionYaExisteException("La región ya existe: " + region.getNameRegion());
        }

        return regionRepository.save(region);
    }

    // Obtener una región por ID
    public Optional<RegionModel> getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    // Actualizar una región
    public RegionModel updateRegion(Long id, RegionModel regionDetails) {
        RegionModel region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region not found with id " + id));

        region.setNameRegion(regionDetails.getNameRegion());
        region.setDescription(regionDetails.getDescription());
        return regionRepository.save(region);
    }

    // Eliminar una región
    public void deleteRegion(Long id) {
        RegionModel region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region not found with id " + id));
        regionRepository.delete(region);
    }
}