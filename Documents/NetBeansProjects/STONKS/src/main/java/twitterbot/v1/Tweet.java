/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbot.v1;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Scanner;
//
///**
// *
// * @author Administrator
// */
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class Tweet {
//
//    /**
//     * Time Stamp
//     */
//    private Date created_at;
//
//    /**
//     * tweet id
//     */
//    private String Id;
//
////    /**
////     * Username
////     */
////    private String author_id;
//    /**
//     * Tweet contents
//     */
//    private String text;
//
//    public Tweet() {
//        super();
//    }
//
//    public Tweet(Date timeStamp, String tweetId, String tweetContent) {
//        this.created_at = timeStamp;
//        this.Id = tweetId;
//        this.text = tweetContent;
//    }
//
////    /**
////     * Retweets
////     */
////    private int retweet_count;
////    
////    /**
////     * number of comments
////     */
////    private int reply_count;
////    
////    /**
////     * Tokenized tweet
////     */
////    private String[] tweetTokens;  
////    
////    /**
////    *  file path for saveToFile method
////    */
////    private String filePath = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\STONKS V1\\Tweets\\tweet.json";
////    
////    /**
////     * 
////     * @param timeStamp
////     * @param handle
////     * @param username
////     * @param tweetContent
////     * @param retweets
////     * @param numberOfComments 
////     */
////    public Tweet(Date timeStamp, String tweetId, String username, String tweetContent, int retweets, int numberOfComments) {
////        this.created_at = timeStamp;
////        this.Id = tweetId;
////        this.author_id = username;
////        this.text = tweetContent;
////        this.retweet_count = retweets;
////        this.reply_count = numberOfComments;
////        tokenizeTweet();
////    }
////    
////    /**
////     * 
////     * @param timeStamp
////     * @param handle
////     * @param username
////     * @param tweetContent 
////     */
////    public Tweet(Date timeStamp, String tweetId, String username, String tweetContent) {
////        this.created_at = timeStamp;
////        this.Id = tweetId;
////        this.author_id = username;
////        this.text = tweetContent;
////        this.retweet_count = -1;
////        this.reply_count = -1;
////        tokenizeTweet();
////    }
////    
////    /**
////     * Tokenizes the tweet
////     */
////    private void tokenizeTweet() {
////        ArrayList<String> stringArrayBuffer = new ArrayList<>();
////        Scanner tweetScanner = new Scanner(text);
////        while (tweetScanner.hasNext()) {
////            stringArrayBuffer.add(tweetScanner.next());
////        }
////        this.tweetTokens = new String[stringArrayBuffer.size()];
////        for (int i = 0; i < tweetTokens.length; i++) {
////            this.tweetTokens[i] = stringArrayBuffer.get(i);
////        }
////    }
////    
////    /**
////     * 
////     * @return created_at
////     */
////    public Date getTimeStamp() {
////        return this.created_at;
////    }
////    
////    /**
////     * 
////     * @return author_id
////     */
////    public String getUsername() {
////        return this.author_id;
////    }
////    
////    /**
////     * 
////     * @return text
////     */
////    public String getTweetContent() {
////        return this.text;
////    }
////    
////    /**
////     * 
////     * @return retweets
////     */
////    public int getRetweets() {
////        return this.retweet_count;
////    }
////    
////    /**
////     * 
////     * @return reply_count
////     */
////    public int getNumberOfComments() {
////        return this.reply_count;
////    }
////    
////    /**
////     * 
////     * @return tweetTokens
////     */
////    public String[] getTweetTokens() {
////        return this.tweetTokens;
////    }
////    
////    /**
////     * 
////     * @return returns string representation of Tweet object
////     */
////    @Override
////    public String toString() {
////        return "time: " + this.created_at + "\nuserename: " + this.author_id + "\n" + this.text + "\nretweets: " + this.retweet_count + "\nnumber of comments: " + this.reply_count + "\n";
////    }
//    @Override
//    public String toString() {
//        return "time: " + this.created_at + " tweet id: " + this.Id + "\n" + this.text + "\n\n";
//    }
////    
////    /**
////    * Saves a single tweet to file
////    */
////    public void saveToFile() {
////        
////    }
//}
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.Scanner;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "created_at",
    "id",
    "text"
})
@Generated("jsonschema2pojo")
public class Tweet implements Serializable {

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("id")
    private String id;
    @JsonProperty("text")
    private String text;
    private final static long serialVersionUID = -8712940487655622243L;
    
    private String author_id;
    
    private String[] tweetTokens;
    
    /**
     * No args constructor for use in serialization
     *
     */
    public Tweet() {
    }

    /**
     *
     * @param createdAt
     * @param id
     * @param text
     */
    public Tweet(String createdAt, String id, String text) {
        super();
        this.createdAt = createdAt;
        this.id = id;
        this.text = text;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }
    
    
    public String getAuthor_id() {
        return author_id;
    }
    
    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }
    
    public String[] getTweetTokens() {
        return tweetTokens;
    }
    public void tokenizeTweet() {
        ArrayList<String> stringArrayBuffer = new ArrayList<>();
        Scanner tweetScanner = new Scanner(text);
        while (tweetScanner.hasNext()) {
            stringArrayBuffer.add(tweetScanner.next());
        }
        this.tweetTokens = new String[stringArrayBuffer.size()];
        for (int i = 0; i < tweetTokens.length; i++) {
            this.tweetTokens[i] = stringArrayBuffer.get(i);
        }
    }

    @Override
    public String toString() {
        return "{\"createdAt\":\"" + this.createdAt + "\",\"id\":\"" + this.id + "\",\"text\":\"" +  this.text + "\"}";
    }

}