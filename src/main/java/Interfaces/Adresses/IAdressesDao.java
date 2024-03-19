package Interfaces.Adresses;

import com.example.tp01.Models.Adresse;

import java.sql.SQLException;
import java.util.List;

public interface IAdressesDao {

    void addAdresse(String nom, String nomRue, int numRue, String ville, long IdCarnet) throws SQLException;

    void deleteAdresse(long id) throws SQLException;

    Adresse getPersonne(String nom)  throws SQLException;

    Adresse getPersonneyIdCarnet(String nom,long id)   throws SQLException;

    List<Adresse> lister()  throws SQLException;

    void deleteAdresseByIdCarnet(long id) throws SQLException; // delete all adresses of a carnet

    List<Adresse> listerByCarnet(long idCarnet)  throws SQLException;



}
