
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="user-page">
    <div class="container">
        <div class="layout">
            <aside class="sidebar">
                <div class="card">
                    <div class="avatar-wrap">
                        <img
                                src="${auth.avatar_url}"
                                alt="Ảnh đại diện"
                        />
                    </div>
                    <h1>Xin chào, ${auth.fullname}!</h1>
                </div>
                <nav class="menu" aria-label="Tùy chọn hồ sơ">
                    <a href="${pageContext.request.contextPath}/profile" class="menu-item" id="menu-profile">
                        <i class="fa-solid fa-user"></i> Thông tin cá nhân
                    </a>
                    <a href="${pageContext.request.contextPath}/address" class="menu-item" id="menu-address">
                        <i class="fa-solid fa-location-dot"></i> Địa chỉ giao hàng
                    </a>
                    <a href="${pageContext.request.contextPath}/changePassword" class="menu-item" id="menu-password">
                        <i class="fa-solid fa-key"></i> Đổi mật khẩu
                    </a>
                </nav>
            </aside>
            <jsp:include page="${userContent}" />
        </div>
    </div>
</main>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const menuItems = document.querySelectorAll('.menu-item');
        
        menuItems.forEach(item => {
            item.addEventListener('click', function() {
                // Xóa class is-active khỏi tất cả items
                menuItems.forEach(i => i.classList.remove('is-active'));
                // Thêm class is-active vào item được nhấn
                this.classList.add('is-active');
            });
        });

        // Đặt active cho menu item dựa trên URL hiện tại
        const currentPath = window.location.pathname;
        if (currentPath.includes('/profile')) {
            document.getElementById('menu-profile')?.classList.add('is-active');
        } else if (currentPath.includes('/address')) {
            document.getElementById('menu-address')?.classList.add('is-active');
        } else if (currentPath.includes('/changePassword')) {
            document.getElementById('menu-password')?.classList.add('is-active');
        }
    });
</script>