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
          AND c.is_active = 1
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
        INSERT INTO comments(product_id, user_id, content, is_active, created_at)
        VALUES (?, ?, ?, 1, NOW())
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



}
