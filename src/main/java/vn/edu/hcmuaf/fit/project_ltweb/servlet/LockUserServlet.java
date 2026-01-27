package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

import java.io.IOException;

@WebServlet("/admin/lock-user")
public class LockUserServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String action = request.getParameter("action");

            boolean isLock = "lock".equals(action);
            
            System.out.println("LockUserServlet - userId: " + userId + ", action: " + action + ", isLock: " + isLock);

            if (service.lockUser(userId, isLock)) {
                System.out.println("User " + userId + " lock status updated successfully. isLock: " + isLock);
                response.sendRedirect("users");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to update user status");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid user ID");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred: " + e.getMessage());
        }
    }
}
