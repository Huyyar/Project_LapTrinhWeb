package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;

import java.io.IOException;

@WebServlet(name = "ErrorServlet", value = "/error")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPageInfo info =  new UserPageInfo();
        info.setTitle("Có lỗi xảy ra!");
        info.setName("error");
        info.setContent("/WEB-INF/views/userpages/error.jsp");
        info.setCss(new String[]{
                "user/error.css",
        });
        request.setAttribute("info",info);
        String message =  request.getParameter("message");
        String page =  request.getParameter("page");
        String actionMsg = request.getParameter("actionMsg");
        request.setAttribute("message", message);
        request.setAttribute("actionMsg", actionMsg);
        request.setAttribute("page", page);

        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}