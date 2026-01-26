package vn.edu.hcmuaf.fit.project_ltweb.controller.user;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

@WebServlet(name = "VerifyController", value = "/verify")
public class VerifyController extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        if(service.verifyAccount(token)) {
            // Kích hoạt xong, gửi người dùng đến trang thông báo thành công
            request.setAttribute("title", "Kích hoạt thành công");
            request.setAttribute("message", "Tài khoản của bạn đã sẵn sàng. Hãy bắt đầu mua sắm ngay!");
            request.setAttribute("btnLink", "login");
            request.setAttribute("btnText", "Đăng nhập ngay");
        } else {
            request.setAttribute("title", "Lỗi xác thực");
            request.setAttribute("message", "Mã xác nhận không hợp lệ hoặc đã hết hạn.");
            request.setAttribute("btnLink", "register");
            request.setAttribute("btnText", "Đăng ký lại");
        }
        request.getRequestDispatcher("/WEB-INF/views/userpages/notification.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
