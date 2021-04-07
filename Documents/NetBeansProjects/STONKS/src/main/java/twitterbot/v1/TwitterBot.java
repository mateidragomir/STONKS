/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates


 * and open the template in the editor.
 */
package twitterbot.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Administrator
 */
public class TwitterBot {

    /**
     * twitter API key
     */
    private static String API_KEY;

    /**
     * twitter API secret key
     */
    private static String API_SECRET_KEY;

    /**
     * twitter Bearer Token
     */
    private static String BEARER_TOKEN;
    
    /**
     * list of keywords with their associated stock tickers
     */
    private static TreeMap<String, String> keywords;
    
    /**
     * list of author_ids with their associated UserTimeline
     */
    private static TreeMap<String, UserTimeline> influencerTimelines;
    
    /**
     * list of influencers with their associated author_ids
     */
    private static TreeMap<String, String> influencerIds;

    /**
     *
     * @return API key
     */
    public static String getAPIKey() throws FileNotFoundException {
        return API_KEY;
    }

    /**
     *
     * @return API secret key
     */
    public static String getAPISecretKey() throws FileNotFoundException {
        return API_SECRET_KEY;
    }

    /**
     *
     * @return Bearer Token
     */
    public static String getBearerToken() throws FileNotFoundException {
        return BEARER_TOKEN;
    }

    public static TreeMap<String, String> getInfluencerIds() {
        return influencerIds;
    }

    public static void setInfluencerIds(TreeMap<String, String> influencerIds) {
        TwitterBot.influencerIds = influencerIds;
    }
    
    public static void addInfluencer(String username) throws IOException, URISyntaxException {
        getTweets(GetUserId(username));
    }
    
    public static UserTimeline getTweetsFromFile(String path) throws FileNotFoundException, JsonProcessingException {
        Scanner fileScnr = new Scanner(new File(path));
        String json = "";
        while(fileScnr.hasNextLine()) {
            json += fileScnr.nextLine();
        }
        return deserializeUsertimeline(json);
    }
    
