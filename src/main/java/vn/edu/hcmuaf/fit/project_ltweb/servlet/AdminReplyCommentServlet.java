package vn.edu.hcmuaf.fit.project_ltweb.servlet;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.dao.CommentDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;

import java.io.IOException;

@WebServlet("/admin/comments/reply")
public class AdminReplyCommentServlet extends HttpServlet {

    private CommentDao commentDao = new CommentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy admin từ session
        User admin = (User) request.getSession().getAttribute("auth");
        if (admin == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 2. Lấy dữ liệu form
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        String content = request.getParameter("content");

        // 3. Gọi DAO trả lời comment
        commentDao.replyComment(parentId, productId, admin.getId(), content);

        // 4. Quay lại trang quản lý comment
        response.sendRedirect(request.getContextPath() + "/admin/comments");
    }
}

