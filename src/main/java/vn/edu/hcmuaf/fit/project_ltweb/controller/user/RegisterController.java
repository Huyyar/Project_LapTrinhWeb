package vn.edu.hcmuaf.fit.project_ltweb.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.MailService;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

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
        // Xử lý định dạng email và mật khẩu
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
            return ;
        }

        // Kiểm tra mật khẩu xác nhận
        if(!password.equals(passwordConfirm)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/register.jsp").forward(request, response);

            return;
        }
// ĐĂNG KÍ TÀI KHOẢN
        boolean isRegistered = service.registerUser(user);
        if(isRegistered) {
            // Tạo đường link xác nhận
            // Link có dạng: http://localhost:8080/project_war/verify?token=abc-123...
            String scheme = request.getScheme();             // http
            String serverName = request.getServerName();     // localhost
            int serverPort = request.getServerPort();       // 8080
            String contextPath = request.getContextPath();   // /project_war
            String verifyLink = scheme + "://" + serverName + ":" + serverPort + contextPath + "/verify?token=" + user.getVerificationToken();

            // Gửi mail thông qua MailService
            String content = "Chào " + user.getFullname() + ",\n\nVui lòng nhấn vào link sau để kích hoạt tài khoản: " + verifyLink;
            MailService.sendMail(user.getEmail(), content);
            
            // Chuyển hướng đến trang thông báo chờ xác nhận email
            request.setAttribute("title", "Kiểm tra email của bạn");
            request.setAttribute("message", "Đăng ký thành công! Chúng tôi đã gửi một email xác nhận đến " + user.getEmail() + ". Vui lòng kiểm tra hộp thư và nhấn vào liên kết để kích hoạt tài khoản.");
            request.setAttribute("btnLink", "login");
            request.setAttribute("btnText", "Đến trang đăng nhập");
            request.getRequestDispatcher("/WEB-INF/views/userpages/notification.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Email đã được sử dụng.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/register.jsp").forward(request, response);
        }


    }
}

