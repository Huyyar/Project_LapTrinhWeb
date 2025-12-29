package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.dao.ContactDAO;
import vn.edu.hcmuaf.fit.project_ltweb.model.Contact;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/contacts")
public class AdminContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ContactDAO dao = new ContactDAO();
        List<Contact> contacts = dao.findAll();

        PageInfo info = new PageInfo();
        info.setName("contacts"); // ⭐ DÒNG QUAN TRỌNG
        info.setTitle("Quản lý liên hệ | Admin");
        info.setContent("/WEB-INF/views/admin_pages/contact.jsp");
        info.setCss(new String[]{
                "admin_contact.css"
        });

        req.setAttribute("contacts", contacts);
        req.setAttribute("info", info);

        req.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp")
                .forward(req, resp);
    }
}
