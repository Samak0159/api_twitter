/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sylvain
 */
public class ConnectionBdd {

    private static Connection _instance;
    private static final String BDD = "api_rest_twitter";
    private static final String USER = "rest";
    private static final String PASSWORD = "epsi";
    private static final String TIME_ZONE = "Europe/Paris";
    

    private ConnectionBdd() {

    }

    public static Connection getInstance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (_instance == null) {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            _instance = DriverManager.getConnection("jdbc:mysql://localhost/" + BDD + "?user=" + USER + "&password=" + PASSWORD+ "&serverTimezone="+TIME_ZONE);
        }

        return _instance;
    }

    /**
     * Execute the query
     *
     * @param query
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ResultSet request(String query) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return ConnectionBdd.getInstance().prepareStatement(query).executeQuery();
    }
}
