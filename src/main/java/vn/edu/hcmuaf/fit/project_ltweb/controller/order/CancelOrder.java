package vn.edu.hcmuaf.fit.project_ltweb.controller.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.services.OrderService;

import java.io.IOException;

@WebServlet(name = "CancelOrder", value = "/cancel-order")
public class CancelOrder extends HttpServlet {
    OrderService service =  new OrderService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =  Integer.parseInt(request.getParameter("orderId"));
        service.cancelOrder(id);
        response.sendRedirect("order-history");
    }
}