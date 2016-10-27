/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author sylvain
 */
public class UtilRoutes {

    private static final String APPLICATION = "/api_twitter/";
    public final static String CALL_BACK_VIEW = APPLICATION+"twitter/friends";
    public final static String CALL_BACK_URL = "http://83.204.90.154:8080/api_rest_twitter/auth/twitter/callBack";
    
    public final static String TWEET_VIEW = APPLICATION+CALL_BACK_VIEW+"tweet.jsp";
    public final static String FAVOURITES_VIEW = APPLICATION+CALL_BACK_VIEW+"favourites.jsp";
    public final static String DELETED_VIEW = APPLICATION+CALL_BACK_VIEW+"deleted.jsp";
}
