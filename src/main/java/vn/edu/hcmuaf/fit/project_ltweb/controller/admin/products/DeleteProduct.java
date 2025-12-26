package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.products;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;

@WebServlet("/admin/delete-product")
public class DeleteProduct extends HttpServlet {
    private ProductService service = new ProductService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int product_id = Integer.parseInt(request.getParameter("productId"));
        service.deleteProduct(product_id);
        response.sendRedirect("products");
    }
}
