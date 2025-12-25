package vn.edu.hcmuaf.fit.project_ltweb.model;
import java.sql.Timestamp;

public class User {
    private int id;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private String gender;
    private Timestamp birthdate;
    private String avatar_url;
    private String role;
    private boolean is_active;
    private Timestamp created_at;

    public User() {}

    public User(String email, String password, String fullname, String avatar_url) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.avatar_url = avatar_url;
    }

    public User(int id, String email, String password, String fullname, String avatar_url, String role, boolean is_active, Timestamp created_at) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.avatar_url = avatar_url;
        this.role = role;
        this.is_active = is_active;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}