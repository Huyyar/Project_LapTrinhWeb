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
        });
        info.setJs(new String[]{
        });
        request.setAttribute("info",info);

        int total = service.getTotalCat();
        request.setAttribute("totalCategory",total);
        List<Category> categories = service.getCategories();
        request.setAttribute("categories",categories);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);

    }
}
