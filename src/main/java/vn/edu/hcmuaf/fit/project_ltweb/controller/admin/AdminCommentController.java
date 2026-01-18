package vn.edu.hcmuaf.fit.project_ltweb.controller.admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.dao.CommentDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Comment;


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

        List<Comment> comments = commentDao.getAllCommentsForAdmin();
        request.setAttribute("comments", comments);

        request.getRequestDispatcher("/views/admin/comments.jsp")
                .forward(request, response);
    }
}
