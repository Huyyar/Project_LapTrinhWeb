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
}
