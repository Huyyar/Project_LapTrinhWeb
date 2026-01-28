/**
 * Thêm sản phẩm vào giỏ hàng với AJAX
 */
function addToCartAjax(productId, quantity, contextPath) {
  const url =
    contextPath +
    "/add-cart?id=" +
    productId +
    "&qty=" +
    quantity +
    "&ajax=true";

  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      if (data.success) {
        // Hiển thị thông báo
        alert(data.message);

        // Reload lại trang để cập nhật số lượng giỏ hàng
        location.reload();
      } else {
        alert("Không thể thêm sản phẩm vào giỏ hàng");
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("Có lỗi xảy ra khi thêm vào giỏ hàng");
    });
}

// Export để sử dụng global
window.addToCartAjax = addToCartAjax;
