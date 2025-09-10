/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import Course.Book;
import Validate.Check;
import java.util.List;

/**
 *
 * @author admin
 */
public class AppManagerBook {
    ManagerBook MBook = new ManagerBook();
    Book book = new Book();
    Check in = new Check();
    public void menu() throws Exception{
        int choice;
        do{
            System.out.println("---------Book Manager--------");
            System.out.println("1. Add Book");
            System.out.println("2. Display Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Search Book by title");
            System.out.println("6. Sort Book by title");
            System.out.println("0. Exit");
            System.out.println("------------------------------");
            choice = in.getInt("Enter yuor choice", 0, 6);
            
            switch(choice){
                case 1:
                    addBook();
                    break;
                case 2:
                    disPlay();
                    break;
                case 3:
                    EditBook();
                    break;
                case 4:
                    deleBook();
                    break;
                case 5:
                    searchbyTitle();
                    break;
                case 6:
                    sortBookbyTitle();
                    break;
                case 0:
                    System.out.println("Exiting........");
                    break;
                    default:System.out.println("Invalid number");
            }
        }while(choice != 0);
    }
    private void addBook() throws Exception {
    String id;
    Book book;

    // Keep asking for the book ID until a unique one is provided
    while (true) {
        id = in.getString("Enter book ID: ");
        book = MBook.searchBookbyId(id);
        
        if (book == null) {
            break;  // If the book is not found, exit the loop
        } else {
            System.out.println("Book with ID " + id + " already exists. Please enter a different ID.");
        }
    }

    // Continue with the rest of the input
    String title = in.getString("Enter book title: ");
    String author = in.getString("Enter book author: ");
    double price = in.getDouble("Enter book price: ");

    // Add the new book
    if (MBook.addBook(new Book(id, title, author, price))) {
        System.out.println("Book added successfully!");
    }
}

    public void disPlay() throws Exception {
    if (MBook.isEmpty()) {
        System.out.println("No books available.");
    } else {
        List<Book> books = MBook.getBooks();  // Lấy danh sách sách từ ManagerBook
        displayBooksAsTable(books);  // Hiển thị sách dưới dạng bảng
    }
}

    public void EditBook() throws Exception{
        String id = in.getStringExit("Enter ID: ");
        Book books = MBook.searchBookbyId(id);
        if(books == null){
            System.out.println("Is empty");
            return;
        }
        System.out.println("Editing book: "+ books.getTitle());
        String newauthor = in.getString("New author: ");
        if(newauthor.isEmpty()){
            newauthor =books.getAuthor();
        }
        String newtitle = in.getString("New title: ");
        if(newtitle.isEmpty()){
            newtitle = books.getTitle();
        }
        double newprice = in.getDouble("New price: ");
        if(newprice == 0){
            newprice = books.getPrice();
        }
        if(MBook.updateBook(id, newauthor, newtitle, newprice)){
            System.out.println("Susces");
        }
    }
    public void deleBook() throws Exception{
        String id = in.getString("Enter id: ");
        Book book = MBook.searchBookbyId(id);
        if(book == null){
            System.out.println("Not found");
            return;
        }
        if(MBook.deleBook(id)){
            System.out.println("Suscess");
        }
    }
    public void searchbyTitle() throws Exception{
        String title = in.getString("Enter tilte: ");
        List<Book> books= MBook.searchBookbyTitle(title);
        if(books.isEmpty()){
            System.out.println("Not found");
        }
        else{
            displayBooksAsTable(books);
        }
    }
    public void sortBookbyTitle(){
        if(MBook.sortBookbyTitle()){
            System.out.println("Suscess");
        }else{
            System.out.println("Not found");
        }
    }

public void displayBooksAsTable(List<Book> books) {
    if (books.isEmpty()) {
        System.out.println("No books to display.");
        return;
    }

    // Tiêu đề bảng
    System.out.println("+------+-------------------------+-------------------------+------------+");
    System.out.printf("| %-4s | %-23s | %-23s | %-10s |\n", "ID",  "Author","Title", "Price");
    System.out.println("+------+-------------------------+-------------------------+------------+");

    // Duyệt qua danh sách sách và in ra thông tin theo định dạng bảng
    for (Book book : books) {
        System.out.printf("| %-4s | %-23s | %-23s | %-10.2f |\n", 
            book.getId(),  book.getAuthor(),book.getTitle(), book.getPrice());
    }

    // Đóng bảng
    System.out.println("+------+-------------------------+-------------------------+------------+");
}   
}
