package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getPathInfo(); // /product, /orders

        if (path == null || "/".equals(path)) {
            resp.sendRedirect(req.getContextPath() + "/admin/product");
            return;
        }

        switch (path) {
            case "/products":
                req.getRequestDispatcher("/WEB-INF/pages/admin/products.jsp")
                        .forward(req, resp);
                break;

            case "/orders":
                req.getRequestDispatcher("/WEB-INF/views/admin/orders.jsp")
                        .forward(req, resp);
                break;
        }
    }
}

