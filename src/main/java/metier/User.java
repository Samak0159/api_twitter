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
    private String oauth_token;
    private String oauth_verifier;

    public User(int id, String nom, String prenom, String email, Date lastConnection, String oauth_token, String oauth_verifier) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lastConnection = lastConnection;
        this.oauth_token = oauth_token;
        this.oauth_verifier = oauth_verifier;
    }

    public User() {
        this.lastConnection = new Date();
    }
    
    

    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_verifier() {
        return oauth_verifier;
    }

    public void setOauth_verifier(String oauth_verifier) {
        this.oauth_verifier = oauth_verifier;
    }

    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }

    public Date getLastConnection() {
        return lastConnection;
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", lastConnection=" + lastConnection + ", oauth_token=" + oauth_token + ", oauth_verifier=" + oauth_verifier + '}';
    }
}
