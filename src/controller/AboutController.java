/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import zunayedhassan.AnimateFX.FadeInAnimation;
import zunayedhassan.AnimateFX.FadeInUpAnimation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AboutController implements Initializable {

    @FXML
    private Pane root;
    @FXML
    private ImageView logo;
    @FXML
    private Label t1;
    @FXML
    private Label t2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("/images/logo1.png"));
        FadeInUpAnimation b1 = new FadeInUpAnimation(logo);
        FadeInUpAnimation b2 = new FadeInUpAnimation(t1);
        FadeInUpAnimation b3 = new FadeInUpAnimation(t2);
        b1.Play();
        b2.Play();
        b3.Play();

    }

}
