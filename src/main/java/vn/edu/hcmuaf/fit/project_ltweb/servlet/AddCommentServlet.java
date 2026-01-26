package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.CommentService;

import java.io.IOException;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {

    private CommentService service = new CommentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy user từ session
        User user = (User) request.getSession().getAttribute("auth");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 2. Lấy dữ liệu form
        int productId = Integer.parseInt(request.getParameter("productId"));
        String content = request.getParameter("content");

        if (content == null || content.trim().isEmpty()) {
            response.sendRedirect(
                    request.getContextPath() + "/product-detail?id=" + productId + "&error=empty"
            );
            return;
        }


        // 3. Gọi service
        service.addComment(productId, user.getId(), content);

        // 4. Quay lại trang chi tiết sản phẩm
        response.sendRedirect(
                request.getContextPath()
                        + "/product-detail?id=" + productId
                        + "&comment=success"
        );

    }

}

