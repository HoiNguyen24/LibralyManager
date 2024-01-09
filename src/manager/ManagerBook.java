package manager;

import model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerBook {
    ArrayList<Book> books = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addBook(Book book) {
        books.add(book);
        writeBook();
    }

    public void display() {
        for (Book b:books) {
            System.out.println(b);
        }
    }

    public void editBook(int index, Book book) {
        book.setId(books.get(index).getId());
        books.set(index, book);
    }

    public void deleteBook(int index) {
        books.remove(index);
    }

    public Book create() {
        String name = validateName();
        System.out.println("Nhập thể loại");
        String category = scanner.nextLine();
        System.out.println("nhập tác giả");
        String author = scanner.nextLine();
        return new Book(name, category, author);
    }

    public String validateName() {
        while (true) {
            System.out.println("Nhập name");
            String name = scanner.nextLine();
            if (checkName(name)) {
                return name;
            } else {
                System.err.println("trùng tên rồi");
            }
        }
    }

    public boolean checkName(String name) {
        for (Book b : books) {
            if (b.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Book> findAllByName(String name) {
        ArrayList<Book> findBook = new ArrayList<>();
        for (Book b : books) {
            if (b.getName().contains(name)) {
                findBook.add(b);
            }
        }
        return findBook;
    }

    public int findIndexByName(String name) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
    public void readBook(){
        try {
            File fileBook = new File("books.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileBook));
            String str = null;
            while((str = bufferedReader.readLine()) != null){
                String[] arr = str.split(",");
                addBook(new Book(arr[0],arr[1],arr[2]));
            }
        }catch (Exception e){
            System.out.println("Lỗi đọc file books");
        }
    }
    public void writeBook(){
        try{
            File fileBook = new File("books.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileBook));
            for(Book book: this.books){
                bufferedWriter.write(book.getName()+","+book.getCategory()+","+book.getAuthor());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi nhập sách");
        }
    }
}
