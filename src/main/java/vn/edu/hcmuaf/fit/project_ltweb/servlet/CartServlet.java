package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPageInfo info =  new UserPageInfo();
        info.setTitle("Trang giỏ hàng");
        info.setContent("/WEB-INF/views/userpages/cart.jsp");
        info.setCss(new String[]{
                "user/cart.css"
        });
        info.setJs(new String[]{
                "user/cart.js"
        });
        request.setAttribute("info",info);
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}