/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbot.v1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class Tester {

    public static void main(String args[]) throws IOException, URISyntaxException, ParseException {
        TwitterBot.loadKeywords("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\STONKS\\keywords.json");
        System.out.println(TwitterBot.getBearerToken());
//        final String bearerToken = TwitterBot.getBearerToken();
//        if (null != bearerToken) {
//            // Replace with user ID below
//            UserTimeline usr = TwitterBot.getTweets("2244994945");
//        } else {
//            System.out.println("There was a problem getting your bearer token. Please make sure you set the BEARER_TOKEN environment variable");
//        }
    }

}
