<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SnackHub - Xác thực OTP</title>
    
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/authorize.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/verify_otp.css" />
</head>
<body class="auth-minimal" data-page="verify" data-context-path="${pageContext.request.contextPath}">
    <main class="auth-shell">
        <span class="auth-brand">SnackHub</span>
        <section class="auth-card" aria-labelledby="verify-title">
            <a href="${pageContext.request.contextPath}/home" class="auth-close" aria-label="Đóng">&times;</a>
            
            <header>
                <h1 id="verify-title">Xác thực OTP</h1>
                <p>
                    Mã xác thực đã được gửi đến<br>
                    <span class="email-highlight">${sessionScope.emailVerify}</span>
                </p>
            </header>

            <!-- Hiển thị lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="error-message">
                    <span>⚠️</span>
                    <span>${error}</span>
                </div>
            </c:if>

            <!-- Hiển thị thông báo thành công nếu có -->
            <c:if test="${not empty success}">
                <div class="success-message">
                    <span>✅</span>
                    <span>${success}</span>
                </div>
            </c:if>

            <!-- Form nhập OTP -->
            <form method="post" action="${pageContext.request.contextPath}/verify" class="auth-form" id="otpForm">
                <div class="otp-inputs">
                    <input type="text" maxlength="1" class="otp-input" id="otp1" pattern="[0-9]" required autocomplete="off">
                    <input type="text" maxlength="1" class="otp-input" id="otp2" pattern="[0-9]" required autocomplete="off">
                    <input type="text" maxlength="1" class="otp-input" id="otp3" pattern="[0-9]" required autocomplete="off">
                    <input type="text" maxlength="1" class="otp-input" id="otp4" pattern="[0-9]" required autocomplete="off">
                    <input type="text" maxlength="1" class="otp-input" id="otp5" pattern="[0-9]" required autocomplete="off">
                    <input type="text" maxlength="1" class="otp-input" id="otp6" pattern="[0-9]" required autocomplete="off">
                </div>

                <!-- Hidden input để gửi mã OTP  -->
                <input type="hidden" name="otp" id="otpValue">

                <!-- Nút xác nhận -->
                <button type="submit" class="auth-submit">Xác minh</button>
                
                <div class="auth-meta">
                    <span>Chưa nhận được OTP?</span>
                    <a href="#" class="auth-link" id="resendLink">
                        Gửi lại <span class="countdown-timer" id="countdown"></span>
                    </a>
                </div>
            </form>
        </section>
    </main>

    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/js/verify_otp.js"></script>
</body>
</html>
