package TableView;

import DAO.DAOMedicament;
import DAO.DAOPatient;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.Insets;
//import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tp6_2024.LaConnexion;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Medicament;

/**
 * FXML Controller class
 *
 * @author rahma
 */
public class TableViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Patient> PatientTable;

    @FXML
    private TableColumn<Patient, Integer> idCol;

    @FXML
    private TableColumn<Patient, String> nomCol;

    @FXML
    private TableColumn<Patient, Integer> numtelCol;

    @FXML
    private AnchorPane pane_Patient;

    @FXML
    private TextField NomFld;

    @FXML
    private TextField NumTelFld;

    private Patient SelectedPatient;

    private final DAOPatient daop = new DAOPatient();

    ///Medicament
    @FXML
    private TableView<Medicament> MedicamentTable;

    @FXML
    private AnchorPane pane_Medicament;

    @FXML
    private TextField NomMedFld;

    @FXML
    private TableColumn<Medicament, Integer> idColMed;

    @FXML
    private TableColumn<Medicament, Double> PrixColMed;

    @FXML
    private TextField PrixFld;

    @FXML
    private TableColumn<Medicament, Integer> QteColMed;

    @FXML
    private TextField QteFld;

    @FXML
    private TableColumn<Medicament, String> TypeColMed;

    @FXML
    private TextField TypeFld;
    @FXML
    private TableColumn<Medicament, String> nomColMed;

    private final DAOMedicament daom = new DAOMedicament();

    private Medicament SelectedMedicament;

    @FXML
    void cleanMed(MouseEvent event) {
        NomMedFld.setText(null);
        PrixFld.setText(null);
        QteFld.setText(null);
        TypeFld.setText(null);
    }

    @FXML
    void deleteMed(MouseEvent event) {
        try {
            System.out.println(this.SelectedMedicament.getNomMed());
            if (this.SelectedMedicament != null) {
                System.out.println("hiiiiiiiiiiiiiiiiiiiii");
                this.daom.delete(this.SelectedMedicament);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void refreshTableMed() {
        try {
            query = "SELECT * FROM medicament";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            MedicamentList.clear();  // Clear the list once before loading new items

            while (resultSet.next()) {
                MedicamentList.add(new Medicament(
                        resultSet.getInt("CodeMed"),
                        resultSet.getString("NomMed"),
                        resultSet.getDouble("PrixMed"),
                        resultSet.getInt("quantite"),
                        resultSet.getString("TypeMed")
                ));
            }
            MedicamentTable.setItems(MedicamentList);  // Set items once after loading all
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    ObservableList<Medicament> MedicamentList = FXCollections.observableArrayList();

    @FXML
    void saveMed(MouseEvent event) throws ClassNotFoundException, SQLException {
        System.out.println("test1");
        conn = LaConnexion.seConnecter();
        String NomMed = NomMedFld.getText();
        Double PrixMed = Double.parseDouble(PrixFld.getText());
        int quantite = Integer.parseInt(QteFld.getText());
        System.out.println("test77");
        String TypeMed = TypeFld.getText();
        System.out.println("test3");
        this.daom.insertMed(new Medicament(NomMed, PrixMed, quantite, TypeMed));
        System.out.println("test2");
    }

    private void insertMed() {
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, NomMedFld.getText());
            preparedStatement.setInt(2, Integer.parseInt(PrixFld.getText()));
            preparedStatement.setInt(3, Integer.parseInt(QteFld.getText()));
            preparedStatement.setString(4, TypeFld.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void updateMed(MouseEvent event) {
        try {
            System.out.println(this.SelectedMedicament.getNomMed());
            if (this.SelectedMedicament != null) {
                System.out.println("3awww");
                this.SelectedMedicament.setNomMed(NomMedFld.getText());
                this.SelectedMedicament.setPrixMed(Double.parseDouble(PrixFld.getText()));
                this.SelectedMedicament.setquantite(Integer.parseInt(QteFld.getText()));
                this.SelectedMedicament.setTypeMed(TypeFld.getText());
                this.daom.updateMed(this.SelectedMedicament);
                System.out.println(this.SelectedMedicament.getNomMed());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupSelectionModelMed() {
        // Clear selection on a new item being added if required or reset fields
        MedicamentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.SelectedMedicament = MedicamentTable.getSelectionModel().getSelectedItem();
            System.out.println("test setup");
            NomMedFld.setText(SelectedMedicament.getNomMed());
            PrixFld.setText(String.valueOf(SelectedMedicament.getPrixMed()));
            QteFld.setText(String.valueOf(SelectedMedicament.getquantite()));
            TypeFld.setText(SelectedMedicament.getTypeMed());
            if (newSelection != null) {
                Medicament selectedMedicament = MedicamentTable.getSelectionModel().getSelectedItem();
                //System.out.println("Selected Name: " + selectedPatient.getNomPatient());  // Assuming there's a getNomPatient method in your Patient model

                // If you have text fields to display selected patient info, you can set them like this:
                // NomFld.setText(selectedPatient.getNomPatient());
                // NumTelFld.setText(String.valueOf(selectedPatient.getTelPatient()));
            }
        });
    }

    ////
    @FXML
    public void MedicamentpaneShow() {
        pane_Patient.setVisible(false);
        pane_Medicament.setVisible(true);
    }

    @FXML
    public void PatientpaneShow() {
        pane_Patient.setVisible(true);
        pane_Medicament.setVisible(false);
    }

    String query = null;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Patient patient = null;

    public void close(MouseEvent event) {
        /* Node source = (Node) event.getSource();
         Stage stage = (Stage) source.getScene().getWindow();
         stage.close();*/
    }

    @FXML
    public void getAddView() {
        /*     try {
         Parent parent = FXMLLoader.load(getClass().getResource("/TaleView/addPatient.fxml"));
         } catch (IOException ex) {
         Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }

    @FXML
    void clean() {
        NomFld.setText(null);
        NumTelFld.setText(null);
    }

    @FXML
    public void save(MouseEvent event) throws ClassNotFoundException, SQLException {
        conn = LaConnexion.seConnecter();
        String NomPatient = NomFld.getText();
        int TelPatient = Integer.parseInt(NumTelFld.getText());
        /* if (NomPatient.isEmpty() || TelPatient <= 0) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText("please fill all data");
         alert.showAndWait();
         } else {
         getQuery();
         insert();
         clean();
         }*/
        this.daop.insert(new Patient(NomPatient, TelPatient));

    }

    private void insert() {
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, NomFld.getText());
            preparedStatement.setInt(2, Integer.parseInt(NumTelFld.getText()));
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupSelectionModel() {
        // Clear selection on a new item being added if required or reset fields
        PatientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.SelectedPatient = PatientTable.getSelectionModel().getSelectedItem();
            System.out.println("test setup");
            NomFld.setText(SelectedPatient.getNomPatient());
            NumTelFld.setText(String.valueOf(SelectedPatient.getTelPatient()));
            if (newSelection != null) {
                Patient selectedPatient = PatientTable.getSelectionModel().getSelectedItem();
                //System.out.println("Selected Name: " + selectedPatient.getNomPatient());  // Assuming there's a getNomPatient method in your Patient model

                // If you have text fields to display selected patient info, you can set them like this:
                // NomFld.setText(selectedPatient.getNomPatient());
                // NumTelFld.setText(String.valueOf(selectedPatient.getTelPatient()));
            }
        });
    }

    @FXML
    void update(MouseEvent event) {
        try {
            System.out.println(this.SelectedPatient.getNomPatient());
            if (this.SelectedPatient != null) {
                System.out.println("3awww");
                this.SelectedPatient.setNomPatient(NomFld.getText());
                this.SelectedPatient.setTelPatient(Integer.parseInt(NumTelFld.getText()));
                this.daop.update(this.SelectedPatient);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void delete(MouseEvent event) {
        try {
            System.out.println(this.SelectedPatient.getNomPatient());
            if (this.SelectedPatient != null) {
                System.out.println("hiiiiiiiiiiiiiiiiiiiii");
                this.daop.delete(this.SelectedPatient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void refreshTable() {
        try {
            query = "SELECT * FROM Patient";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            PatientList.clear();  // Clear the list once before loading new items

            while (resultSet.next()) {
                PatientList.add(new Patient(
                        resultSet.getInt("CodePatient"),
                        resultSet.getString("NomPatient"),
                        resultSet.getInt("TelPatient")
                ));
            }
            PatientTable.setItems(PatientList);  // Set items once after loading all
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    ObservableList<Patient> PatientList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDate();
            setupSelectionModel();
            setupSelectionModelMed();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() throws ClassNotFoundException {
        conn = tp6_2024.LaConnexion.seConnecter();  // Assume this is where you handle your DB connection
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("CodePatient"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("NomPatient"));
        numtelCol.setCellValueFactory(new PropertyValueFactory<>("TelPatient"));
        refreshTableMed();
        idColMed.setCellValueFactory(new PropertyValueFactory<>("CodeMed"));
        PrixColMed.setCellValueFactory(new PropertyValueFactory<>("PrixMed"));
        QteColMed.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        TypeColMed.setCellValueFactory(new PropertyValueFactory<>("TypeMed"));
        nomColMed.setCellValueFactory(new PropertyValueFactory<>("NomMed"));

    }

    private void getQuery() {
        query = "INSERT INTO patient (NomPatient, TelPatient) VALUES (?, ?)";
    }

}
