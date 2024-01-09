import manager.ManagerAccount;
import manager.ManagerBook;
import model.Account;
import model.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class MainRun {
    static Scanner scanner = new Scanner(System.in);
    static ManagerAccount managerAccount = new ManagerAccount();
    static ManagerBook managerBook = new ManagerBook();

    public static void main(String[] args) {
        managerAccount.readRole();
        managerAccount.readAccount();
        managerBook.readBook();
        while (true) {
            System.out.println("Xin chào");
            System.out.println("1. Login");
            System.out.println("2. Register");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    managerAccount.create();
                    break;
                default:
                    System.err.println("Nhập sai");
            }
        }
    }

    public static void login() {
        System.out.println("Nhập username");
        String username = scanner.nextLine();
        System.out.println("Nhập password");
        String password = scanner.nextLine();
        Account account = managerAccount.checkLogin(username, password);
        if (account != null) {
            if (account.getRole().getName().equals("Admin")) {
                menuAdmin();
            } else {
                menuUser();
            }
        } else {
            System.err.println("Bạn đã đăng nhập lỗi");
        }
    }


    public static void menuUser() {
        while (true) {
            System.out.println("Chào mừng User");
            System.out.println("1. Hiển thi sách");
            System.out.println("2. Tìm kiếm sách theo tên");
            System.out.println("3. logout");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    managerBook.display();
                    break;
                case 2:
                    System.out.println("Nhập name cần tìm");
                    String name = scanner.nextLine();
                    ArrayList<Book> books = managerBook.findAllByName(name);
                    for (Book b : books) {
                        System.out.println(b);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Nhập sai");
            }
        }
    }

    public static void menuAdmin() {
        while (true) {
            System.out.println("Chào mừng Admin");
            System.out.println("1. Hiển thi sách");
            System.out.println("2. Thêm sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Sửa sách");
            System.out.println("5. Tìm kiếm sách theo tên");
            System.out.println("6. logout");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    managerBook.display();
                    break;
                case 2:
                    managerBook.addBook(managerBook.create());
                    break;
                case 3:
                    System.out.println("Nhập name cần xóa");
                    String nameDelete = scanner.nextLine();
                    managerBook.deleteBook(managerBook.findIndexByName(nameDelete));
                    break;
                case 4:
                    System.out.println("Nhập name cần sửa");
                    String nameEdit = scanner.nextLine();
                    int index = managerBook.findIndexByName(nameEdit);
                    Book book = managerBook.create();
                    managerBook.editBook(index, book);
                    break;
                case 5:
                    System.out.println("Nhập name cần sửa");
                    String nameFind = scanner.nextLine();
                    ArrayList<Book> books = managerBook.findAllByName(nameFind);
                    for (Book b : books) {
                        System.out.println(b);
                    }
                    break;
                case 6:
                    return;
                default:
                    System.err.println("Nhập sai");
            }
        }
    }


}