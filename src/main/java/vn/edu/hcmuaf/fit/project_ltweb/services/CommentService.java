package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.CommentDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Comment;

import java.util.List;

public class CommentService {
    private CommentDao dao = new CommentDao();

    public List<Comment> getCommentsByProduct(int productId) {
        return dao.getCommentsByProduct(productId);
    }

    public void addComment(int productId, int userId, String content) {
        dao.addComment(productId, userId, content);
    }

    public List<Comment> getCommentsWithRepliesByProduct(int productId) {
        return dao.getCommentsWithRepliesByProduct(productId);
    }
    public List<Comment> getRepliesByCommentId(int parentId) {
        return dao.findRepliesByParentId(parentId);
    }

}
