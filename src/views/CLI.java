package views;

import controllers.UserController;
import controllers.ComplaintManager;
import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        // Initialize controllers
        UserController userController = new UserController();
        ComplaintManager complaintManager = new ComplaintManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("------Complaint Reporting System---------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int userId = -1;  // Placeholder for user ID

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

            // After registration, the user can file a complaint
            userId = userController.getUserIdByEmail(email); // Fetch the user ID based on email (assumed method)

        } else if (choice == 2) {
            // Login user
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (userController.loginUser(email, password) != null) {
                System.out.println("Login successful!");
                userId = userController.getUserIdByEmail(email); // Fetch user ID after successful login
            } else {
                System.out.println("Invalid credentials.");
                scanner.close();
                return;  // Exit if login fails
            }
        } else {
            System.out.println("Invalid choice. Exiting...");
            scanner.close();
            return;
        }

        // After successful registration/login, give options to file complaints
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n------Complaint Options---------");
            System.out.println("1. File a Complaint");
            System.out.println("2. View Complaints");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int actionChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (actionChoice) {
                case 1:
                    // File a complaint
                    System.out.print("Enter complaint category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter complaint description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter location of the complaint: ");
                    String location = scanner.nextLine();

                    complaintManager.addComplaint(userId, category, description, location);
                    System.out.println("Complaint filed successfully!");
                    break;

                case 2:
                    // View complaints (simple demonstration)
                    System.out.println("1. View complaints by status");
                    System.out.println("2. View complaints by category");
                    System.out.println("3. View complaints by user ID");
                    System.out.println("4. View complaints by location");
                    System.out.print("Enter your choice: ");
                    int viewChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (viewChoice == 1) {
                        System.out.print("Enter status (Pending, In Progress, Resolved): ");
                        String status = scanner.nextLine();
                        complaintManager.getComplaintsByStatus(status).forEach(c -> {
                            System.out.println("Complaint ID: " + c.getComplaintId() + ", Category: " + c.getCategory() + ", Status: " + c.getStatus());
                        });

                    } else if (viewChoice == 2) {
                        System.out.print("Enter category: ");
                        String complaintCategory = scanner.nextLine();
                        complaintManager.getComplaintsByCategory(complaintCategory).forEach(c -> {
                            System.out.println("Complaint ID: " + c.getComplaintId() + ", Description: " + c.getDescription());
                        });

                    } else if (viewChoice == 3) {
                        complaintManager.getComplaintsByUserId(userId).forEach(c -> {
                            System.out.println("Complaint ID: " + c.getComplaintId() + ", Category: " + c.getCategory());
                        });

                    } else if (viewChoice == 4) {
                        System.out.print("Enter location: ");
                        String complaintLocation = scanner.nextLine();
                        complaintManager.getComplaintsByLocation(complaintLocation).forEach(c -> {
                            System.out.println("Complaint ID: " + c.getComplaintId() + ", Location: " + c.getLocation());
                        });
                    }
                    break;

                case 3:
                    // Exit program
                    keepRunning = false;
                    System.out.println("We'll update you on the status of your complaint once fowarded to the concerned department.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close(); // Close scanner at the end
    }
}
