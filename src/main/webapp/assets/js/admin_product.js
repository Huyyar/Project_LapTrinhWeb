document.addEventListener("DOMContentLoaded", () => {
  const overlay = document.getElementById("modalOverlay");
  const addModal = document.getElementById("addProductModal");
  const editModal = document.getElementById("editProductModal");

  const addBtn = document.querySelector(".top-actions .btn.primary");

  const editBtns = document.querySelectorAll(".fa-pen-to-square");

  function openModal(modal) {
    if (modal) {
      modal.hidden = false;
      overlay.hidden = false;
    }
  }

  function closeAll() {
    if (overlay) overlay.hidden = true;
    if (addModal) addModal.hidden = true;
    if (editModal) editModal.hidden = true;
  }

  if (addBtn) {
    addBtn.addEventListener("click", () => {
      console.log("Đã bấm thêm"); // Kiểm tra xem nút có nhận lệnh không
      openModal(addModal);
    });
  }

  editBtns.forEach((icon) => {
    const btn = icon.closest("button");
    if (btn) {
      btn.addEventListener("click", () => {
        console.log("Đã bấm sửa");
        openModal(editModal);
      });
    }
  });

  if (overlay) overlay.addEventListener("click", closeAll);

  const closeBtns = document.querySelectorAll("[data-close-modal]");
  closeBtns.forEach((btn) => btn.addEventListener("click", closeAll));

  // Handle existing images in edit modal
  setupExistingImageHandlers("editImagesPreview");

  // Form submission handlers
  const addForm = document.getElementById("addProductForm");
  if (addForm) {
    addForm.addEventListener("submit", function (e) {
      e.preventDefault();
      const images = getModalImages("addImagesPreview", "addMainThumbnail");
      console.log("Add Product - Images:", images);
      alert(`Sản phẩm đã được thêm!\nTổng số ảnh: ${images.length}`);
      closeAll();
      this.reset();
      document.getElementById("addImagesPreview").innerHTML = "";
    });
  }

  const editForm = document.getElementById("editProductForm");
  if (editForm) {
    editForm.addEventListener("submit", function (e) {
      e.preventDefault();
      const images = getModalImages("editImagesPreview", "editMainThumbnail");
      console.log("Edit Product - Images:", images);
      alert(`Sản phẩm đã được cập nhật!\nTổng số ảnh: ${images.length}`);
      closeAll();
    });
  }

  // Setup handlers for existing images
  function setupExistingImageHandlers(previewId) {
    const preview = document.getElementById(previewId);
    if (!preview) return;

    const removeBtns = preview.querySelectorAll(".modal-btn-remove");
    removeBtns.forEach((btn) => {
      btn.addEventListener("click", function (e) {
        e.stopPropagation();
        const item = this.closest(".modal-image-item");
        const radioName = item.querySelector('input[type="radio"]').name;
        removeModalImage(item, preview, radioName);
      });
    });
  }

  // Remove modal image
  function removeModalImage(item, preview, radioName) {
    const wasMain = item.querySelector('input[type="radio"]').checked;

    item.style.opacity = "0";
    item.style.transform = "scale(0.8)";

    setTimeout(() => {
      item.remove();

      if (wasMain) {
        const firstRadio = preview.querySelector(`input[name="${radioName}"]`);
        if (firstRadio) firstRadio.checked = true;
      }
    }, 300);
  }

  // Get all images from modal
  function getModalImages(previewId, radioName) {
    const preview = document.getElementById(previewId);
    if (!preview) return [];

    const images = [];
    const items = preview.querySelectorAll(".modal-image-item");

    items.forEach((item, index) => {
      const img = item.querySelector("img");
      const isMain = item.querySelector(`input[name="${radioName}"]`).checked;

      images.push({
        id: item.getAttribute("data-image-id"),
        src: img.src,
        alt: img.alt,
        isThumbnail: isMain,
        order: index,
      });
    });

    return images;
  }
});

// Add image from URL - Global function
window.addImageFromUrl = function (inputId, previewId, radioName) {
  const input = document.getElementById(inputId);
  const preview = document.getElementById(previewId);

  if (!input || !preview) return;

  const url = input.value.trim();
  if (!url) {
    alert("Vui lòng nhập URL ảnh");
    return;
  }

  // Validate URL
  try {
    new URL(url);
  } catch (e) {
    alert("URL không hợp lệ. Vui lòng nhập đúng định dạng URL");
    return;
  }

  const MAX_IMAGES = 10;
  const currentCount = preview.querySelectorAll(".modal-image-item").length;

  if (currentCount >= MAX_IMAGES) {
    alert(`Bạn chỉ có thể thêm tối đa ${MAX_IMAGES} ảnh.`);
    return;
  }

  const imageCounter = Date.now();
  const imageId = `modal-img-${imageCounter}`;
  const isFirst = preview.children.length === 0;

  const item = document.createElement("div");
  item.className = "modal-image-item";
  item.setAttribute("data-image-id", imageId);
  item.innerHTML = `
    <img src="${url}" alt="Product Image" onerror="this.parentElement.style.border='2px solid red'; this.alt='Ảnh lỗi';" />
    <button type="button" class="modal-btn-remove"><i class="fa-solid fa-xmark"></i></button>
    <label class="modal-thumbnail-badge">
      <input type="radio" name="${radioName}" value="${imageId}" ${
    isFirst ? "checked" : ""
  } />
      <i class="fa-solid fa-star"></i>
    </label>
  `;

  preview.appendChild(item);

  // Setup remove button
  const removeBtn = item.querySelector(".modal-btn-remove");
  removeBtn.addEventListener("click", function (e) {
    e.stopPropagation();
    const preview = document.getElementById(previewId);
    removeModalImage(item, preview, radioName);
  });

  // Animation
  setTimeout(() => {
    item.style.opacity = "0";
    item.style.transform = "scale(0.8)";
    item.style.transition = "all 0.3s ease";
    setTimeout(() => {
      item.style.opacity = "1";
      item.style.transform = "scale(1)";
    }, 10);
  }, 10);

  // Clear input
  input.value = "";
};

// Helper function to remove image (accessed from global context)
function removeModalImage(item, preview, radioName) {
  const wasMain = item.querySelector('input[type="radio"]').checked;

  item.style.opacity = "0";
  item.style.transform = "scale(0.8)";

  setTimeout(() => {
    item.remove();

    if (wasMain) {
      const firstRadio = preview.querySelector(`input[name="${radioName}"]`);
      if (firstRadio) firstRadio.checked = true;
    }
  }, 300);
}
