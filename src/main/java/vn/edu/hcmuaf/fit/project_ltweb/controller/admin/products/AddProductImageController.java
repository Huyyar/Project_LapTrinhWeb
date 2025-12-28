package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.products;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductImageService;

import java.io.IOException;

@WebServlet(name = "AddProductImageController", value = "/admin/add-product-image")
public class AddProductImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int product_id =  Integer.parseInt(request.getParameter("product_id"));
        String image_url =  request.getParameter("image_url");
        ProductImageService service =  new ProductImageService();
        service.addProductImage(product_id, image_url, false);
        response.sendRedirect("products");
    }
}