    public static UserTimeline deserializeUsertimeline(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);
        UserTimeline timeline = objectMapper.readValue(json, UserTimeline.class);
        if (timeline.getMentions().isEmpty()) {
            timeline.constructMentions(keywords);
        }
        influencerTimelines.put(timeline.getAuthor_id(), timeline);
        return timeline;
    }
    
    public static void loadAllUserTimelines(String path) throws FileNotFoundException, JsonProcessingException {
        Scanner fileScnr = new Scanner(new File(path));
        String json = "";
        while(fileScnr.hasNextLine()) {
            json += fileScnr.nextLine();
        }
        TypeReference<ArrayList<UserTimeline>> typeRef = new TypeReference<ArrayList<UserTimeline>>() {};
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);
        ArrayList<UserTimeline> timeLines = objectMapper.readValue(json, typeRef);
        for (UserTimeline timeLine: timeLines) {
            influencerTimelines.put(timeLine.getAuthor_id(), timeLine);
        }
    }
    /**
     * This method calls the v2 User Tweet timeline endpoint by user ID
     */
    public static UserTimeline getTweets(String userId) throws IOException, URISyntaxException {
//        String tweetResponse = null;
//        
//        HttpClient httpClient = HttpClients.custom()
//                .setDefaultRequestConfig(RequestConfig.custom()
//                        .setCookieSpec(CookieSpecs.STANDARD).build())
//                .build();
//
//        URIBuilder uriBuilder = new URIBuilder(String.format("https://api.twitter.com/2/users/%s/tweets", userId));
//        ArrayList<NameValuePair> queryParameters = new ArrayList<>();
//        queryParameters.add(new BasicNameValuePair("tweet.fields", "created_at"));
//        queryParameters.add(new BasicNameValuePair("tweet.fields", "author_id"));
//        uriBuilder.addParameters(queryParameters);
//        
//        HttpGet httpGet = new HttpGet(uriBuilder.build());
//        httpGet.setHeader("Authorization", String.format("Bearer %s", BEARER_TOKEN));
//        httpGet.setHeader("Content-Type", "application/json");
//        
//        HttpResponse response = httpClient.execute(httpGet);
//        HttpEntity entity = response.getEntity();
//        
//        if (null != entity) {
//            tweetResponse = EntityUtils.toString(entity, "UTF-8");
//        }

        String tweetResponse = "{\"data\":[{\"created_at\":\"2021-03-29T19:09:16.000Z\",\"id\":\"1376612438313852930\",\"text\":\"In just two months, thousands of people have been reviewed and approved to BLAH3 our Academic Research product track. \\uD83E\\uDD13 \\n\\nWant to know what the buzz is all about? @SuhemParack gives a quick overview of what’s available, and how you can get started \\uD83D\\uDC47 https://t.co/SWlnUivybZ\"},{\"created_at\":\"2021-03-25T20:22:03.000Z\",\"id\":\"1375181203502944261\",\"text\":\"We also recently launched code BLAH0 samples in R for some of our new v2 endpoints\\nhttps://t.co/KkSwXhw9ds\"},{\"created_at\":\"2021-03-25T20:22:02.000Z\",\"id\":\"1375181201980416006\",\"text\":\"If you're looking to get started with BLAH1 the #TwitterAPI v2 and R @jessicagarson has you covered with a new tutorial.https://t.co/16iKAEMGib\"},{\"created_at\":\"2021-03-23T16:59:18.000Z\",\"id\":\"1374405406261268481\",\"text\":\"Live now! https://t.co/9BbWekeWq2\"},{\"created_at\":\"2021-03-22T21:04:00.000Z\",\"id\":\"1374104599456534531\",\"text\":\"Hope to see you tomorrow at 1 pm EST for APIs 101! \\nhttps://t.co/GrtBOXyHmB https://t.co/YyQfmgiLlL\"},{\"created_at\":\"2021-03-19T19:59:10.000Z\",\"id\":\"1373001119480344583\",\"text\":\"Looking to get started with the Twitter API but new to APIs in general? @jessicagarson will walk you through BLAH2 everything you need to know in APIs 101 session. She’ll use examples using our v2 endpoints, Tuesday, March 23rd at 1 pm EST.\\n\\nJoin us on Twitch\\nhttps://t.co/GrtBOXyHmB\"},{\"created_at\":\"2021-03-18T19:15:37.000Z\",\"id\":\"1372627771717869568\",\"text\":\"Thanks to everyone who joined and made today a great session! \\uD83D\\uDE4C \\n\\nIf weren't able to attend, we've got you covered. Academic researchers can now sign up for office hours for help using the new product track. See how you can sign up, here \\uD83D\\uDC47\\nhttps://t.co/duIkd27lPx https://t.co/AP9YY4F8FG\"},{\"created_at\":\"2021-03-18T19:07:02.000Z\",\"id\":\"1372625612460810242\",\"text\":\"@geet_qcp @suhemparack Hey! We have good news, we just introduced Academic Research office hours. Hopefully, this lets you sign up for a day/time where you have flexibility:  https://t.co/duIkd27lPx\\n\\nWe do also have a resources page, which includes \\n code samples shared today https://t.co/0SfXa84EDO\"},{\"created_at\":\"2021-03-18T16:50:09.000Z\",\"id\":\"1372591165006999556\",\"text\":\"We're going to get started in just a few minutes! Hope to see you soon on https://t.co/TDK0i7zTpK \\uD83D\\uDC40 https://t.co/AP9YY4F8FG\"},{\"created_at\":\"2021-03-18T15:50:07.000Z\",\"id\":\"1372576056020901893\",\"text\":\"The team met at UC Berkeley. “We made countless nonsensical projects at hackathons, but we soon realized the Twitter API offers us a tangible opportunity to make an impact with the projects we’re building.”\"}],\"meta\":{\"oldest_id\":\"1372576056020901893\",\"newest_id\":\"1376612438313852930\",\"result_count\":10,\"next_token\":\"7140dibdnow9c7btw3w3xyy7cigubrxfrxa40uwo3kbar\"}}";
        UserTimeline timeline = deserializeUsertimeline(tweetResponse);
        timeline.setAuthor_id(userId);
        influencerTimelines.put(userId, timeline);
        return timeline;
    }

    public static String GetUserId(String username) throws IOException, URISyntaxException {
        String idResponse = null;
        String id = null;
        if (influencerIds.containsKey(username)) {
            id = influencerIds.get(username);
            return id;
        }
//        HttpClient httpClient = HttpClients.custom()
//                .setDefaultRequestConfig(RequestConfig.custom()
//                        .setCookieSpec(CookieSpecs.STANDARD).build())
//                .build();
//
//        URIBuilder uriBuilder = new URIBuilder(String.format("https://api.twitter.com/2/users/by/username/%s", username));
//        
//        HttpGet httpGet = new HttpGet(uriBuilder.build());
//        httpGet.setHeader("Authorization", String.format("Bearer %s", BEARER_TOKEN));
//        httpGet.setHeader("Content-Type", "application/json");
//        
//        HttpResponse response = httpClient.execute(httpGet);
//        HttpEntity entity = response.getEntity();
//        if (null != entity) {
//            idResponse = EntityUtils.toString(entity, "UTF-8");
//        }
        idResponse = "{\"data\":{\"id\":\"44196397\",\"name\":\"Elon Musk\",\"username\":\"elonmusk\"}}";
        ObjectNode node = new ObjectMapper().readValue(idResponse, ObjectNode.class);
        id = node.get("data").get("id").asText();
        return id;
    }

    public static void GetUserIds() throws IOException, URISyntaxException {
        String idResponse = null;
        String id[] = null;
        String[] usernames = influencerIds.keySet().toArray(new String[influencerIds.size()]);
//        String usernameCSV = "";
//        for (String username : usernames) {
//           usernameCSV = usernameCSV + "," + username;
//        }
//        usernameCSV = usernameCSV.substring(1, usernameCSV.length());
//        HttpClient httpClient = HttpClients.custom()
//                .setDefaultRequestConfig(RequestConfig.custom()
//                        .setCookieSpec(CookieSpecs.STANDARD).build())
//                .build();
//
//        URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/users/by");
//        ArrayList<NameValuePair> queryParameters = new ArrayList<>();
//        queryParameters.add(new BasicNameValuePair("usernames", usernameCSV));
//        uriBuilder.addParameters(queryParameters);
//        
//        HttpGet httpGet = new HttpGet(uriBuilder.build());
//        httpGet.setHeader("Authorization", String.format("Bearer %s", BEARER_TOKEN));
//        httpGet.setHeader("Content-Type", "application/json");
//
//        HttpResponse response = httpClient.execute(httpGet);
//        HttpEntity entity = response.getEntity();
//        if (null != entity) {
//            idResponse = EntityUtils.toString(entity, "UTF-8");
//        }
        idResponse = "{\"data\":[{\"id\":\"44196397\",\"name\":\"Elon Musk\",\"username\":\"elonmusk\"},{\"id\":\"50393960\",\"name\":\"Bill Gates\",\"username\":\"BillGates\"}]}";
        ObjectNode node = new ObjectMapper().readValue(idResponse, ObjectNode.class);
        for (String username: usernames) {
            influencerIds.put(username, node.get("data").get(username).get("id").asText());
        }
    }
    
    public static void saveAllUserMentions(String path) throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter(path));
        TreeMap temp = (TreeMap) influencerTimelines.clone();
        output.append("{\n");
        while (!temp.isEmpty()) {
            Entry entry = temp.pollFirstEntry();
            output.print('\"' + (String) entry.getKey() + "\":\"" + ((UserTimeline)entry.getValue()).userStockMentionsToJson() + "\"\n");
        }
        output.append('}');
        output.close();
    }
    
    public static void loadAllUserMentions(String path) throws FileNotFoundException, JsonProcessingException {
        Scanner fileScnr = new Scanner(new File(path));
        String json = "";
        while(fileScnr.hasNextLine()) {
            json += fileScnr.nextLine();
        }
        TypeReference<TreeMap<String, String>> typeRef = new TypeReference<TreeMap<String, String>>() {};
        TreeMap<String, String> allMentions = new ObjectMapper().readValue(json, typeRef);
        while (!allMentions.isEmpty()) {
            Entry entry = allMentions.pollFirstEntry();
            influencerTimelines.get((String)entry.getKey()).loadUserStockMentions((String)entry.getValue());
        }
    }
    
    public static void loadKeywords(String path) throws FileNotFoundException, JsonProcessingException {
        Scanner fileScnr = new Scanner(new File(path));
        String json = "";
        while(fileScnr.hasNextLine()) {
            json += fileScnr.nextLine();
        }
        TypeReference<TreeMap<String, String>> typeRef = new TypeReference<TreeMap<String, String>>() {};
        keywords = new ObjectMapper().readValue(json, typeRef);
    }
    
    public static void saveKeywords(String path) throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter(path));
        TreeMap temp = (TreeMap) keywords.clone();
        output.append("{\n");
        while (!temp.isEmpty()) {
            Entry entry = temp.pollFirstEntry();
            output.print('\"' + (String) entry.getKey() + "\":\"" + (String) entry.getValue() + "\"\n");
        }
        output.append('}');
        output.close();
    }
    
    public static void addKeyword(String key, String value) {
        keywords.put(key, value);
    }

    public static TreeMap<String, String> getKeywords() {
        return keywords;
    }

    public static void setKeywords(TreeMap<String, String> keywords) {
        TwitterBot.keywords = keywords;
    }
}
