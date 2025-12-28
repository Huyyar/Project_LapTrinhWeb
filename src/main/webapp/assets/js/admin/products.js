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
function openEditProductModal(btn) {
    const modal = document.getElementById('editProductModal');
    const overlay = document.getElementById("modalOverlay");
    const form = document.getElementById('editProductForm');

    // 1. Lấy dữ liệu từ dataset của nút được nhấn
    const ds = btn.dataset;

    // 2. Đổ dữ liệu vào các trường trong form
    // Tìm input/select theo thuộc tính 'name' để gán giá trị
    form.querySelector('input[name="id"]').value = ds.id;
    form.querySelector('input[name="name"]').value = ds.name;
    form.querySelector('input[name="price"]').value = ds.price;
    form.querySelector('input[name="inventory_qty"]').value = ds.inventoryqty;
    form.querySelector('input[name="image_url"]').value = ds.imageurl;
    form.querySelector('textarea[name="description"]').value = ds.description;

    // Đổ dữ liệu cho các thẻ Select
    form.querySelector('select[name="category"]').value = ds.categoryid;

    // Lưu ý: data-isactive thường trả về "1" hoặc "true" dưới dạng chuỗi
    form.querySelector('select[name="is_active"]').value = ds.isactive;
    form.querySelector('select[name="featured"]').value = ds.featured;

    // 3. Hiển thị Modal
    modal.hidden = false;
    if (overlay) overlay.hidden = false;
    modal.setAttribute("aria-hidden", "false");

    // 4. Định nghĩa hàm đóng modal
    const closeModal = () => {
        modal.hidden = true;
        if (overlay) overlay.hidden = true;
        modal.setAttribute("aria-hidden", "true");
    };

    // Gán sự kiện đóng cho các nút Close
    modal.querySelectorAll("[data-close-modal]").forEach(cBtn => {
        cBtn.onclick = closeModal;
    });

    if (overlay) overlay.onclick = closeModal;
}
