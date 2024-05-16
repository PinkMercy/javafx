package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Medicament;
import tp6_2024.LaConnexion;

/**
 *
 * @author rahma
 */
public class DAOMedicament {

    public int updateMed(Medicament medicament) throws SQLException, ClassNotFoundException {
        Connection conn = LaConnexion.seConnecter();
        PreparedStatement statement = conn.prepareStatement("UPDATE Medicament SET NomMed = ?, PrixMed = ? ,quantite = ?,TypeMed=? WHERE CodeMed = ?");
        statement.setString(1, medicament.getNomMed());
        statement.setDouble(2, medicament.getPrixMed());
        statement.setInt(3, medicament.getquantite());
        statement.setString(4, medicament.getTypeMed());
         statement.setInt(5, medicament.getCodeMed());
        return statement.executeUpdate();
    }

    public int delete(Medicament medicament) throws SQLException, ClassNotFoundException {
        Connection conn = LaConnexion.seConnecter();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM Medicament WHERE CodeMed = ?");
        statement.setInt(1, medicament.getCodeMed());

        return statement.executeUpdate();
    }

    public int insertMed(Medicament medicament) throws SQLException, ClassNotFoundException {
        Connection conn = LaConnexion.seConnecter();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO Medicament (NomMed, PrixMed , quantite , TypeMed) VALUES ( ?,?,?,?)");
        statement.setString(1, medicament.getNomMed());
        statement.setDouble(2, medicament.getPrixMed());
        statement.setInt(3, medicament.getquantite());
        statement.setString(4, medicament.getTypeMed());
        return statement.executeUpdate();
    }

}
