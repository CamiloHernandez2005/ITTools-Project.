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
    public ResponseEntity<RegionModel> crearRegion(@RequestBody RegionModel region) {
        RegionModel nuevaRegion = regionService.createRegion(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRegion);
    }

    // Obtener una región por ID
    @GetMapping("/{id}")
    public ResponseEntity<RegionModel> getRegionById(@PathVariable Long id) {
        return regionService.getRegionById(id)
                .map(region -> ResponseEntity.ok().body(region))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar una región
    @PutMapping("/{id}")
    public ResponseEntity<RegionModel> updateRegion(@PathVariable Long id, @RequestBody RegionModel regionDetails) {
        try {
            RegionModel updatedRegion = regionService.updateRegion(id, regionDetails);
            return ResponseEntity.ok(updatedRegion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una región
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        try {
            regionService.deleteRegion(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}