/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import data.connectionDB;
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
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MaterielModel;
import zunayedhassan.AnimateFX.FadeInRightAnimation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EnregistrementController implements Initializable {

    @FXML
    private TableView<MaterielModel> table;
    @FXML
    private TableColumn<MaterielModel, String> colNum;
    @FXML
    private TableColumn<MaterielModel, String> colRef;
    @FXML
    private TableColumn<MaterielModel, String> colMarque;
    @FXML
    private TableColumn<MaterielModel, String> colType;
    @FXML
    private TableColumn<MaterielModel, String> colEtat;
    @FXML
    private TableColumn<MaterielModel, String> colDirection;
    @FXML
    private TableColumn<MaterielModel, String> coldate;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnMod;
    @FXML
    private JFXButton btnDelete;
    ObservableList<MaterielModel> o = FXCollections.observableArrayList();
    @FXML
    private TableColumn<MaterielModel, String> colCode;
    Connection con;
    connectionDB db = new connectionDB();
    @FXML
    private TextField t_search;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private ComboBox<String> cmbFilter;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnprint;
    @FXML
    private ComboBox<String> cmbMonth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initAnim();
        con = db.getcon();
        initCellule();
        btnprint.setDisable(true);

    }

    @FXML
    private void onAdd(ActionEvent event) {
        try {
            BoxBlur bb = new BoxBlur();
            bb.setWidth(100);
            bb.setWidth(100);
            bb.setIterations(1);
            table.setEffect(bb);
            btnAdd.setEffect(bb);
            btnDelete.setEffect(bb);
            btnMod.setEffect(bb);
            btnSearch.setEffect(bb);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/newMateriel.fxml"));
            AnchorPane catcont = loader.load();

            Stage s = new Stage();
            s.setScene(new Scene(catcont));
            s.initStyle(StageStyle.UNDECORATED);
            s.setAlwaysOnTop(true);
            s.setOnHidden(e -> {
                try {
                    getData();
                    bb.setWidth(0);
                    bb.setHeight(0);
                    table.setEffect(bb);
                    deactiveButton();
                    showSnack("Enregistrement reussi");
                } catch (Exception ex) {
                    System.err.println("erreur " + ex);
                }
            });
            s.show();

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @FXML
    private void onMod(ActionEvent event) {
        MaterielModel m = table.getSelectionModel().getSelectedItem();
        try {
            BoxBlur bb = new BoxBlur();
            bb.setWidth(100);
            bb.setWidth(100);
            bb.setIterations(1);
            table.setEffect(bb);
            btnAdd.setEffect(bb);
            btnDelete.setEffect(bb);
            btnMod.setEffect(bb);
            btnSearch.setEffect(bb);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/modiMat.fxml"));
            AnchorPane catcont = loader.load();

            //access the controller and call a method
            ModMaterielController controller = loader.getController();
            controller.initData(m);

            Stage s = new Stage();
            s.setScene(new Scene(catcont));
            s.initStyle(StageStyle.UNDECORATED);
            s.setAlwaysOnTop(true);
            s.setOnHidden(e -> {
                try {
                    getData();
                    bb.setWidth(0);
                    bb.setHeight(0);
                    table.setEffect(bb);
                    deactiveButton();
                    showSnack("Modification reussi");
                } catch (Exception ex) {
                    System.err.println("erreur " + ex);
                }
            });
            s.show();

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        MaterielModel m = table.getSelectionModel().getSelectedItem();
        BoxBlur bb = new BoxBlur();
        bb.setWidth(100);
        bb.setWidth(100);
        bb.setIterations(1);

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/delMateriel.fxml"));
            AnchorPane catcont = loader.load();
            table.setEffect(bb);
            btnAdd.setEffect(bb);
            btnDelete.setEffect(bb);
            btnMod.setEffect(bb);
            btnSearch.setEffect(bb);

            //access the controller and call a method
            DelMaterielController controller = loader.getController();
            controller.initData(m);
            //Mettre le blur
//root.setEffect();
            // root.setEffect();
            root.setEffect(null);
            Stage s = new Stage();
            s.setScene(new Scene(catcont));
            s.initStyle(StageStyle.UNDECORATED);
            s.setAlwaysOnTop(true);
            s.setOnHidden(e -> {
                try {
                    getData();
                    bb.setWidth(0);
                    bb.setHeight(0);
                    table.setEffect(bb);
                    deactiveButton();
                    showSnack("Suppression reussi");
                } catch (Exception ex) {
                    System.err.println("erreur " + ex);
                }
            });
            s.show();

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    private void initCellule() {
        cmbFilter.getItems().addAll("Code", "Etat", "Marque", "Bailleur", "Type", "Montant");
        cmbFilter.setValue("Code");
        colNum.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRef.setCellValueFactory(new PropertyValueFactory<>("numRef"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDirection.setCellValueFactory(new PropertyValueFactory<>("direction"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        getData();

        table.getSelectionModel().selectedIndexProperty().addListener((obs, old, newv) -> {
            activeButton();
        });

    }

    public void getData() {
        table.getItems().clear();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `v_materiel`");

            ResultSet rs = ps.executeQuery();

            int n = 1;

            while (rs.next()) {
                o.add(new MaterielModel(n, rs.getString("num_Ref"), rs.getString("code"), rs.getString("marque"), rs.getString("type_materiel"), rs.getString("etat"), rs.getString("nom_dep"), rs.getString("date_acquisition")));

                n++;
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }

        table.setItems(o);
    }

    @FXML
    private void onSearch(ActionEvent event) {
        rechercher();
    }

    @FXML
    private void onChangeSearches(KeyEvent event) {
        rechercher();
    }

    public void rechercher() {
        if (t_search.getText().equals("")) {
            getData();
        } else {
            table.getItems().clear();
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM `v_materiel` WHERE " + cmbFilter.getValue().toString() + " like ?");
                ps.setString(1, t_search.getText() + '%');
                ResultSet rs = ps.executeQuery();

                int n = 1;

                while (rs.next()) {
                    o.add(new MaterielModel(n, rs.getString("num_Ref"), rs.getString("code"), rs.getString("marque"), rs.getString("type_materiel"), rs.getString("etat"), rs.getString("nom_dep"), rs.getString("date_acquisition")));

                    n++;
                }

            } catch (Exception e) {
                System.err.println("erreur " + e.getMessage());
            }

            table.setItems(o);
        }
    }

    public void activeButton() {
        btnDelete.setDisable(false);
        btnMod.setDisable(false);
        btnDelete.setOpacity(1);
        btnMod.setOpacity(1);
    }

    public void deactiveButton() {
        btnDelete.setDisable(true);
        btnMod.setDisable(true);
        btnDelete.setOpacity(0.5);
        btnMod.setOpacity(0.5);

    }

    private void initAnim() {
        FadeInRightAnimation b1 = new FadeInRightAnimation(btnAdd);
        FadeInRightAnimation b2 = new FadeInRightAnimation(btnDelete);
        FadeInRightAnimation b3 = new FadeInRightAnimation(btnMod);
                FadeInRightAnimation b4 = new FadeInRightAnimation(btnprint);

        b1.Play();
        b2.Play();
        b3.Play();
        b4.Play();
        deactiveButton();
    }

    
    
    public void showSnack(String msg){
        
        JFXSnackbar snackBar=new JFXSnackbar(root);
////        snackBar.setPrefHeight(rootPane.getHeight()*2);
////        snackBar.setPrefWidth(rootPane.getWidth());
////        snackBar.setAlignment(Pos.TOP_CENTER);
       // snackBar.setStyle(".jfx-snackbar-content{-fx-background-color:black} .jfx-snackbar-toast{-fx-text-fill:white");
        snackBar.show(msg,4000);
    }
}
