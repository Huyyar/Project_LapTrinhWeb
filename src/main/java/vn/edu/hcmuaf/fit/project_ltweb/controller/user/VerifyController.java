package vn.edu.hcmuaf.fit.project_ltweb.controller.user;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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
        String email = (String) request.getSession().getAttribute("emailVerify");

        // Bạn nên viết thêm một hàm check OTP dựa trên cả Email và Token trong UserService
        // Ở đây tận dụng hàm verifyAccount cũ nếu logic DAO của bạn chỉ cần token
        if(otp != null && service.verifyAccount(otp)) {
            request.getSession().removeAttribute("emailVerify"); // Xóa session sau khi xong

            request.setAttribute("title", "Kích hoạt thành công");
            request.setAttribute("message", "Tài khoản của bạn đã sẵn sàng!");
            request.setAttribute("btnLink", "login");
            request.setAttribute("btnText", "Đăng nhập ngay");
        } else {
            request.setAttribute("error", "Mã OTP không chính xác.");
            request.getRequestDispatcher("/WEB-INF/views/userpages/verify_otp.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/userpages/notification.jsp").forward(request, response);
    }
}
