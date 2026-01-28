package vn.edu.hcmuaf.fit.project_ltweb.services;

import java.util.List;

import vn.edu.hcmuaf.fit.project_ltweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.project_ltweb.dao.ProductImageDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.model.ProductImage;

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
    public int getTotalFeaturedProduct(){return dao.getTotalFeaturedProducts();};
    public List<Product> getFeaturedProducts(int offset, int pageSize) {

        return dao.getFeaturedProducts(offset, pageSize);
    }
    public int getTotalProducts(String search){
        if(search == null){
            return dao.getTotalProducts();
        }else{
            return dao.getTotalSearchProducts(search);
        }
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
    public void  deleteProduct(int id){
        dao.deleteProduct(id);
    }
    public void updateProduct(Product product){
        dao.updateProduct(product);
    }

    public List<Product> getProductsWithSortAndSearch(String search, String sortBy, int offset,  int pageSize) {
        return dao.getProductsWithSortAndSearch(search, sortBy, offset, pageSize);
    }

    private ProductImageDao imageDao = new ProductImageDao();
    public List<ProductImage> getProductImages(int productId) {
        return imageDao.getImagesByProductId(productId);
    }

}
