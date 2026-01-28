package vn.edu.hcmuaf.fit.project_ltweb.model;

import vn.edu.hcmuaf.fit.project_ltweb.utils.AppContextListener;

public class OrderItem {
    private int id;
    private int order_id;
    private int product_id;
    private String product_name;
    private String product_image_url;
    private double price;
    private int quantity;

    public OrderItem(int order_id, int product_id, double price, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image_url() {
        if (this.product_image_url == null || this.product_image_url.trim().isEmpty()) {
            return AppContextListener.contextPath + "/assets/images/no-image.png";
        }

        String url = "";
        if (this.product_image_url.startsWith("http") || this.product_image_url.startsWith("data:")) {
            url = this.product_image_url;
        } else {
            String cp = AppContextListener.contextPath;
            String path = this.product_image_url.startsWith("/") ? this.product_image_url : "/" + this.product_image_url;
            url = cp + path;
        }

        return url;
    }

    public void setProduct_image_url(String product_image_url) {
        this.product_image_url = product_image_url;
    }
    public double getTotalPrice(){
        return price *  quantity;
    }
}