<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="profile-content" aria-label="Địa chỉ giao hàng">
  <section class="panel profile-section is-active" data-section="address">
    <header class="panel-header">
      <h2>Địa chỉ giao hàng</h2>
      <p>Lưu nhiều địa chỉ để điền nhanh mỗi lần checkout.</p>
    </header>

    <div class="panel-body address-list">
      <c:choose>
        <c:when test="${empty addressGet}">
          <!-- Không có địa chỉ -->
          <div class="empty-state">
            <p>Bạn chưa có địa chỉ nào. Hãy thêm địa chỉ giao hàng.</p>
          </div>
        </c:when>
        <c:otherwise>
          <!-- Có địa chỉ - Loop qua danh sách -->
          <c:forEach items="${addressGet}" var="addr">
            <article
              class="address-card ${addr.defaultAddress ? 'is-default' : ''}"
              data-address-id="${addr.id}"
              data-recipient-name="${addr.recipientName}"
              data-recipient-phone="${addr.recipientPhone}"
              data-province="${addr.province}"
              data-district="${addr.district}"
              data-ward="${addr.ward}"
              data-province-code="${addr.provinceCode}"
              data-district-code="${addr.districtCode}"
              data-ward-code="${addr.wardCode}"
              data-address-detail="${addr.addressDetail}"
              data-default-address="${addr.defaultAddress}"
            >
              <div>
                <h3>${addr.recipientName}</h3>
                <p>${addr.recipientPhone}</p>
                <p>${addr.fullAddress}</p>
                <c:if test="${addr.defaultAddress}">
                  <span class="badge">Mặc định</span>
                </c:if>
              </div>
              <div class="address-actions">
                <c:choose>
                  <c:when test="${addr.defaultAddress}">
                    <!-- Địa chỉ mặc định: chỉ có Sửa và Xóa -->
                    <button
                      type="button"
                      class="link btn-edit"
                      data-id="${addr.id}"
                    >
                      Sửa
                    </button>
                    <button
                      type="button"
                      class="link btn-delete"
                      data-id="${addr.id}"
                    >
                      Xóa
                    </button>
                  </c:when>
                  <c:otherwise>
                    <!-- Địa chỉ phụ: có Đặt mặc định và Xóa -->
                    <button
                      type="button"
                      class="link btn-set-default"
                      data-id="${addr.id}"
                    >
                      Đặt làm mặc định
                    </button>
                    <button
                      type="button"
                      class="link btn-delete"
                      data-id="${addr.id}"
                    >
                      Xóa
                    </button>
                  </c:otherwise>
                </c:choose>
              </div>
            </article>
          </c:forEach>
        </c:otherwise>
      </c:choose>

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
      action="${pageContext.request.contextPath}/address"
      method="post"
    >
      <input type="hidden" name="action" value="add" id="formAction" />
      <!-- Thêm hidden input để controller biết là add hay update -->
      <input type="hidden" name="addressId" id="addressId" value="" />
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
          id="defaultAddress"
          name="defaultAddress"
          class="address-checkbox-input"
          value="true"
        />
        <label for="defaultAddress" class="address-checkbox-label">
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
