/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6_2024;

import DAO.DAOUser;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author rahma
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btn_login;
    
    @FXML
    private Button btn_signup;
    
    @FXML
    private AnchorPane pane_login;

    @FXML
    private AnchorPane pane_signup;

    @FXML
    private TextField txt_Ph_email;

    @FXML
    private TextField txt_Ph_email_up;

    @FXML
    private TextField txt_Ph_nom_up;

    @FXML
    private TextField txt_Ph_number_up;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_password_up;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void LoginpaneShow() {
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }

    public void SignuppaneShow() {
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }

    @FXML
    private void Login(ActionEvent event) throws Exception {
        conn = LaConnexion.seConnecter();
        String sql = "select * from users where email = ? and password = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_Ph_email.getText());
            pst.setString(2, DAOUser.hashPassword(txt_password.getText()));
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Email and password is corect");

                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/TableView/TableView.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

            } else {
                JOptionPane.showMessageDialog(null, "inavlid Email and password is incorect");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void add_users(ActionEvent event) throws ClassNotFoundException {
        conn = LaConnexion.seConnecter();
        String sql = "insert into users (Ph_nom,password ,email,numtel) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_Ph_nom_up.getText());
            pst.setString(3, txt_Ph_email_up.getText());
            pst.setString(4, txt_Ph_number_up.getText());
            pst.setString(2, DAOUser.hashPassword(txt_password_up.getText()));
            pst.execute();

            JOptionPane.showMessageDialog(null, "saved");
            btn_signup.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/TableView/TableView.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
