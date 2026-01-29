package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.ProductImageDao;

public class ProductImageService {
    private ProductImageDao dao = new ProductImageDao();

    public void addProductImage(int product_id, String image_url, boolean is_default){
        dao.addProductImage(product_id, image_url, is_default);
    }

    // Thêm hàm này
    public void deleteAllImagesByProductId(int productId) {
        dao.deleteAllImagesByProductId(productId);
    }
}