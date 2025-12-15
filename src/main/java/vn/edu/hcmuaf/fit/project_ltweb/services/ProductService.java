package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

public class ProductService {
    private ProductDao dao = new ProductDao();
    public void addProduct(Product product){
        dao.addProduct(product);
    }
}
