<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>SnackHub</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
      integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <link rel="stylesheet" href="assets/css/home.css" />
    <link rel="stylesheet" href="assets/css/slideshow.css" />
    <link rel="stylesheet" href="assets/css/header.css" />
    <link rel="stylesheet" href="assets/css/footer.css" />
    <link rel="stylesheet" href="assets/css/product.css" />
  </head>
  <body>
    <header class="navbar" id="navbar">
      <div class="container nav-inner">
        <a class="logo" href="index.html">
          <span class="logo-icon">🍿</span>
          <span>SnackHub</span>
        </a>
        <nav>
          <ul class="nav-links">
            <li>
              <a href="index.html#home" class="active">Trang chủ</a>
            </li>
            <li>
              <a href="WEB-INF/pages/product.html"> Sản phẩm</a>
            </li>
            <li>
              <a href="WEB-INF/pages/contact.html">Liên hệ</a>
            </li>
          </ul>
          <div class="nav-actions">
            <div class="nav-search">
              <span class="icon">🔍</span>
              <input
                type="search"
                id="nav-search"
                placeholder="Bạn muốn ăn gì hôm nay?"
              />
            </div>
            <div class="nav-auth">
              <a class="link-button" href="WEB-INF/pages/register.html"
                >Đăng kí</a
              >
              <span class="nav-divider"></span>
              <a class="link-button" href="WEB-INF/pages/login.html"
                >Đăng nhập</a
              >
            </div>
            <button class="cart-button">
                <i class="fa-solid fa-cart-shopping"></i>
              <a href="cart">Giỏ Hàng(${sessionScope.cart.totalQty})</a>
            </button>
          </div>
        </nav>
      </div>
    </header>

    <main>
      <section class="hero" id="home">
        <div class="hero-slideshow">
          <div class="hero-slide is-active">
            <img
              src="assets/images/slideshow_halloween.jpg"
              alt="Bàn tiệc Halloween "
            />
            <div class="hero-slide-content">
              <span class="hero-slide-chip">Halloween 2025</span>
              <h3>Đại tiệc snack đêm Halloween</h3>
              <p>
                Cho kẹo hay bị ghẹo - Một đêm Halloween tuyệt vời với nhiều loại
                kẹo.
              </p>
            </div>
          </div>

          <div class="hero-slide" data-hero-slide>
            <img
              src="assets/images/slideshow_drinks.jpg"
              alt="Các loại nước uống"
            />
            <div class="hero-slide-content">
              <span class="hero-slide-chip">SnackHub Drinks</span>
              <h3>Thưởng thức nhiều loại nước uống khác nhau</h3>
              <p>Đánh thức vị giác với hương vị đa dạng .</p>
            </div>
          </div>

          <div class="hero-slide" data-hero-slide>
            <img
              src="assets/images/slideshow_mochi.jpg"
              alt="Sản phẩm mochi matcha mới của SnackHub"
            />
            <div class="hero-slide-content">
              <span class="hero-slide-chip">Ra mắt tuần này</span>
              <h3>Mochi lava thơm tan</h3>
              <p>
                Lớp vỏ mềm thủ công ôm lấy nhân matcha Uji tan chảy, giới hạn
                cho mùa lễ hội.
              </p>
            </div>
          </div>

          <button
            class="hero-slide-nav prev"
            type="button"
            data-hero-prev
            aria-label="Chuyển về slide trước"
          >
            &lsaquo;
          </button>
          <button
            class="hero-slide-nav next"
            type="button"
            data-hero-next
            aria-label="Chuyển sang slide tiếp theo"
          >
            &rsaquo;
          </button>
        </div>
      </section>

      <div class="content">
        <div class="container">
          <div class="section-header stack">
            <div class="section-title">
              <h2>Sản phẩm nổi bật hôm nay</h2>
              <p class="section-subtitle">
                Cùng khám các ưu đãi và món mới hấp dẫn.
              </p>
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
                            alt="Rong biển cay giòn"
                            loading="lazy"
                    />
                    <span class="product-tag">Đồ mặn</span>
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
        </div>
      </div>
    </main>

    <footer class="footer">
      <div class="container footer-inner">
        <div>
          <div class="logo">
            <span class="logo-icon">🍿</span>
            <span>SnackHub</span>
          </div>
          <p>
            Tụ điểm snack boutique chuẩn vị quốc tế, mang tới trải nghiệm mua
            sắm thú vị cho mọi cuộc vui.
          </p>
        </div>
        <div>
          <h4>Hỗ trợ khách hàng</h4>
          <ul class="footer-links">
            <li><a href="tel:0123456789">Hotline: 0123 456 789</a></li>
            <li>
              <a href="mailto:contact@snackhub.vn"
                >Email: contact@snackhub.vn</a
              >
            </li>
            <li>
              <a
                href="https://maps.google.com/?q=123+%C4%90%C6%B0%E1%BB%9Dng+ABC,+Qu%E1%BA%ADn+1,+TP.HCM"
                target="_blank"
                rel="noopener"
              >
                Store: 123 Đường ABC, Quận 1, TP.HCM
              </a>
            </li>
          </ul>
        </div>
        <div>
          <h4>Kết nối</h4>
          <div class="social">
            <a href="https://www.facebook.com/" aria-label="Facebook">
              <i class="fa-brands fa-facebook"></i>
            </a>
            <a href="https://www.instagram.com/" aria-label="Instagram">
              <i class="fa-brands fa-instagram"></i>
            </a>
            <a href="https://www.tiktok.com/" aria-label="TikTok">
              <i class="fa-brands fa-tiktok"></i>
            </a>
          </div>
        </div>
      </div>
      <div class="footer-bottom">
        <p class="footer-note">
          © 2025 SnackHub. Made with ❤️ for snack lovers.
        </p>
        <button class="link-button" type="button">Lên đầu trang</button>
      </div>
    </footer>

    <script src="assets/js/wishlist.js"></script>
    <script src="assets/js/auth.js"></script>
    <script src="assets/js/slideshow.js"></script>
    <script>
      // Product data for homepage
      const homeProductsData = {
        1: {
          id: 1,
          name: "Rong biển cay giòn",
          nameEn: "Spicy Crispy Seaweed",
          description:
            "Miếng rong biển nướng phủ gia vị cay nhẹ chuẩn Hàn Quốc",
          price: 35700,
          image: "assets/images/Image_Rong_Bien.jpeg",
          category: "Đồ mặn",
        },
        2: {
          id: 2,
          name: "Bắp rang caramel",
          nameEn: "Caramel Popcorn",
          description: "Hạt bắp nổ bằng máy khí nóng, phủ caramel béo nhẹ",
          price: 55000,
          image: "assets/images/Image_Bap_Rang.jpg",
          category: "Đồ ngọt",
        },
        3: {
          id: 3,
          name: "Bánh xoắn phô mai",
          nameEn: "Cheese Twist",
          description: "Bánh xoắn giòn rụm với hương vị phô mai đậm đà",
          price: 42000,
          image: "assets/images/banh-xoan.jpg",
          category: "Đồ mặn",
        },
        4: {
          id: 4,
          name: "Bánh mochi matcha",
          nameEn: "Matcha Mochi",
          description: "Vỏ mochi dẻo mịn ôm trọn nhân kem matcha béo nhẹ",
          price: 58900,
          image: "assets/images/banh_mochi.jpg",
          category: "Đồ ngọt",
        },
        5: {
          id: 5,
          name: "Nước nho soda",
          nameEn: "Grape Soda",
          description: "Thức uống nho tím có gas vị thanh, ít đường",
          price: 29000,
          image: "assets/images/soda nho.jpg",
          category: "Đồ uống",
        },
        6: {
          id: 6,
          name: "Khoai tây mật ong bơ",
          nameEn: "Honey Butter Chips",
          description: "Khoai tây cắt lát mỏng chiên, phủ mật ong và bơ",
          price: 45760,
          image: "assets/images/khoai tay mat ong.jpg",
          category: "Đồ mặn",
        },
      };

      function toggleProductWishlist(productId) {
        const product = homeProductsData[productId];
        if (!product) return;

        const isAdded = toggleWishlist(product);
        const productCard = document.querySelector(
          `[data-product-id="${productId}"]`
        );
        const wishlistBtn = productCard?.querySelector(".wishlist-icon");

        if (wishlistBtn) {
          wishlistBtn.classList.toggle("active", isAdded);
        }
      }

      document.addEventListener("DOMContentLoaded", function () {
        Object.keys(homeProductsData).forEach((productId) => {
          const isInWishlistStatus = isInWishlist(parseInt(productId));
          const productCard = document.querySelector(
            `[data-product-id="${productId}"]`
          );
          const wishlistBtn = productCard?.querySelector(".wishlist-icon");

          if (wishlistBtn && isInWishlistStatus) {
            wishlistBtn.classList.add("active");
          }
        });
      });
    </script>
  </body>
</html>
