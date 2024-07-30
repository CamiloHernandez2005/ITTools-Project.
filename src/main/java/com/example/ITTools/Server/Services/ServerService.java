package com.example.ITTools.Server.Services;


import com.example.ITTools.Region.Models.RegionModel;
import com.example.ITTools.Region.Services.RegionService;
import com.example.ITTools.Server.DTO.ServerDTO;
import com.example.ITTools.Server.Models.ServerModel;
import com.example.ITTools.Server.Repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private RegionService regionService;

    public List<ServerDTO> getAllServers() {
        List<ServerModel> servers = serverRepository.findAll();
        return servers.stream().map(ServerModel::toDTO).collect(Collectors.toList());
    }

    public ServerDTO getServerById(int id) {
        ServerModel server = serverRepository.findById(id).orElseThrow(() -> new RuntimeException("Server not found"));
        return server.toDTO();
    }

    public ServerDTO createServer(ServerDTO serverDTO) {
        // Obtener la región como RegionModel
        if (serverDTO.getRegionId() == null) {
            throw new IllegalArgumentException("El ID de la región no debe ser nulo.");
        }


        Optional<RegionModel> optionalRegion = regionService.getRegionById(serverDTO.getRegionId());
        if (optionalRegion.isEmpty()) {
            throw new RuntimeException("Region not found with id " + serverDTO.getRegionId());
        }

        RegionModel region = optionalRegion.get();
        if (region.getStatus() != 1) {
            throw new RuntimeException("Region is not active.");
        }
        if (serverDTO.getIpServer() == null || serverDTO.getIpServer().isEmpty()) {
            throw new IllegalArgumentException("La IP del servidor no puede estar vacía");
        }

        // Convertir DTO a entidad
        ServerModel serverModel = new ServerModel();
        serverModel.setServerName(serverDTO.getServerName());
        serverModel.setDescription(serverDTO.getDescription());
        serverModel.setIpServer(serverDTO.getIpServer());
        serverModel.setPortServer(serverDTO.getPortServer());
        serverModel.setInstance(serverDTO.getInstance());
        serverModel.setServerDB(serverDTO.getServerDB());
        serverModel.setUserLogin(serverDTO.getUserLogin());
        serverModel.setPassword(serverDTO.getPassword());
        serverModel.setRepeatPassword(serverDTO.getRepeatPassword());
        serverModel.setDbFR(serverDTO.getDbFR());
        serverModel.setStatus(1); // Activar por defecto
        serverModel.setRegion(region); // Asignar la entidad RegionModel

        // Guardar el servidor
        ServerModel savedServer = serverRepository.save(serverModel);
        return savedServer.toDTO();
    }

    public ServerDTO updateServer(int id, ServerDTO serverDTO) {
        ServerModel server = serverRepository.findById(id).orElseThrow(() -> new RuntimeException("Server not found"));

        // Actualizar campos
        server.setServerName(serverDTO.getServerName());
        server.setIpServer(serverDTO.getIpServer());
        server.setInstance(serverDTO.getInstance());
        server.setPassword(serverDTO.getPassword());
        server.setRepeatPassword(serverDTO.getRepeatPassword());
        server.setPortServer(serverDTO.getPortServer());
        server.setServerDB(serverDTO.getServerDB());
        server.setDbFR(serverDTO.getDbFR());

        if (serverDTO.getRegionId() != null) {
            RegionModel region = regionService.getRegionById(serverDTO.getRegionId())
                    .orElseThrow(() -> new RuntimeException("Region not found"));
            server.setRegion(region); // Asignar la entidad RegionModel
        }

        // Guardar el servidor actualizado
        ServerModel updatedServer = serverRepository.save(server);
        return updatedServer.toDTO();
    }

    public void deleteServer(int id) {
        ServerModel server = serverRepository.findById(id).orElseThrow(() -> new RuntimeException("Server not found"));
        server.setStatus(0); // Marcar como inactivo
        serverRepository.save(server);
    }
}



