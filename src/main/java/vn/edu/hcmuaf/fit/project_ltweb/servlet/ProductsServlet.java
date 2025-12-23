package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductsServlet", value = "/product")
public class ProductsServlet extends HttpServlet {
    private ProductService service =  new ProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Trang sản phẩm");
        request.setAttribute("contentPage", "/WEB-INF/views/userpages/products.jsp");
        request.setAttribute("pageCss", new String[]{
                "assets/css/product.css",
                "assets/css/cart-drawer.css"
        });

        request.setAttribute("pageJs", new String[]{
                "assets/js/cart-drawer.js",
                "assets/js/wishlist.js",

        });
        List<Product> products = service.getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp")
                .forward(request, response);
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}