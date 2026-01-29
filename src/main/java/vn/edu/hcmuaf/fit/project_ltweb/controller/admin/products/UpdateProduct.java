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

@WebServlet("/admin/update-product")
public class UpdateProduct extends HttpServlet {
    private ProductService service = new ProductService();
    private ProductImageService imageService = new ProductImageService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            // 1. Lấy dữ liệu
            String idStr = request.getParameter("id");
            System.out.println("DEBUG: Bắt đầu update ID: " + idStr);

            int id = Integer.parseInt(idStr);
            int category_id = Integer.parseInt(request.getParameter("category"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            String image_url = request.getParameter("image_url");
            int inventory_qty = Integer.parseInt(request.getParameter("inventory_qty"));
            boolean featured = Boolean.parseBoolean(request.getParameter("featured"));
            boolean is_active = Boolean.parseBoolean(request.getParameter("is_active"));

            // 2. Update Product
            Product product = new Product(category_id, name, description, price, image_url, inventory_qty, featured, is_active);
            product.setId(id);
            service.updateProduct(product);
            System.out.println("DEBUG: Đã update thông tin cơ bản Product");

            // 3. Xử lý ảnh
            // Xóa hết ảnh cũ
            imageService.deleteAllImagesByProductId(id);
            System.out.println("DEBUG: Đã gọi hàm xóa ảnh cũ");

            // Thêm ảnh chính
            if (image_url != null && !image_url.trim().isEmpty()) {
                imageService.addProductImage(id, image_url.trim(), true);
                System.out.println("DEBUG: Đã thêm ảnh chính: " + image_url);
            }

            // Thêm ảnh phụ
            String[] subImages = request.getParameterValues("sub_images");

            if (subImages != null) {
                System.out.println("DEBUG: Tìm thấy " + subImages.length + " input ảnh phụ.");
                for (String url : subImages) {
                    if (url == null || url.trim().isEmpty()) {
                        System.out.println("DEBUG: Bỏ qua do url rỗng");
                        continue;
                    }
                    // Bỏ qua nếu trùng ảnh chính
                    if (url.trim().equals(image_url.trim())) {
                        System.out.println("DEBUG: Bỏ qua do trùng ảnh chính: " + url);
                        continue;
                    }

                    imageService.addProductImage(id, url.trim(), false);
                    System.out.println("DEBUG: Đã insert ảnh phụ: " + url);
                }
            } else {
                System.out.println("DEBUG: subImages là NULL (Kiểm tra lại name input trong HTML)");
            }

            response.sendRedirect("products");

        } catch (Exception e) {
            System.err.println("DEBUG ERROR: Có lỗi xảy ra trong UpdateProduct");
            e.printStackTrace(); // In lỗi ra console để đọc
            response.sendRedirect("products?error=update_failed");
        }
    }
}