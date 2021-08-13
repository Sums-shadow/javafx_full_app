/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import data.connectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.Departement;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DepartementController implements Initializable {

    @FXML
    private StackPane root;
    @FXML
    private TableView<Departement> table;
    @FXML
    private TableColumn<Departement, String> colNum;
    @FXML
    private TableColumn<Departement, String> colRef;
    @FXML
    private TableColumn<Departement, String> colCode;
    @FXML
    private TableColumn<Departement, String> colMarque;
    @FXML
    private TableColumn<Departement, String> colType;
    @FXML
    private TableColumn<Departement, String> colEtat;
    @FXML
    private TableColumn<Departement, String> colbailleur;
    @FXML
    private TableColumn<Departement, String> coluser;
    @FXML
    private TableColumn<Departement, String> coldate;
    @FXML
    private TextField t_search;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private ComboBox<String> cmbFilter;
    int id_dep = -1;
    Connection con;
    connectionDB db = new connectionDB();
    ObservableList<Departement> o = FXCollections.observableArrayList();
    @FXML
    private ImageView iback;
    @FXML
    private AnchorPane rot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = db.getcon();
        initCellule();

    }

    private void initCellule() {
        cmbFilter.getItems().addAll("Code", "Etat", "Marque", "Bailleur", "Type", "Montant");
        iback.setImage(new Image("/images/back.png"));
        cmbFilter.setValue("Code");
        colNum.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRef.setCellValueFactory(new PropertyValueFactory<>("numRef"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colbailleur.setCellValueFactory(new PropertyValueFactory<>("bailleur"));
        coluser.setCellValueFactory(new PropertyValueFactory<>("user"));

        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

//        table.getSelectionModel().selectedIndexProperty().addListener((obs, old, newv) -> {
//            activeButton();
//        });
    }

    @FXML
    private void onChangeSearches(KeyEvent event) {
        rechercher();
    }

    @FXML
    private void onSearch(ActionEvent event) {
        rechercher();
    }

    public void getData(int id) {
        table.getItems().clear();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `v_materiel` WHERE `id_Dep`=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            int n = 1;

            while (rs.next()) {
                o.add(new Departement(n, rs.getString("num_Ref"), rs.getString("code"), rs.getString("marque"), rs.getString("type_materiel"), rs.getString("etat"), rs.getString("bailleur"), rs.getString("nom_utilisateur"), rs.getString("date_acquisition")));

                n++;
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }

        table.setItems(o);
    }

    public void rechercher() {
        if (t_search.getText().equals("")) {
            getData(id_dep);
        } else {
            table.getItems().clear();
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM `v_materiel` WHERE `id_Dep`=1 AND " + cmbFilter.getValue().toString() + " like ?");
                ps.setString(1, t_search.getText() + '%');
                ResultSet rs = ps.executeQuery();

                int n = 1;

                while (rs.next()) {
                    o.add(new Departement(n, rs.getString("num_Ref"), rs.getString("code"), rs.getString("marque"), rs.getString("type_materiel"), rs.getString("etat"), rs.getString("bailleur"), rs.getString("nom_utilisateur"), rs.getString("date_acquisition")));

                    n++;
                }

            } catch (Exception e) {
                System.err.println("erreur " + e.getMessage());
            }

            table.setItems(o);
        }
    }

    @FXML
    private void onReturn(MouseEvent event) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/vue/consulte.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }

    void initData(int id) {
        getData(id);
        id_dep = id;
    }

}
