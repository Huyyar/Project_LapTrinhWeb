package vn.edu.hcmuaf.fit.project_ltweb.servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.dao.ContactDAO;
import vn.edu.hcmuaf.fit.project_ltweb.model.Contact;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PageInfo info = new PageInfo();
        info.setTitle("Liên hệ | SnackHub");
        info.setName("contact");
        info.setContent("/WEB-INF/views/userpages/contact.jsp");
        info.setCss(new String[]{
                "contact.css"

        });
        info.setJs(new String[]{
                "contact.js"
        });
        req.setAttribute("info", info);
        req.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String message = req.getParameter("message");

        Contact c = new Contact(fullName, email, message);
        new ContactDAO().insert(c);

        req.setAttribute("success", "Gửi liên hệ thành công!");
        doGet(req, resp);
    }
}
