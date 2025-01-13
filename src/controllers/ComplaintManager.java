package controllers;

import models.Complaint;
import java.util.ArrayList;
import java.util.List;

public class ComplaintManager {
    private static List<Complaint> complaints = new ArrayList<>(); // List to store complaints
    private static int complaintIdCounter = 1;

    // Add a new complaint
    public void addComplaint(int userId, String category, String description, String location) {
        Complaint complaint = new Complaint(complaintIdCounter++, userId, category, description, location);
        complaints.add(complaint);
    }

    // Retrieve complaints filtered by status
    public List<Complaint> getComplaintsByStatus(String status) {
        List<Complaint> filtered = new ArrayList<>();
        for (Complaint c : complaints) {
            if (c.getStatus().equalsIgnoreCase(status)) {
                filtered.add(c);
            }
        }
        return filtered;
    }

    // Retrieve complaints filtered by category
    public List<Complaint> getComplaintsByCategory(String category) {
        List<Complaint> filtered = new ArrayList<>();
        for (Complaint c : complaints) {
            if (c.getCategory().equalsIgnoreCase(category)) {
                filtered.add(c);
            }
        }
        return filtered;
    }

    // Retrieve complaints filtered by user ID
    public List<Complaint> getComplaintsByUserId(int userId) {
        List<Complaint> filtered = new ArrayList<>();
        for (Complaint c : complaints) {
            if (c.getUserId() == userId) {
                filtered.add(c);
            }
        }
        return filtered;
    }

    // Retrieve complaints filtered by location
    public List<Complaint> getComplaintsByLocation(String location) {
        List<Complaint> filtered = new ArrayList<>();
        for (Complaint c : complaints) {
            if (c.getLocation().equalsIgnoreCase(location)) {
                filtered.add(c);
            }
        }
        return filtered;
    }

    // Retrieve a specific complaint by ID
    public Complaint getComplaintById(int complaintId) {
        for (Complaint c : complaints) {
            if (c.getComplaintId() == complaintId) {
                return c;
            }
        }
        return null; 
    }

    // Update the status of a complaint
    // Update complaint status
    public boolean updateComplaintStatus(int complaintId, String newStatus) {
        for (Complaint complaint : complaints) {
            if (complaint.getComplaintId() == complaintId) {
                complaint.setStatus(newStatus);  // Update the status
                return true;
            }
        }
        return false;  // Return false if no complaint with the given ID
    }
}
