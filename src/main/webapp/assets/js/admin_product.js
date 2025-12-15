document.addEventListener("DOMContentLoaded", () => {
    const openBtn = document.getElementById("add-product-btn");
    const modal = document.getElementById("addProductModal");
    const overlay = document.getElementById("modalOverlay");
    const closeBtns = document.querySelectorAll("[data-close-modal]");

    if (!openBtn || !modal || !overlay) {
        console.error("Thiếu phần tử modal");
        return;
    }

    function openModal() {
        modal.hidden = false;
        overlay.hidden = false;
        modal.setAttribute("aria-hidden", "false");
    }

    function closeModal() {
        modal.hidden = true;
        overlay.hidden = true;
        modal.setAttribute("aria-hidden", "true");
    }

    openBtn.addEventListener("click", openModal);

    closeBtns.forEach(btn => {
        btn.addEventListener("click", closeModal);
    });

    overlay.addEventListener("click", closeModal);
});
