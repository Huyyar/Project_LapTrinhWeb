package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.products;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductImageService;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;

@WebServlet("/admin/add-product")
public class AddProductController extends HttpServlet {
    private ProductService service = new ProductService();
    private ProductImageService imageService = new ProductImageService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. Lấy thông tin cơ bản
        int category_id = Integer.parseInt(request.getParameter("category"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String image_url = request.getParameter("image_url"); // Ảnh chính
        int inventory_qty = Integer.parseInt(request.getParameter("inventory_qty"));
        boolean featured = Boolean.parseBoolean(request.getParameter("featured"));
        boolean is_active = Boolean.parseBoolean(request.getParameter("is_active"));

        Product product = new Product(category_id, name, description, price, image_url, inventory_qty, featured, is_active);

        // 2. Lưu sản phẩm -> lấy ID mới
        int product_id = service.addProduct(product);

        // 3. Lưu ảnh chính vào bảng ProductImage (nếu logic hệ thống yêu cầu lưu cả vào bảng phụ)
        // Lưu ý: Thường thì bảng products đã có image_url (main), nhưng bảng images lưu sub-images.
        // Dòng dưới đây là code cũ của bạn, giữ nguyên để đảm bảo tương thích:
        imageService.addProductImage(product_id, image_url, true);

        // 4. Lưu danh sách ảnh phụ
        String[] subImages = request.getParameterValues("sub_images");
        if (subImages != null) {
            for (String url : subImages) {
                if (url != null && !url.trim().isEmpty()) {
                    imageService.addProductImage(product_id, url.trim(), false);
                }
            }
        }

        response.sendRedirect("products");
    }
}