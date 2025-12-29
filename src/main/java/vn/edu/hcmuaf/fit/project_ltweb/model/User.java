package vn.edu.hcmuaf.fit.project_ltweb.model;
import java.sql.Date;
import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private String gender;
    private Date birthdate;
    private String avatar_url;
    private String role;
    private boolean is_active;
    private Date created_at;

    public User() {}

    public User(String email, String password, String fullname, String avatar_url) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.avatar_url = avatar_url;
    }

    public User(int id, String email, String password, String fullname, String phone, String gender, Date birthdate, String avatar_url, String role, boolean is_active, Date created_at) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.avatar_url = avatar_url;
        this.role = role;
        this.is_active = is_active;
        this.created_at = created_at;
    }

    public User(int id , String email, String fullname, String phone, Date birthdate, String gender) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public User(int id, String email, String password, String fullname, String avatar_url, String role, boolean is_active, Date created_at) {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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

    public boolean isIs_active(){

        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}