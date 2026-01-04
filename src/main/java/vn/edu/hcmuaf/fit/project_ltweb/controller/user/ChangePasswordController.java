package vn.edu.hcmuaf.fit.project_ltweb.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;
import vn.edu.hcmuaf.fit.project_ltweb.utils.HashUtil;

@WebServlet(name = "UserChangePassword", value = "/changePassword")
public class ChangePasswordController extends HttpServlet {
    private UserService userService = new UserService();
    private HashUtil hashUtil = new HashUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageInfo info = new PageInfo();
        info.setName("changePassword");
        info.setTitle("User - Address");
        info.setContent("/WEB-INF/views/layouts/userProfile_layout.jsp");
        info.setCss(new String[]{
                "user_profile.css", "userPage.css" , "user_changePassword.css"
        });
        info.setJs(new String[]{
                "user_profile.js" ,"user/changePassword.js"
        });


        request.setAttribute("info", info);
        request.setAttribute("userContent", "/WEB-INF/views/userpages/user_changePassword.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = (User) request.getSession().getAttribute("auth");
        // no hash
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String rePassword = request.getParameter("rePassword");
        //hash
        String oldPasswordHash = hashUtil.passwordHash(oldPassword);
        String newPasswordHash = hashUtil.passwordHash(newPassword);
        String rePasswordHash = hashUtil.passwordHash(rePassword);
        if (oldPassword == null || newPassword == null || rePassword == null) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            request.getRequestDispatcher("changePassword").forward(request, response);
        } else if (!oldPasswordHash.equals(u.getPassword())) {
            request.setAttribute("error", "Mật khẩu không chính xác");
            request.getRequestDispatcher("changePassword").forward(request, response);
        } else if (!newPassword.equals(rePassword)) {
            request.setAttribute("error", "Mật khẩu không khớp");
            request.getRequestDispatcher("changePassword").forward(request, response);
        } else {
            if (userService.updateUserPassword(newPassword, u)) {
                request.getSession().setAttribute("auth", u);
                response.sendRedirect("changePassword?success=1");
            }else response.sendRedirect("changePassword?success=0");
        }
    }
}


    