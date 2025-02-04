package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Statut")
public class Statut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatut;

    private String nomStatut;

    @OneToMany(mappedBy = "statut", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prospect> prospects;


    //Constructeur vide pour Hibernate
    public Statut() {}

    //Constructeur avec paramètres pour créer des objets facilement
    public Statut(String nomStatut) {
        this.nomStatut = nomStatut;
    }

    // Getters et Setters

    public Integer getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Integer idStatut) {
        this.idStatut = idStatut;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }

    public List<Prospect> getProspects() {
        return prospects;
    }

    public void setProspects(List<Prospect> prospects) {
        this.prospects = prospects;
    }
}
