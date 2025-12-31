<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Link CSS cho Modal -->
<link
  rel="stylesheet"
  href="${pageContext.request.contextPath}/assets/css/user/user_address_modal.css"
/>

<section class="profile-content" aria-label="Địa chỉ giao hàng">
  <section class="panel profile-section is-active" data-section="address">
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
      <button type="button" class="btn-outline">+ Thêm địa chỉ mới</button>
    </div>
  </section>
</section>

<!-- Overlay nền mờ -->
<div class="address-modal-overlay" id="addressModalOverlay"></div>

<!-- Modal Container -->
<div class="address-modal" id="addressModal">
  <!-- Modal Header -->
  <header class="address-modal-header">
    <h2>Thêm địa chỉ mới</h2>
    <button
      type="button"
      class="address-modal-close"
      id="closeAddressModal"
      aria-label="Đóng"
    >
      ✕
    </button>
  </header>

  <!-- Modal Body -->
  <div class="address-modal-body">
    <form
      id="addressForm"
      action="${pageContext.request.contextPath}/address/add"
      method="post"
    >
      <!-- Tên và Số điện thoại -->
      <div class="address-form-row">
        <div class="address-form-group">
          <label for="recipientName" class="address-form-label required"
            >Tên người nhận</label
          >
          <input
            type="text"
            id="recipientName"
            name="recipientName"
            class="address-form-input"
            placeholder="Nhập tên người nhận"
            required
          />
        </div>

        <div class="address-form-group">
          <label for="recipientPhone" class="address-form-label required"
            >Số điện thoại</label
          >
          <input
            type="tel"
            id="recipientPhone"
            name="recipientPhone"
            class="address-form-input"
            placeholder="Nhập số điện thoại"
            required
          />
        </div>
      </div>

      <!-- Tỉnh/Thành phố -->
      <div class="address-form-group">
        <label for="province" class="address-form-label required"
          >Tỉnh/Thành phố</label
        >
        <select
          id="province"
          name="provinceCode"
          class="address-form-select"
          required
        >
          <option value="">Chọn Tỉnh/Thành phố</option>
        </select>
        <input type="hidden" id="provinceName" name="province" />
      </div>

      <!-- Quận/Huyện -->
      <div class="address-form-group">
        <label for="district" class="address-form-label required"
          >Quận/Huyện</label
        >
        <select
          id="district"
          name="districtCode"
          class="address-form-select"
          required
          disabled
        >
          <option value="">Chọn Quận/Huyện</option>
        </select>
        <input type="hidden" id="districtName" name="district" />
      </div>

      <!-- Phường/Xã -->
      <div class="address-form-group">
        <label for="ward" class="address-form-label required">Phường/Xã</label>
        <select
          id="ward"
          name="wardCode"
          class="address-form-select"
          required
          disabled
        >
          <option value="">Chọn Phường/Xã</option>
        </select>
        <input type="hidden" id="wardName" name="ward" />
      </div>

      <!-- Địa chỉ cụ thể -->
      <div class="address-form-group">
        <label for="addressDetail" class="address-form-label required"
          >Địa chỉ cụ thể</label
        >
        <textarea
          id="addressDetail"
          name="addressDetail"
          class="address-form-textarea"
          placeholder="Ví dụ: Số 123, Đường ABC, Tòa nhà XYZ..."
          required
        ></textarea>
      </div>

      <!-- Checkbox đặt làm mặc định -->
      <div class="address-checkbox-group">
        <input
          type="checkbox"
          id="isDefault"
          name="isDefault"
          class="address-checkbox-input"
          value="true"
        />
        <label for="isDefault" class="address-checkbox-label">
          Đặt làm địa chỉ mặc định
        </label>
      </div>
    </form>
  </div>

  <!-- Modal Footer -->
  <footer class="address-modal-footer">
    <button
      type="button"
      class="address-btn address-btn-cancel"
      id="cancelAddressBtn"
    >
      Hủy
    </button>
    <button
      type="submit"
      form="addressForm"
      class="address-btn address-btn-submit"
      id="submitAddressBtn"
    >
      Hoàn tất
    </button>
  </footer>
</div>

<script src="${pageContext.request.contextPath}/assets/js/user/user_address.js"></script>
