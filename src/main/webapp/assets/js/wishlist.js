let wishlist = JSON.parse(localStorage.getItem("wishlist")) || [];

const sampleProducts = [
  {
    id: 1,
    name: "Rong biển cay giòn",
    nameEn: "Spicy Crispy Seaweed",
    description: "Miếng rong biển nướng phủ gia vị cay nhẹ chuẩn Hàn Quốc",
    price: 35700,
    originalPrice: null,
    image: "../../assets/images/Image_Rong_Bien.jpeg",
    category: "Đồ mặn",
    badge: "Bán chạy",
  },
  {
    id: 2,
    name: "Khoai tây chiên giòn",
    nameEn: "Crispy Potato Chips",
    description: "Khoai tây lát mỏng chiên giòn vị nguyên bản",
    price: 28000,
    originalPrice: 35000,
    image: "../../assets/images/Image_Khoai_Tay.jpeg",
    category: "Đồ mặn",
    badge: "Giảm giá",
  },
];

// Initialize page when DOM is loaded
document.addEventListener("DOMContentLoaded", function () {
  loadWishlist();
  updateStats();
});

// Load wishlist items
function loadWishlist() {
  const wishlistGrid = document.getElementById("wishlistGrid");
  const emptyState = document.getElementById("emptyState");

  if (!wishlistGrid) return;

  // Clear grid except empty state
  const existingCards = wishlistGrid.querySelectorAll(".wishlist-card");
  existingCards.forEach((card) => card.remove());

  if (wishlist.length === 0) {
    emptyState.classList.remove("hidden");
    return;
  }

  emptyState.classList.add("hidden");

  // Render each wishlist item
  wishlist.forEach((item) => {
    const card = createWishlistCard(item);
    wishlistGrid.appendChild(card);
  });
}

// Create wishlist card HTML
function createWishlistCard(item) {
  const card = document.createElement("article");
  card.className = "wishlist-card";
  card.dataset.productId = item.id;

  const hasDiscount = item.originalPrice && item.originalPrice > item.price;

  card.innerHTML = `
    <div class="wishlist-card-image">
      <img src="${item.image}" alt="${item.name}" loading="lazy">
      <button class="wishlist-remove-btn" onclick="removeFromWishlist(${
        item.id
      })" title="Xóa khỏi danh sách yêu thích">
        <i class="fa-solid fa-heart-crack"></i>
      </button>
      ${
        item.category ? `<span class="product-tag">${item.category}</span>` : ""
      }
    </div>
    <div class="wishlist-card-content">
      <h3>${item.name}</h3>
      <p>${item.description || ""}</p>
      <div class="wishlist-card-price">
        <span class="price-current">${formatPrice(item.price)}</span>
        ${
          hasDiscount
            ? `<span class="price-original">${formatPrice(
                item.originalPrice,
              )}</span>`
            : ""
        }
      </div>
      <div class="wishlist-card-actions">
        <button class="btn-add-cart" onclick="addToCart(${item.id})">
          <i class="fa-solid fa-cart-plus"></i>
          Thêm vào giỏ
        </button>
        <button class="btn-view-detail" onclick="viewProductDetail(${
          item.id
        })" title="Xem chi tiết">
          <i class="fa-solid fa-eye"></i>
        </button>
      </div>
    </div>
  `;

  return card;
}

// Add product to wishlist
function addToWishlist(product) {
  // Check if product already exists
  const existingIndex = wishlist.findIndex((item) => item.id === product.id);

  if (existingIndex === -1) {
    wishlist.push(product);
    localStorage.setItem("wishlist", JSON.stringify(wishlist));

    // Show notification
    showNotification("Đã thêm vào danh sách yêu thích!", "success");

    // Update UI
    updateStats();
    loadWishlist();
    return true;
  } else {
    showNotification("Sản phẩm đã có trong danh sách yêu thích", "info");
    return false;
  }
}

// Remove product from wishlist
function removeFromWishlist(productId) {
  const index = wishlist.findIndex((item) => item.id === productId);

  if (index !== -1) {
    wishlist.splice(index, 1);
    localStorage.setItem("wishlist", JSON.stringify(wishlist));

    showNotification("Đã xóa khỏi danh sách yêu thích", "info");

    updateStats();
    loadWishlist();
  }
}

// Check if product is in wishlist
function isInWishlist(productId) {
  return wishlist.some((item) => item.id === productId);
}

// Toggle wishlist status
function toggleWishlist(product) {
  if (isInWishlist(product.id)) {
    removeFromWishlist(product.id);
    return false;
  } else {
    addToWishlist(product);
    return true;
  }
}

