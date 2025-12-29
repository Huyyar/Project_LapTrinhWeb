package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession  session = request.getSession();
        User user  = (User) session.getAttribute("auth");
        if (user == null) {
            String errorMsg = URLEncoder.encode("Vui lòng đăng nhập để thực hiện thanh toán!", "UTF-8");
            String actionMsg = URLEncoder.encode("Đến trang đăng nhập!", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/error?message=" + errorMsg + "&actionMsg=" +  actionMsg + "&page=login");

            return; // Kết thúc để không chạy code phía dưới
        }
        UserPageInfo info =  new UserPageInfo();
        info.setTitle("Trang checkout");
        info.setContent("/WEB-INF/views/userpages/checkout.jsp");
        info.setCss(new String[]{
                "user/checkout.css"
        });
        info.setJs(new String[]{
        });
        request.setAttribute("info",info);
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}