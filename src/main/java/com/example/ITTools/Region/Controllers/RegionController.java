package com.example.ITTools.Region.Controllers;


import com.example.ITTools.Region.DTO.RegionDTO;
import com.example.ITTools.Region.Models.RegionModel;
import com.example.ITTools.Region.Services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public List<RegionDTO> getAllRegions() {
        return regionService.getAllRegions().stream()
                .map(RegionModel::toDTO)
                .toList();
    }

    @PostMapping("/register")
    public ResponseEntity<RegionDTO> createRegion(@RequestBody RegionDTO regionDTO) {
        RegionModel regionModel = new RegionModel();
        regionModel.setNameRegion(regionDTO.getNameRegion());
        regionModel.setDescription(regionDTO.getDescription());
        regionModel.setStatus(regionDTO.getStatus());
        // No es necesario asignar servers aquí ya que no se deberían pasar en el DTO

        RegionModel createdRegion = regionService.createRegion(regionModel);
        return ResponseEntity.ok(createdRegion.toDTO());
    }

    @GetMapping("/active")
    public List<RegionDTO> getActiveRegions() {
        return regionService.getActiveRegions().stream()
                .map(RegionModel::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getRegionById(@PathVariable Long id) {
        return regionService.getRegionById(id)
                .map(RegionModel::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> updateRegion(@PathVariable Long id, @RequestBody RegionDTO regionDTO) {
        RegionModel regionModel = new RegionModel();
        regionModel.setNameRegion(regionDTO.getNameRegion());
        regionModel.setDescription(regionDTO.getDescription());
        regionModel.setStatus(regionDTO.getStatus());
        // No es necesario asignar servers aquí ya que no se deberían pasar en el DTO

        RegionModel updatedRegion = regionService.updateRegion(id, regionModel);
        return ResponseEntity.ok(updatedRegion.toDTO());
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Void> updateRegionStatus(@PathVariable Long id, @RequestParam int status) {
        regionService.updateRegionStatus(id, status);
        return ResponseEntity.noContent().build();
    }

}

