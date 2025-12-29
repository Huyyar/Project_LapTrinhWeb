<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="error-container">
    <div class="error-content">
        <div class="error-icon">
            <i class="fas fa-exclamation-triangle"></i>
        </div>

        <h1>Có lỗi xảy ra!</h1>

        <p class="error-message">
            <c:choose>
                <c:when test="${not empty message}">
                    ${message}
                </c:when>
                <c:otherwise>
                    Rất tiếc, yêu cầu của bạn không thể thực hiện lúc này.
                </c:otherwise>
            </c:choose>
        </p>

        <div class="error-actions">
            <a href="${pageContext.request.contextPath}/${not empty page ? page : 'home'}" class="btn-home">
                <i class="fas fa-home"></i> ${actionMsg}
            </a>
        </div>
    </div>
</main>