package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderHistory", value = "/order-history")
public class OrderHistory extends HttpServlet {
    OrderService service =  new OrderService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPageInfo info =  new UserPageInfo();
        info.setTitle("Trang lịch sử đơn hàng");
        info.setContent("/WEB-INF/views/userpages/order_history.jsp");
        info.setCss(new String[]{
                "user/order_history.css"
        });
        info.setJs(new String[]{
        });
        request.setAttribute("info",info);
        String status = request.getParameter("status");
        List<Order> orders = service.getOrders(status);
        request.setAttribute("orders",orders);
        request.setAttribute("status",status);
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}