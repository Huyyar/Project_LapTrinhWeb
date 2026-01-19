package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrders", value = "/admin/orders")
public class AdminOrders extends HttpServlet {
    OrderService service =  new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageInfo info = new  PageInfo();
        info.setName("orders");
        info.setTitle("Admin - Orderss");
        info.setContent("/WEB-INF/views/admin_pages/orders.jsp");
        info.setCss(new String[]{
                "admin/orders.css"
        });
        info.setJs(new String[]{
                "admin/orders.js"
        });
        request.setAttribute("info",info);
        List<Order> orders = service.getOrders(null);
        request.setAttribute("orders",orders);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);
    }
}