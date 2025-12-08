// UI-only modal toggling for admin_product page
// Wait for DOM to be ready before wiring events
(function () {
  // Ensure DOM is ready
  if (document.readyState === "loading") {
    document.addEventListener("DOMContentLoaded", init);
  } else {
    init();
  }

  function init() {
    const overlay = document.getElementById("modalOverlay");
    const addModal = document.getElementById("addProductModal");
    const editModal = document.getElementById("editProductModal");

    // Topbar: Add product button
    const addBtn = document.querySelector(".top-actions .btn.primary");

    // Table: Edit buttons (pen icon)
    const editBtns = Array.from(
      document.querySelectorAll("table .fa-pen-to-square")
    );

    function openModal(modal) {
      if (!modal) return;
      overlay.hidden = false;
      modal.hidden = false;
      modal.setAttribute("aria-hidden", "false");
      // lock scroll
      document.body.style.overflow = "hidden";
    }

    function closeAll() {
      overlay.hidden = true;
      [addModal, editModal].forEach((m) => {
        if (!m) return;
        m.hidden = true;
        m.setAttribute("aria-hidden", "true");
      });
      document.body.style.overflow = "";
    }

    // Wire Add button
    if (addBtn) {
      addBtn.addEventListener("click", () => openModal(addModal));
    }

    // Wire Edit buttons
    editBtns.forEach((icon) => {
      const btn = icon.closest("button");
      (btn || icon).addEventListener("click", () => openModal(editModal));
    });

    // Close handlers
    overlay && overlay.addEventListener("click", closeAll);
    document.querySelectorAll("[data-close-modal]").forEach((el) => {
      el.addEventListener("click", closeAll);
    });

    // Escape key to close
    document.addEventListener("keydown", (e) => {
      if (e.key === "Escape") closeAll();
    });
  }
})();
