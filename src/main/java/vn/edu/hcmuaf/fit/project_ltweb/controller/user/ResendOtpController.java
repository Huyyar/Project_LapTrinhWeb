package vn.edu.hcmuaf.fit.project_ltweb.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.MailService;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

@WebServlet(name = "ResendOtpController", value = "/resend-otp")
public class ResendOtpController extends HttpServlet {
    UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
       
        String email = (String) session.getAttribute("emailVerify");

    
        if (email == null || email.isEmpty()) {
            response.sendRedirect("register");
            return;
        }

        // Lấy thông tin user từ database
        User user = service.getUserByEmail(email);
        
        if (user == null) {
            request.setAttribute("error", "Không tìm thấy tài khoản.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/verify_otp.jsp").forward(request, response);
            return;
        }

        // Tạo mã OTP mới (6 số ngẫu nhiên)
        String newOtp = String.valueOf((int)((Math.random() * (999999 - 100000)) + 100000));
        
        // Cập nhật OTP mới vào database
        boolean updated = service.updateOtp(email, newOtp);
        
        if (updated) {
            // Gửi email với OTP mới
            String content = "Mã xác thực (OTP) mới của bạn là: " + newOtp +
                    "\n\nMã này dùng để kích hoạt tài khoản tại SnackHub.";
            MailService.sendMail(email, content);

            // Thông báo thành công
            request.setAttribute("success", "Mã OTP mới đã được gửi đến email của bạn!");
            request.getRequestDispatcher("/WEB-INF/views/userpages/verify_otp.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Có lỗi xảy ra. Vui lòng thử lại.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/verify_otp.jsp").forward(request, response);
        }
    }
}
