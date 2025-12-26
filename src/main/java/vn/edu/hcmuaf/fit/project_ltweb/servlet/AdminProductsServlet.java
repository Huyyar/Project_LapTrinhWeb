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
    int PAGE_SIZE = 5;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminPageInfo info = new  AdminPageInfo();
        info.setName("products");
        info.setTitle("Admin - Products");
        info.setContent("/WEB-INF/views/admin_pages/products.jsp");
        info.setCss(new String[]{
                "admin/pagination.css",
                "admin/admin_product.css"
        });
        info.setJs(new String[]{
                "admin/products.js"
        });
        request.setAttribute("info",info);

        int totalProduct, totalPage;
        String search = request.getParameter("search");
        if(search == null){
            search="";
        }
        request.setAttribute("search", search);
        totalProduct = service.getTotalSearchProducts(search);
        request.setAttribute("totalProduct", totalProduct);
        totalPage = (int) Math.ceil((double) totalProduct / PAGE_SIZE);
        request.setAttribute("totalPage", totalPage);
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        if (currentPage > totalPage || totalPage < 0) {
            currentPage = 1;
        }
        request.setAttribute("currentPage", currentPage);
        int offset = (currentPage - 1) * PAGE_SIZE;
        List<Product> products = service.getPagedSearchProducts(offset, PAGE_SIZE, search);
        request.setAttribute("products", products);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);

    }
}

