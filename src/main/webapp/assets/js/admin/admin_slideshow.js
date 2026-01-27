function openEditSlideModal(button) {
  const modal = document.getElementById("editSlideModal");
  const overlay = document.getElementById("modalOverlay");
  const form = document.getElementById("editSlideForm");

  if (!modal || !form) {
    console.error("Khong tim thay form hoac modal de chinh sua slide");
    return;
  }

  // Get data from button's dataset
  const dataset = button.dataset;

  // Populate form fields with slide data
  const idInput = form.querySelector('input[name="id"]');
  const titleInput = form.querySelector('input[name="title"]');
  const descriptionTextarea = form.querySelector(
    'textarea[name="description"]',
  );
  const imageUrlInput = form.querySelector('input[name="image_url"]');
  const priorityInput = form.querySelector('input[name="priority"]');
  const activeSelect = form.querySelector('select[name="active"]');

  if (idInput) idInput.value = dataset.id || "";
  if (titleInput) titleInput.value = dataset.title || "";
  if (descriptionTextarea)
    descriptionTextarea.value = dataset.description || "";
  if (imageUrlInput) imageUrlInput.value = dataset.imageurl || "";
  if (priorityInput) priorityInput.value = dataset.priority || "0";
  if (activeSelect) activeSelect.value = dataset.active || "1";

  // Open the modal
  openModal("editSlideModal");
}

function previewSlideImage(input, previewId) {
  const previewContainer = document.getElementById(previewId);
  const previewImg = previewContainer?.querySelector("img");

  if (!previewContainer || !previewImg) return;

  const imageUrl = input.value.trim();

  if (imageUrl) {
    previewImg.src = imageUrl;
    previewImg.onerror = function () {
      previewContainer.classList.remove("show");
    };
    previewImg.onload = function () {
      previewContainer.classList.add("show");
    };
  } else {
    previewContainer.classList.remove("show");
  }
}

function validateSlideForm(form) {
  const title = form.querySelector('input[name="title"]')?.value.trim();
  const imageUrl = form.querySelector('input[name="image_url"]')?.value.trim();
  const priority = form.querySelector('input[name="priority"]')?.value;

  if (!title) {
    alert("Vui lòng nhập tiêu đề slide");
    return false;
  }

  if (!imageUrl) {
    alert("Vui lòng nhập URL hình ảnh");
    return false;
  }

  if (priority === "" || isNaN(priority)) {
    alert("Vui lòng nhập thứ tự hiển thị hợp lệ");
    return false;
  }

  return true;
}

/**
 * Initializes event listeners when DOM is loaded
 */
document.addEventListener("DOMContentLoaded", function () {
  // Add form validation on submit
  const addForm = document.getElementById("addSlideForm");
  const editForm = document.getElementById("editSlideForm");

  if (addForm) {
    addForm.addEventListener("submit", function (e) {
      if (!validateSlideForm(this)) {
        e.preventDefault();
      }
    });
  }

  if (editForm) {
    editForm.addEventListener("submit", function (e) {
      if (!validateSlideForm(this)) {
        e.preventDefault();
      }
    });
  }

  // Image preview functionality
  const addImageInput = document.querySelector(
    '#addSlideForm input[name="image_url"]',
  );
  const editImageInput = document.querySelector(
    '#editSlideForm input[name="image_url"]',
  );

  if (addImageInput) {
    addImageInput.addEventListener("input", function () {
      previewSlideImage(this, "addImagePreview");
    });
  }

  if (editImageInput) {
    editImageInput.addEventListener("input", function () {
      previewSlideImage(this, "editImagePreview");
    });
  }
});

function confirmDeleteSlide(slideName) {
  return confirm(`Bạn có chắc chắn muốn xóa slide "${slideName}" không?`);
}
