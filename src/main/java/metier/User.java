/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.ArrayList;
import java.util.Date;
import twitter4j.Status;

/**
 *
 * @author sylvain
 */
public class User {

    private long id;
    private String fullname;
    private String username;
    private String urlPhoto;
    private Date lastConnection;
    private String twitter_token;
    private String twitter_secret_token;
    
    private ArrayList<Status> currentTweets;

    public User(long id, String fullname, String username, String urlPhoto, Date lastConnection, String twitter_token, String twitter_secret_token, ArrayList<Status> currentTweets) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.urlPhoto = urlPhoto;
        this.lastConnection = lastConnection;
        this.twitter_token = twitter_token;
        this.twitter_secret_token = twitter_secret_token;
        this.currentTweets = currentTweets;
    }

    public User() {
        this.lastConnection = new Date();
    }
    
    public void addStatus(Status status) {
        this.currentTweets.add(status);
    }
    
    public void resetStatus() {
        this.currentTweets = new ArrayList<Status>();
    }

    public String getTwitter_token() {
        return twitter_token;
    }

    public void setTwitter_token(String twitter_token) {
        this.twitter_token = twitter_token;
    }

    public String getTwitter_secret_token() {
        return twitter_secret_token;
    }

    public void setTwitter_secret_token(String twitter_secret_token) {
        this.twitter_secret_token = twitter_secret_token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    
    
    
    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    public ArrayList<Status> getCurrentTweets() {
        return currentTweets;
    }

    public void setCurrentTweets(ArrayList<Status> currentTweets) {
        this.currentTweets = currentTweets;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullname=" + fullname + ", username=" + username + ", urlPhoto=" + urlPhoto + ", lastConnection=" + lastConnection + ", twitter_token=" + twitter_token + ", twitter_secret_token=" + twitter_secret_token + ", currentTweets=" + currentTweets + '}';
    }
}
