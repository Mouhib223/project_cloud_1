package com.crud.upteckapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Stagiaires")
@Data
// ceci est constructeur avec tous les parametres
@AllArgsConstructor
//ceci est constructeur vide
@NoArgsConstructor
public class Stagiaire implements Serializable {
    @Id
    @GeneratedValue
    private Long id ;
    @Column(name = "Prenom")
    private String Prenom ;
    @Column(name = "Nom")
    private String Nom ;
    @Column(name = "Ecole")
    private String Ecole ;
    @Column(name = "Filiere")
    private String Filiere ;
    @Column(name = "Niveau")
    private String Niveau ;
    @Column(name = "Telephone")
    private String Telephone ;
    @Column(name = "Mail")
    private String Mail ;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stagiaire)) return false;
        Stagiaire stagiaire = (Stagiaire) o;
        return getId().equals(stagiaire.getId()) && getPrenom().equals(stagiaire.getPrenom()) && getNom().equals(stagiaire.getNom()) && getEcole().equals(stagiaire.getEcole()) && Objects.equals(getFiliere(), stagiaire.getFiliere()) && Objects.equals(getNiveau(), stagiaire.getNiveau()) && Objects.equals(getTelephone(), stagiaire.getTelephone()) && getMail().equals(stagiaire.getMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrenom(), getNom(), getEcole(), getFiliere(), getNiveau(), getTelephone(), getMail());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getEcole() {
        return Ecole;
    }

    public void setEcole(String ecole) {
        Ecole = ecole;
    }

    public String getFiliere() {
        return Filiere;
    }

    public void setFiliere(String filiere) {
        Filiere = filiere;
    }

    public String getNiveau() {
        return Niveau;
    }

    public void setNiveau(String niveau) {
        Niveau = niveau;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }
}

