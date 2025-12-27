package vn.edu.hcmuaf.fit.project_ltweb.cart;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {
    Map<Integer, CartItem> data;
    private boolean isChoseAll = false;
    public Cart(){
        this.data  = new HashMap<Integer, CartItem>();
    }
    public void addItem(Product product, int qty){
        if(qty <= 0){
            qty = 1;
        }
        if(!data.containsKey(product.getId())){
            data.put(product.getId(), new CartItem(product, qty, product.getPrice(), false));
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
            if(item.isIsChose()){
                total += item.getPrice() * item.getQty();
            }
        }
        return  total;
    }
    public CartItem getItem(int id){
        if(data.containsKey(id)){
            return data.get(id);
        }
        return null;
    }
    public void choseAllItem(){
        for(CartItem item : data.values()){
            item.setIsChose(!isIsChoseAll());
        }
        this.isChoseAll = !isIsChoseAll();
    }
    public boolean isIsChoseAll(){
        return this.isChoseAll;
    }
    public void checkIsChoseAll(){
        for(CartItem item : data.values()){
            if(!item.isIsChose()){
                this.isChoseAll = false;
            }else{
                this.isChoseAll = true;
            }
        }
    }
}
