package vn.edu.hcmuaf.fit.project_ltweb.controller.admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.dao.CommentDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.AdminPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.Comment;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;


import java.io.IOException;
import java.util.List;

@WebServlet("/admin/comments")
public class AdminCommentController extends HttpServlet {

    private CommentDao commentDao;

    @Override
    public void init() {
        commentDao = new CommentDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy dữ liệu comment
        List<Comment> comments = commentDao.getAllCommentsForAdmin();
        request.setAttribute("comments", comments);

        // 2. Khai báo thông tin page admin
        AdminPageInfo info = new AdminPageInfo(
                "comments",
                "Quản lý bình luận",
                "/WEB-INF/views/admin_pages/comments.jsp",
                new String[]{ "admin/comments.css" },
                new String[]{ "admin/comments.js" }
        );

        request.setAttribute("info", info);

        // 3. Forward vào layout cha
        request.getRequestDispatcher(
                "/WEB-INF/views/layouts/admin_layout.jsp"
        ).forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/admin/comments");
            return;
        }

        switch (action) {

            case "approve" -> {
                int commentId = Integer.parseInt(request.getParameter("commentId"));
                commentDao.approveComment(commentId);
            }

            case "delete" -> {
                int commentId = Integer.parseInt(request.getParameter("commentId"));
                commentDao.deleteComment(commentId);
            }

            case "reply" -> {
                int parentId = Integer.parseInt(request.getParameter("parentId"));
                int productId = Integer.parseInt(request.getParameter("productId"));
                String content = request.getParameter("content");

                User admin = (User) request.getSession().getAttribute("auth");
                if (admin != null && content != null && !content.trim().isEmpty()) {
                    commentDao.replyComment(parentId, productId, admin.getId(), content);
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/comments");
    }


}
