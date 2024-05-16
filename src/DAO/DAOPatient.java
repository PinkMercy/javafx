/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Patient;
import tp6_2024.LaConnexion;

/**
 *
 * @author rahma
 */
public class DAOPatient {

    
     public int update(Patient patient) throws SQLException, ClassNotFoundException {
        Connection conn = LaConnexion.seConnecter();
        PreparedStatement statement = conn.prepareStatement("UPDATE Patient SET NomPatient = ?, TelPatient = ? WHERE CodePatient = ?");
        statement.setString(1, patient.getNomPatient());
        statement.setInt(2, patient.getTelPatient());
        statement.setInt(3, patient.getCodePatient());


        return statement.executeUpdate();
    }
     
        public int delete(Patient patient) throws SQLException, ClassNotFoundException {
        Connection conn = LaConnexion.seConnecter();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM Patient WHERE CodePatient = ?");
        statement.setInt(1, patient.getCodePatient());

        return statement.executeUpdate();
    }
        
        public int insert(Patient patient) throws SQLException, ClassNotFoundException {
        Connection conn = LaConnexion.seConnecter();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO Patient (NomPatient, TelPatient) VALUES ( ?, ?)");
        statement.setString(1, patient.getNomPatient());
        statement.setInt(2, patient.getTelPatient());

        return statement.executeUpdate();
    }
    
     
    
}
