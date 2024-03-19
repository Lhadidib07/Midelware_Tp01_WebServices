package com.example.tp01.Models;

import java.util.List;

public class Carnet {
    private Long id;
    private String nom;
    public List<Adresse> ListAdresse;

    //seters and getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Carnet(String nom, List<Adresse> list) {
        this.nom = nom;
        this.ListAdresse = list;
    }

    public Carnet() {

    }

    public void enregistrer(Adresse address) {

        try {
            this.ListAdresse.add(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void effacer(Adresse adresse) {
        try {
            this.ListAdresse.remove(adresse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Adresse chercher(String nom) {
        Adresse add = null;
        try {
            add = ListAdresse.stream().filter(adresse -> adresse.getNomPersonne().equals(nom)).findFirst().orElse(null);
            return add;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add;
    }

    public String lister() {
        StringBuilder result = null;
        try {
            for (Adresse adresse : ListAdresse) {
                result.append(adresse.getNomPersonne()).append(" habite a : ").append(adresse.getNomRue()).append(" ").append(adresse.getNumRue()).append(" ").append(adresse.getNomVille()).append("\n");
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
