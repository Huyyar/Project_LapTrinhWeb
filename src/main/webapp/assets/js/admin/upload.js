document.addEventListener("DOMContentLoaded", () => {
    const openBtn = document.getElementById("add-image-file-btn");
    const modal = document.getElementById("add-image-file-modal");
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
function copyUrl(url, button) {
    // 1. Sử dụng Clipboard API
    navigator.clipboard.writeText(url).then(() => {
        // 2. Phản hồi trực quan cho người dùng: Đổi icon thành dấu tích
        const icon = button.querySelector('i');
        const originalClass = icon.className;

        icon.className = 'fa-solid fa-check'; // Đổi sang icon tích xanh
        button.style.color = '#28a745'; // Đổi màu nút thành xanh lá

        // 3. Trả lại icon cũ sau 2 giây
        setTimeout(() => {
            icon.className = originalClass;
            button.style.color = '';
        }, 2000);

        // (Tùy chọn) Hiện thông báo nhỏ
        console.log('Đã copy: ' + url);
    }).catch(err => {
        console.error('Lỗi khi copy: ', err);
        alert('Không thể copy link!');
    });
}
