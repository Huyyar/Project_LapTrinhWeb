package vn.edu.hcmuaf.fit.project_ltweb.model;
import java.sql.Timestamp;

public class Order {
    private int id;
    private String order_code;
    private int user_id;
    private int coupon_id;
    private String full_name;
    private String phone;
    private String email;
    private String shipping_address;
    private String shipping_method;
    private double shipping_fee;
    private String payment_method;
    private String notes;
    private double total_amount;
    private double discount_amount;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Order() {}

    // Getters và Setters...
}