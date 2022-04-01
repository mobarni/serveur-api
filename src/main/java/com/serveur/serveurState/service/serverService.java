package com.serveur.serveurState.service;

import com.serveur.serveurState.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

public interface serverService {
    Server create(Server server);
    List<Server> list(int limit);
    Server update(Server server);
    boolean delete(Long id);
    Server get(Long id);
    Server ping(String ipAddress) throws IOException;
}
