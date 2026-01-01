
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="user-page">
    <div class="container">
        <div class="layout">
            <aside class="sidebar">
                <div class="card">
                    <div class="avatar-wrap">
                        <img
                                src="../../assets/icons/icon_user.png"
                                alt="Ảnh đại diện"
                        />
                        <button type="button">Đổi ảnh</button>
                    </div>
                    <h1>Xin chào, Nguyễn!</h1>
                </div>
                <nav class="menu" aria-label="Tùy chọn hồ sơ">
                    <a href="${pageContext.request.contextPath}/profile" class="menu-item is-active">
                        <i class="fa-solid fa-user"></i> Thông tin cá nhân
                    </a>
                    <a href="${pageContext.request.contextPath}/address" class="menu-item">
                        <i class="fa-solid fa-location-dot"></i> Địa chỉ giao hàng
                    </a>
                    <a href="${pageContext.request.contextPath}/changePassword" class="menu-item">
                        <i class="fa-solid fa-key"></i> Đổi mật khẩu
                    </a>
                    <a href="${pageContext.request.contextPath}/wishlist" class="menu-item">
                        <i class="fa-solid fa-heart"></i> Sản phẩm yêu thích
                    </a>
                </nav>
            </aside>
            <jsp:include page="${userContent}" />
        </div>
    </div>
</main>