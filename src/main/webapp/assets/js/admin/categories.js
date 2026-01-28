
function openEditCategoryModal(btn) {
    const modal = document.getElementById('editCategoryModal');
    const overlay = document.getElementById("modalOverlay");
    const form = document.getElementById('editCategoryForm');

    const ds = btn.dataset;
    form.querySelector('input[name="id"]').value = ds.id;
    form.querySelector('input[name="name"]').value = ds.name;
    form.querySelector('input[name="display_order"]').value = ds.displayorder;

    modal.hidden = false;
    if (overlay) overlay.hidden = false;
    modal.setAttribute("aria-hidden", "false");

    const closeModal = () => {
        modal.hidden = true;
        if (overlay) overlay.hidden = true;
        modal.setAttribute("aria-hidden", "true");
    };

    modal.querySelectorAll("[data-close-modal]").forEach(cBtn => {
        cBtn.onclick = closeModal;
    });

    if (overlay) overlay.onclick = closeModal;
}
