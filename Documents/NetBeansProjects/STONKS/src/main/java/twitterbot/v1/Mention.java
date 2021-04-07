/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbot.v1;

/**
 *
 * @author Administrator
 */
public class Mention {
    public final String timeStamp;
    public final String ticker;

    public Mention(String timeStamp, String ticker) {
        this.timeStamp = timeStamp;
        this.ticker = ticker;
    }
    
    public String toString(){
        return "{\"timeStamp\":\""+ this.timeStamp + "\",\"ticker\":\"" + this.ticker + "\"}";
    }
}
