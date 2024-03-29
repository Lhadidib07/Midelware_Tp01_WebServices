package Servlets;

import Interfaces.Adresses.IAdressesDaoImp;
import Interfaces.carnet.ICarnetDaoImp;
import JDBC.SingletonConnection;
import com.example.tp01.Models.Adresse;
import com.example.tp01.Models.Carnet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "modificationServlet", value = "/modification/*")
public class ModificationServlet extends HttpServlet {
    IAdressesDaoImp metierAdresse;
    ICarnetDaoImp metierCarnet;
    List<Carnet> listeCanets = new ArrayList<>();

    SingletonConnection SingletonConnection;

    public void init() {
        metierAdresse = new IAdressesDaoImp(SingletonConnection);
        metierCarnet = new ICarnetDaoImp(SingletonConnection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
//        System.out.println("je suis dans le servlet modifieer "+id);
        Adresse adr = null;
        Carnet carnet = null;
        try {
            adr =  metierAdresse.getPersonneById(id);
            carnet = metierCarnet.getCarnetById(adr.getIdCarnet());
            listeCanets = metierCarnet.lister();
            req.setAttribute("carnet", carnet);
            req.setAttribute("listeCarnets", listeCanets);
            req.setAttribute("personne", adr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/modifier.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession();

        System.out.println("je suis dans le servlet modifieer POST");
        Long idPersonne = Long.valueOf(req.getParameter("idPersonne"));
        String nom = req.getParameter("nom");
        String nomRue = req.getParameter("nomRue");
        String numRue = req.getParameter("numRue");
        String ville = req.getParameter("ville");
        Long idCarnet = Long.parseLong(req.getParameter("carnetId"));

        System.out.println("je suis dans le servlet modifieer id "+idPersonne+" nom "+nom+" nomRue "+nomRue+" numRue "+numRue+" ville "+ville+" idCarnet "+idCarnet);

        if(nom != null && nomRue != null && numRue != null && ville != null && idPersonne != null) {
            try {
               if(metierAdresse.ModifierAdresse(nom, nomRue, Integer.parseInt(numRue), ville, idCarnet, idPersonne)){
                   System.out.println("modification reussie");
                   session.setAttribute("message", "Modification effectuer avec succes");
               }else{
                     System.out.println("modification echouée");
                     //envoyer un message d'erreur
                     session.setAttribute("message", "Erreur lors de la modification");
               }
                res.sendRedirect(req.getContextPath() + "/modification/*?id="+idPersonne);
            } catch (SQLException  | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
