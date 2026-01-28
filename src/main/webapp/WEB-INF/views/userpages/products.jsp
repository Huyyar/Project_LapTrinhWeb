<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <div class="product">
      <div class="content">
        <div class="container">
          <div class="section-header stack">
            <div class="section-title">
              <h2>Danh mục sản phẩm</h2>
              <p class="section-subtitle">
                <c:choose>
                  <c:when test="${not empty searchKeyword}">
                    Kết quả tìm kiếm cho: "<strong>${searchKeyword}</strong>"
                  </c:when>
                  <c:otherwise>
                    Lọc theo khẩu vị, tìm kiếm món yêu thích và săn ưu đãi đang diễn ra.
                  </c:otherwise>
                </c:choose>
              </p>
            </div>
            <div class="search-bar">
              <span class="icon">🔍</span>
              <input type="search" id="search" placeholder="Tìm kiếm ..." value="${searchKeyword}" />
            </div>
          </div>

          <div class="product-controls">
            <div class="filter-chips">
              <button type="button" class="chip" onclick="filterByCategory('')">
                <span class="chip-icon">✨</span>Tất cả</button
              ><c:forEach var="cat" items="${categories}">
              <button type="button" class="chip" onclick="filterByCategory('${cat.name}')">
                <span class="chip-icon">🏷️</span>${cat.name}</button
              ></c:forEach>
            </div>
            <div class="product-actions">
              <label class="sort-select">
                <span>Sắp xếp</span>
                <select id="sort-select">
                  <option value="featured" ${sortBy == 'featured' ? 'selected' : ''}>Nổi bật</option>
                  <option value="price-asc" ${sortBy == 'price-asc' ? 'selected' : ''}>Giá tăng dần</option>
                  <option value="price-desc" ${sortBy == 'price-desc' ? 'selected' : ''}>Giá giảm dần</option>
                  <option value="updated-desc" ${sortBy == 'updated-desc' ? 'selected' : ''}>Mới cập nhật</option>
                </select>
              </label>
            </div>
          </div>

          <div class="product-grid" data-product-grid="">
              <c:choose>
                  <c:when test="${not empty products}">
                      <c:forEach var="p" items="${products}">
                          <article class="product-card" data-product-id=${p.id}>

                              <div class="product-image">
                                  <img
                                          src="${p.imgPath}"
                                          alt="${p.name}"
                                          loading="lazy"
                                  />
                                  <span class="product-tag">${p.category}</span>
                              </div>
                              <div class="product-content">
                                  <h3>${p.name}</h3>
                                  <p class="product-description">${p.description}</p>
                                  <div class="product-price">
                                      <span class="product-price-current">
                                               <fmt:formatNumber value="${p.price}" type="number" /> ₫
                                      </span>
                                  </div>
                              </div>
                              <div class="card-actions">
                                  <a class="btn btn-outline"
                                     href="product-detail?id=${p.id}"
                                     target="_blank">
                                      Xem chi tiết
                                  </a>

                                  <c:choose>
                                      <c:when test="${p.is_active && p.inventory_qty > 0}">
                                          <a href="add-cart?id=${p.id}&qty=1&page=products" class="btn btn-primary">Thêm vào giỏ</a>
                                      </c:when>
                                      <c:otherwise>
                                          <button class="btn btn-primary" disabled style="opacity: 0.6; cursor: not-allowed;">
                                              <i class="fa-solid fa-ban"></i>
                                              Đã hết hàng
                                          </button>
                                      </c:otherwise>
                                  </c:choose>

                              </div>
                          </article>
                      </c:forEach>
                  </c:when>
                  <c:otherwise>
                      <span>Không có sản phẩm</span>
                  </c:otherwise>
              </c:choose>

          </div>
            <c:if test="${totalPage > 0}">
                <ul class="pagination">
                    <c:set var="maxVisible" value="5" />
                    <c:set var="half" value="2" /> <c:set var="beginPage" value="${currentPage - half > 1 ? currentPage - half : 1}" />
                    <c:set var="endPage" value="${beginPage + maxVisible - 1 > totalPage ? totalPage : beginPage + maxVisible - 1}" />

                    <c:if test="${endPage - beginPage < maxVisible - 1 && totalPage > maxVisible}">
                        <c:set var="beginPage" value="${endPage - maxVisible + 1 > 1 ? endPage - maxVisible + 1 : 1}" />
                    </c:if>
                    <c:if test="${currentPage != 1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/products?page=1${not empty sortBy ? '&sort=' += sortBy : ''}${not empty searchKeyword ? '&search=' += searchKeyword : ''}">
                                Quay lại trang đầu
                            </a>
                        </li>
                    </c:if>

                    <c:forEach var="p" begin="${beginPage}" end="${endPage}">
                        <li class="${p == currentPage ? 'active' : ''}">
                            <a href="${pageContext.request.contextPath}/products?page=${p}${not empty sortBy ? '&sort=' += sortBy : ''}${not empty searchKeyword ? '&search=' += searchKeyword : ''}">
                                    ${p}
                            </a>
                        </li>
                    </c:forEach>
                    <c:if test="${currentPage != totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/products?page=${totalPage}${not empty sortBy ? '&sort=' += sortBy : ''}${not empty searchKeyword ? '&search=' += searchKeyword : ''}">
                                Về trang cuối(${totalPage})
                            </a>
                        </li>
                    </c:if>
                </ul>
            </c:if>
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
