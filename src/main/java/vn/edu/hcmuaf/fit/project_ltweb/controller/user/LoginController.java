package vn.edu.hcmuaf.fit.project_ltweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User auth = (User) request.getSession().getAttribute("auth");
        if (auth != null) {
            response.sendRedirect("home");
            return;
        }
        
        // Check if user was locked
        String error = request.getParameter("error");
        if ("locked".equals(error)) {
            request.setAttribute("error", "Tài khoản của bạn đang bị khóa. Vui lòng liên hệ với quản trị viên để biết thêm chi tiết.");
        }

        request.getRequestDispatcher("/WEB-INF/views/userpages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email =request.getParameter("email");
        String password = request.getParameter("password");
        if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/login.jsp").forward(request, response);
            return;
        }
        User user = service.login(email, password);
        if(user!= null) {
            // Check if account is locked
            if ("ACCOUNT_LOCKED".equals(user.getPassword())) {
                request.setAttribute("error", "Tài khoản của bạn đang bị khóa. Vui lòng liên hệ với quản trị viên để biết thêm chi tiết.");
                request.getRequestDispatcher("/WEB-INF/views/userpages/login.jsp").forward(request, response);
                return;
            }
            
            HttpSession session = request.getSession();
           if(user.isAdmin()) {
               System.out.println("user admin logged in");
               session.setAttribute("auth", user);
               response.sendRedirect("admin/dashboard");
           }else if(user.getRole().equalsIgnoreCase("user")) {
               session.setAttribute("auth", user) ;
               if(session.getAttribute("prevPage") != null) {
                   response.sendRedirect((String)session.getAttribute("prevPage"));
                   return;
               }

               response.sendRedirect("home");
           }

        }else {
            // Check if user exists to provide appropriate error message
            User checkUser = service.getUserByEmail(email);
            if (checkUser == null) {
                request.setAttribute("error", "Tài khoản không tồn tại.");
            } else {
                request.setAttribute("error", "Email hoặc mật khẩu không đúng.");
            }
            request.getRequestDispatcher("/WEB-INF/views/userpages/login.jsp").forward(request, response);
            return;
        }
    }
}
