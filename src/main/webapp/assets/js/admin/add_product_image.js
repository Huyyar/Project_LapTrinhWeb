    document.querySelectorAll('.add-image-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        const productId = btn.dataset.productId;
        const productName = btn.dataset.productName;

        document.getElementById('productIdInput').value = productId;
        document.getElementById('productNameInModal').textContent = productName;

        const modal = document.getElementById('addProductImageModal');
        modal.hidden = false;
        modal.setAttribute('aria-hidden', 'false');
    });
});

    document.querySelectorAll('[data-close-modal]').forEach(btn => {
    btn.addEventListener('click', () => {
        const modal = btn.closest('.modal');
        modal.hidden = true;
        modal.setAttribute('aria-hidden', 'true');
    });
});