// Clear all wishlist
function clearWishlist() {
  if (wishlist.length === 0) {
    showNotification("Danh sách yêu thích đang trống", "info");
    return;
  }

  if (
    confirm("Bạn có chắc muốn xóa tất cả sản phẩm khỏi danh sách yêu thích?")
  ) {
    wishlist = [];
    localStorage.setItem("wishlist", JSON.stringify(wishlist));

    showNotification(
      "Đã xóa tất cả sản phẩm khỏi danh sách yêu thích",
      "success",
    );

    updateStats();
    loadWishlist();
  }
}

// Add all items to cart
function addAllToCart() {
  if (wishlist.length === 0) {
    showNotification("Danh sách yêu thích đang trống", "info");
    return;
  }

  // Get existing cart
  let cart = JSON.parse(localStorage.getItem("cart")) || [];

  // Add each wishlist item to cart
  wishlist.forEach((item) => {
    const existingItem = cart.find((cartItem) => cartItem.id === item.id);
    if (existingItem) {
      existingItem.quantity = (existingItem.quantity || 1) + 1;
    } else {
      cart.push({ ...item, quantity: 1 });
    }
  });

  localStorage.setItem("cart", JSON.stringify(cart));

  showNotification(
    `Đã thêm ${wishlist.length} sản phẩm vào giỏ hàng!`,
    "success",
  );

  // Update cart count if exists
  updateCartCount();
}

// Add single item to cart
function addToCart(productId) {
  const product = wishlist.find((item) => item.id === productId);
  if (!product) return;

  let cart = JSON.parse(localStorage.getItem("cart")) || [];

  const existingItem = cart.find((item) => item.id === productId);
  if (existingItem) {
    existingItem.quantity = (existingItem.quantity || 1) + 1;
  } else {
    cart.push({ ...product, quantity: 1 });
  }

  localStorage.setItem("cart", JSON.stringify(cart));

  showNotification("Đã thêm vào giỏ hàng!", "success");

  updateCartCount();
}

// View product detail
function viewProductDetail(productId) {
  window.location.href = `product-detail.html?id=${productId}`;
}

// Update wishlist statistics
function updateStats() {
  const countElement = document.getElementById("wishlistCount");
  const totalValueElement = document.getElementById("totalValue");

  if (countElement) {
    countElement.textContent = wishlist.length;
  }

  if (totalValueElement) {
    const totalValue = wishlist.reduce(
      (sum, item) => sum + (item.price || 0),
      0,
    );
    totalValueElement.textContent = formatPrice(totalValue);
  }

  // Update wishlist count badge if exists (for header)
  updateWishlistBadge();
}

// Update wishlist badge in header
function updateWishlistBadge() {
  const badge = document.querySelector(".wishlist-count-badge");
  if (badge) {
    badge.textContent = wishlist.length;
    badge.style.display = wishlist.length > 0 ? "flex" : "none";
  }
}

// Update cart count
function updateCartCount() {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const totalItems = cart.reduce((sum, item) => sum + (item.quantity || 1), 0);

  const cartCount = document.getElementById("cartCount");
  if (cartCount) {
    cartCount.textContent = totalItems;
  }
}

// Format price with currency
function formatPrice(price) {
  if (!price && price !== 0) return "0 đ";
  return new Intl.NumberFormat("vi-VN").format(price) + " đ";
}

// Show notification
function showNotification(message, type = "info") {
  // Create notification element
  const notification = document.createElement("div");
  notification.className = `notification notification-${type}`;
  notification.style.cssText = `
    position: fixed;
    top: 100px;
    right: 24px;
    background: ${
      type === "success" ? "#10b981" : type === "error" ? "#ef4444" : "#3b82f6"
    };
    color: white;
    padding: 16px 24px;
    border-radius: 12px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
    z-index: 10000;
    display: flex;
    align-items: center;
    gap: 12px;
    font-weight: 600;
    animation: slideIn 0.3s ease, slideOut 0.3s ease 2.7s;
  `;

  const icon = type === "success" ? "✓" : type === "error" ? "✕" : "ℹ";
  notification.innerHTML = `<span style="font-size: 1.2rem;">${icon}</span> ${message}`;

  document.body.appendChild(notification);

  // Remove after 3 seconds
  setTimeout(() => {
    notification.remove();
  }, 3000);
}

// Add animation styles
const style = document.createElement("style");
style.textContent = `
  @keyframes slideIn {
    from {
      transform: translateX(400px);
      opacity: 0;
    }
    to {
      transform: translateX(0);
      opacity: 1;
    }
  }
  
  @keyframes slideOut {
    from {
      transform: translateX(0);
      opacity: 1;
    }
    to {
      transform: translateX(400px);
      opacity: 0;
    }
  }
`;
document.head.appendChild(style);

// Export functions for use in other files
if (typeof module !== "undefined" && module.exports) {
  module.exports = {
    addToWishlist,
    removeFromWishlist,
    isInWishlist,
    toggleWishlist,
    wishlist,
  };
}
