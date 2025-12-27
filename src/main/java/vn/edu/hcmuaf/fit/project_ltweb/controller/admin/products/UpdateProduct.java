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
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int category_id = Integer.parseInt(request.getParameter("category"));
        String name = request.getParameter("name");
        String description =  request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String image_url = request.getParameter("image_url");
        int inventory_qty = Integer.parseInt(request.getParameter("inventory_qty"));
        boolean featured = Boolean.parseBoolean(request.getParameter("featured"));
        boolean is_active =  Boolean.parseBoolean(request.getParameter("is_active"));
        Product product = new Product(category_id, name, description, price, image_url, inventory_qty, featured, is_active);
        product.setId(id);
        service.updateProduct(product);
        response.sendRedirect("products");
    }
}