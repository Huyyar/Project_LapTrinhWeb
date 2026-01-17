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
                "wishlist.js",
                "product-sort.js"
        });
        request.setAttribute("info",info);
        
        // Lấy tham số tìm kiếm và sắp xếp
        String searchKeyword = request.getParameter("search");
        String sortBy = request.getParameter("sort");
        
     
        if (sortBy == null || sortBy.trim().isEmpty()) {
            sortBy = "featured";
        }
        

        List<Product> products = service.getProductsWithSortAndSearch(
            searchKeyword != null ? searchKeyword.trim() : null, 
            sortBy
        );
        
      
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            request.setAttribute("searchKeyword", searchKeyword.trim());
        }
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}