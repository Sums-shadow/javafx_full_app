/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import data.connectionDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import zunayedhassan.AnimateFX.ShakeAnimation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginController implements Initializable {

    @FXML
    private ImageView ilogo;
    @FXML
    private ImageView ichart;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private ImageView iuser;
    @FXML
    private ImageView ipass;

    final String ACTIF = "#0dc603";
    final String ACTIF2 = "#0b3200";
    final String INACTIF = "#d80303";
    final String INACTIF2 = "#440000";
    public Boolean isValid = false;

    Connection con;
    connectionDB db = new connectionDB();
    @FXML
    private Circle cicle;
    @FXML
    private JFXTextField t_user;
    @FXML
    private JFXTextField t_pass;
    @FXML
    private Pane p2;
    @FXML
    private ImageView iloading;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = db.getcon();
        if (con == null) {
            setState(INACTIF, INACTIF2);
        } else {
            setState(ACTIF, ACTIF2);
        }
        ilogo.setImage(new Image("/images/logo1.png"));
        ichart.setImage(new Image("/images/logo2.jpg"));
        ipass.setImage(new Image("/images/pass.png"));
        iuser.setImage(new Image("/images/user.png"));
        iloading.setImage(new Image("/images/loading.gif"));
        iloading.setOpacity(0);

    }

    @FXML
    private void onLogin(ActionEvent event) {
        if (t_user.getText().equals("") || t_pass.getText().equals("")) {
            if (t_user.getText().equals("")) {
                notValid(t_user);
            }else{
                isActive(t_user);
            }
            if (t_pass.getText().equals("")) {
                notValid(t_pass);
            }else{
                isActive(t_pass);
            }
            isValid = false;
        } else {

            isValid = true;
        }
        
        
        
        
        if (con == null) {
            showSnack("Impossible de trouvé le serveur");
        } else {
            if (isValid) {
                iloading.setOpacity(1);
                btnLogin.setDisable(true);
                FadeTransition fade = new FadeTransition(Duration.seconds(5), iloading);
                fade.setFromValue(1);
                fade.setToValue(1);
                fade.setCycleCount(1);
                fade.play();
                fade.setOnFinished(e -> {
                    iloading.setOpacity(0);
                    connecterMoi();
                });
            }

        }

    }

    private void setState(String etat, String etat2) {
        cicle.setFill(Color.web(etat2));

        FillTransition ft = new FillTransition(Duration.millis(3000), cicle, Color.web(etat2), Color.web(etat));
        ft.setCycleCount(100);
        ft.setAutoReverse(true);
        ft.setDelay(Duration.seconds(1));
        ft.play();
        ft.setOnFinished(e -> {
            setState(etat, etat2);
        });

    }

    public void notValid(Node node) {
        node.setStyle("-fx-border-color:red");
        ShakeAnimation shake = new ShakeAnimation(node);
        shake.Play();
        isValid = false;
    }

    public void isActive(Node node) {
        node.setStyle("-fx-border-color:none");
        isValid = true;
    }

    public void showSnack(String msg) {

        JFXSnackbar snackBar = new JFXSnackbar(p2);
////        snackBar.setPrefHeight(rootPane.getHeight()*2);
////        snackBar.setPrefWidth(rootPane.getWidth());
////        snackBar.setAlignment(Pos.TOP_CENTER);
        // snackBar.setStyle(".jfx-snackbar-content{-fx-background-color:black} .jfx-snackbar-toast{-fx-text-fill:white");
        snackBar.show(msg, 4000);
    }

    private void connecterMoi() {
        String username = t_user.getText();
        String password = t_pass.getText();
        String sql = "SELECT * FROM `user` WHERE `user`=? and `pass`=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                showSnack("Connection reussie");
                Parent root = FXMLLoader.load(getClass().getResource("/vue/dashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.show();
                Stage s = (Stage) t_pass.getScene().getWindow();
                s.close();
            } else {
                showSnack("Echec de connection");
            }
        } catch (Exception e) {
        }
        btnLogin.setDisable(false);
    }
}
