package com.example.ITTools.Region.Repositories;

import com.example.ITTools.Region.Models.RegionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RegionRepository extends JpaRepository<RegionModel, Long> {
    Optional<RegionModel> findByNameRegion(String nameRegion);
}
