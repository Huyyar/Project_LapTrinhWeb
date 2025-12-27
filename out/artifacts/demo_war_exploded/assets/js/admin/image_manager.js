function openRenameImageModal(btn){
    const modal = document.getElementById('rename-image-modal');
    const overlay = document.getElementById("modalOverlay");
    const closeBtns = document.querySelectorAll("[data-close-modal]");

    const name = btn.dataset.name;
    document.getElementById('imageNameInModal').textContent = name;
    document.getElementById('imageNameInput').value = name;
    document.getElementById('imageNewNameInput').value = name;
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

