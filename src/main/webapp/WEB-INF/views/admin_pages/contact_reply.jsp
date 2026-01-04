<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 04/01/2026
  Time: 5:00 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setTimeZone value="Asia/Ho_Chi_Minh"/>

<div class="admin-contact-reply">
    <h1>Phản hồi liên hệ</h1>

    <p><b>Tên:</b> ${contact.fullName}</p>
    <p><b>Email:</b> ${contact.email}</p>
    <p><b>Ngày gửi:</b>
        <fmt:formatDate value="${contact.createdAt}"
                        pattern="dd/MM/yyyy
                        HH:mm"/>
    </p>

    <p><b>Nội dung:</b></p>
    <div class="message-box">
        ${contact.message}
    </div>

    <form action="${pageContext.request.contextPath}/admin/contact/reply"
          method="post">
        <input type="hidden" name="email" value="${contact.email}">

        <label>Phản hồi của admin:</label>
        <textarea name="replyContent" rows="6" required></textarea>

        <br><br>
        <button type="submit">Gửi phản hồi</button>
        <a href="${pageContext.request.contextPath}/admin/contacts">Quay lại</a>
    </form>
</div>

