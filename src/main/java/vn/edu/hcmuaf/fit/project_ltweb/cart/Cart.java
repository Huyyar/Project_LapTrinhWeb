package vn.edu.hcmuaf.fit.project_ltweb.cart;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {
    Map<Integer, CartItem> data;
    private boolean isChooseAll = false;
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
    public void delChosenItems(){
        data.values().removeIf(CartItem::isIsChoose);
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
            if(item.isIsChoose()){
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
    public void chooseAllItem(){
        for(CartItem item : data.values()){
            item.setIsChoose(!isIsChooseAll());
        }
        this.isChooseAll = !isIsChooseAll();
    }
    public boolean isIsChooseAll(){
        return this.isChooseAll;
    }
    public void checkIsChooseAll(){
        for(CartItem item : data.values()){
            if(!item.isIsChoose()){
                this.isChooseAll = false;
                return;
            }
        }
        this.isChooseAll = true;
    }
    public List<CartItem> getChosenItems(){
        List<CartItem> items = new ArrayList<>();
        for(CartItem item : data.values()){
            if(item.isIsChoose()){
                items.add(item);
            }
        }
        return items;
    }
}
