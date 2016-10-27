/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.User;

/**
 *
 * @author sylvain
 */
public class UserDao {

    /**
     * Get All Users
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<User> getAll() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<User> users = new ArrayList<User>();

        String requete = "SELECT * FROM user";

        ResultSet resultSet = ConnectionBdd.request(requete);

        User user;
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFullname(resultSet.getString("fullname"));
            user.setUsername(resultSet.getString("username"));
            user.setUrlPhoto(resultSet.getString("urlPhoto"));
            user.setLastConnection(resultSet.getDate("lastConnection"));
            user.setTwitter_token(resultSet.getString("twitter_token"));
            user.setTwitter_secret_token(resultSet.getString("twitter_secret_token"));

            users.add(user);
        }

        return users;
    }

    public static User getUserById(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        User user = new User();

        ResultSet resultSet = null;
        PreparedStatement preparedStatement;

        String requete = "SELECT * FROM user WHERE id= ?";

        // préparer la requête
        preparedStatement = ConnectionBdd.getInstance().prepareStatement(requete);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setFullname(resultSet.getString("fullname"));
            user.setUsername(resultSet.getString("username"));
            user.setUrlPhoto(resultSet.getString("urlPhoto"));
            user.setLastConnection(resultSet.getDate("lastConnection"));
            user.setTwitter_token(resultSet.getString("twitter_token"));
            user.setTwitter_secret_token(resultSet.getString("twitter_secret_token"));
        }

        return user;
    }

    public static boolean alreadyRegistered(String secretToken) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        User user = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement;

        String requete = "SELECT * FROM user WHERE twitter_secret_token= ?";

        // préparer la requête
        preparedStatement = ConnectionBdd.getInstance().prepareStatement(requete);
        preparedStatement.setString(1, secretToken);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setFullname(resultSet.getString("fullname"));
            user.setUsername(resultSet.getString("username"));
            user.setUrlPhoto(resultSet.getString("urlPhoto"));
            user.setLastConnection(resultSet.getDate("lastConnection"));
            user.setTwitter_token(resultSet.getString("twitter_token"));
            user.setTwitter_secret_token(resultSet.getString("twitter_secret_token"));
        }

        return (user != null);
    }

    /**
     * Insert one User
     *
     * @param user
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int insert(User user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //init
        String requete;
        PreparedStatement preparedStatement;

        requete = "INSERT INTO user (id, fullname, username, urlPhoto, lastConnection, twitter_token, twitter_secret_token) VALUES (?, ?, ?, ?, ?, ?, ?)";

        //Transfer Date type
        java.sql.Date date = new java.sql.Date(user.getLastConnection().getTime());

        //prepareStatement
        preparedStatement = ConnectionBdd.getInstance().prepareStatement(requete);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getFullname());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getUrlPhoto());
        preparedStatement.setDate(5, date);
        preparedStatement.setString(6, user.getTwitter_token());
        preparedStatement.setString(7, user.getTwitter_secret_token());

        //Execute
        int nb = preparedStatement.executeUpdate();

        return nb;
    }
}
