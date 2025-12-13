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
});
