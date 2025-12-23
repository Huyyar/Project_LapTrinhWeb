package vn.edu.hcmuaf.fit.project_ltweb.cart;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {
    Map<Integer, CartItem> data;
    public Cart(){
        this.data  = new HashMap<Integer, CartItem>();
    }
    public void addItem(Product product, int qty){
        if(qty <= 0){
            qty = 1;
        }
        if(!data.containsKey(product.getId())){
            data.put(product.getId(), new CartItem(product, qty, product.getPrice()));
        }else{
            data.get(product.getId()).upQty(qty);
        }
    }
    public void updateItem(Product product, int qty){

    }
    public CartItem delItem(int id){
        return data.remove(id);
    }
    public List<CartItem> delAll(){
        List<CartItem> items = new ArrayList<CartItem>(data.values());
        data.clear();
        return items;
    }
    public List<CartItem> getItems(){
        return new ArrayList<>(data.values());
    }
    public int getTotalQty(){
        int total = 0;
        for(CartItem item : data.values()){
            total += item.getQty();
        }
        return total;
    }
    public double getTotalPrice(){
        double total = 0;
        for(CartItem item : data.values()){
            total += item.getPrice() * item.getQty();
        }
        return  total;
    }
}
