/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.connectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MaterielModel;
import zunayedhassan.AnimateFX.FadeInUpAnimation;
import zunayedhassan.AnimateFX.FlipInXAnimation;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ConsulteController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView logo;
    @FXML
    private StackPane rot;
    @FXML
    private AnchorPane first;
    @FXML
    private ScrollPane scroll;
    Connection con;
    connectionDB db = new connectionDB();
    @FXML
    private VBox vbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = db.getcon();
        initImage();
        initAnim();
       initDep();

    }

    private void onBd(MouseEvent event) {
        gotos("/vue/departement/bd.fxml");
    }

    private void onRTM(MouseEvent event) {
        gotos("/vue/departement/rtm.fxml");
    }

    private void initImage() {

    }

    private void initAnim() {
//        
//        FlipInXAnimation b1=new FlipInXAnimation(p1);
//                FlipInXAnimation b2=new FlipInXAnimation(btnRtm);
        FadeInUpAnimation b3 = new FadeInUpAnimation(logo);

//                b1.Play();b2.Play();
        b3.Play();

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

    private void initDep() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `departement`");

            ResultSet rs = ps.executeQuery();

            int n = 1;
            HBox h1= new HBox(); 
            HBox h2= new HBox(); 
            HBox h3= new HBox(); 
            HBox h4 = new HBox();
          
             logo.setImage(new Image("/images/logo1.png"));
     

        BoxBlur bb = new BoxBlur();
        bb.setWidth(10);
       
        bb.setIterations(1);
      
            while (rs.next()) {
                System.out.println(rs.getString("nom_dep"));
                Pane p = new Pane();
                System.err.println("HEREEE");
                p.setPrefHeight(229.0);
                p.setPrefWidth(350.8);
                p.setStyle("-fx-background-color: #e0e0e0;");
//                ImageView img = new ImageView();
//                img.setFitHeight(229.0);
//                img.setFitWidth(307.0);
//                  img.setImage(new Image("/images/bg.jpg"));
//                    img.setEffect(bb);
                Label lab = new Label();
                lab.setLayoutX(109.0);
                lab.setLayoutY(55.0);
                lab.setFont(new Font("Lato Bold", 34.0));
//                lab.setPrefHeight(49);
//                lab.setPrefWidth(75);
                Label ll = new Label();
                ll.setLayoutX(14.0);
                ll.setLayoutY(104.0);
                ll.setWrapText(true);
                ll.setPrefHeight(100);
                ll.setText(rs.getString("nom_dep"));
                ll.setPrefWidth(330.8);
                DropShadow shadow=new DropShadow(10, Color.web("#d3d3d3"));
                p.setEffect(shadow);
                lab.setText(rs.getString("abbr"));
                p.getChildren().addAll( lab, ll);
                int id=rs.getInt("id_Dep");
                p.setOnMouseClicked(e->{

                        gotoOneDep(id);

                });
                if (n <= 3) {

                    h1.getChildren().add(p);

                } else if (n <= 6) {
                    h2.getChildren().add(p);
                } else if (n <= 9) {
                    h3.getChildren().add(p);
                } else if (n <= 12) {
                    h4.getChildren().add(p);
                }

                n++;
            }
            h1.setSpacing(10);
                        h2.setSpacing(10);
            h3.setSpacing(10);
            h4.setSpacing(10);

            vbox.getChildren().addAll(h1, h2, h3, h4);
            vbox.setSpacing(30);
        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void gotoOneDep(int id) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/departement.fxml"));
            AnchorPane catcont = loader.load();

            //access the controller and call a method
            DepartementController controller = loader.getController();
            controller.initData(id);
 root.getChildren().clear();
            root.getChildren().add(catcont);

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

}
