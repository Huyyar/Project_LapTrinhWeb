package vn.edu.hcmuaf.fit.project_ltweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/products")
public class ListProductController extends HttpServlet {
    private ProductService service =  new ProductService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = service.getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/pages/admin/products.jsp").forward(request, response);
    }
}
