/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Course;

import java.util.List;

/**
 *
 * @author admin
 */
public class Book {
    private String id;
    private String author;
    private String title;
    private double price;

    public Book(String id, String author, String title, double price) {
        if(id ==  null|| id.isEmpty() || author.isEmpty() || author == null || title == null || title.isEmpty()|| price < 0){
            throw new IllegalAccessError();
        }
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public Book() {
      
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
