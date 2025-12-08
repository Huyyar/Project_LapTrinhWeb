
    // --- Javascript xử lý giao diện ---

    // 1. Hàm xem trước ảnh khi upload
    function previewImage(event) {
      const reader = new FileReader();
      const file = event.target.files[0];
      
      if (file) {
        reader.onload = function(){
          const preview = document.getElementById('preview');
          preview.src = reader.result;
        }
        reader.readAsDataURL(file);
      }
    }

    // 2. Xử lý sự kiện Submit Form (Giả lập)
    document.getElementById('editProductForm').addEventListener('submit', function(e) {
      e.preventDefault();
      
      // Lấy dữ liệu (để demo logic)
      const name = document.getElementById('productName').value;
      const price = document.getElementById('productPrice').value;

      // Hiệu ứng thông báo giả lập
      const btn = this.querySelector('button[type="submit"]');
      const originalText = btn.innerHTML;
      
      btn.innerHTML = '<i class="fa-solid fa-circle-notch fa-spin"></i> Đang lưu...';
      btn.disabled = true;

      setTimeout(() => {
        alert(`Đã cập nhật sản phẩm: ${name}\nGiá mới: ${price}đ`);
        
        // Reset nút bấm
        btn.innerHTML = originalText;
        btn.disabled = false;
        
        // Chuyển hướng về trang danh sách (Giả lập)
        window.location.href = './admin_product.html';
      }, 1000);
    });

   

 