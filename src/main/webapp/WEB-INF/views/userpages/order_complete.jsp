<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="order-complete-page">
      <div class="container">
        <header>
          <h1>Đặt hàng thành công</h1>
          <p>
            Cảm ơn bạn đã tin tưởng SnackHub. Chúng tôi đang chuẩn bị món ngon
            cho bạn.
          </p>
        </header>

        <div class="payment-steps">
          <div class="payment-step">Giỏ hàng</div>
          <div class="payment-step">Thông tin giao hàng</div>
          <div class="payment-step active">Hoàn tất</div>
        </div>

        <section class="order-complete-card">
          <div class="order-status-icon">
            <i class="fa-solid fa-circle-check"></i>
          </div>

          <h2>Đặt hàng thành công!</h2>
          <p class="order-note">
            SnackHub sẽ gửi thông báo qua email và SMS khi đơn hàng được giao
            cho đơn vị vận chuyển.
          </p>
          <p>Mã đơn hàng: <strong>${orderCode}</strong></p>
          <p>Tổng thanh toán: <strong>${total}₫</strong></p>

          <div class="order-complete-actions">
            <a href="home" class="button">Tiếp tục mua hàng</a>
            <a href="order-history" class="button">Xem đơn hàng của tôi</a>
          </div>
        </section>
      </div>
    </main>

