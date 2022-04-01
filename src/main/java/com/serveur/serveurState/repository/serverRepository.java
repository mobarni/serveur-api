package com.serveur.serveurState.repository;

import com.serveur.serveurState.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface serverRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
