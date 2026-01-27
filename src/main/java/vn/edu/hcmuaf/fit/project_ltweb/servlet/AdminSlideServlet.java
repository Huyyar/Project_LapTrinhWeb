package vn.edu.hcmuaf.fit.project_ltweb.servlet;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;

import java.io.IOException;

@WebServlet(name = "AdminSlideServlet", value = "/admin/slideshow")
public class AdminSlideServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageInfo info = new PageInfo();
        info.setName("slideshow");
        info.setTitle("Quản lý liên hệ | Admin");
        info.setContent("/WEB-INF/views/admin_pages/admin_slideshow.jsp");
        info.setCss(new String[]{
                "admin_slideshow.css"
        });
        info.setJs(new String[]{
                "admin_slideshow.js"
        });

        request.setAttribute("info", info);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
