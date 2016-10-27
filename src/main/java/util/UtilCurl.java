/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 *
 * @author sylvain
 */
public class UtilCurl {

    //private
    private static final String PATH = "http://192.168.32.1:8080/api_wks_back/user/788060666687000576/following/tweet";
    private static final String DEFAULT_FORMAT = "application/json";
    
    //public
    public static final String URL_FOLLOWWINGS_TWEET = "user/:id/followings/tweets";
    
    /**
     * rend le choix du format de retour factultatif
     * @param url
     * @return
     * @throws IOException 
     */
    public static StringBuffer getContent(String url) throws IOException {
        return getContent(url, DEFAULT_FORMAT);
    }

    public static StringBuffer getContent(String url,String format) throws MalformedURLException, IOException {
        String httpUrl = PATH+url;

        //Make the connection
        URLConnection connection = new URL(httpUrl).openConnection();
        connection.setRequestProperty("Content-Type", format);
        connection.setRequestProperty("Accept-Charset", "utf-8");

        //Read the answer
        BufferedReader bufferReader = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()
                )
        );
        
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = bufferReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        bufferReader.close();
        
        //Send the answer
        return stringBuffer;
    }
}
