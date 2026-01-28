package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.Category;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.CategoryService;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductImageService;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;

@WebServlet("/admin/add-category")
public class AddCategoryController extends HttpServlet {
    private CategoryService service = new CategoryService();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int display_order =  Integer.parseInt(request.getParameter("display_order"));
        Category cat = new  Category();
        cat.setName(name);
        cat.setDisplay_order(display_order);
        service.addCategory(cat);
        response.sendRedirect("categories");
    }
}
