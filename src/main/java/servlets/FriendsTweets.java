/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.User;
import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import util.UtilCurl;
import util.UtilRoutes;

/**
 *
 * @author sylvain
 */
@WebServlet("/twitter/friends")
public class FriendsTweets extends HelloServlet {

    /**
     * Get all friends timeline
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Start TwitterCallBackOAuth.doGet");

        /*
        User u = (User) request.getSession().getAttribute("currentUser");
        u.resetStatus();
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

        try {
            PagableResponseList<twitter4j.User> userList = twitter.getFriendsList(twitter.getId(), -1);

            for (twitter4j.User us : userList) {
                List<Status> statusFollower = twitter.getUserTimeline(us.getId());
                for (Status sts : statusFollower) {
                    u.addStatus(sts);
                }
            }
        } catch (TwitterException e) {
        } catch (IllegalStateException ex) {
            Logger.getLogger(FriendsTweets.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("user", u);
        */

        User user = (User) request.getSession().getAttribute("currentUser");
        
        String url = UtilCurl.URL_FOLLOWWINGS_TWEET;
        url = url.replace(":id", String.valueOf(user.getId()));
        
        StringBuffer stringBuffer = UtilCurl.getContent(url);
        //JsonReader jsonReader = new ;
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/tweet.jsp").forward(request, response);
        //response.sendRedirect(UtilRoutes.TWEET_VIEW);
    }
}
