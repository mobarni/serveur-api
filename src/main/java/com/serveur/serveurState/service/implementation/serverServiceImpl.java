package com.serveur.serveurState.service.implementation;

import com.serveur.serveurState.enumeration.Status;
import com.serveur.serveurState.model.Server;
import com.serveur.serveurState.repository.serverRepository;
import com.serveur.serveurState.service.serverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.serveur.serveurState.enumeration.Status.SERVER_DOWN;
import static com.serveur.serveurState.enumeration.Status.SERVER_UP;

@RequiredArgsConstructor //permet d'ajouter le contructeur les les arguments presents
@Service
@Slf4j //permet d'afficher des elements dans la console
public class serverServiceImpl implements serverService {

    private final serverRepository serverRepo;

    @Override
    public Server create(Server server) {
        log.info("enregistrement du serveur: {}",server.getName());
        server.setImageURL(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public List<Server> list(int limit) {
        log.info("liste des serveurs existants");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server update(Server server) {
        log.info("modification du serveur: {}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public boolean delete(Long id) {
        log.info("suppression du serveur: {}",id);
        serverRepo.deleteById(id);
        return true;
    }

    @Override
    public Server get(Long id) {
        log.info("recherche du serveur: {}",id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Ping du serveur {}", ipAddress);
        Server server=serverRepo.findByIpAddress(ipAddress);
        InetAddress address= InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP: SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png","server2.png","server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+ imageNames[new Random().nextInt(3)]).toUriString();
    }

}
