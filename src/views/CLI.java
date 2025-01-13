package views;

import controllers.UserController;
import controllers.ComplaintManager;
import models.User; // Import User model for role checking
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

        User loggedInUser = null; // To store the logged-in user object

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

            System.out.print("Enter your role (User/Admin): "); // Prompt for role
            String role = scanner.nextLine();

            userController.registerUser(name, email, password, address, role); // Add user with role
            System.out.println("User registered successfully!");

            loggedInUser = userController.loginUser(email, password); // Automatically log in the user
        } else if (choice == 2) {
            // Login user
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            loggedInUser = userController.loginUser(email, password); // Attempt login

            if (loggedInUser != null) {
                System.out.println("Login successful! Welcome, " + loggedInUser.getName());
            } else {
                System.out.println("Invalid credentials. Exiting...");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid choice. Exiting...");
            scanner.close();
            return;
        }

        // Role-based menu after login
        boolean keepRunning = true;

        while (keepRunning) {
            if (loggedInUser.getRole().equalsIgnoreCase("Admin")) {
                // Admin menu
                System.out.println("\n------Admin Menu---------");
                System.out.println("1. View All Complaints");
                System.out.println("2. Update Complaint Status");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                int adminChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (adminChoice) {
                    case 1:
                        // View all complaints
                        System.out.println("All Complaints:");
                        complaintManager.getComplaintsByStatus("Pending").forEach(c -> {
                            System.out.println("ID: " + c.getComplaintId() + ", Category: " + c.getCategory() +
                                    ", Status: " + c.getStatus() + ", Location: " + c.getLocation());
                        });
                        break;

                    case 2:
                        // Update complaint status
                        System.out.print("Enter Complaint ID: ");
                        int complaintId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter new status (Pending, In Progress, Resolved): ");
                        String newStatus = scanner.nextLine();

                        if (complaintManager.updateComplaintStatus(complaintId, newStatus)) {
                            System.out.println("Complaint status updated successfully!");
                        } else {
                            System.out.println("Invalid complaint ID.");
                        }
                        break;

                    case 3:
                        // Logout
                        keepRunning = false;
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else {
                // Regular user menu
                System.out.println("\n------User Menu---------");
                System.out.println("1. File a Complaint");
                System.out.println("2. View My Complaints");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (userChoice) {
                    case 1:
                        // File a complaint
                        System.out.print("Enter complaint category: ");
                        String category = scanner.nextLine();

                        System.out.print("Enter complaint description: ");
                        String description = scanner.nextLine();

                        System.out.print("Enter location of the complaint: ");
                        String location = scanner.nextLine();

                        complaintManager.addComplaint(loggedInUser.getUserId(), category, description, location);
                        System.out.println("Complaint filed successfully!");
                        break;

                    case 2:
                        // View user complaints
                        System.out.println("Your Complaints:");
                        complaintManager.getComplaintsByUserId(loggedInUser.getUserId()).forEach(c -> {
                            System.out.println("ID: " + c.getComplaintId() + ", Category: " + c.getCategory() +
                                    ", Status: " + c.getStatus());
                        });
                        break;

                    case 3:
                        // Logout
                        keepRunning = false;
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }

        scanner.close(); // Close scanner at the end
    }
}
