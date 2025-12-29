package vn.edu.hcmuaf.fit.project_ltweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;

import java.io.IOException;

@WebServlet(name = "UserWishList", value = "/wishlist")
public class UserWishListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageInfo info = new  PageInfo();
        info.setName("Wishlist");
        info.setTitle("User - Wishlist");
        info.setContent("/WEB-INF/views/layouts/userProfile_layout.jsp");
        info.setCss(new String[]{
                "user_profile.css" ,"userPage.css" , "user_wishlist.css"
        });
        info.setJs(new String[]{
                "user_profile.js" ,
        });


        request.setAttribute("info",info);
        request.setAttribute("userContent" , "/WEB-INF/views/userpages/user_wishlist.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
