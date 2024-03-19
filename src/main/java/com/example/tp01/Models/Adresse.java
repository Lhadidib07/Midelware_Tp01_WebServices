package com.example.tp01.Models;

public class Adresse {

    long id ;
    private String nomPersonne;

    private String nomRue;
    private int numRue;

    private String nomVille;

    public Adresse(String nomPersonne,String nomRue,int numRue ,String nomVille){
        this.nomPersonne = nomPersonne;
        this.nomRue = nomRue;
        this.numRue= numRue;
        this.nomVille = nomVille;
    }

    public Adresse(long id, String nomPersonne,String nomRue,int numRue ,String nomVille){
        this.id = id;
        this.nomPersonne = nomPersonne;
        this.nomRue = nomRue;
        this.numRue= numRue;
        this.nomVille = nomVille;
    }

    public Adresse() {

    }


    // geters and setters
    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public int getNumRue() {
        return numRue;
    }

    public void setNumRue(int numRue) {
        this.numRue = numRue;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", nomPersonne='" + nomPersonne + '\'' +
                ", nomRue='" + nomRue + '\'' +
                ", numRue=" + numRue +
                ", nomVille='" + nomVille + '\'' +
                '}';
    }


}
