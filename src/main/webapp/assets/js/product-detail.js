document.addEventListener("DOMContentLoaded", function () {
  const mainImage = document.getElementById("mainImage");
  const thumbnails = document.querySelectorAll(".thumbnail");

  if (!mainImage || thumbnails.length === 0) {
    return;
  }

  thumbnails.forEach((thumbnail) => {
    thumbnail.addEventListener("click", function () {
      const newImageSrc = this.getAttribute("data-image");

      if (newImageSrc) {
        mainImage.style.opacity = "0.5";

        setTimeout(() => {
          mainImage.src = newImageSrc;
          mainImage.alt = this.querySelector("img").alt;
          mainImage.style.opacity = "1";
        }, 150);

        thumbnails.forEach((thumb) => thumb.classList.remove("active"));

        this.classList.add("active");
      }
    });

    thumbnail.setAttribute("tabindex", "0");
    thumbnail.addEventListener("keypress", function (e) {
      if (e.key === "Enter" || e.key === " ") {
        e.preventDefault();
        this.click();
      }
    });
  });

  const qtyInput = document.getElementById("product-quantity-input");
  const qtyBtns = document.querySelectorAll(".qty-btn");

  if (qtyInput && qtyBtns.length > 0) {
    qtyBtns.forEach((btn, index) => {
      btn.addEventListener("click", function () {
        let currentValue = parseInt(qtyInput.value) || 1;

        if (index === 0) {
          if (currentValue > 1) {
            qtyInput.value = currentValue - 1;
          }
        } else {
          qtyInput.value = currentValue + 1;
        }
      });
    });

    qtyInput.addEventListener("change", function () {
      let value = parseInt(this.value);
      if (isNaN(value) || value < 1) {
        this.value = 1;
      }
    });
  }
});
