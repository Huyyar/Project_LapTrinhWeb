package vn.edu.hcmuaf.fit.project_ltweb.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private int id;
    private String order_code;
    private int user_id ;
    private String full_name;
    private String phone;
    private String email;
    private int address_id;
    private String shipping_method;
    private double shipping_fee;
    private String payment_method;
    private String notes;
    private double total_amount;
    private String status;
    private List<OrderItem> order_items;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Order(String order_code, int user_id, String full_name, String phone, String email, int address_id, String shipping_method, double shipping_fee, String payment_method, String notes, double total_amount, String status) {
        this.order_code = order_code;
        this.user_id = user_id;
        this.full_name = full_name;
        this.phone = phone;
        this.email = email;
        this.address_id = address_id;
        this.shipping_method = shipping_method;
        this.shipping_fee = shipping_fee;
        this.payment_method = payment_method;
        this.notes = notes;
        this.total_amount = total_amount;
        this.status = status;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<OrderItem> order_items) {
        this.order_items = order_items;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
    public String getFormattedCreatedAt() {
        if (this.created_at == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.created_at.format(formatter);
    }
    public String getStatusStr() {
        if (this.status == null) return "";
        if (this.status.equals("processing")) {
            return "Đang xử lý";
        } else if (this.status.equals("delivering")) {
            return "Đang giao";
        } else if (this.status.equals("delivered")) {
            return "Đã giao";
        } else if (this.status.equals("cancelled")) {
            return "Đã hủy";
        } else {
            return "";
        }
    }
    public double getSubtotal(){
        double subtotal = 0;
        for(OrderItem item : order_items){
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }
}