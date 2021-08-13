/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import zunayedhassan.AnimateFX.BounceInLeftAnimation;
import zunayedhassan.AnimateFX.FadeInLeftAnimation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DashboardController implements Initializable {

    @FXML
    private BorderPane border;
    @FXML
    private JFXButton accueille;
    @FXML
    private JFXButton btnEnre;
    @FXML
    private StackPane root;
    @FXML
    private ImageView ilogos;
    String defaultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";
    @FXML
    private JFXButton btnConsult;
    @FXML
    private JFXButton btnAbout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AccueilMoi();
        ilogos.setImage(new Image("/images/logo2.jpg"));
        FadeInLeftAnimation b1 = new FadeInLeftAnimation(accueille);
        FadeInLeftAnimation b2 = new FadeInLeftAnimation(btnEnre);
        FadeInLeftAnimation b3 = new FadeInLeftAnimation(btnConsult);
        FadeInLeftAnimation b4 = new FadeInLeftAnimation(btnAbout);
        b1.Play();
        b2.Play();
        b3.Play();
        b4.Play();

    }

    @FXML
    private void onaccueille(ActionEvent event) {
        AccueilMoi();
    }

    @FXML
    private void onEnre(ActionEvent event) {
        onEnre();
    }

    @FXML
    private void onConsulte(ActionEvent event) {
        onConsult();
    }

    @FXML
    private void onAbout(ActionEvent event) {
        onAbout();
    }

    public void AccueilMoi() {
        onHome();
    }

    public void gotos(String path) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(path).openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }

    public void onAbout() {
        gotos("/vue/about.fxml");
        btnAbout.setStyle(activeStyle);
        btnConsult.setStyle(defaultStyle);
        btnEnre.setStyle(defaultStyle);
        accueille.setStyle(defaultStyle);
    }

    public void onConsult() {
        gotos("/vue/consulte.fxml");
        btnAbout.setStyle(defaultStyle);
        btnConsult.setStyle(activeStyle);
        btnEnre.setStyle(defaultStyle);
        accueille.setStyle(defaultStyle);
    }

    public void onEnre() {
        gotos("/vue/save.fxml");
        btnAbout.setStyle(defaultStyle);
        btnConsult.setStyle(defaultStyle);
        btnEnre.setStyle(activeStyle);
        accueille.setStyle(defaultStyle);
    }

    public void onHome() {
        gotos("/vue/accueille.fxml");
        btnAbout.setStyle(defaultStyle);
        btnConsult.setStyle(defaultStyle);
        btnEnre.setStyle(defaultStyle);
        accueille.setStyle(activeStyle);
    }

}
