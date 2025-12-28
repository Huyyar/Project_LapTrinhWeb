<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="order-history-page">
      <div class="container">
        <header>
          <h1>Đơn hàng của bạn</h1>
          <p>
            Cảm ơn bạn đã tin tưởng SnackHub. Chúng tôi đang chuẩn bị món ngon
            cho bạn.
          </p>
        </header>

        <div class="order-filters" aria-label="Lọc trạng thái đơn hàng">
          <button type="button" class="filter-btn is-active">Tất cả</button>
          <button type="button" class="filter-btn">Đang xử lý</button>
          <button type="button" class="filter-btn">Đang giao</button>
          <button type="button" class="filter-btn">Đã giao</button>
          <button type="button" class="filter-btn">Đã hủy</button>
        </div>

        <section class="order-list" aria-label="Danh sách đơn hàng">
          <article class="order-card">
            <header class="order-card-header">
              <div>
                <span class="order-code">Mã đơn #SH20241101</span>
                <time datetime="2024-11-02">Đặt ngày 02/11/2024</time>
              </div>
              <span class="order-status status-pending">Đang xử lý</span>
            </header>
            <ul class="order-summary">
              <li>
                <div class="left">
                  <img src="../../assets/images/yogurt.jpg" alt="Yogurt" />
                  <span class="item-name">Yogurt</span>
                </div>
                <div class="right">
                  <span class="item-qty">x2</span>
                  <span class="item-price">28.000₫</span>
                </div>
              </li>
              <li>
                <div class="left">
                  <img src="../../assets/images/yogurt.jpg" alt="Yogurt" />
                  <span class="item-name">Yogurt</span>
                </div>
                <div class="right">
                  <span class="item-qty">x2</span>
                  <span class="item-price">28.000₫</span>
                </div>
              </li>
            </ul>
            <footer class="order-card-footer">
              <div class="order-total">
                <span>Tổng thanh toán</span>
                <strong>117.000₫</strong>
              </div>
              <div class="order-actions">
                <a class="link-detail" href="order_detail.html">Xem chi tiết</a>
                <button type="button" class="btn-reorder">Hủy đơn hàng</button>
              </div>
            </footer>
          </article>

          <article class="order-card">
            <header class="order-card-header">
              <div>
                <span class="order-code">Mã đơn #SH20241101</span>
                <time datetime="2024-11-02">Đặt ngày 02/11/2024</time>
              </div>
              <span class="order-status status-pending">Đang giao hàng</span>
            </header>
            <ul class="order-summary">
              <li>
                <div class="left">
                  <img src="../../assets/images/yogurt.jpg" alt="Yogurt" />
                  <span class="item-name">Yogurt</span>
                </div>
                <div class="right">
                  <span class="item-qty">x2</span>
                  <span class="item-price">28.000₫</span>
                </div>
              </li>
              <li>
                <div class="left">
                  <img src="../../assets/images/yogurt.jpg" alt="Yogurt" />
                  <span class="item-name">Yogurt</span>
                </div>
                <div class="right">
                  <span class="item-qty">x2</span>
                  <span class="item-price">28.000₫</span>
                </div>
              </li>
            </ul>
            <footer class="order-card-footer">
              <div class="order-total">
                <span>Tổng thanh toán</span>
                <strong>117.000₫</strong>
              </div>
              <div class="order-actions">
                <a class="link-detail" href="order_detail.html">Xem chi tiết</a>
                <button type="button" class="btn-reorder">Hủy đơn hàng</button>
              </div>
            </footer>
          </article>

          <article class="order-card">
            <header class="order-card-header">
              <div class="order-info">
                <span class="order-code">Mã đơn #SH20241102</span>
                <time datetime="2024-11-01">Đặt ngày 01/11/2024</time>
                <span class="order-note">
                  Đã giao hành thành công vào 01/11/2024.
                </span>
              </div>
              <span class="order-status status-done">Đã giao</span>
            </header>
            <ul class="order-summary">
              <li>
                <div class="left">
                  <img src="../../assets/images/yogurt.jpg" alt="Yogurt" />
                  <span class="item-name">Yogurt</span>
                </div>
                <div class="right">
                  <span class="item-qty">x2</span>
                  <span class="item-price">28.000₫</span>
                </div>
              </li>
              <li>
                <div class="left">
                  <img src="../../assets/images/yogurt.jpg" alt="Yogurt" />
                  <span class="item-name">Yogurt</span>
                </div>
                <div class="right">
                  <span class="item-qty">x2</span>
                  <span class="item-price">28.000₫</span>
                </div>
              </li>
            </ul>
            <footer class="order-card-footer">
              <div class="order-total">
                <span>Tổng thanh toán</span>
                <strong>117.000₫</strong>
              </div>
              <div class="order-actions">
                <a class="link-detail" href="order_detail.html">Xem chi tiết</a>
                <button type="button" class="btn-reorder">Mua lại</button>
              </div>
            </footer>
          </article>

          <article class="order-card">
            <header class="order-card-header">
              <div class="order-info">
                <span class="order-code">Mã đơn #SH20241103</span>
                <time datetime="2024-10-31">Đặt ngày 31/10/2024</time>
                <span class="order-note"> Bạn đã hủy đơn vào 01/11/2024. </span>
              </div>
              <span class="order-status status-cancel">Đã hủy</span>
            </header>
            <ul class="order-summary">
              <li>
                <div class="left">
                  <img src="../../assets/images/yogurt.jpg" alt="Yogurt" />
                  <span class="item-name">Yogurt</span>
                </div>
                <div class="right">
                  <span class="item-qty">x2</span>
                  <span class="item-price">28.000₫</span>
                </div>
              </li>
              <li>
                <div class="left">
                  <img src="../../assets/images/yogurt.jpg" alt="Yogurt" />
                  <span class="item-name">Yogurt</span>
                </div>
                <div class="right">
                  <span class="item-qty">x2</span>
                  <span class="item-price">28.000₫</span>
                </div>
              </li>
            </ul>
            <footer class="order-card-footer is-single">
              <div class="order-total">
                <span>Tổng tạm tính</span>
                <strong>117.000₫</strong>
              </div>
              <div class="order-actions">
                <a class="link-detail" href="order_detail.html">Xem chi tiết</a>
                <button type="button" class="btn-reorder">
                  Đặt lại đơn này
                </button>
              </div>
            </footer>
          </article>
        </section>
      </div>
    </main>

