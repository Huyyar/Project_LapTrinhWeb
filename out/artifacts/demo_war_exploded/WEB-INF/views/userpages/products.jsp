<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="product">
      <div class="content">
        <div class="container">
          <div class="section-header stack">
            <div class="section-title">
              <h2>Danh mục sản phẩm</h2>
              <p class="section-subtitle">
                Lọc theo khẩu vị, tìm kiếm món yêu thích và săn ưu đãi đang diễn
                ra.
              </p>
            </div>
            <div class="search-bar">
              <span class="icon">🔍</span>
              <input type="search" id="search" placeholder="Tìm kiếm ..." />
            </div>
          </div>

          <div class="product-controls">
            <div class="filter-chips">
              <button type="button" class="chip">
                <span class="chip-icon">✨</span>Tất cả</button
              ><button type="button" class="chip">
                <span class="chip-icon">🍗</span>Đồ mặn</button
              ><button type="button" class="chip">
                <span class="chip-icon">🍰</span>Đồ ngọt</button
              ><button type="button" class="chip">
                <span class="chip-icon">🥤</span>Đồ uống</button
              ><button type="button" class="chip">
                <span class="chip-icon">🧧</span>Phiên bản giới hạn
              </button>
            </div>
            <div class="product-actions">
              <label class="sort-select">
                <span>Sắp xếp</span>
                <select id="sort-select">
                  <option value="featured">Nổi bật</option>
                  <option value="price-asc">Giá tăng dần</option>
                  <option value="price-desc">Giá giảm dần</option>
                  <option value="updated-desc">Mới cập nhật</option>
                </select>
              </label>
            </div>
          </div>

          <div class="product-grid" data-product-grid="">
              <c:choose>
                  <c:when test="${not empty products}">
                      <c:forEach var="p" items="${products}">
                          <article class="product-card" data-product-id=${p.id}>
                              <div class="product-head">
                                  <span class="product-badge">${p.featured}</span>
                                  <button
                                          class="wishlist-icon"
                                          onclick="toggleProductWishlist(1)"
                                          title="Thêm vào yêu thích"
                                  >
                                      <i class="fa-regular fa-heart"></i>
                                      <i class="fa-solid fa-heart"></i>
                                  </button>
                              </div>

                              <div class="product-image">
                                  <img
                                          src="${p.image_url}"
                                          alt="${p.name}"
                                          loading="lazy"
                                  />
                                  <span class="product-tag">${p.category}</span>
                              </div>
                              <div class="product-content">
                                  <h3>${p.name}</h3>
                                  <p class="product-description">${p.description}</p>
                                  <div class="product-price">
                                      <span class="product-price-current">${p.price}₫</span>
                                  </div>
                              </div>
                              <div class="card-actions">
                                  <a
                                          class="btn btn-outline"
                                          href="WEB-INF/pages/product-detail.html"
                                  >Xem chi tiết</a
                                  >
                                  <a href="add-cart?id=${p.id}&qty=1" class="btn btn-primary">Thêm vào giỏ</a>
                              </div>
                          </article>
                      </c:forEach>
                  </c:when>
                  <c:otherwise>
                      <span>Không có sản phẩm</span>
                  </c:otherwise>
              </c:choose>

          </div>
          <div class="pagination">
            <button type="button" class="page-button active">1</button
            ><button type="button" class="page-button">2</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Cart Drawer -->
    <div
      class="cart-overlay"
      id="cartOverlay"
      onclick="toggleCartDrawer()"
    ></div>
    <div class="cart-drawer" id="cartDrawer">
      <div class="cart-drawer-header">
        <h3>Giỏ Hàng (<span id="cartItemCount">0</span>)</h3>
        <button class="btn-close-drawer" onclick="toggleCartDrawer()">
          <i class="fa-solid fa-xmark"></i>
        </button>
      </div>

      <div class="cart-drawer-body" id="cartDrawerBody">
        <!-- Empty State -->
        <div class="cart-empty" id="cartEmpty">
          <i class="fa-solid fa-bag-shopping"></i>
          <p>Giỏ hàng trống</p>
          <button class="btn-continue-shopping" onclick="toggleCartDrawer()">
            Tiếp tục mua sắm
          </button>
        </div>

        <!-- Cart Items List -->
        <div class="cart-items" id="cartItems" style="display: none">
          <!-- Items will be rendered here by JS -->
        </div>
      </div>

      <div class="cart-drawer-footer" id="cartFooter" style="display: none">
        <div class="cart-summary">
          <div class="summary-row">
            <span>Tạm tính:</span>
            <strong id="cartSubtotal">0₫</strong>
          </div>
          <div class="summary-row">
            <span>Phí ship:</span>
            <strong id="cartShipping">30.000₫</strong>
          </div>
          <div class="summary-row total">
            <span>Tổng cộng:</span>
            <strong id="cartTotal">0₫</strong>
          </div>
        </div>
        <button
          class="btn-checkout"
          onclick="window.location.href='checkout.jsp'"
        >
          <i class="fa-solid fa-credit-card"></i>
          Thanh Toán
        </button>
      </div>
    </div>
