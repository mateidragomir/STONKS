/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbot.v1;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "meta"
})
@Generated("jsonschema2pojo")
public class UserTimeline implements Serializable {

    @JsonProperty("data")
    private List<Tweet> data = null;
    @JsonProperty("meta")
    private Meta meta;
    private final static long serialVersionUID = 1776092399014873923L;
    
    private String author_id;
    
    private ArrayList<Mention> mentions;
    
    /**
     * No args constructor for use in serialization
     *
     */
    public UserTimeline() {
    }

    /**
     *
     * @param data
     * @param meta
     */
    public UserTimeline(List<Tweet> data, Meta meta) {
        super();
        this.data = data;
        this.meta = meta;
    }

    @JsonProperty("data")
    public List<Tweet> getData() {
        return this.data;
    }

    @JsonProperty("data")
    public void setData(List<Tweet> data) {
        this.data = data;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return this.meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    
    @JsonProperty("author_id")
    public String getAuthor_id() {
        return this.author_id;
    }
    @JsonProperty("author_id")
    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }
    
    public void loadUserStockMentionsFromFile(String path) throws FileNotFoundException, JsonProcessingException {
        Scanner fileScnr = new Scanner(new File(path));
        String json = "";
        while(fileScnr.hasNextLine()) {
            json += fileScnr.nextLine();
        }
        TypeReference<ArrayList<Mention>> typeRef = new TypeReference<ArrayList<Mention>>() {};
        this.mentions = new ObjectMapper().readValue(json, typeRef);
    }
    
    public void loadUserStockMentions(String json) throws JsonProcessingException {
        TypeReference<ArrayList<Mention>> typeRef = new TypeReference<ArrayList<Mention>>() {};
        this.mentions = new ObjectMapper().readValue(json, typeRef);
    }
    
    public boolean saveUserStockMentions(String path) throws ParseException, IOException {
        path = "\\" + path + this.author_id + ".json";
        if (this.mentions.isEmpty()) 
            return false;
        PrintWriter output = new PrintWriter(new FileWriter(path));
        output.print("{\"mentions\":" + Arrays.toString(this.mentions.toArray()) + '}');
        System.out.print("{\"mentions\":" + Arrays.toString(this.mentions.toArray()) + ",\"author_id\"" + this.author_id +"\"}");
        output.close();
        return true;
    }
    
    public String userStockMentionsToJson() {
        return "{\"mentions\":" + Arrays.toString(this.mentions.toArray()) + '}';
    }

    public ArrayList<Mention> getMentions() {
        return this.mentions;
    }

    public void setMentions(ArrayList<Mention> mentions) {
        this.mentions = mentions;
    }

    public void constructMentions(TreeMap<String, String> keywords) {
        this.mentions = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++)
                this.data.get(i).tokenizeTweet();
        this.data.forEach((twt) -> {
            for (String wrd: twt.getTweetTokens()) {
                if (keywords.containsKey(wrd)) {
                    mentions.add(new Mention(twt.getCreatedAt(), keywords.get(wrd)));
                }
            }
        });
    }
    
    @Override
    public String toString() {
        String str = "{\"data\":" + Arrays.toString(this.data.toArray());
        if (!this.mentions.isEmpty()) {
            str += "\"mentions\":" + Arrays.toString(this.mentions.toArray()) + ",\"";
        }
        str += "\"author_id\"" + this.author_id +"\"}";
        return str;
    }

}
