<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 18/01/2026
  Time: 10:19 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quản lý bình luận</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/admin/admin_comments.css">

</head>
<body>
<div class="main_comment" >
    <h1 class = "topbar">Quản lý bình luận</h1>
    <table class="table_content">
        <thead>
        <tr>
            <th>ID</th>
            <th>Người dùng</th>
            <th>Nội dung</th>
            <th>Sản phẩm</th>
            <th>Ngày tạo</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${comments}">
            <tr>
                <td>${c.id}</td>
                <td>
                    <strong>${c.user.fullname}</strong><br>
                    <img src="${c.user.avatar_url != null
                        ? c.user.avatar_url
                            : pageContext.request.contextPath + '/assets/img/default-avatar.png'}"
                         width="40">
                </td>
                <td>
                        ${c.content}
                    <c:if test="${c.parentId != null}">
                        <div class="comment-reply-label">
                            ↳ Trả lời
                        </div>
                    </c:if>
                </td>

                <td>#${c.productId}</td>
                <td>
                    <fmt:formatDate value="${c.createdAt}"
                                    pattern="dd/MM/yyyy HH:mm"/>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${c.status == 'PENDING'}">
                            <span class="status-pending">Chờ duyệt</span>
                        </c:when>
                        <c:when test="${c.status == 'APPROVED'}">
                            <span class="status-approved">Đã duyệt</span>
                        </c:when>

                        <c:otherwise>
                            <span class="status-hidden">Đã ẩn</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td class="actions">

                    <!-- DUYỆT COMMENT -->
                    <c:if test="${c.status == 'PENDING'}">
                        <form method="post"
                              action="${pageContext.request.contextPath}/admin/comments"
                              style="display:inline">
                            <input type="hidden" name="action" value="approve">
                            <input type="hidden" name="commentId" value="${c.id}">
                            <button type="submit">Duyệt</button>
                        </form>
                    </c:if>

                    <!-- TRẢ LỜI COMMENT -->
                    <form method="post"
                          action="${pageContext.request.contextPath}/admin/comments"
                          style="display:inline-block">
                        <input type="hidden" name="action" value="reply">
                        <input type="hidden" name="parentId" value="${c.id}">
                        <input type="hidden" name="productId" value="${c.productId}">
                        <textarea name="content"
                                  placeholder="Trả lời comment..."
                                  rows="2"></textarea>
                        <button type="submit">Trả lời</button>
                    </form>

                    <!-- XÓA COMMENT -->
                    <form method="post"
                          action="${pageContext.request.contextPath}/admin/comments"
                          style="display:inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="commentId" value="${c.id}">
                        <button type="submit"
                                onclick="return confirm('Bạn chắc chắn muốn xóa?')">
                            Xóa
                        </button>
                    </form>

                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
