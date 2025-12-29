package vn.edu.hcmuaf.fit.project_ltweb.model;

import java.sql.Timestamp;

public class Contact {

    private int id;
    private String fullName;
    private String email;
    private String message;
    private Timestamp createdAt;

    public Contact() {}

    public Contact(String fullName, String email, String message) {
        this.fullName = fullName;
        this.email = email;
        this.message = message;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
