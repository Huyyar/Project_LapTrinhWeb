package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.Category;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.CategoryService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/categories")
public class AdminCategories extends HttpServlet {
    private CategoryService service = new CategoryService();
    private int PAGE_SIZE = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageInfo info = new  PageInfo();
        info.setName("categories");
        info.setTitle("Admin - Categories");
        info.setContent("/WEB-INF/views/admin_pages/categories.jsp");
        info.setCss(new String[]{
                "pagination.css",
                "admin/admin_product.css"
        });
        info.setJs(new String[]{
                "admin/categories.js"
        });
        request.setAttribute("info",info);

        int totalCat, totalPage;
        String search = request.getParameter("search");
        if(search == null){
            search="";
        }
        request.setAttribute("search", search);
        totalCat = service.getTotalCat(search);
        request.setAttribute("totalCategory",totalCat);

        totalPage = (int) Math.ceil((double) totalCat / PAGE_SIZE);
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

        List<Category> categories = service.getPagedCategories(offset, PAGE_SIZE, search);
        request.setAttribute("categories",categories);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);

    }
}
