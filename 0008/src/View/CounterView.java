/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.Map;


/**
 *
 * @author NGOC ANH
 */
public class CounterView {
    public void displayRequest(){
        System.out.println("Enter your content:");
    }
    public void displayWordCount(Map<String,Integer> wordCount){
        System.out.println(wordCount);
    }
    public void displayLetterCount(Map<Character, Integer> letterCount){
        System.out.println(letterCount);
    }
}
