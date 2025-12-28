package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;

import java.io.IOException;

@WebServlet(name = "OrderComplete", value = "/order-complete")
public class OrderComplete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPageInfo info =  new UserPageInfo();
        info.setTitle("Trang hoàn tất thanh toán");
        info.setContent("/WEB-INF/views/userpages/order_complete.jsp");
        info.setCss(new String[]{
                "user/order_complete.css"
        });
        info.setJs(new String[]{
        });
        request.setAttribute("info",info);
        request.setAttribute("orderCode", request.getParameter("orderCode"));
        request.setAttribute("total", request.getParameter("total"));
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}