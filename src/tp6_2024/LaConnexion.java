package tp6_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class LaConnexion {

    private static Connection con;

    public static Connection seConnecter() throws ClassNotFoundException{
        if(con==null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacie", "root", "");
                System.out.println("nice");
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return con;
    }

    public static void setCon(Connection con) {
        LaConnexion.con = con;
    }

}