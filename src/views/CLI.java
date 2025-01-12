package views;

import controllers.UserController;
import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        //// Initialize controller
        UserController userController = new UserController(); 
        Scanner scanner = new Scanner(System.in);

        System.out.println("------Complaint Reporting System---------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice == 1) {
            
            // Register user
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your address: ");
            String address = scanner.nextLine();

            userController.registerUser(name, email, password, address); // Add user
            System.out.println("User registered successfully!");
        } else if (choice == 2) {
            
            // Login user
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (userController.loginUser(email, password) != null) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid credentials.");
            }
            scanner.close();
        }
    }
}
