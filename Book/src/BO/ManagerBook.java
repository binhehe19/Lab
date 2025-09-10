/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import Course.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author admin
 */
public class ManagerBook {
    private ArrayList<Book> books = new ArrayList<>();
    public boolean addBook(Book book) throws Exception{
        for(Book b : books){
            if(b.getId()==book.getId()){
                throw new Exception("Input ID exits.");
            }
        }books.add(book);
        return true;
    }
    public boolean updateBook( String id, String author, String title, double price) throws Exception{
        for(Book s : books ){
            if(s.getId().equalsIgnoreCase(id)){
                if(!author.isEmpty()){
                    s.setAuthor(author);
                }
                if(!title.isEmpty()){
                    s.setTitle(title);
                }
                if(price > 0){
                    s.setPrice(price);
                }
                return true;
            }
        }
        throw new Exception("Not found");
    }
    public  boolean isEmpty() {
    // Assuming books is the list that holds Book objects
        return books.isEmpty();
}
   public List<Book> getBooks() {
        return books;
    }
    public boolean deleBook( String id) throws Exception{
        for(Book s : books){
            if(s.getId().equalsIgnoreCase(id)){
                books.remove(s);
                return true;
            }
        }
        throw new Exception("Not found");
    }
    public List<Book> searchBookbyTitle(String title) throws Exception{
        List<Book> matchedBooks = new ArrayList<>();
    for (Book s : books) {
        if (s.getTitle().toLowerCase().contains(title.toLowerCase())) {
            matchedBooks.add(s);
        }
    }
    return matchedBooks;
    }
    public Book searchBookbyId(String id){
        for(Book s : books ){
            if(s.getId().equalsIgnoreCase(id)){
                return s;
            }
        }
        return null;
    }
    public boolean sortBookbyTitle(){
        if(books.isEmpty()){
            System.out.println("Not Found");
            return false;
        }
        
            Collections.sort(books, new Comparator<Book>(){
                @Override
                
                public int compare(Book b1, Book b2){
                    return b1.getTitle().compareToIgnoreCase(b2.getTitle());
                }
            });
        
        return true;
    }

   
}
