package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.DashboardService;

@WebServlet(name = "DashBoardServlet", value = "/admin/dashboard")
public class DashBoardServlet extends HttpServlet {
    private DashboardService dashboardService = new DashboardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get dashboard statistics
        Map<String, Object> stats = dashboardService.getDashboardStats();
        request.setAttribute("stats", stats);
        
        // Get recent orders (limit 5)
        List<Order> recentOrders = dashboardService.getRecentOrders(5);
        request.setAttribute("recentOrders", recentOrders);
        
        // Pass service to JSP for getting order item counts
        request.setAttribute("dashboardService", dashboardService);

        PageInfo info = new PageInfo();
        info.setName("dashboard");
        info.setTitle("Admin - Dashboard");
        info.setContent("/WEB-INF/views/admin_pages/dashboard.jsp");
        info.setCss(new String[]{
                "admin/admin.css",
                "admin/dashboard.css"
        });

        request.setAttribute("info", info);
        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
