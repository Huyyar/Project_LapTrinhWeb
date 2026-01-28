<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Lỗi máy chủ</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/error_500.css" />
</head>
<body>
    <div class="error-container">
        <div class="error-icon">⚠️</div>
        <div class="error-code">500</div>
        <h1 class="error-title">Lỗi máy chủ</h1>
        <p class="error-message">
            Xin lỗi, đã xảy ra lỗi không mong muốn trên máy chủ. Chúng tôi đang làm việc để khắc phục vấn đề này.
        </p>
        <c:if test="${not empty pageContext.errorData.throwable}">
            <div class="error-details">
                <strong>Chi tiết lỗi:</strong><br/>
                ${pageContext.errorData.throwable.class.name}: ${pageContext.errorData.throwable.message}
            </div>
        </c:if>
        <div class="error-actions">
            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">
                🏠 Về trang chủ
            </a>
            <button data-action="reload" class="btn btn-secondary">
                🔄 Thử lại
            </button>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/error_common.js"></script>
</body>
</html>
