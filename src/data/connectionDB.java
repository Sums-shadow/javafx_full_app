/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class connectionDB {
       Connection con = null;
    public Connection getcon (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_gestion_inventaire","root","");
            System.out.println("connection reusssi");
        }catch(ClassNotFoundException | SQLException e){
        
         System.out.println("connection echouee "+e.getMessage());
       
        
        }
        return con;
    }
}
