package Interfaces.Adresses;

import Interfaces.Adresses.IAdressesDao;
import JDBC.SingletonConnection;
import com.example.tp01.Models.Adresse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IAdressesDaoImp implements IAdressesDao {
    // TODO: INJECTION DE LA DEPENDACE   singletonConnection
    static SingletonConnection singletonConnection;

    public IAdressesDaoImp(SingletonConnection singletonConnection) {
        this.singletonConnection = singletonConnection;
    }
    @Override
    public void addAdresse(String nom, String nomRue, int numRue, String ville, long IdCarnet) throws SQLException {
        System.out.println("addAdresse" + nom + nomRue + numRue + ville +" dans le carnet id : " + IdCarnet);
        Connection connection = singletonConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO adresse(nomPersonne, nomRue, numRue, ville,IdCarnet) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, nomRue);
            preparedStatement.setInt(3, numRue);
            preparedStatement.setString(4, ville);
            preparedStatement.setLong(5, IdCarnet);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAdresse(long id) {
        // TODO Auto-generated method stub
        Connection connection = singletonConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM adresse WHERE id = ?");
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Adresse> lister() {
        // TODO Auto-generated method stub
        Connection connection = singletonConnection.getConnection();
        List<Adresse> list = new ArrayList<>(); // liste des adresses
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adresse");
            ResultSet rs = preparedStatement.executeQuery();
            // redner le resultat sous forme d'objet  les ajouter a la liste
            while (rs.next()) {
                Adresse adresse = new Adresse();
                adresse.setId(rs.getLong("id"));
                adresse.setNomPersonne(rs.getString("nomPersonne"));
                adresse.setNomRue(rs.getString("nomRue"));
                adresse.setNumRue(rs.getInt("numRue"));
                adresse.setNomVille(rs.getString("ville"));
                list.add(adresse);
            }
            if (list != null)
                return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Adresse getPersonne(String nom) {
        // TODO Auto-generated method stub
        Connection connection = singletonConnection.getConnection();
        try {
            String searchName = "%" + nom + "%";
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adresse WHERE nomPersonne LIKE ?");
            preparedStatement.setString(1, searchName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("oui il ya une reponse");
                Adresse adresse = new Adresse();
                adresse.setId(rs.getLong("id"));
                adresse.setNomPersonne(rs.getString("nomPersonne"));
                adresse.setNomRue(rs.getString("nomRue"));
                adresse.setNumRue(rs.getInt("numRue"));
                adresse.setNomVille(rs.getString("ville"));
                return adresse;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("non il ya pas de reponse et je vais retourner null ");
        return null;
    }

    public Adresse getPersonneyIdCarnet(String nom, long id) throws SQLException {
        // TODO Auto-generated method stub
        Connection connection = singletonConnection.getConnection();
        try {
            String searchName = "%" + nom + "%";
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adresse WHERE nomPersonne LIKE ? and IdCarnet = ?");
            preparedStatement.setString(1, searchName);
            preparedStatement.setString(2, String.valueOf(id));
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("oui il ya une reponse");
                Adresse adresse = new Adresse();
                adresse.setId(rs.getLong("id"));
                adresse.setNomPersonne(rs.getString("nomPersonne"));
                adresse.setNomRue(rs.getString("nomRue"));
                adresse.setNumRue(rs.getInt("numRue"));
                adresse.setNomVille(rs.getString("ville"));
                return adresse;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("non il ya pas de reponse et je vais retourner null ");
        return null;
    }

    public void deleteAdresseByIdCarnet(long id) throws SQLException {
        // TODO Auto-generated method stub
        Connection connection = singletonConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM adresse WHERE IdCarnet = ?");
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Adresse> listerByCarnet(long idCarnet) {
        Connection connection = singletonConnection.getConnection();
        List<Adresse> list = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adresse where IdCarnet = ? ");
            preparedStatement.setString(1,String.valueOf(idCarnet));
           ResultSet rs = preparedStatement.executeQuery();
           while(rs.next()) {
                Adresse adresse = new Adresse();
                adresse.setId(rs.getLong("id"));
                adresse.setNomRue(rs.getString("nomRue"));
                adresse.setNumRue(rs.getInt("numRue"));
                adresse.setNomVille(rs.getString("ville"));
                adresse.setNomPersonne(rs.getString("nomPersonne"));
                list.add(adresse);
           }
            if (list != null)
                return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
