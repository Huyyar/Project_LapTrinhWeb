<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>SnackHub - ${title != null ? title : 'Thông báo'}</title>

    <link rel="stylesheet" href="assets/css/authorize.css" />
    <link rel="stylesheet" href="assets/css/verify.css" />

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
            href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700&display=swap"
            rel="stylesheet"
    />
</head>
<body class="auth-minimal" data-page="notification">
<main class="auth-shell">
    <span class="auth-brand">SnackHub</span>
    <section class="auth-card" aria-labelledby="notification-title">
        <a href="${pageContext.request.contextPath}/home" class="auth-close" aria-label="Đóng"
        >&times;</a>

        <c:choose>
            <c:when test="${title == 'Kích hoạt thành công'}">
                <div class="notification-icon success">
                    ✓
                </div>
            </c:when>
            <c:otherwise>
                <div class="notification-icon error">
                    ⚠
                </div>
            </c:otherwise>
        </c:choose>

        <div class="notification-content">
            <h1 id="notification-title">
                ${title != null ? title : 'Thông báo'}
            </h1>
            <p>
                ${message != null ? message : 'Có lỗi xảy ra. Vui lòng thử lại sau.'}
            </p>
        </div>

        <div class="notification-action">
            <c:if test="${btnLink != null && btnText != null}">
                <a href="${pageContext.request.contextPath}/${btnLink}" class="notification-btn notification-btn-primary">
                        ${btnText}
                </a>
            </c:if>
            
            <a href="${pageContext.request.contextPath}/home" class="notification-btn notification-btn-secondary">
                Về trang chủ
            </a>
        </div>
    </section>
</main>
</body>
</html>
