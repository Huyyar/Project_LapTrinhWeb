/* === XỬ LÝ PREVIEW ẢNH CHÍNH === */
function setupMainImagePreview() {
    const inputs = document.querySelectorAll('.input-preview-main');
    inputs.forEach(input => {
        input.addEventListener('input', function() {
            const container = this.parentNode.querySelector('.image-preview-container');
            const img = container.querySelector('.main-preview');
            if (this.value && this.value.trim() !== "") {
                img.src = this.value;
                img.style.display = 'block';
            } else {
                img.style.display = 'none';
                img.src = "";
            }
        });
    });
}

/* === XỬ LÝ ẢNH PHỤ (SUB IMAGES) === */
function addSubImageInput(containerId, value = "") {
    const container = document.getElementById(containerId);

    // Tạo wrapper
    const div = document.createElement('div');
    div.className = 'sub-image-group';

    // Tạo input
    const input = document.createElement('input');
    input.type = 'text';
    input.name = 'sub_images'; // Controller sẽ nhận getParameterValues("sub_images")
    input.placeholder = 'Nhập URL ảnh phụ...';
    input.value = value;
    input.style.flex = "1";

    // Tạo ảnh preview nhỏ
    const imgPreview = document.createElement('img');
    imgPreview.className = 'preview-img';
    imgPreview.style.width = '40px';
    imgPreview.style.height = '40px';
    imgPreview.style.marginLeft = '5px';
    if(value) {
        imgPreview.src = value;
        imgPreview.style.display = 'block';
    }

    // Sự kiện nhập liệu để hiện preview
    input.addEventListener('input', function() {
        if(this.value) {
            imgPreview.src = this.value;
            imgPreview.style.display = 'block';
        } else {
            imgPreview.style.display = 'none';
        }
    });

    // Nút xóa
    const btnRemove = document.createElement('button');
    btnRemove.type = 'button';
    btnRemove.className = 'btn-remove-img';
    btnRemove.innerHTML = '<i class="fa-solid fa-trash"></i>';
    btnRemove.onclick = function() {
        container.removeChild(div);
    };

    div.appendChild(input);
    div.appendChild(imgPreview);
    div.appendChild(btnRemove);
    container.appendChild(div);
}

/* === MODAL LOGIC === */
function openModal(modalId) {
    const modal = document.getElementById(modalId);
    const overlay = document.getElementById("modalOverlay");
    const closeBtns = document.querySelectorAll("[data-close-modal]");

    // Nếu là modal thêm mới, reset form và ảnh phụ
    if(modalId === 'addProductModal') {
        const form = document.getElementById('addProductForm');
        form.reset();
        document.getElementById('addProductSubImagesContainer').innerHTML = '';
        form.querySelector('.main-preview').style.display = 'none';
    }

    modal.hidden = false;
    if(overlay) overlay.hidden = false;
    modal.setAttribute("aria-hidden", "false");

    function closeModal() {
        modal.hidden = true;
        if(overlay) overlay.hidden = true;
        modal.setAttribute("aria-hidden", "true");
    }

    closeBtns.forEach(btn => {
        btn.onclick = closeModal;
    });

    if(overlay) overlay.onclick = closeModal;
}

function openEditProductModal(btn) {
    const modal = document.getElementById('editProductModal');
    const overlay = document.getElementById("modalOverlay");
    const form = document.getElementById('editProductForm');

    // 1. Lấy dữ liệu từ dataset
    const ds = btn.dataset;

    form.querySelector('input[name="id"]').value = ds.id;
    form.querySelector('input[name="name"]').value = ds.name;
    form.querySelector('input[name="price"]').value = ds.price;
    form.querySelector('input[name="inventory_qty"]').value = ds.inventoryqty;
    form.querySelector('textarea[name="description"]').value = ds.description;

    // Select box
    form.querySelector('select[name="category"]').value = ds.categoryid;
    form.querySelector('select[name="is_active"]').value = ds.isactive;
    form.querySelector('select[name="featured"]').value = ds.featured;

    // --- XỬ LÝ ẢNH CHÍNH (QUAN TRỌNG) ---
    const mainImgInput = form.querySelector('input[name="image_url"]');
    const mainImgPreview = form.querySelector('.main-preview');

    // Input: Dùng imageurl (giá trị lưu trong DB) để người dùng có thể sửa
    mainImgInput.value = ds.imageurl;

    // Preview: Dùng imgpath (đường dẫn hiển thị) để hiện ảnh đúng
    if (ds.imgpath && ds.imgpath.trim() !== "") {
        mainImgPreview.src = ds.imgpath;
        mainImgPreview.style.display = 'block';
    } else {
        // Fallback: nếu không có imgpath thì thử dùng imageurl
        if (ds.imageurl) {
            mainImgPreview.src = ds.imageurl;
            mainImgPreview.style.display = 'block';
        } else {
            mainImgPreview.style.display = 'none';
        }
    }
    // ------------------------------------

    // 2. Đổ dữ liệu ảnh phụ (Sub Images)
    const container = document.getElementById('editProductSubImagesContainer');
    container.innerHTML = '';

    const subImagesStr = ds.subImages || "";
    if(subImagesStr) {
        const urls = subImagesStr.split('|||');
        urls.forEach(url => {
            if(url && url.trim() !== "") {
                addSubImageInput('editProductSubImagesContainer', url);
            }
        });
    }

    // 3. Hiển thị Modal
    modal.hidden = false;
    if(overlay) overlay.hidden = false;
    modal.setAttribute("aria-hidden", "false");

    const closeModal = () => {
        modal.hidden = true;
        if(overlay) overlay.hidden = true;
        modal.setAttribute("aria-hidden", "true");
    };

    modal.querySelectorAll("[data-close-modal]").forEach(cBtn => cBtn.onclick = closeModal);
    if(overlay) overlay.onclick = closeModal;
}

function openAddProductImageModal(btn) {
    // Giữ nguyên logic cũ nếu cần dùng nút "Thêm ảnh" ở ngoài bảng
    const modal = document.getElementById('addProductImageModal');
    const overlay = document.getElementById("modalOverlay");
    const closeBtns = document.querySelectorAll("[data-close-modal]");

    const id = btn.dataset.id;
    const name = btn.dataset.name;
    document.getElementById('productIdInput').value = id;
    document.getElementById('productNameInModal').textContent = name;

    modal.hidden = false;
    if(overlay) overlay.hidden = false;
    modal.setAttribute("aria-hidden", "false");

    // ... (logic close modal tương tự)
    const closeModal = () => {
        modal.hidden = true;
        if(overlay) overlay.hidden = true;
        modal.setAttribute("aria-hidden", "true");
    };
    closeBtns.forEach(btn => btn.onclick = closeModal);
    if(overlay) overlay.onclick = closeModal;
}

// Khởi chạy listener cho preview ảnh chính khi load trang
document.addEventListener('DOMContentLoaded', () => {
    setupMainImagePreview();
});