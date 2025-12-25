package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.AdminPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/products")
public class AdminProductsServlet extends HttpServlet {
    ProductService service =  new ProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminPageInfo info = new  AdminPageInfo();
        info.setName("products");
        info.setTitle("Admin - Products");
        info.setContent("/WEB-INF/views/admin_pages/products.jsp");
        info.setCss(new String[]{
                "admin_product.css"
        });
        info.setJs(new String[]{
                "admin_product.js",
                "admin/add_product_image.js"
        });
        request.setAttribute("info",info);

        List<Product> products = service.getProducts();
        request.setAttribute("products", products);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);

    }
}

