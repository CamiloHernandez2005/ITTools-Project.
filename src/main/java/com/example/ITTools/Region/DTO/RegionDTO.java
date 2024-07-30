package com.example.ITTools.Region.DTO;


import com.example.ITTools.Server.DTO.ServerDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegionDTO {
    private Long idRegion;
    private String nameRegion;
    private String description;
    private int status;
    private Set<ServerDTO> servers;  // Usa el DTO de ServerModel aquí
}
