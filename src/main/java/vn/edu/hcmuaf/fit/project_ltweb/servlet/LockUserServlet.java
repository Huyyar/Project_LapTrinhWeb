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
            String userIdStr = request.getParameter("userId");
            System.out.println("[LockUser] Received userId: '" + userIdStr + "'");
            
            // Check if userId is null or empty BEFORE parsing
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                System.out.println("[LockUser] ERROR: userId is null or empty");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("User ID is missing");
                return;
            }
            
            int userId = Integer.parseInt(userIdStr);
            String action = request.getParameter("action");
            
            System.out.println("[LockUser] Parsed userId: " + userId + ", action: " + action);

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
            String userIdStr = request.getParameter("userId");
            System.err.println("[LockUser] NumberFormatException - userId='" + userIdStr + "'");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid user ID: " + userIdStr);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred: " + e.getMessage());
        }
    }
}
