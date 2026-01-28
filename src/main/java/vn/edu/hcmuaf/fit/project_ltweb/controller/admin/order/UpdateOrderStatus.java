package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.services.OrderService;

import java.io.IOException;

@WebServlet("/admin/update-order-status")
public class UpdateOrderStatus extends HttpServlet {
    OrderService service = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String newStatus = request.getParameter("newStatus");
        service.updateOrderStatus(orderId, newStatus);
        response.sendRedirect("orders");
    }
}