/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class Departement {
       private int id;
    private String numRef;    private String code;

    
    private String marque;
    private String type;
    private String etat;
    private String bailleur;
        private String user;

    private String date;

    public Departement() {
    }

    public Departement(int id, String numRef, String code, String marque, String type, String etat, String bailleur, String user, String date) {
        this.id = id;
        this.numRef = numRef;
        this.code = code;
        this.marque = marque;
        this.type = type;
        this.etat = etat;
        this.bailleur = bailleur;
        this.user = user;
        this.date = date;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the numRef
     */
    public String getNumRef() {
        return numRef;
    }

    /**
     * @param numRef the numRef to set
     */
    public void setNumRef(String numRef) {
        this.numRef = numRef;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the etat
     */
    public String getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * @return the bailleur
     */
    public String getBailleur() {
        return bailleur;
    }

    /**
     * @param bailleur the bailleur to set
     */
    public void setBailleur(String bailleur) {
        this.bailleur = bailleur;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
}
