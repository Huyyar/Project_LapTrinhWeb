package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession  session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            response.sendRedirect("cart");
            return;
        }
        if(cart.getChosenItems().isEmpty()){
            response.sendRedirect("cart");
            return;
        }
        User user  = (User) session.getAttribute("auth");
        if(user==null){
            request.setAttribute("error","Vui lòng đăng nhập để mua hàng!");
            session.setAttribute("prevPage",request.getContextPath() + "/checkout");
            request.getRequestDispatcher("/WEB-INF/views/userpages/login.jsp")
                    .forward(request, response);
            return;
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