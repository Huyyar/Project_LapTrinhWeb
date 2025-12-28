<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="checkout-page">
    <div class="container">
        <header>
            <h1>Thanh toán đơn hàng</h1>
            <p>
                Điền thông tin giao hàng và xác nhận đơn để SnackHub chuẩn bị món
                ngon cho bạn ngay.
            </p>
        </header>

        <div class="payment-steps">
            <div class="payment-step">Giỏ hàng</div>
            <div class="payment-step active">Thông tin giao hàng</div>
            <div class="payment-step">Hoàn tất</div>
        </div>

        <div class="checkout-page-layout">
            <form class="checkout-form" method="post" action="create-order">
                <section class="checkout-form-section">
                    <div class="form-grid">
                        <label class="form-field">
                            <span>Họ và tên</span>
                            <input
                                    type="text"
                                    name="full_name"
                                    placeholder="Nguyễn Snack"
                                    required
                            />
                        </label>
                        <label class="form-field">
                            <span>Số điện thoại</span>
                            <input
                                    type="tel"
                                    name="phone"
                                    placeholder="0123456789"
                                    required
                            />
                        </label>
                        <label class="form-field">
                            <span>Email</span>
                            <input
                                    type="email"
                                    name="email"
                                    placeholder="ban@example.com"
                                    required
                            />
                        </label>
                    </div>
                </section>

                <section class="checkout-form-section">
                    <header class="form-section-header">
                        <h2>Địa chỉ giao hàng</h2>
                        <p>
                            Chọn địa chỉ giao hàng mặc định hoặc thay đổi địa chỉ khác.
                        </p>
                    </header>
                    <div class="address-select-component">
                        <a
                                href="user_address.html"
                                class="selected-address change-address-btn"
                        >
                            <div class="address-info">
                    <span
                            class="address-label"
                            style="font-weight: 600; color: #222"
                    >Nhà riêng (Mặc định)</span
                    >
                                <span class="address-value" style="color: #555"
                                >123 Đường ABC, Phường 7, Quận 3, TP.HCM</span
                                >
                            </div>
                            <i
                                    class="fa-solid fa-chevron-right"
                                    style="margin-left: auto; font-size: 1.1em; color: #ff7a00"
                            ></i>
                        </a>
                    </div>
                </section>

                <section class="checkout-form-section">
                    <header class="form-section-header">
                        <h2>Phương thức giao hàng</h2>
                        <p>Chọn lựa tốc độ giao phù hợp với lịch trình của bạn.</p>
                    </header>
                    <div class="option-list">
                        <label class="option-card">
                            <input
                                    type="radio"
                                    name="shipping"
                                    value="standard"
                                    checked
                            />
                            <span class="option-marker" aria-hidden="true"></span>
                            <div class="option-card-body">
                                <span class="option-title">Giao tiêu chuẩn</span>
                                <span class="option-meta">Dự kiến 1-2 ngày • 15.000₫</span>
                            </div>
                        </label>
                        <label class="option-card">
                            <input type="radio" name="shipping" value="express"/>
                            <span class="option-marker" aria-hidden="true"></span>
                            <div class="option-card-body">
                                <span class="option-title">Giao nhanh</span>
                                <span class="option-meta"
                                >Trong ngày tại nội thành • 45.000₫</span
                                >
                            </div>
                        </label>
                    </div>
                </section>

                <section class="checkout-form-section">
                    <header class="form-section-header">
                        <h2>Thanh toán</h2>
                        <p>SnackHub hỗ trợ nhiều phương thức thanh toán linh hoạt.</p>
                    </header>
                    <div class="option-list option-grid">
                        <label class="option-card">
                            <input type="radio" name="payment" value="cod" checked/>
                            <span class="option-marker" aria-hidden="true"></span>
                            <div class="option-card-body">
                                <span class="option-title">Thanh toán khi nhận hàng</span>
                                <span class="option-meta"
                                >Không mất phí, kiểm tra hàng trước khi trả</span
                                >
                            </div>
                        </label>
                        <label class="option-card">
                            <input type="radio" name="payment" value="bank"/>
                            <span class="option-marker" aria-hidden="true"></span>
                            <div class="option-card-body">
                                <span class="option-title">Chuyển khoản ngân hàng</span>
                                <span class="option-meta"
                                >Hỗ trợ hầu hết ngân hàng nội địa</span
                                >
                            </div>
                        </label>
                        <label class="option-card">
                            <input type="radio" name="payment" value="ewallet"/>
                            <span class="option-marker" aria-hidden="true"></span>
                            <div class="option-card-body">
                                <span class="option-title">Ví điện tử</span>
                                <span class="option-meta">ZaloPay, Momo, ViettelPay</span>
                            </div>
                        </label>
                    </div>
                    <label class="form-field full-width">
                        <span>Ghi chú đơn hàng</span>
                        <textarea
                                name="notes"
                                rows="2"
                                placeholder="Lưu ý khi giao hàng"
                        ></textarea>
                    </label>
                </section>
                <footer>
                    <a href="cart" class="btn"> Thay đổi sản phẩm </a>
                    <button class="btn" type="submit">Xác nhận đặt hàng</button>
                </footer>
            </form>

            <aside class="checkout-order-summary">
                <header class="summary-header">
                    <h2>Tóm tắt đơn hàng</h2>
                    <p>Kiểm tra nhanh các món bạn đã chọn trước khi xác nhận.</p>
                </header>
                <c:choose>
                    <c:when test="${not empty sessionScope.cart.chosenItems or not empty sessionScope.cart}">
                        <div class="summary-main items">
                            <c:forEach var="ci" items="${sessionScope.cart.chosenItems}">
                                <div class="item">
                                    <div class="left">
                                        <img src="${pageContext.request.contextPath}/${ci.product.image_url}" alt="${ci.product.name}"/>
                                        <span class="name">${ci.product.name}</span>
                                    </div>
                                    <div class="right">
                                        <span class="quantity">x${ci.qty}</span>
                                        <span class="price">${ci.price}đ</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="summary-divider" aria-hidden="true"></div>
                        <dl class="summary-totals">
                            <div class="checkout-summary-row">
                                <dt>Tạm tính</dt>
                                <dd>${sessionScope.cart.totalPrice}₫</dd>
                            </div>
                            <div class="checkout-summary-row">
                                <dt>Phí vận chuyển</dt>
                                <dd>15000₫</dd>
                            </div>
                            <div class="checkout-summary-row checkout-summary-total">
                                <dt>Tổng cộng</dt>
                                <dd><strong>${sessionScope.cart.totalPrice + 15000}₫</strong></dd>
                            </div>
                        </dl>
                    </c:when>
                    <c:otherwise>
                        <div class="summary-main empty">
                            <p>
                                Giỏ hàng của bạn đang trống. Hãy quay lại trang giỏ hàng để chọn
                                sản phầm trước khi thanh toán.
                            </p>
                            <a href="cart.jsp" class="btn"
                            ><i class="fa-solid fa-cart-shopping"></i>
                                Quay lại giỏ hàng.
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </aside>
        </div>
    </div>
</main>

