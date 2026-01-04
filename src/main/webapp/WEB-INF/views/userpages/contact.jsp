<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28/12/2025
  Time: 9:22 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
    <section class="contact">
        <div class="container contact-inner">

            <div>
                <h2>Liên hệ SnackHub</h2>
                <p>Gửi tin nhắn cho chúng tôi, đội ngũ SnackHub sẽ phản hồi nhanh chóng.</p>
            </div>

            <form class="contact-form"
                  action="${pageContext.request.contextPath}/contact"
                  method="post">

                <label>
                    Họ và tên
                    <input type="text" name="fullName" required>
                </label>

                <label>
                    Email
                    <input type="email" name="email" required>
                </label>

                <label>
                    Nội dung
                    <textarea name="message" rows="4" required></textarea>
                </label>

                <button id="btn" type="submit">Gửi liên hệ</button>
            </form>

            <c:if test="${not empty success}">
                <div class="contact-popup-overlay" id="contact-popup">
                    <div class="contact-popup">
                        <button class="popup-close" onclick="closePopup()">
                            <i class="fa-solid fa-xmark"></i>
                        </button>
                        <div class="popup-content">
                            <i class="fa-solid fa-circle-check popup-icon"></i>
                            <p>${success}</p>
                        </div>
                    </div>
                </div>
            </c:if>


        </div>
    </section>
</main>
