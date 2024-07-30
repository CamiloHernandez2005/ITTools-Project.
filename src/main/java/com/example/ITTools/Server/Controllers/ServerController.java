package com.example.ITTools.Server.Controllers;


import com.example.ITTools.Server.DTO.ServerDTO;
import com.example.ITTools.Server.Services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @GetMapping
    public List<ServerDTO> getAllServers() {
        return serverService.getAllServers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServerDTO> getServerById(@PathVariable int id) {
        ServerDTO serverDTO = serverService.getServerById(id);
        return ResponseEntity.ok(serverDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ServerDTO> createServer(@RequestBody ServerDTO serverDTO) {
        ServerDTO createdServer = serverService.createServer(serverDTO);
        return ResponseEntity.ok(createdServer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServerDTO> updateServer(@PathVariable int id, @RequestBody ServerDTO serverDTO) {
        ServerDTO updatedServer = serverService.updateServer(id, serverDTO);
        return ResponseEntity.ok(updatedServer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable int id) {
        serverService.deleteServer(id);
        return ResponseEntity.noContent().build();
    }
}

