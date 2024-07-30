package com.example.ITTools.Region.Controllers;
import com.example.ITTools.Region.Models.RegionModel;
import com.example.ITTools.Region.Services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    // Obtener todas las regiones
    @GetMapping
    public List<RegionModel> getAllRegions() {
        return regionService.getAllRegions();
    }

    // Crear una nueva región
    @PostMapping("/register")
    public ResponseEntity<RegionModel> createRegion(@RequestBody RegionModel region) {
        RegionModel nuevaRegion = regionService.createRegion(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRegion);
    }

    // Obtener una región por ID
    @GetMapping("/{id}")
    public ResponseEntity<RegionModel> getRegionById(@PathVariable Long idRegion) {
        return regionService.getRegionById(idRegion)
                .map(region -> ResponseEntity.ok().body(region))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar una región
    @PutMapping("/{idRegion}")
    public ResponseEntity<RegionModel> updateRegion(@PathVariable Long idRegion, @RequestBody RegionModel regionDetails) {
        try {
            RegionModel updatedRegion = regionService.updateRegion(idRegion, regionDetails);
            return ResponseEntity.ok(updatedRegion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una región
    @PutMapping("/status/{idRegion}")
    public ResponseEntity<Void> updateRegionStatus(@PathVariable Long idRegion, @RequestParam int status) {
        regionService.updateRegionStatus(idRegion, status);
        return ResponseEntity.ok().build();
    }
}