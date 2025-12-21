package vn.edu.hcmuaf.fit.project_ltweb.model;

public class Product {
    private int id;
    private int category_id;
    private String name;
    private String description;
    private double price;
    private String image_url;
    private int inventory_qty;
    private String category;
    private String featured;
    private boolean is_active;
    public Product() {
    }

    public Product(int category_id, String name, String description, double price, String image_url, int inventory_qty, String featured, boolean is_active) {
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
        this.inventory_qty = inventory_qty;
        this.featured =  featured;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getInventory_qty() {
        return inventory_qty;
    }

    public void setInventory_qty(int inventory_qty) {
        this.inventory_qty = inventory_qty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
