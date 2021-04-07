/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbot.v1;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
///**
// *
// * @author Administrator
// */
//@JsonIgnoreProperties(ignoreUnknown = true)
//class Meta {
//    public String oldest_id;
//    public String newest_id;
//    public int result_count;
//    public String next_token;
//    
//    public Meta() {
//        super();
//    }
//    
//    public Meta(String oldest_id, String newest_id, int result_count, String next_token) {
//        this.oldest_id = oldest_id;
//        this.newest_id = newest_id;
//        this.result_count = result_count;
//        this.next_token = next_token;
//    }
//}
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "oldest_id",
    "newest_id",
    "result_count",
    "next_token"
})
@Generated("jsonschema2pojo")
public class Meta implements Serializable {

    @JsonProperty("oldest_id")
    private String oldestId;
    @JsonProperty("newest_id")
    private String newestId;
    @JsonProperty("result_count")
    private int resultCount;
    @JsonProperty("next_token")
    private String nextToken;
    private final static long serialVersionUID = 4858363746759018030L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Meta() {
    }

    /**
     *
     * @param resultCount
     * @param nextToken
     * @param oldestId
     * @param newestId
     */
    public Meta(String oldestId, String newestId, int resultCount, String nextToken) {
        super();
        this.oldestId = oldestId;
        this.newestId = newestId;
        this.resultCount = resultCount;
        this.nextToken = nextToken;
    }

    @JsonProperty("oldest_id")
    public String getOldestId() {
        return oldestId;
    }

    @JsonProperty("oldest_id")
    public void setOldestId(String oldestId) {
        this.oldestId = oldestId;
    }

    @JsonProperty("newest_id")
    public String getNewestId() {
        return newestId;
    }

    @JsonProperty("newest_id")
    public void setNewestId(String newestId) {
        this.newestId = newestId;
    }

    @JsonProperty("result_count")
    public int getResultCount() {
        return resultCount;
    }

    @JsonProperty("result_count")
    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    @JsonProperty("next_token")
    public String getNextToken() {
        return nextToken;
    }

    @JsonProperty("next_token")
    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Meta.class.getName()).append('{');
        sb.append("\noldestId:");
        sb.append(((this.oldestId == null) ? "<null>" : this.oldestId));
        sb.append("\nnewestId:");
        sb.append(((this.newestId == null) ? "<null>" : this.newestId));
        sb.append("\nresultCount:");
        sb.append(this.resultCount);
        sb.append("\nnextToken:");
        sb.append(((this.nextToken == null) ? "<null>" : this.nextToken));
        sb.append("\n}\n");
        return sb.toString();
    }

}
