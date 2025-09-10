/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controller.CounterControll;
import Model.Counter;
import View.CounterView;



/**
 *
 * @author NGOC ANH
 */
public class Main {
    public static void main(String[] args) {
    Counter model = new Counter();
    CounterView view =  new CounterView();
    CounterControll controller = new CounterControll(model,view);
    controller.displayInput();
    }
    
}
