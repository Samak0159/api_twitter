/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import util.UtilTwitter;

/**
 *
 * @author sylvain
 */
@WebServlet("/auth/twitter/connect")
public class TwitterConnectionServlet extends HttpServlet {

    public TwitterConnectionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Start TwitterConnectionServlet.doGet");

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(UtilTwitter.CONSUMER_KEY);
        builder.setOAuthConsumerSecret(UtilTwitter.CONSUMER_SECRET);

        TwitterFactory factory = new TwitterFactory(builder.build());
        Twitter twitter = factory.getInstance();
        //Twitter twitter = new TwitterFactory().getInstance();
        request.getSession().setAttribute("twitter", twitter);
        try {
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/callback");

            RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
            request.getSession().setAttribute("requestToken", requestToken);
            response.sendRedirect(requestToken.getAuthenticationURL());

        } catch (TwitterException e) {
            Logger.getLogger(TwitterCallBackOAuth.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
