<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
isErrorPage="true" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>403 - Truy cập bị từ chối</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/error_403.css"
    />
  </head>
  <body>
    <div class="error-container">
      <div class="error-icon">🚫</div>
      <div class="error-code">403</div>
      <h1 class="error-title">Truy cập bị từ chối</h1>
      <p class="error-message">
        Xin lỗi, bạn không có quyền truy cập vào trang này. Vui lòng kiểm tra
        quyền truy cập của bạn hoặc liên hệ quản trị viên.
      </p>
      <div class="error-actions">
        <a
          href="${pageContext.request.contextPath}/home"
          class="btn btn-primary"
        >
          🏠 Về trang chủ
        </a>
        <button data-action="back" class="btn btn-secondary">← Quay lại</button>
      </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/error_common.js"></script>
  </body>
</html>
