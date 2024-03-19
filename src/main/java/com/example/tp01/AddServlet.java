package com.example.tp01;


import java.io.*;
import java.lang.*;
import java.util.List;

import Interfaces.Adresses.IAdressesDaoImp;
import JDBC.SingletonConnection;
import com.example.tp01.Models.Adresse;
import com.example.tp01.Models.Carnet;
import com.example.tp01.Models.Personne;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "addServlet", value = "/add-servlet")
public class AddServlet extends HttpServlet {
    Adresse adrs;
    Personne prsn;
    public List<Adresse> ListAdresse;
    Carnet carnet;

    IAdressesDaoImp metier;

    SingletonConnection SingletonConnection;

    public void init() {
        carnet = new Carnet("carnet test", ListAdresse);
        metier = new IAdressesDaoImp(SingletonConnection);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException {

        response.setContentType("text/html");

        String nom = request.getParameter("nom");

        String nomRue = request.getParameter("nomRue");
        String numRue = request.getParameter("numRue");
        String ville = request.getParameter("ville");

        adrs = new Adresse(nom,nomRue, Integer.parseInt(numRue), ville);
        carnet.enregistrer(adrs);

        // iCarnetDaoImp.addAdresse(nom,nomRue, Integer.parseInt(numRue), ville, carnet.nom);


    }
}
