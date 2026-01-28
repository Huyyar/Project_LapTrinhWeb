package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.category;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.services.CategoryService;

import java.io.IOException;

@WebServlet(name = "DeleteCategory", value = "/admin/delete-category")
public class DeleteCategory extends HttpServlet {
    CategoryService service =  new CategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =  Integer.parseInt(request.getParameter("id"));
        service.deleteCategory(id);
        response.sendRedirect("categories");
    }
}