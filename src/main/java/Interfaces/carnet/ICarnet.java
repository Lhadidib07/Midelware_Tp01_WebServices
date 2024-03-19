package Interfaces.carnet;

import com.example.tp01.Models.Carnet;

import java.sql.SQLException;
import java.util.List;

public interface ICarnet {
    void addCarnet(String nom) throws SQLException;

    void deleteCarnet(long id) throws SQLException;

     Carnet getCarnet(String nom) throws SQLException;

     Carnet getCarnetById(long id) throws SQLException;

    List<Carnet> lister() throws SQLException;
}
