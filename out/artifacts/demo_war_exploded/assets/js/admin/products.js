function openAddProductImageModal(btn){
    const modal = document.getElementById('addProductImageModal');
    const overlay = document.getElementById("modalOverlay");
    const closeBtns = document.querySelectorAll("[data-close-modal]");

    const id = btn.dataset.id;
    const name = btn.dataset.name;
    document.getElementById('productIdInput').value = id;
    document.getElementById('productNameInModal').textContent = name;

    modal.hidden = false;
    overlay.hidden = false;
    modal.setAttribute("aria-hidden", "false");

    function closeModal() {
        modal.hidden = true;
        overlay.hidden = true;
        modal.setAttribute("aria-hidden", "true");
    }
    closeBtns.forEach(btn => {
        btn.addEventListener("click", closeModal);
    });

    overlay.addEventListener("click", closeModal);
}

