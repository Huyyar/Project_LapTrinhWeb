package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.dao.ContactDAO;
import vn.edu.hcmuaf.fit.project_ltweb.model.Contact;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.MailService;

import java.io.IOException;

import static vn.edu.hcmuaf.fit.project_ltweb.services.MailService.sendMail;

@WebServlet("/admin/contact/reply")
public class AdminContactReplyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        ContactDAO dao = new ContactDAO();
        Contact contact = dao.findById(id);

        req.setAttribute("contact", contact);

        PageInfo info = new PageInfo();
        info.setName("contacts");
        info.setTitle("Trả lời liên hệ | Admin");
        info.setContent("/WEB-INF/views/admin_pages/contact_reply.jsp");
        info.setCss(new String[]{
                "admin_contact_reply.css"
        });

        req.setAttribute("info", info);

        req.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp")
                .forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String toEmail = req.getParameter("email");
        String replyContent = req.getParameter("replyContent");

        MailService.sendMail(toEmail, replyContent);

        resp.sendRedirect(req.getContextPath() + "/admin/contacts");
    }


}
