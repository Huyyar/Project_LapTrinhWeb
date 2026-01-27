function openRenameImageModal(btn) {
    const modal = document.getElementById('rename-image-modal');
    const overlay = document.getElementById("modalOverlay");
    const closeBtns = modal.querySelectorAll("[data-close-modal]"); // Chỉ chọn nút trong modal này

    // 1. Lấy dữ liệu từ thuộc tính data-
    const name = btn.dataset.name;
    const url = btn.dataset.url; // Lấy URL ảnh

    // 2. Gán dữ liệu vào các phần tử trong modal
    document.getElementById('imageInModal').src = url; // Hiển thị ảnh
    document.getElementById('imageInModal').style.maxWidth = "100%"; // Đảm bảo ảnh không tràn

    document.getElementById('imageNameInModal').textContent = name;
    document.getElementById('imageNameInput').value = name;
    document.getElementById('imageNewNameInput').value = name;

    // 3. Hiển thị modal
    modal.hidden = false;
    overlay.hidden = false;
    modal.setAttribute("aria-hidden", "false");

    // Xử lý đóng modal
    function closeModal() {
        modal.hidden = true;
        overlay.hidden = true;
        modal.setAttribute("aria-hidden", "true");
        // Xóa src khi đóng để tránh lag ảnh cũ lần sau
        document.getElementById('imageInModal').src = "";
    }

    closeBtns.forEach(b => b.onclick = closeModal);
    overlay.onclick = closeModal;
}