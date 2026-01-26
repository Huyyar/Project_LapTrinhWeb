<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setTimeZone value="Asia/Ho_Chi_Minh"/>

<div class="admin-contact">
    <header class="contact-header">
        <h1 class="page-title">Quản Lý Liên Hệ</h1>
        <div class="count-pill">${contacts.size()} tin nhắn</div>
    </header>

    <section class="contact-content">
        <div class="panel">
            <div class="panel-header">
                <h2>Danh sách tin nhắn</h2>
            </div>

            <div class="table-wrap">
                <table>
                    <thead>
                    <tr>
                        <th>Tên</th>
                        <th>Email</th>
                        <th>Nội dung</th>
                        <th>Ngày gửi</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${contacts}" var="c">
                        <tr>
                            <td>${c.fullName}</td>
                            <td>${c.email}</td>
                            <td>${c.message}</td>
                            <td>
                                <fmt:formatDate value="${c.createdAt}"
                                                pattern="dd/MM/yyyy HH:mm"/>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${c.is_replied}">
                                        <span style="color: green; font-weight: bold;">✓ Đã phản hồi</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: orange; font-weight: bold;">⧗ Chưa phản hồi</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <!-- LINK SANG TRANG TRẢ LỜI -->
                                <div class="button_delete">
                                    <a class="btn-reply"
                                       href="${pageContext.request.contextPath}/admin/contact/reply?id=${c.id}">
                                        Trả lời
                                    </a>

                                    <form action="${pageContext.request.contextPath}/admin/contacts"
                                          method="post"
                                          style="display:inline"
                                          onsubmit="return confirm('Bạn có chắc muốn xóa?')">
                                        <input type="hidden" name="id" value="${c.id}">
                                        <button type="submit" class="btn-delete">Xóa</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>
    </section>
</div>
