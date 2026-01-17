document.addEventListener("DOMContentLoaded", function () {
  const navSearchInput = document.getElementById("nav-search");
  const productSearchInput = document.getElementById("search");

  if (navSearchInput) {
    navSearchInput.addEventListener("keypress", function (event) {
      if (event.key === "Enter") {
        event.preventDefault();
        performSearch(navSearchInput);
      }
    });
  }

  if (productSearchInput) {
    productSearchInput.addEventListener("keypress", function (event) {
      if (event.key === "Enter") {
        event.preventDefault();
        performSearch(productSearchInput);
      }
    });
  }

  function performSearch(inputElement) {
    const searchKeyword = inputElement.value.trim();
    const contextPath = document.body.getAttribute("data-context-path") || "";

    if (searchKeyword) {
      window.location.href = contextPath + "/products?search=" + encodeURIComponent(searchKeyword);
    } else {
      window.location.href = contextPath + "/products";
    }
  }
});
