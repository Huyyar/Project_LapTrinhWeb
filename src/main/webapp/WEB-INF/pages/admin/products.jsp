<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="vi">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Products - Admin</title>
  <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700&display=swap"
    rel="stylesheet" />
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/admin_product.css">
</head>

<body>
  <div class="app">
     <aside class="sidebar" id="sidebar">
      <div class="brand">
        <div class="logo">🍿</div>
        <span class="brand-text">SnackHub Admin</span>
      </div>
      <nav class="nav-menu">
        <a href="./admin.html" class="nav-link">
          <i class="fa-solid fa-chart-pie"></i>
          <span>Dashboard</span>
        </a>
        <a href="./admin_product.html" class="nav-link active">
          <i class="fa-solid fa-box-open"></i>
          <span>Sản phẩm</span>
        </a>
        <a href="./orders.html" class="nav-link">
          <i class="fa-solid fa-clipboard-list"></i>
          <span>Đơn hàng</span>
        </a>
        <a href="./users.html" class="nav-link">
          <i class="fa-solid fa-users"></i>
          <span>Người dùng</span>
        </a>
        <a href="./admin_contact.html" class="nav-link">
          <i class="fa-solid fa-envelope"></i>
          <span>Liên Hệ</span>
        </a>
        <a href="../../index.jsp" class="nav-link" id="logout-btn" style="margin-top: auto; color: var(--danger);">
          <i class="fa-solid fa-right-from-bracket"></i>
          <span>Đăng xuất</span>
        </a>
      </nav>
    </aside>

    <main class="main">
      <header class="topbar">
        <div class="header-group">
          <button class="toggle" id="toggleBtn">☰</button>
          <h1>Quản Lý Sản Phẩm</h1>
        </div>
        <div class="top-actions">
          <input placeholder="Tìm kiếm sản phẩm..." />
          <button class="btn primary" id="add-product-btn">Thêm sản phẩm</button>
        </div>
      </header>

      <section class="content">
        <div class="panel">
          <div class="panel-header">
            <h2>Danh sách sản phẩm</h2>
          </div>
          <div class="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>Ảnh</th>
                  <th>Tên sản phẩm</th>
                  <th>Danh mục</th>
                  <th>Giá</th>
                  <th>Tồn kho</th>
                  <th>Trạng thái</th>
                  <th>Hành động</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td><img src="../../assets/images/banh_mochi.jpg" alt="Bánh mochi matcha" class="product-image" /></td>
                  <td>Bánh mochi matcha<br><small>Matcha Mochi</small></td>
                  <td>Đồ ngọt</td>
                  <td>58.900₫</td>
                  <td>40</td>
                  <td><span class="badge confirmed">Hiện</span></td>
                  <td><button class="btn"><i class="fa-solid fa-eye"></i></button> <button class="btn"><i class="fa-solid fa-pen-to-square"></i></button> <button class="btn"><i class="fa-solid fa-trash"></i></button>
                  </td>
                </tr>
            </table>
          </div>
        </div>
      </section>
    </main>
  </div>
  <!-- Modals: Add Product & Edit Product (UI only) -->
  <div class="modal-overlay" id="modalOverlay" hidden></div>

  <div class="modal" id="addProductModal" hidden aria-hidden="true" role="dialog" aria-labelledby="addProductTitle">
    <div class="modal-header">
      <h3 id="addProductTitle">Thêm sản phẩm</h3>
      <button class="icon-btn" data-close-modal aria-label="Đóng">✕</button>
    </div>
    <div class="modal-body">
      <form id="addProductForm" action="product" method="post">
        <div class="form-grid">
          <label>
            Tên sản phẩm
            <input type="text" name="name" placeholder="Nhập tên sản phẩm" required />
          </label>
          <label>
            Danh mục
            <select required name="category">
              <option>Đồ mặn</option>
              <option>Đồ ngọt</option>
              <option>Đồ uống</option>
            </select>
          </label>
          <label>
            Giá (₫)
            <input type="number" name="price" placeholder="Ví dụ: 45000" required />
          </label>
          <label>
            Tồn kho
            <input type="number" name="quantity" placeholder="Ví dụ: 100" required />
          </label>
          <label class="full">
            Mô tả
            <textarea rows="3" name="description" placeholder="Mô tả ngắn"></textarea>
          </label>

          <!-- Image URL Input Section -->
          <label class="full">
            <span>URL Hình ảnh sản phẩm</span>
            <div class="image-url-section">
              <div class="url-input-group">
                <input type="url" name="img" id="addImageUrl" placeholder="https://example.com/image.jpg" class="form-control" />
                <button type="button" class="btn-add-url" onclick="addImageFromUrl('addImageUrl', 'addImagesPreview', 'addMainThumbnail')">
                  <i class="fa-solid fa-plus"></i> Thêm
                </button>
              </div>
              <small class="hint-text">Nhập URL ảnh và nhấn "Thêm". Bạn có thể thêm nhiều ảnh (tối đa 10)</small>
              <div id="addImagesPreview" class="modal-images-preview"></div>
            </div>
          </label>
        </div>
        <div class="modal-actions">
          <button class="btn" data-close-modal type="button">Hủy</button>
          <button class="btn primary" type="submit">Lưu sản phẩm</button>
        </div>
      </form>
    </div>
  </div>

  <div class="modal" id="editProductModal" hidden aria-hidden="true" role="dialog" aria-labelledby="editProductTitle">
    <div class="modal-header">
      <h3 id="editProductTitle">Chỉnh sửa sản phẩm</h3>
      <button class="icon-btn" data-close-modal aria-label="Đóng">✕</button>
    </div>
    <div class="modal-body">
      <form id="editProductForm" onsubmit="return false">
        <div class="form-grid">
          <label>
            Tên sản phẩm
            <input type="text" value="Rong biển cay giòn" required />
          </label>
          <label>
            Danh mục
            <select required>
              <option selected>Đồ mặn</option>
              <option>Đồ ngọt</option>
              <option>Đồ uống</option>
            </select>
          </label>
          <label>
            Giá (₫)
            <input type="number" value="35700" required />
          </label>
          <label>
            Tồn kho
            <input type="number" value="180" required />
          </label>
          <label class="full">
            Trạng thái
            <select required>
              <option selected>Hiện</option>
              <option>Ẩn</option>
            </select>
          </label>

          <!-- Image URL Input Section -->
          <label class="full">
            <span>URL Hình ảnh sản phẩm</span>
            <div class="image-url-section">
              <div class="url-input-group">
                <input type="url" id="editImageUrl" placeholder="https://example.com/image.jpg" class="form-control" />
                <button type="button" class="btn-add-url" onclick="addImageFromUrl('editImageUrl', 'editImagesPreview', 'editMainThumbnail')">
                  <i class="fa-solid fa-plus"></i> Thêm
                </button>
              </div>
              <small class="hint-text">Nhập URL ảnh và nhấn "Thêm". Bạn có thể thêm nhiều ảnh (tối đa 10)</small>
              <div id="editImagesPreview" class="modal-images-preview">
                <!-- Sample existing image -->
                <div class="modal-image-item" data-image-id="existing-1">
                  <img src="../../assets/images/Image_Rong_Bien.jpeg" alt="Rong Biển" />
                  <button type="button" class="modal-btn-remove"><i class="fa-solid fa-xmark"></i></button>
                  <label class="modal-thumbnail-badge">
                    <input type="radio" name="editMainThumbnail" value="existing-1" checked />
                    <i class="fa-solid fa-star"></i>
                  </label>
                </div>
              </div>
            </div>
          </label>
        </div>
        <div class="modal-actions">
          <button class="btn" data-close-modal type="button">Hủy</button>
          <button class="btn primary" type="submit">Cập nhật sản phẩm</button>
        </div>
      </form>
    </div>
  </div>

  <script src="${pageContext.request.contextPath}/assets/js/admin_product.js" defer></script>

</body>

</html>