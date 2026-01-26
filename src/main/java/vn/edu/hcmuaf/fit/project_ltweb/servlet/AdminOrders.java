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
    private int PAGE_SIZE = 5;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageInfo info = new  PageInfo();
        info.setName("orders");
        info.setTitle("Admin - Orderss");
        info.setContent("/WEB-INF/views/admin_pages/orders.jsp");
        info.setCss(new String[]{
                "admin/orders.css",
                "pagination.css"
        });
        info.setJs(new String[]{
                "admin/orders.js"
        });
        request.setAttribute("info",info);
        String status = (String) request.getParameter("status");
        if (status == null || status.isEmpty()) {
            status = "all";
        }
        request.setAttribute("status", status);
        int totalOrder = service.getTotalOrder(status);
        int totalPage = (int) Math.ceil((double) totalOrder / PAGE_SIZE);
        request.setAttribute("totalPage", totalPage);
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        if (currentPage > totalPage || totalPage < 0) {
            currentPage = 1;
        }
        request.setAttribute("currentPage", currentPage);
        int offset = (currentPage - 1) * PAGE_SIZE;

        List<Order> orders = service.getAllOrders(offset, PAGE_SIZE, status);
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);
    }
}