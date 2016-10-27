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
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import util.UtilRoutes;

/**
 *
 * @author sylvain
 */
@WebServlet("/auth/twitter/callback")
public class TwitterCallBackOAuth extends HttpServlet {

    public TwitterCallBackOAuth() {
        super();
    }

    /**
     * Call when the user successfuly connect to twitter
     *
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Start TwitterCallBackOAuth.doGet");

        AccessToken accessToken = null;

        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");

        try {
            accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
        } catch (TwitterException e) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, e);
        }

        String token = accessToken.getToken();
        String secretToken = accessToken.getTokenSecret();
        
        twitter4j.User twitterUser = null;
        try {
            twitterUser = twitter.showUser( accessToken.getScreenName() );
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(twitterUser==null) {
            //throw error
        }

        User user = new User();
        user.setId(twitterUser.getId());
        user.setFullname(twitterUser.getName());
        user.setUsername(twitterUser.getScreenName());
        user.setUrlPhoto(twitterUser.getProfileImageURL());
        user.setTwitter_token(token);
        user.setTwitter_secret_token(secretToken);

        try {
            UserDao.insert(user);
        } catch (SQLException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getSession().setAttribute("currentUser", user);

        System.out.println("End TwitterCallBackOAuth.doGet");
        try {
            response.sendRedirect(UtilRoutes.CALL_BACK_VIEW);
        } catch (IOException ex) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
