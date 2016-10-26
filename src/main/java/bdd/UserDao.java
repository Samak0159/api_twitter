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
            user.setId(resultSet.getInt("id"));
            user.setNom(resultSet.getString("nom"));
            user.setPrenom(resultSet.getString("prenom"));
            user.setEmail(resultSet.getString("email"));
            user.setOauth_token(resultSet.getString("oauth_token"));
            user.setOauth_verifier(resultSet.getString("oauth_verifier"));

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
            user.setId(resultSet.getInt("id"));
            user.setNom(resultSet.getString("nom"));
            user.setPrenom(resultSet.getString("prenom"));
            user.setEmail(resultSet.getString("email"));
            user.setOauth_token(resultSet.getString("oauth_token"));
            user.setOauth_verifier(resultSet.getString("oauth_verifier"));
        }

        return user;
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

        requete = "INSERT INTO user (nom, prenom, email, lastConnection, oauth_token, oauth_verifier) VALUES (?, ?, ?, ?, ?, ?)";
        
        //Transfer Date type
        System.out.println("User Date");;
        System.out.println(user.getLastConnection().getTime());
        
        java.sql.Date date = new java.sql.Date(user.getLastConnection().getTime());
        
        System.out.println("DATE :");
        System.out.println(date);

        //prepareStatement
        preparedStatement = ConnectionBdd.getInstance().prepareStatement(requete);
        preparedStatement.setString(1, user.getNom());
        preparedStatement.setString(2, user.getPrenom());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setDate(4, date);
        preparedStatement.setString(5, user.getOauth_token());
        preparedStatement.setString(6, user.getOauth_verifier());

        //Execute
        int nb = preparedStatement.executeUpdate();

        return nb;
    }
}
