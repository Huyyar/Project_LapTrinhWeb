function openModal(modalId){
    const modal = document.getElementById(modalId);
    const overlay = document.getElementById("modalOverlay");
    const closeBtns = document.querySelectorAll("[data-close-modal]");

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