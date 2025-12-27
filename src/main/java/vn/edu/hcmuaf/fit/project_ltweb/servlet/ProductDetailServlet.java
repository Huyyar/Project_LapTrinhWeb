package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/product-detail")
public class ProductDetailServlet extends HttpServlet {

    private ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect(request.getContextPath() + "/product");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/product");
            return;
        }
        Product product = service.getProduct(id);
        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/product");
            return;
        }
        request.setAttribute("product", product);

        request.setAttribute("pageTitle", product.getName());
        request.setAttribute("contentPage",
                "/WEB-INF/views/userpages/product-detail.jsp");

        request.setAttribute("pageCss", new String[]{
                "assets/css/product-detail.css"
        });
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }
}

