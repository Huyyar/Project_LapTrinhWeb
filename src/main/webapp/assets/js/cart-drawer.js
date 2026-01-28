// ===== CART DRAWER JAVASCRIPT =====

// Mock cart data (In production, this would come from localStorage or backend)
let cartData = [
  {
    id: 1,
    name: "Rong biển cay giòn",
    price: 35700,
    quantity: 2,
    image: "../../assets/images/Image_Rong_Bien.jpeg",
  },
  {
    id: 2,
    name: "Bắp rang caramel",
    price: 55000,
    quantity: 1,
    image: "../../assets/images/Image_Bap_Rang.jpg",
  },
];

// Constants
const SHIPPING_FEE = 30000;
const FREE_SHIPPING_THRESHOLD = 200000;

// Initialize cart on page load
document.addEventListener("DOMContentLoaded", function () {
  updateCartUI();
  updateCartCount();
});

// Toggle cart drawer
function toggleCartDrawer() {
  const drawer = document.getElementById("cartDrawer");
  const overlay = document.getElementById("cartOverlay");

  if (drawer && overlay) {
    const isActive = drawer.classList.contains("active");

    if (isActive) {
      drawer.classList.remove("active");
      overlay.classList.remove("active");
      document.body.style.overflow = "";
    } else {
      drawer.classList.add("active");
      overlay.classList.add("active");
      document.body.style.overflow = "hidden";
      updateCartUI(); // Refresh cart when opening
    }
  }
}

// Update cart count badge
function updateCartCount() {
  const totalItems = cartData.reduce((sum, item) => sum + item.quantity, 0);
  const countElements = document.querySelectorAll("#cartCount, #cartItemCount");

  countElements.forEach((el) => {
    if (el) {
      el.textContent = totalItems;
    }
  });
}

// Calculate totals
function calculateTotals() {
  const subtotal = cartData.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0,
  );
  const shipping = subtotal >= FREE_SHIPPING_THRESHOLD ? 0 : SHIPPING_FEE;
  const total = subtotal + shipping;

  return { subtotal, shipping, total };
}

// Format currency
function formatCurrency(amount) {
  return new Intl.NumberFormat("vi-VN").format(amount) + " đ";
}

// Render cart items
function renderCartItems() {
  const cartItemsContainer = document.getElementById("cartItems");

  if (!cartItemsContainer) return;

  if (cartData.length === 0) {
    cartItemsContainer.innerHTML = "";
    return;
  }

  const itemsHTML = cartData
    .map(
      (item) => `
    <div class="cart-item" data-id="${item.id}">
      <img src="${item.image}" alt="${item.name}" class="cart-item-image" />
      <div class="cart-item-info">
        <div class="cart-item-name">${item.name}</div>
        <div class="cart-item-price">${formatCurrency(item.price)}</div>
        <div class="cart-item-actions">
          <div class="quantity-control">
            <button class="btn-qty" onclick="updateQuantity(${item.id}, -1)">
              <i class="fa-solid fa-minus"></i>
            </button>
            <span class="qty-value">${item.quantity}</span>
            <button class="btn-qty" onclick="updateQuantity(${item.id}, 1)">
              <i class="fa-solid fa-plus"></i>
            </button>
          </div>
          <button class="btn-remove-item" onclick="removeItem(${
            item.id
          })" title="Xóa sản phẩm">
            <i class="fa-regular fa-trash-can"></i>
          </button>
        </div>
      </div>
    </div>
  `,
    )
    .join("");

  cartItemsContainer.innerHTML = itemsHTML;
}

// Update cart UI
function updateCartUI() {
  const cartEmpty = document.getElementById("cartEmpty");
  const cartItems = document.getElementById("cartItems");
  const cartFooter = document.getElementById("cartFooter");

  if (!cartEmpty || !cartItems || !cartFooter) return;

  if (cartData.length === 0) {
    // Show empty state
    cartEmpty.style.display = "flex";
    cartItems.style.display = "none";
    cartFooter.style.display = "none";
  } else {
    // Show cart items
    cartEmpty.style.display = "none";
    cartItems.style.display = "flex";
    cartFooter.style.display = "block";

    // Render items
    renderCartItems();

    // Update totals
    const { subtotal, shipping, total } = calculateTotals();

    const subtotalEl = document.getElementById("cartSubtotal");
    const shippingEl = document.getElementById("cartShipping");
    const totalEl = document.getElementById("cartTotal");

    if (subtotalEl) subtotalEl.textContent = formatCurrency(subtotal);
    if (shippingEl) {
      shippingEl.textContent =
        shipping === 0 ? "Miễn phí" : formatCurrency(shipping);
      shippingEl.style.color = shipping === 0 ? "var(--success, #0f9d58)" : "";
    }
    if (totalEl) totalEl.textContent = formatCurrency(total);
  }

  updateCartCount();
}

// Update quantity
function updateQuantity(itemId, change) {
  const item = cartData.find((i) => i.id === itemId);

  if (!item) return;

  item.quantity += change;

  // Remove if quantity is 0
  if (item.quantity <= 0) {
    removeItem(itemId);
    return;
  }

  // Update UI
  updateCartUI();
}

// Remove item
function removeItem(itemId) {
  cartData = cartData.filter((item) => item.id !== itemId);
  updateCartUI();
}

// Add item to cart (for product pages)
function addToCart(productId, productName, productPrice, productImage) {
  const existingItem = cartData.find((item) => item.id === productId);

  if (existingItem) {
    existingItem.quantity += 1;
  } else {
    cartData.push({
      id: productId,
      name: productName,
      price: productPrice,
      quantity: 1,
      image: productImage,
    });
  }

  updateCartUI();

  // Open drawer to show the added item
  toggleCartDrawer();

  // Show notification (optional)
  showNotification(`Đã thêm "${productName}" vào giỏ hàng`);
}

// Show notification (simple version)
function showNotification(message) {
  // Create notification element
  const notification = document.createElement("div");
  notification.style.cssText = `
    position: fixed;
    top: 80px;
    right: 20px;
    background: var(--success, #0f9d58);
    color: white;
    padding: 12px 20px;
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    z-index: 10000;
    animation: slideIn 0.3s ease;
  `;
  notification.textContent = message;

  // Add to page
  document.body.appendChild(notification);

  // Remove after 3 seconds
  setTimeout(() => {
    notification.style.animation = "slideOut 0.3s ease";
    setTimeout(() => {
      document.body.removeChild(notification);
    }, 300);
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

// Export functions for use in other scripts
if (typeof window !== "undefined") {
  window.toggleCartDrawer = toggleCartDrawer;
  window.addToCart = addToCart;
  window.updateQuantity = updateQuantity;
  window.removeItem = removeItem;
}
