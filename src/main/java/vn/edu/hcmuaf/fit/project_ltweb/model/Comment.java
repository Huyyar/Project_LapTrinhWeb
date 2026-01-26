package vn.edu.hcmuaf.fit.project_ltweb.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private int id;
    private int productId;
    private User user; // chứa fullname + avatar
    private String content;
    private Timestamp createdAt;
    private boolean is_active; // Comment có bị xóa / ẩn không
    private String status; // trạng thái duyệt
    private Integer parentId;
    private List<Comment> replies = new ArrayList<>();


    public Comment() {}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public List<Comment> getReplies() {
        return replies;
    }
    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

}
