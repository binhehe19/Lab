/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Counter;
import View.CounterView;
import java.util.Map;
import java.util.Scanner;




/**
 *
 * @author NGOC ANH
 */
public class CounterControll {
    private Counter model;
    private CounterView view;

    public CounterControll(Counter model, CounterView view) {
        this.model = model;
        this.view = view;
    }
    
    public void displayInput(){
        Scanner sc =  new Scanner(System.in);
        view.displayRequest();
        String ip = sc.nextLine();
        Map<String, Integer> wordCount = model.countWord(ip);
        view.displayWordCount(wordCount);
        Map<Character, Integer> letterCount = model.countLetter(ip);
        view.displayLetterCount(letterCount);
    }
    
}
