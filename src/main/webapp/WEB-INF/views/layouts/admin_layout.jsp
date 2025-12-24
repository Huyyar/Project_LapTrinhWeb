<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="vi">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <title>${info.title}</title>
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700&display=swap"
          rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
          integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://releases.transloadit.com/uppy/v3.0.0/uppy.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/modal.css">
    <c:forEach items="${info.css}" var="css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/${css}">
    </c:forEach>
</head>

<body>
<div class="app">
    <aside class="sidebar" id="sidebar">
        <div class="brand">
            <div class="logo">🍿</div>
            <span class="brand-text">SnackHub Admin</span>
        </div>
        <nav class="nav-menu">
            <a href="dashboard" class="nav-link ${info.name == 'dashboard' ? 'active' : ''}">
                <i class="fa-solid fa-chart-pie"></i>
                <span>Dashboard</span>
            </a>
            <a href="products" class="nav-link ${info.name == 'products' ? 'active' : ''}">
                <i class="fa-solid fa-box-open"></i>
                <span>Sản phẩm</span>
            </a>
            <a href="orders" class="nav-link ${info.name == 'orders' ? 'active' : ''}">
                <i class="fa-solid fa-clipboard-list"></i>
                <span>Đơn hàng</span>
            </a>
            <a href="users" class="nav-link ${info.name == 'users' ? 'active' : ''}">
                <i class="fa-solid fa-users"></i>
                <span>Người dùng</span>
            </a>
            <a href="contacts" class="nav-link ${info.name == 'contacts' ? 'active' : ''}">
                <i class="fa-solid fa-envelope"></i>
                <span>Liên Hệ</span>
            </a>
            <a href="image-manager" class="nav-link ${info.name == 'image-manager' ? 'active' : ''}">
                <i class="fa-solid fa-upload"></i>
                <span>Quản lí ảnh</span>
            </a>
            <a href="logout" class="nav-link" id="logout-btn" style="margin-top: auto; color: var(--danger);">
                <i class="fa-solid fa-right-from-bracket"></i>
                <span>Đăng xuất</span>
            </a>
        </nav>
    </aside>
    <jsp:include page="${info.content}"/>
    <div class="modal-overlay" id="modalOverlay" hidden></div>
</div>
<script src="https://releases.transloadit.com/uppy/v3.21.0/uppy.min.js"></script>
<script src="https://releases.transloadit.com/uppy/locales/v3.0.0/vi_VN.min.js"></script>
<c:forEach items="${info.js}" var="js">
    <script src="${pageContext.request.contextPath}/assets/js/${js}"></script>
</c:forEach>
</body>

</html>