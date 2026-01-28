document.addEventListener("DOMContentLoaded", function () {
  const sortSelect = document.getElementById("sort-select");

  if (sortSelect) {
    sortSelect.addEventListener("change", function () {
      const sortValue = this.value;
      const contextPath = document.body.getAttribute("data-context-path") || "";
      const urlParams = new URLSearchParams(window.location.search);

      // Giữ lại category filter nếu có
      urlParams.set("sort", sortValue);

      window.location.href = contextPath + "/products?" + urlParams.toString();
    });
  }
});

// Hàm lọc sản phẩm theo danh mục
function filterByCategory(categoryName) {
  const contextPath = document.body.getAttribute("data-context-path") || "";
  const urlParams = new URLSearchParams(window.location.search);

  // Xóa search keyword nếu có
  urlParams.delete("search");
  // Xóa sort để về mặc định
  urlParams.delete("sort");
  // Xóa page để về trang đầu
  urlParams.delete("page");

  if (categoryName && categoryName.trim() !== "") {
    urlParams.set("category", categoryName);
  } else {
    // Nếu chọn "Tất cả" thì xóa category filter
    urlParams.delete("category");
  }

  window.location.href = contextPath + "/products?" + urlParams.toString();
}
