package vn.edu.hcmuaf.fit.project_ltweb.cart;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

public class CartItem {
    private Product product;
    private int qty;
    private double price;
    private boolean isChoose;

    public CartItem(Product product, int qty, double price, boolean isChoose) {
        this.product = product;
        this.qty = qty;
        this.price = price;
        this.isChoose = isChoose;
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
    public void setIsChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }
    public boolean isIsChoose(){
        return isChoose;
    }
}
