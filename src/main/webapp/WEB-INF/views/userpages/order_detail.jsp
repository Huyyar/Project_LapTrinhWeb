<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="dialog">
      <div class="dialog-content">
        <div class="dialog-header">
          <h2>Chi tiết đơn hàng <span>${order.order_code}</span></h2>
        </div>
        <div class="dialog-body">
          <div class="order-info">
            <div class="info-section">
              <h3>Thông tin khách hàng</h3>
              <div class="info-grid">
                <div class="info-item">
                  <label>Họ tên:</label>
                  <span>${order.full_name}</span>
                </div>
                <div class="info-item">
                  <label>Số điện thoại:</label>
                  <span>${order.phone}</span>
                </div>
                <div class="info-item">
                  <label>Email:</label>
                  <span>${order.email}</span>
                </div>
                <div class="info-item">
                  <label>Địa chỉ:</label>
                  <span>${order.address_id}</span>
                </div>
              </div>
            </div>
            <div class="info-section">
              <h3>Chi tiết đơn hàng</h3>
              <table class="order-items">
                <thead>
                  <tr>
                    <th>Sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Thành tiền</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${order.order_items}">
                    <tr>
                        <td>${i.product_name}</td>
                        <td>${i.quantity}</td>
                        <td>${i.price}₫</td>
                        <td>${i.quantity * i.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
              </table>

              <div class="order-summary">
                <div class="summary-item">
                  <span>Tạm tính:</span>
                  <span>126.400 ₫</span>
                </div>
                <div class="summary-item">
                  <span>Phí vận chuyển:</span>
                  <span>${order.shipping_fee}₫</span>
                </div>
                <div class="summary-item total">
                  <span>Tổng cộng:</span>
                  <span>${o.total_amount}₫</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
