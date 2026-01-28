<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>${info.title}</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
            href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700&display=swap"
            rel="stylesheet"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
            integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/authorize.css" />

    <c:forEach items="${info.css}" var="css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/${css}">
    </c:forEach>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<header class="navbar" id="navbar">
    <div class="container nav-inner">
        <a class="logo" href="${pageContext.request.contextPath}/home">
            <span class="logo-icon">🍿</span>
            <span>SnackHub</span>
        </a>
        <nav>
            <ul class="nav-links">
                <li>
                    <a href="${pageContext.request.contextPath}/home" class="${info.name == "home"? "active" : ""}">Trang chủ</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/products" class="${info.name == "products"? "active" : ""}">Sản phẩm</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/contact"
                       class="${info.name == 'contact' ? 'active' : ''}">
                        Liên hệ
                    </a>

                </li>
            </ul>
            <div class="nav-actions">
                <div class="nav-search">
                    <span class="icon">🔍</span>
                    <input
                            type="search"
                            id="nav-search"
                            placeholder="Bạn muốn ăn gì hôm nay?"
                    />
                </div>
                <c:if test="${ empty sessionScope.auth}">
                <div class="nav-auth">
                    <a class="link-button" href="${pageContext.request.contextPath}/register"
                    >Đăng kí</a
                    >
                    <span class="nav-divider"></span>
                    <a class="link-button" href="${pageContext.request.contextPath}/login"
                    >Đăng nhập</a
                    >
                </div>
                </c:if>
                <c:if test="${ not empty sessionScope.auth}">

                    <div class="user-profile">
                        <button class="user-icon-btn" aria-label="Tài khoản người dùng">
                            <img src="${pageContext.request.contextPath}/${auth.avatar_url}" alt="User Avatar" class="user-avatar">
                        </button>

                        <div class="user-dropdown">
                            <a href="${pageContext.request.contextPath}/profile">Profile</a>
                            <a href="${pageContext.request.contextPath}/order-history">Lịch sử mua hàng</a>
                    <c:if test="${sessionScope.auth.isAdmin()}">
                        <a href="${pageContext.request.contextPath}/admin/dashboard">Quản trị viên</a>
                    </c:if>
                            <a href="logout" id="logoutBtn">Đăng xuất</a>
                        </div>

                    </div>
                </c:if>
                <button class="cart-button">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <a href="cart" target="_blank">Giỏ Hàng(${not empty sessionScope.cart.totalQty? sessionScope.cart.totalQty : 0})</a>
                </button>
            </div>
        </nav>
    </div>
</header>
    <jsp:include page="${info.content}" />
<footer class="footer">
    <div class="container footer-inner">
        <div>
            <div class="logo">
                <span class="logo-icon">🍿</span>
                <span>SnackHub</span>
            </div>
            <p>
                Tụ điểm snack boutique chuẩn vị quốc tế, mang tới trải nghiệm mua
                sắm thú vị cho mọi cuộc vui.
            </p>
        </div>
        <div>
            <h4>Hỗ trợ khách hàng</h4>
            <ul class="footer-links">
                <li><a href="tel:0123456789">Hotline: 0123 456 789</a></li>
                <li>
                    <a href="mailto:contact@snackhub.vn"
                    >Email: contact@snackhub.vn</a
                    >
                </li>
                <li>
                    <a
                            href="https://maps.google.com/?q=123+%C4%90%C6%B0%E1%BB%9Dng+ABC,+Qu%E1%BA%ADn+1,+TP.HCM"
                            target="_blank"
                            rel="noopener"
                    >
                        Store: 123 Đường ABC, Quận 1, TP.HCM
                    </a>
                </li>
            </ul>
        </div>
        <div>
            <h4>Kết nối</h4>
            <div class="social">
                <a href="https://www.facebook.com/" aria-label="Facebook">
                    <i class="fa-brands fa-facebook"></i>
                </a>
                <a href="https://www.instagram.com/" aria-label="Instagram">
                    <i class="fa-brands fa-instagram"></i>
                </a>
                <a href="https://www.tiktok.com/" aria-label="TikTok">
                    <i class="fa-brands fa-tiktok"></i>
                </a>
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <p class="footer-note">
            © 2025 SnackHub. Made with ❤️ for snack lovers.
        </p>
        <button class="link-button" type="button">Lên đầu trang</button>
    </div>
</footer>
<script src="${pageContext.request.contextPath}/assets/js/logout.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/nav-search.js"></script>
<c:forEach items="${info.js}" var="js">
    <script src="${pageContext.request.contextPath}/assets/js/${js}"></script>
</c:forEach>
</body>
</html>
