package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.AdminPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;

import java.io.IOException;

@WebServlet(name = "DashBoardServlet", value = "/admin/dashboard")
public class DashBoardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageInfo info = new  PageInfo();
        info.setName("dashboard");
        info.setTitle("Admin - Dashboard");
        info.setContent("/WEB-INF/views/admin_pages/dashboard.jsp");
        info.setCss(new String[]{
                "admin.css"
        });

        request.setAttribute("info",info);
        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
