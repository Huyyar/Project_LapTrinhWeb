package vn.edu.hcmuaf.fit.project_ltweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User auth = (User) request.getSession().getAttribute("auth");
        if (auth != null) {
            response.sendRedirect("home");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/userpages/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("name") ;
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("confirm-password");
        String avatar_url="assets/icons/icon_user.png";
        System.out.println(avatar_url);
        User user = new User(email, password ,fullname,avatar_url);
        if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/register.jsp").forward(request, response);

            return;
        }
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("error", "Định dạng email không hợp lệ.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/register.jsp").forward(request, response);
            return;
        }
        if(!password.matches(
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
        )) {
            request.setAttribute("error",
                    "Mật khẩu phải ≥ 8 ký tự, có chữ hoa, chữ thường và số");
            request.getRequestDispatcher("/WEB-INF/views/userpages/register.jsp").forward(request, response);
        }

        if(!password.equals(passwordConfirm)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/register.jsp").forward(request, response);

            return;
        }

        boolean isRegistered = service.registerUser(user);
        if(isRegistered) {

                HttpSession session = request.getSession();
                User u = service.getUserByEmail(email);

                session.setAttribute("auth", u);
                response.sendRedirect("home");

        } else {

            request.setAttribute("error", "Email đã được sử dụng.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/register.jsp").forward(request, response);
        }


    }
}
