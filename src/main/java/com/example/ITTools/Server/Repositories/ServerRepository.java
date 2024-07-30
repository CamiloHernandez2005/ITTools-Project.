package com.example.ITTools.Server.Repositories;

import com.example.ITTools.Server.Models.ServerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ServerRepository extends JpaRepository<ServerModel, Integer> {
}
