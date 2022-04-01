package com.serveur.serveurState.model;

import com.serveur.serveurState.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.*;


@Entity //mapping O/R permet de faire le lien avec une table de la base de données
@Data //permet d'obtenir les Getters et Setter
@NoArgsConstructor //le constructeur par defaut sans aucun argument
@AllArgsConstructor //le constructeur avec tout les argument
public class Server {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "l'adresse IP doit être unique et non vide") //cela vient de la dependance Validate
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageURL;
    private Status status;
}
