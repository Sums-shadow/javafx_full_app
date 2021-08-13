/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import data.connectionDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.MaterielModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DelMaterielController implements Initializable {

    @FXML
    private ImageView iquestion;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnCancel;

    Connection con;
    connectionDB db = new connectionDB();

    public int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iquestion.setImage(new Image("/images/warning.png"));
        initDB();
    }

    @FXML
    private void notHover(MouseEvent event) {
    }

    @FXML
    private void hoverConfirm(MouseEvent event) {
    }

    @FXML
    private void onDelete(ActionEvent event) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM `materiels` WHERE `num_Ref`=?");
            ps.setInt(1, id);

            ps.execute();
            System.out.println("Suppression reussie!!");
            Stage s = (Stage) btnCancel.getScene().getWindow();
            s.close();

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    @FXML
    private void hoverExit(MouseEvent event) {
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Stage s = (Stage) btnCancel.getScene().getWindow();
        s.close();
    }

    public void initDB() {
        con = db.getcon();
    }

    public void initData(MaterielModel m) {

      id = Integer.parseInt(m.getNumRef());

    }

}
