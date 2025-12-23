package vn.edu.hcmuaf.fit.project_ltweb.cart;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

public class CartItem {
    private Product product;
    private int qty;
    private double price;

    public CartItem(Product product, int qty, double price) {
        this.product = product;
        this.qty = qty;
        this.price = price;
    }

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void upQty(int qty) {
        this.qty += qty;
    }
}
