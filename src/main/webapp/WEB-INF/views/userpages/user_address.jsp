<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

          <section class="profile-content" aria-label="Địa chỉ giao hàng">
            <section
              class="panel profile-section is-active"
              data-section="address"
            >
              <header class="panel-header">
                <h2>Địa chỉ giao hàng</h2>
                <p>Lưu nhiều địa chỉ để điền nhanh mỗi lần checkout.</p>
              </header>
              <div class="panel-body address-list">
                <article class="address-card is-default">
                  <div>
                    <h3>Nhà riêng</h3>
                    <p>123 Đường ABC, Phường 7, Quận 3, TP.HCM</p>
                    <span class="badge">Mặc định</span>
                  </div>
                  <div class="address-actions">
                    <button type="button" class="link">Sửa</button>
                    <button type="button" class="link">Xóa</button>
                  </div>
                </article>
                <article class="address-card">
                  <div>
                    <h3>Văn phòng</h3>
                    <p>Tầng 5, Tòa nhà XYZ, Quận 1, TP.HCM</p>
                  </div>
                  <div class="address-actions">
                    <button type="button" class="link">Đặt làm mặc định</button>
                    <button type="button" class="link">Xóa</button>
                  </div>
                </article>
                <button type="button" class="btn-outline">
                  + Thêm địa chỉ mới
                </button>
              </div>
            </section>
          </section>
