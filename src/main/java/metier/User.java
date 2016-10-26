/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.Date;

/**
 *
 * @author sylvain
 */
public class User {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Date lastConnection;

    private Twitter twitter;

    public User(int id, String nom, String prenom, String email, Date lastConnection, Twitter twitter) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lastConnection = lastConnection;
        this.twitter = twitter;
    }

    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", lastConnection=" + lastConnection + ", twitter=" + twitter + '}';
    }
}
