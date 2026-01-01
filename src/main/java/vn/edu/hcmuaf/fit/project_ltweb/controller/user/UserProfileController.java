package vn.edu.hcmuaf.fit.project_ltweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.UserService;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

@WebServlet(name = "UserProfileController", value = "/profile")
public class UserProfileController extends HttpServlet {
    UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PageInfo info = new PageInfo();
        info.setName("Profile");
        info.setTitle("User - Profile");
        info.setContent("/WEB-INF/views/layouts/userProfile_layout.jsp");
        info.setCss(new String[]{
                "user_profile.css", "userPage.css"
        });
        info.setJs(new String[]{
                "user_profile.js"
        });
        request.setAttribute("info", info);
        request.setAttribute("userContent",
                "/WEB-INF/views/userpages/user_profile.jsp");


        User u = (User) request.getSession().getAttribute("auth");
        request.setAttribute("auth", u);


        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        int id = user.getId();
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");

        // neu bỏ  trống thi lay cai cu
        user.setEmail((email != null && !email.isEmpty()) ? email : user.getEmail());
        user.setFullname(fullname != null && !fullname.isEmpty() ? fullname : user.getFullname());
        user.setPhone(phone != null && !phone.isEmpty() ? phone : user.getPhone());
        user.setGender(gender != null && !gender.isEmpty() ? gender : user.getGender());
        if (birthdate != null && !birthdate.isEmpty()) {
            try {
                user.setBirthdate(Date.valueOf(birthdate));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid birthdate format: " + birthdate);
                user.setBirthdate(user.getBirthdate());
            }
        }

            User updatedUser = service.updateUserProfile(user);
            if (updatedUser != null) {
                System.out.println("update tcong");
                request.getSession().setAttribute("auth", updatedUser);
                response.sendRedirect("profile?success=true");
            } else {
                System.out.println("update that bai");
                response.sendRedirect("profile?success=false");
            }
        }

}
