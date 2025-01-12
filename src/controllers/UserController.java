package controllers;

import models.User;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static List<User> users = new ArrayList<>(); // List to store registered users
    private static int userIdCounter = 1;

    // Method to register a new user
    public void registerUser(String name, String email, String password, String address) {
        User user = new User(userIdCounter++, name, email, password, address);
        users.add(user);
    }

    // Method to handle user login
    public User loginUser(String email, String password) {
        for (User user : users) {
            if (user.login(email, password)) { // Use the `login` method from the User class
                return user; // Return the user if credentials match
            }
        }
        return null; // Return null if no matching user is found
    }
}
