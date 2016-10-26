/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import bdd.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.User;
import util.UtilRoutes;

/**
 *
 * @author sylvain
 */
@WebServlet("/auth/twitter/callBack")
public class TwitterCallBackOAuth extends HttpServlet {

    public TwitterCallBackOAuth() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Start TwitterCallBackOAuth.doPost");

        response.getWriter().print("DO POST");

        System.out.println("End TwitterCallBackOAuth.doPost");
    }

    /**
     * Call when the user successfuly connect to twitter
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Start TwitterCallBackOAuth.doGet");

        //response.getWriter().print("hello world");
        System.out.println("parameters");

        String token = request.getParameter("oauth_token");

        System.out.println("Token");
        System.out.println(token);

        String verifier = request.getParameter("oauth_verifier");
        System.out.println("verifier");
        System.out.println(verifier);

        User user = new User();
        user.setOauth_token(token);
        user.setOauth_verifier(verifier);

        try {
            UserDao.insert(user);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect(UtilRoutes.CALL_BACK_VIEW);

        System.out.println("End TwitterCallBackOAuth.doGet");
    }
}
