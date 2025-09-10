/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;




/**
 *
 * @author NGOC ANH
 */
public class Counter {
    public Map<String,Integer> countWord(String ip){
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        StringTokenizer tkn = new StringTokenizer(ip);
        ip = ip.replaceAll("[^a-zA-Z]", "").toLowerCase();
        while(tkn.hasMoreTokens()){
            String word = tkn.nextToken();
            wordCount.put(word, wordCount.getOrDefault(word, 0)+1);
        }
        return wordCount;
    }
    public Map<Character, Integer> countLetter(String ip){
        Map<Character, Integer> letterCount = new LinkedHashMap<>();
        ip = ip.replaceAll("[^a-zA-Z]", "").toLowerCase();
        for(int i =0; i<ip.length();i++){
            char c = ip.charAt(i);
            letterCount.put(c, letterCount.getOrDefault(c, 0)+1);
        }
        return letterCount;
    }
}
