package vn.edu.hcmuaf.fit.project_ltweb.controller.user;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

import java.io.IOException;

@WebServlet(name = "VerifyController", value = "/verify")
public class VerifyController extends HttpServlet {
    UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị giao diện nhập OTP
        request.getRequestDispatcher("/WEB-INF/views/userpages/verify_otp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String otp = request.getParameter("otp");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("emailVerify");
        User pendingUser = (User) session.getAttribute("pendingUser");
        
        // Kiểm tra xem có thông tin user tạm trong session không
        if(pendingUser == null || email == null) {
            request.setAttribute("error", "Phiên làm việc đã hết hạn. Vui lòng đăng ký lại.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/verify_otp.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra OTP
        if(otp != null && otp.equals(pendingUser.getVerificationToken())) {
            // OTP đúng -> Insert user vào DB với is_active = true
            boolean isRegistered = service.createVerifiedUser(pendingUser);
            
            if(isRegistered) {
                // Xóa thông tin tạm trong session
                session.removeAttribute("emailVerify");
                session.removeAttribute("pendingUser");
                
                response.sendRedirect("login?message=verify_success");
            } else {
                request.setAttribute("error", "Có lỗi xảy ra. Vui lòng thử lại.");
                request.getRequestDispatcher("/WEB-INF/views/userpages/verify_otp.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Mã OTP không chính xác.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/verify_otp.jsp").forward(request, response);
        }
    }
}
