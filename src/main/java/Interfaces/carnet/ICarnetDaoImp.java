package Interfaces.carnet;

import JDBC.SingletonConnection;
import com.example.tp01.Models.Carnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ICarnetDaoImp implements ICarnet {
    static SingletonConnection singletonConnection;

    public ICarnetDaoImp(SingletonConnection singletonConnection) {
        this.singletonConnection = singletonConnection;
    }



    @Override
    public void addCarnet(String nom) throws SQLException {
        Connection connection = singletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO carnet (nom) VALUES (?)");
            ps.setString(1, nom);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCarnet(long id) throws SQLException {
        Connection connection = singletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM carnet WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Carnet getCarnet(String nom) throws SQLException {
        Connection connection = singletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM carnet WHERE nom LIKE ?");
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Carnet carnet = new Carnet();
                carnet.setId(rs.getLong("id"));
                carnet.setNom(rs.getString("nom"));
                return carnet;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Carnet
    getCarnetById(long id) throws SQLException{
        Connection connection = singletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM carnet WHERE id LIKE ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Carnet carnet = new Carnet();
                carnet.setId(rs.getLong("id"));
                carnet.setNom(rs.getString("nom"));
                return carnet;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



    @Override
    public List<Carnet> lister() throws SQLException {
        Connection connection = singletonConnection.getConnection();
        List<Carnet> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM carnet");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Carnet carnet = new Carnet();
                carnet.setId(rs.getLong("id"));
                carnet.setNom(rs.getString("nom"));
                list.add(carnet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;    }
}
