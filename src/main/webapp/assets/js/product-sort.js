document.addEventListener("DOMContentLoaded", function () {
  const sortSelect = document.getElementById("sort-select");

  if (sortSelect) {
    sortSelect.addEventListener("change", function () {
      const sortValue = this.value;
      const contextPath = document.body.getAttribute("data-context-path") || "";
      const urlParams = new URLSearchParams(window.location.search);

    
      urlParams.set("sort", sortValue);

     
      window.location.href = contextPath + "/products?" + urlParams.toString();
    });
  }
});
