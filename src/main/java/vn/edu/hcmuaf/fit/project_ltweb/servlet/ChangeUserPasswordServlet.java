package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

import java.io.IOException;

@WebServlet("/admin/change-user-password")
public class ChangeUserPasswordServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Debug: Print ALL parameters
            System.out.println("[ChangePassword] ======= REQUEST DEBUG =======");
            System.out.println("[ChangePassword] Content-Type: " + request.getContentType());
            System.out.println("[ChangePassword] All parameters received:");
            java.util.Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                String value = request.getParameter(name);
                System.out.println("[ChangePassword]   " + name + " = '" + value + "'");
            }
            System.out.println("[ChangePassword] ======= END DEBUG =======");
            
            String userIdStr = request.getParameter("userId");
            System.out.println("[ChangePassword] Received userId: '" + userIdStr + "'");
            
            // Check if userId is null or empty BEFORE parsing
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                System.out.println("[ChangePassword] ERROR: userId is null or empty");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("User ID is missing");
                return;
            }
            
            int userId = Integer.parseInt(userIdStr);
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            
            System.out.println("[ChangePassword] Parsed userId: " + userId);

            // Validate password match
            if (!newPassword.equals(confirmPassword)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Passwords do not match");
                return;
            }

            // Validate password length
            if (newPassword.length() < 6) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Password must be at least 6 characters");
                return;
            }

            // Update password
            if (service.changeUserPassword(userId, newPassword)) {
                response.sendRedirect("users");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to update password");
            }
        } catch (NumberFormatException e) {
            String userIdStr = request.getParameter("userId");
            System.err.println("[ChangePassword] NumberFormatException - userId='" + userIdStr + "'");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid user ID: " + userIdStr);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred: " + e.getMessage());
        }
    }
}
