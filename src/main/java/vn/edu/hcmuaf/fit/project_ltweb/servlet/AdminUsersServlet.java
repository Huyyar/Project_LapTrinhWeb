package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.AdminPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUsersServlet extends HttpServlet {
    private UserService service = new UserService();
    private int PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminPageInfo info = new AdminPageInfo();
        info.setName("users");
        info.setTitle("Admin - Users");
        info.setContent("/WEB-INF/views/admin_pages/users.jsp");
        info.setCss(new String[]{
                "pagination.css",
                "admin/admin_users.css"
        });
        info.setJs(new String[]{
                "admin/admin_users.js"
        });
        request.setAttribute("info", info);

        int totalUsers, totalPage;
        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }
        request.setAttribute("search", search);

        if (search.isEmpty()) {
            totalUsers = service.getTotalUsers();
        } else {
            totalUsers = service.getTotalSearchUsers(search);
        }

        request.setAttribute("totalUsers", totalUsers);
        totalPage = (int) Math.ceil((double) totalUsers / PAGE_SIZE);
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
        List<User> users;
        if (search.isEmpty()) {
            users = service.getAllUsers(offset, PAGE_SIZE);
        } else {
            users = service.searchUsers(search, offset, PAGE_SIZE);
        }
        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp").forward(request, response);
    }
}
