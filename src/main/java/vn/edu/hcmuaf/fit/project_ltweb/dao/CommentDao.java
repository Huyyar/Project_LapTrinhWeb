package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.Comment;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    public List<Comment> getCommentsByProduct(int productId) {
        List<Comment> comments = new ArrayList<>();

        String sql = """
        SELECT c.id, c.product_id, c.content, c.created_at, c.is_active,
               u.id AS user_id, u.fullname, u.avatar_url
        FROM comments c
        JOIN users u ON c.user_id = u.id
        WHERE c.product_id = ?
          AND c.status = 'APPROVED'
          AND u.is_active = 1
        
        ORDER BY c.created_at DESC
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setProductId(rs.getInt("product_id"));
                comment.setContent(rs.getString("content"));
                comment.setCreatedAt(rs.getTimestamp("created_at"));
                comment.setIs_active(rs.getBoolean("is_active"));

                // 👉 MAP USER OBJECT
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAvatar_url(rs.getString("avatar_url"));

                comment.setUser(user);

                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }

    public void addComment(int productId, int userId, String content) {
        String sql = """
        INSERT INTO comments(product_id, user_id, content, status, is_active, created_at)
        VALUES (?, ?, ?, 'PENDING', 0, NOW())
        
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ps.setInt(2, userId);
            ps.setString(3, content);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // phương thức lấy tất cả comment bên trang admin
    public List<Comment> getAllCommentsForAdmin() {
        List<Comment> comments = new ArrayList<>();

        String sql = """
        SELECT c.id, c.product_id, c.content, c.created_at, c.is_active, c.status, c.parent_id,
               u.id AS user_id, u.fullname, u.avatar_url,
               p.name AS product_name
        FROM comments c
        JOIN users u ON c.user_id = u.id
        JOIN products p ON c.product_id = p.id
        ORDER BY c.created_at DESC
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setProductId(rs.getInt("product_id"));
                c.setContent(rs.getString("content"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setIs_active(rs.getBoolean("is_active"));
                c.setStatus(rs.getString("status"));
                c.setParentId((Integer) rs.getObject("parent_id"));
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAvatar_url(rs.getString("avatar_url"));
                c.setUser(user);
                c.setProductId(rs.getInt("product_id"));
                comments.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }
    // phương thức duyệt comment (APPROVE) ( chấp thuận)
    public void approveComment(int commentId) {
        String sql = "UPDATE comments SET status = 'APPROVED' WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, commentId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // xóa comment
    public void deleteComment(int commentId) {
        String sql = "UPDATE comments SET is_active = 0 WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, commentId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // phương thức trả lời comment bên phía admin
    public void replyComment(int parentId, int productId, int adminId, String content) {
        String sql = """
        INSERT INTO comments
        (product_id, user_id, content, parent_id, status, is_active, created_at)
        VALUES (?, ?, ?, ?, 'APPROVED', 1, NOW())
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ps.setInt(2, adminId);
            ps.setString(3, content);
            ps.setInt(4, parentId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







}
