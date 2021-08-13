/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import data.connectionDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MaterielModel;
import zunayedhassan.AnimateFX.ShakeAnimation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ModMaterielController implements Initializable {

    @FXML
    private JFXTextField t_code;
    @FXML
    private JFXTextField t_marque;
    @FXML
    private JFXComboBox<String> t_etat;
    @FXML
    private JFXComboBox<String> t_departement;
    @FXML
    private DatePicker t_date;
    @FXML
    private JFXTextField t_bailleur;
    @FXML
    private JFXTextField t_type;
    @FXML
    private JFXTextField t_montant;
    @FXML
    private JFXTextField t_nomuser;
    @FXML
    private JFXTextField t_fonctionuser;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btncancel;

    Connection con;
    connectionDB db = new connectionDB();
    public int id;
     boolean isValid = true;
    @FXML
    private ImageView isave;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDB();
        intitCombot();
        isave.setImage(new Image("/images/logo1.png"));
    }

    @FXML
    private void onSave(ActionEvent event) {
         if (t_code.getText().equals("")) {
            notValid(t_code);
        } else {
            isActive(t_code);
        }

        if (t_marque.getText().equals("")) {
            notValid(t_marque);
        } else {
            isActive(t_marque);
        }


        if (t_type.getText().equals("")) {
            notValid(t_type);
        } else {
            isActive(t_type);
        }

        if (t_montant.getText().equals("")) {
            notValid(t_montant);
        } else {
            isActive(t_montant);
        }
        if(isValid){
            try {
            PreparedStatement ps = con.prepareStatement("UPDATE `materiels` SET `code`=?, `etat`=?, `marque`=?, `date`=?, `bailleur`=?, `type`=?, `montant`=?, `nom_utilisateur`=?, `fonction_utilisateur`=?, `id_Dep`=? WHERE `num_Ref`=?");
            ps.setString(1, t_code.getText());
            ps.setString(2, t_etat.getValue());
            ps.setString(3, t_marque.getText());
            ps.setDate(4, Date.valueOf(LocalDate.of(t_date.getValue().getYear(), t_date.getValue().getMonth(), t_date.getValue().getDayOfMonth())));
            ps.setString(5, t_bailleur.getText());
            ps.setString(6, t_type.getText());
            ps.setString(7, t_montant.getText());
            ps.setString(8, t_nomuser.getText());
            ps.setString(9, t_fonctionuser.getText());
            ps.setInt(10, getDepId(t_departement.getValue()));
            ps.setInt(11, id);
            ps.executeUpdate();

            System.out.println("Modification reusssie!");
            Stage s = (Stage) btnSave.getScene().getWindow();
            s.close();

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
        }else{
//          snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout(message), Duration.seconds(5), null));
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
         Stage s = (Stage) btnSave.getScene().getWindow();
            s.close();
    }

    public void intitCombot() {
        t_etat.getItems().addAll("Excellent", "Trés bien", "Bien", "Normal", "Mauvais", "Tres mauvais", "Mediocre");
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `departement`");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                t_departement.getItems().add(rs.getString("nom_dep"));
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    public void initDB() {
        con = db.getcon();
    }

    public void initData(MaterielModel m) {

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `v_materiel` where num_Ref=?");
            ps.setString(1, m.getNumRef());
            ResultSet rs = ps.executeQuery();
            id = Integer.parseInt(m.getNumRef());
            while (rs.next()) {
                t_code.setText(rs.getString("code"));
                t_departement.setValue(rs.getString("nom_dep"));
                t_etat.setValue(rs.getString("etat"));
                t_nomuser.setText(rs.getString("nom_utilisateur"));
                t_fonctionuser.setText(rs.getString("fonction_utilisateur"));

                t_bailleur.setText(rs.getString("bailleur"));

                t_date.setValue(LocalDate.of(rs.getDate("date_acquisition").getYear(), rs.getDate("date_acquisition").getMonth(), rs.getDate("date_acquisition").getDay()));
                t_marque.setText(rs.getString("marque"));
                t_montant.setText(rs.getString("montant"));
                t_type.setText(rs.getString("type_materiel"));

            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    public int getDepId(String dep) {
        int id = -1;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `departement` where nom_dep=?");
            ps.setString(1, dep);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_Dep");
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
        return id;
    }
    
    
       
    public void notValid(Node node){
          node.setStyle("-fx-border-color:red");
            ShakeAnimation shake = new ShakeAnimation(t_code);
            shake.Play();
            isValid = false;
    }
    
    
    
    public void isActive(Node node){
        node.setStyle("-fx-border-color:none");
            isValid = true;
    }

}
