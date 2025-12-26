package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

import java.util.List;

public class ProductService {
    private ProductDao dao = new ProductDao();

    public List<Product> getProducts() {
        return dao.getProducts();
    }
    public int addProduct(Product product){
        return dao.addProduct(product);
    }

    public Product getProduct(int id) {
        return dao.getProduct(id);
    }

    public List<Product> getFeaturedProducts() {
        return dao.getFeaturedProducts();
    }
    public int getTotalProducts(){
        return dao.getTotalProducts();
    }
    public int getTotalSearchProducts(String search){
        return dao.getTotalSearchProducts(search);
    }
    public List<Product> getPagedProducts(int offset, int pageSize){
        return dao.getPagedProducts(offset, pageSize);
    }
    public List<Product> getPagedSearchProducts(int offset,  int pageSize, String search){
        return dao.getPagedSearchProducts(offset, pageSize, search);
    }
}
