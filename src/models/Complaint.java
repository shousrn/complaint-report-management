package models;

public class Complaint {
    // Define valid status values as constants
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_IN_PROGRESS = "In Progress";
    public static final String STATUS_RESOLVED = "Resolved";

    private int complaintId;
    private int userId;
    private String category;
    private String description;
    private String location;
    private String status;

    // Constructor
    public Complaint(int complaintId, int userId, String category, String location, String description) {
        this.complaintId = complaintId;
        this.userId = userId;
        this.category = category;
        this.description = description;
        this.location = location;
        this.status = STATUS_PENDING; 
    }

    // Getters and setters
    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Method to update the status with validation
    public void updateStatus(String newStatus) {
        if (newStatus.equals(STATUS_PENDING) || newStatus.equals(STATUS_IN_PROGRESS) || newStatus.equals(STATUS_RESOLVED)) {
            this.status = newStatus;
        } else {
            throw new IllegalArgumentException("Invalid status: " + newStatus);
        }
    }
}
