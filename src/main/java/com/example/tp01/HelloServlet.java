package com.example.tp01;

import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.util.*;

import Interfaces.Adresses.IAdressesDaoImp;
import Interfaces.carnet.ICarnetDaoImp;
import JDBC.SingletonConnection;
import com.example.tp01.Models.Adresse;
import com.example.tp01.Models.Carnet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/liste/*")
public class HelloServlet extends HttpServlet {
    private String message;

    IAdressesDaoImp metierAdresse;
    ICarnetDaoImp metierCarnet;
    SingletonConnection SingletonConnection;
    List<Adresse> list = new ArrayList<>();
    List<Carnet> listeCanets = new ArrayList<>();


    // Carnet carnet;

    public void init() {
        // carnet = new Carnet("carnet01", list);
        metierAdresse = new IAdressesDaoImp(SingletonConnection);
        metierCarnet = new ICarnetDaoImp(SingletonConnection);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        //System.out.println("GET voici le path info helloServelet: " + pathInfo);
        String carnetId = request.getParameter("idCarnet");
        String nom = request.getParameter("likeNom");

        if(carnetId != null){
           // System.out.println("\t carnetId : " + carnetId);
            list = metierAdresse.listerByCarnet(Long.parseLong(carnetId));
            try {
                Carnet carnet = metierCarnet.getCarnetById(Long.parseLong(carnetId));
                request.setAttribute("carnet", carnet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("listeAdresses", list);
            //request.getRequestDispatcher("/liste.jsp").forward(request, response);
        }else {
            //System.out.println("\t carnetId : " + carnetId);
            if (pathInfo == null || pathInfo.equals("/")) {
                list = metierAdresse.lister();
                try {
                    listeCanets = metierCarnet.lister();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (!listeCanets.isEmpty()) {
                   // System.out.println("dans hello servelet listeCarnets n'est pas vide");
                    request.setAttribute("listeCarnets", listeCanets);
                } else {
                    request.setAttribute("listeCarnets", null);
                    //System.out.println("dans hello servelet listeCarnets est vide");
                }
                request.setAttribute("listeAdresses", list);
               // request.getRequestDispatcher("/liste.jsp").forward(request, response);
            }
        }
        System.out.println("GET voici le nom : " + nom);
        if(nom != null){
            Adresse adr = null;
            if(carnetId != null){
                try {
                    adr =  metierAdresse.getPersonneyIdCarnet(nom,Long.parseLong(carnetId));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else {
                adr = metierAdresse.getPersonne(nom);
            }
            if(adr != null){
                //System.out.println("ligne 77 hello servelt Do get  person ! nom ->"+ adr.getNomPersonne());
                request.setAttribute("personne", adr);
            }else{
                request.setAttribute("errorMessage", "No person found with the provided name.");
            }
        }
       request.getRequestDispatcher("/liste.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methode = req.getParameter("_method");
        String carnetId = req.getParameter("idCarnet");

        System.out.println("\n \t\t POST methode HelloServlet : " + methode);
        if (methode.equals("DELETE")) {
            //long id = Long.parseLong(pathInfo.substring(1));
            long id = Long.parseLong(req.getParameter("personneId"));
            metierAdresse.deleteAdresse(id);
            if(carnetId != null){
                resp.sendRedirect(req.getContextPath() + "/liste?idCarnet=" + carnetId);
            } else {
                resp.sendRedirect(req.getContextPath() + "/liste");
            }
        }
        else {
            String nom = req.getParameter("nom");
            String nomRue = req.getParameter("nomRue");
            String numRue = req.getParameter("numRue");
            String ville = req.getParameter("ville");
            Long id = Long.parseLong(req.getParameter("carnetId"));
            if(nom != null && nomRue != null && numRue != null || ville != null && id != null) {

                try {
                    metierAdresse.addAdresse(nom, nomRue, Integer.parseInt(numRue), ville, id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (carnetId != null) {
                    resp.sendRedirect(req.getContextPath() + "/liste?idCarnet=" + carnetId);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/liste");
                }
            }else{
                System.out.println("impossoible d'ajouter une personne avec des champs vides");
            }
        }


    }


    public void destroy() {

    }
}