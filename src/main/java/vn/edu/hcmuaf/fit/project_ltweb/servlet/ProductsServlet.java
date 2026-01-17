package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

@WebServlet(name = "ProductsServlet", value = "/products")
public class ProductsServlet extends HttpServlet {
    private ProductService service =  new ProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPageInfo info =  new UserPageInfo();
        info.setTitle("Trang sản phẩm");
        info.setName("products");
        info.setContent("/WEB-INF/views/userpages/products.jsp");
        info.setCss(new String[]{
                "product.css",
                "cart-drawer.css",
                "pagination.css"
        });
        info.setJs(new String[]{
                "cart-drawer.js",
                "wishlist.js"
        });
        request.setAttribute("info",info);
        
     
        String searchKeyword = request.getParameter("search");
        List<Product> products;
        
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
          
            products = service.getPagedSearchProducts(0, Integer.MAX_VALUE, searchKeyword.trim());
            request.setAttribute("searchKeyword", searchKeyword.trim());
        } else {
           
            products = service.getProducts();
        }
        
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}