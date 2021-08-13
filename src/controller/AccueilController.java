/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.connectionDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import zunayedhassan.AnimateFX.FadeInAnimation;
import zunayedhassan.AnimateFX.FadeInRightAnimation;
import zunayedhassan.AnimateFX.RotateInAnimation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AccueilController implements Initializable {

    Connection con;
    connectionDB db = new connectionDB();
    @FXML
    private Label nb_mat;
    @FXML
    private Label nb_mat_etat_bien;
    @FXML
    private Label nb_mat_etat_mauvais;
    @FXML
    private Label nb_dep;
    @FXML
    private PieChart stat;

    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    @FXML
    private Label lbl_mat_max;
    @FXML
    private Label lbl_moy_mat;
    @FXML
    private Label lbl_min_mat;

    String max_mat = "SELECT max(`montant`) as nb FROM `materiels`";
    String min_mat = "SELECT min(`montant`) as nb FROM `materiels`";
    String moy_mat = "SELECT avg(`montant`) as nb FROM `materiels`";
    @FXML
    private Pane p1;
    @FXML
    private Pane p2;
    @FXML
    private Pane p3;
    @FXML
    private Pane p4;
    @FXML
    private Pane p5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = db.getcon();

        requete("SELECT count(*) as nb FROM `materiels`", nb_mat);
        requete("SELECT count(*) as nb FROM `departement`", nb_dep);
        requete("SELECT count(*) as nb FROM `materiels` WHERE `etat`='Mal'", nb_mat_etat_mauvais);
        requete("SELECT count(*) as nb FROM `materiels` WHERE `etat`='Bien'", nb_mat_etat_bien);
        requete(max_mat, lbl_mat_max);
        requete(min_mat, lbl_min_mat);
        requete(moy_mat, lbl_moy_mat);

        initAnim();

        initPie();
    }

    public void requete(String requete, Label node) {
        try {
            PreparedStatement ps = con.prepareStatement(requete);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                node.setText(rs.getString("nb"));
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    public void initPie() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT count(*) as nbBien from materiels where `etat`='Bien'");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                data.add(new PieChart.Data("Bonne etat", rs.getInt("nbBien")));

            }

            ps = con.prepareStatement("SELECT count(*) as nbMal from materiels where `etat`='Mal'");

            rs = ps.executeQuery();

            if (rs.next()) {
                data.add(new PieChart.Data("Mauvaise etat", rs.getInt("nbMal")));

            }

            stat.setData(data);

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    private void initAnim() {
        FadeInAnimation b1 = new FadeInAnimation(p1);
        FadeInAnimation b2 = new FadeInAnimation(p2);

        FadeInAnimation b3 = new FadeInAnimation(p3);

        FadeInAnimation b4 = new FadeInAnimation(p4);
        RotateInAnimation b5 = new RotateInAnimation(stat);
        FadeInRightAnimation b6=new FadeInRightAnimation(p5);
              

        b1.Play();
        b2.Play();
        b3.Play();
        b4.Play();
        b5.Play();
        b6.Play();
       

    }

}
