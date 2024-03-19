package com.example.tp01;

import Interfaces.Adresses.IAdressesDaoImp;
import Interfaces.carnet.ICarnetDaoImp;
import JDBC.SingletonConnection;
import com.example.tp01.Models.Carnet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "carnetServlet", value = "/carnet/*")
public class carnetServlet extends HttpServlet {
    ICarnetDaoImp metierCarnet;
    IAdressesDaoImp metierAdresse;

    SingletonConnection SingletonConnection;

    List<Carnet> list = new ArrayList<>();

    public void init() {
        metierAdresse = new IAdressesDaoImp(SingletonConnection);
        metierCarnet = new ICarnetDaoImp(SingletonConnection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/") ){
            // get all carnet
            try {
                list = metierCarnet.lister();
                req.setAttribute("carnets", list);
                req.getRequestDispatcher("/carnet.jsp").forward(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            // get one carnet
            String nom = pathInfo.substring(1);
            Carnet carnet = null;
            try {
                carnet = metierCarnet.getCarnet(nom);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("carnet", carnet);
            req.getRequestDispatcher("/carnet.jsp").forward(req, resp);
    }
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        System.out.println("je suis dans le doPost pathInfo : " + pathInfo);
        if (pathInfo != null && pathInfo.equals("/delete")) {
            String idParam = req.getParameter("id"); // Obtenez l'ID à partir d'un paramètre de requête
            if (idParam != null) {
                long id = Long.parseLong(idParam);
                try {
                    metierCarnet.deleteCarnet(id);
                    metierAdresse.deleteAdresseByIdCarnet(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/carnet");
            }
        } else if (pathInfo != null && pathInfo.equals("/add")) {
            try {
                String nom = req.getParameter("nom");
                metierCarnet.addCarnet(nom);
                resp.sendRedirect("/carnet");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
