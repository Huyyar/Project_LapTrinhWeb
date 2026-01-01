
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="wishlist-content" aria-label="Sản phẩm yêu thích">
            <div class="panel wishlist-section">
              <header class="panel-header">
                <h2>Sản phẩm yêu thích</h2>
                <p>Danh sách các sản phẩm bạn đã thêm vào mục yêu thích</p>
              </header>
              <div class="panel-body">
                <div class="wishlist-stats">
                  <div class="stat-item">
                    <i class="fa-solid fa-heart"></i>
                    <span class="stat-number" id="wishlistCount">0</span>
                    <span class="stat-label">Sản phẩm</span>
                  </div>
                  <div class="stat-item">
                    <i class="fa-solid fa-tag"></i>
                    <span class="stat-number" id="totalValue">0₫</span>
                    <span class="stat-label">Tổng giá trị</span>
                  </div>
                </div>

                <div class="wishlist-actions-top">
                  <button class="btn-secondary" onclick="addAllToCart()">
                    <i class="fa-solid fa-cart-plus"></i>
                    Thêm tất cả vào giỏ hàng
                  </button>
                  <button class="btn-outline" onclick="clearWishlist()">
                    <i class="fa-solid fa-trash"></i>
                    Xóa tất cả
                  </button>
                </div>

                <div class="wishlist-grid" id="wishlistGrid">
                  <!-- Empty state -->
                  <div class="empty-state" id="emptyState">
                    <i class="fa-regular fa-heart"></i>
                    <h3>Chưa có sản phẩm yêu thích</h3>
                    <p>
                      Hãy thêm những món ăn bạn thích vào danh sách này để dễ
                      dàng tìm lại sau!
                    </p>
                    <a href="products.jsp" class="btn-primary">
                      <i class="fa-solid fa-utensils"></i>
                      Khám phá sản phẩm
                    </a>
                  </div>
                  <!-- Products will be added here dynamically -->
                </div>
              </div>
            </div>
          </section>
