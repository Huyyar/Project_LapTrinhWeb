// --- Admin Product Edit - Multi Image Upload Handler ---

document.addEventListener("DOMContentLoaded", function () {
  const productImagesInput = document.getElementById("productImages");
  const imagesPreviewGrid = document.getElementById("imagesPreviewGrid");

  let imageCounter = 1;
  const MAX_IMAGES = 10;

  if (!productImagesInput || !imagesPreviewGrid) {
    return;
  }

  // Handle file selection
  productImagesInput.addEventListener("change", function (event) {
    const files = Array.from(event.target.files);

    if (files.length === 0) return;

    // Check if adding these files would exceed the limit
    const currentImageCount = imagesPreviewGrid.querySelectorAll(
      ".image-preview-item"
    ).length;
    if (currentImageCount + files.length > MAX_IMAGES) {
      alert(
        `Bạn chỉ có thể tải lên tối đa ${MAX_IMAGES} ảnh. Hiện tại có ${currentImageCount} ảnh.`
      );
      return;
    }

    files.forEach((file) => {
      if (file.type.startsWith("image/")) {
        addImagePreview(file);
      }
    });

    // Reset input to allow selecting the same file again
    event.target.value = "";
  });

  // Add image preview to grid
  function addImagePreview(file) {
    const reader = new FileReader();

    reader.onload = function (e) {
      imageCounter++;
      const imageId = `img-${Date.now()}-${imageCounter}`;

      // Check if this is the first image
      const isFirstImage = imagesPreviewGrid.children.length === 0;

      const previewItem = document.createElement("div");
      previewItem.className = "image-preview-item";
      previewItem.setAttribute("data-image-id", imageId);

      previewItem.innerHTML = `
        <img src="${e.target.result}" alt="Product Image ${imageCounter}" />
        <button type="button" class="btn-remove-image" title="Xóa ảnh">
          <i class="fa-solid fa-xmark"></i>
        </button>
        <label class="thumbnail-badge">
          <input type="radio" name="mainThumbnail" value="${imageId}" ${
        isFirstImage ? "checked" : ""
      } />
          <span class="badge-icon">
            <i class="fa-solid fa-star"></i>
          </span>
          <span class="badge-text">Ảnh đại diện</span>
        </label>
      `;

      imagesPreviewGrid.appendChild(previewItem);

      // Add remove button event
      const removeBtn = previewItem.querySelector(".btn-remove-image");
      removeBtn.addEventListener("click", function (e) {
        e.stopPropagation();
        removeImage(previewItem, imageId);
      });

      // Show animation
      setTimeout(() => {
        previewItem.style.opacity = "0";
        previewItem.style.transform = "scale(0.8)";
        previewItem.style.transition = "all 0.3s ease";
        setTimeout(() => {
          previewItem.style.opacity = "1";
          previewItem.style.transform = "scale(1)";
        }, 10);
      }, 10);
    };

    reader.readAsDataURL(file);
  }

  // Remove image from grid
  function removeImage(previewItem, imageId) {
    const wasMainThumbnail = previewItem.querySelector(
      'input[name="mainThumbnail"]'
    ).checked;

    // Animate removal
    previewItem.style.opacity = "0";
    previewItem.style.transform = "scale(0.8)";

    setTimeout(() => {
      previewItem.remove();

      // If removed image was the main thumbnail, set first image as main
      if (wasMainThumbnail) {
        const firstRadio = imagesPreviewGrid.querySelector(
          'input[name="mainThumbnail"]'
        );
        if (firstRadio) {
          firstRadio.checked = true;
        }
      }
    }, 300);
  }

  // Handle existing images on page load
  const existingRemoveBtns =
    imagesPreviewGrid.querySelectorAll(".btn-remove-image");
  existingRemoveBtns.forEach((btn) => {
    btn.addEventListener("click", function (e) {
      e.stopPropagation();
      const previewItem = this.closest(".image-preview-item");
      const imageId = previewItem.getAttribute("data-image-id");
      removeImage(previewItem, imageId);
    });
  });

  // Form submission handler
  const editForm = document.getElementById("editProductForm");
  if (editForm) {
    editForm.addEventListener("submit", function (e) {
      e.preventDefault();

      // Get all images data
      const images = [];
      const imageItems = imagesPreviewGrid.querySelectorAll(
        ".image-preview-item"
      );

      imageItems.forEach((item, index) => {
        const img = item.querySelector("img");
        const isMain = item.querySelector(
          'input[name="mainThumbnail"]'
        ).checked;

        images.push({
          id: item.getAttribute("data-image-id"),
          src: img.src,
          alt: img.alt,
          isThumbnail: isMain,
          order: index,
        });
      });

      console.log("Product images:", images);

      // Get form data
      const name = document.getElementById("productName").value;
      const price = document.getElementById("productPrice").value;

      // Show loading state
      const btn = this.querySelector('button[type="submit"]');
      const originalText = btn.innerHTML;

      btn.innerHTML =
        '<i class="fa-solid fa-circle-notch fa-spin"></i> Đang lưu...';
      btn.disabled = true;

      // Simulate API call
      setTimeout(() => {
        alert(
          `Đã cập nhật sản phẩm: ${name}\nGiá mới: ${price}đ\nTổng số ảnh: ${images.length}`
        );

        // Reset button
        btn.innerHTML = originalText;
        btn.disabled = false;

        // Redirect (Demo)
        // window.location.href = './products.jsp';
      }, 1000);
    });
  }
});
