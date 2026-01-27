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

@WebServlet("/admin/delete-user")
public class DeleteUserServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("userId");
            System.out.println("[DeleteUser] Received userId: '" + userIdStr + "'");
            
            // Check if userId is null or empty BEFORE parsing
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                System.out.println("[DeleteUser] ERROR: userId is null or empty");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("User ID is missing");
                return;
            }
            
            int userId = Integer.parseInt(userIdStr);
            System.out.println("[DeleteUser] Parsed userId: " + userId);

            if (service.deleteUser(userId)) {
                // If deleted user is currently logged in, invalidate their session
                HttpSession session = request.getSession(false);
                if (session != null) {
                    User loggedInUser = (User) session.getAttribute("auth");
                    if (loggedInUser != null && loggedInUser.getId() == userId) {
                        // Current logged in user is the one being deleted, so invalidate session
                        session.invalidate();
                    }
                }
                response.sendRedirect("users");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to delete user");
            }
        } catch (NumberFormatException e) {
            String userIdStr = request.getParameter("userId");
            System.err.println("[DeleteUser] NumberFormatException - userId='" + userIdStr + "'");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid user ID: " + userIdStr);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred: " + e.getMessage());
        }
    }
}
