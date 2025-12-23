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