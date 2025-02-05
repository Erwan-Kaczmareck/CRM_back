package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Prospect")
public class Prospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProspect;

    private String type;
    private String origineLead;
    private String mois;
    private Short annee;
    private String codePostale;
    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private String niveauActuel;
    private String diplomePrepare;
    private String specialite;
    private String ville;
    private Short anneeRecrutement;
    private String entreProchaineAnnee;

    private String commentaire;
    private String etablissement;

    @ManyToOne
    @JoinColumn(name = "Statut_id_Statut") // Clé étrangère vers Statut
    private Statut statut;

    // Getters et Setters

    public Integer getIdProspect() {
        return idProspect;
    }

    public void setIdProspect(Integer idProspect) {
        this.idProspect = idProspect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }
    public String getOrigineLead() {
        return origineLead;
    }

    public void setOrigineLead(String origineLead) {
        this.origineLead = origineLead;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNiveauActuel() {
        return niveauActuel;
    }

    public void setNiveauActuel(String niveauActuel) {
        this.niveauActuel = niveauActuel;
    }

    public String getDiplomePrepare() {
        return diplomePrepare;
    }

    public void setDiplomePrepare(String diplomePrepare) {
        this.diplomePrepare = diplomePrepare;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Short getAnneeRecrutement() {
        return anneeRecrutement;
    }

    public void setAnneeRecrutement(Short anneeRecrutement) {
        this.anneeRecrutement = anneeRecrutement;
    }

    public String getEntreProchaineAnnee() {
        return entreProchaineAnnee;
    }

    public void setEntreProchaineAnnee(String entreProchaineAnnee) {
        this.entreProchaineAnnee = entreProchaineAnnee;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
