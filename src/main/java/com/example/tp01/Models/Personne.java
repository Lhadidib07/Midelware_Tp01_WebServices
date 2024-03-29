package com.example.tp01.Models;

import java.io.Serializable;

public class Personne implements Serializable {
    public String nom;
    public  String prenom;
    public int age;
    public String phone;

    public Personne(String nom,String prenom , int age){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }


}
