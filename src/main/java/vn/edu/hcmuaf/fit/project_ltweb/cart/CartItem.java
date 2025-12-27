package vn.edu.hcmuaf.fit.project_ltweb.cart;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

public class CartItem {
    private Product product;
    private int qty;
    private double price;
    private boolean isChose;

    public CartItem(Product product, int qty, double price, boolean isChose) {
        this.product = product;
        this.qty = qty;
        this.price = price;
        this.isChose = isChose;
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

    public int upQty(int qty) {
        return this.qty += qty;
    }
    public void setIsChose(boolean isChose) {
        this.isChose = isChose;
    }
    public boolean isIsChose(){
        return isChose;
    }
}
