<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="main">
    <header class="topbar">
        <div class="header-group">
            <button class="toggle" id="toggleBtn">☰</button>
            <h1>Quản Lý Sản Phẩm</h1>
        </div>
        <div class="top-actions">
            <input placeholder="Tìm kiếm sản phẩm..."/>
            <button class="btn primary"
                    onClick="openModal('addProductModal')">Thêm sản phẩm</button>
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
                    <c:choose>
                    <c:when test="${not empty products}">
                    <c:forEach var="p" items="${products}">
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/${p.image_url}" alt="${p.name}" class="product-image"/></td>
                        <td>${p.name}</td>
                        <td>${p.category}</td>
                        <td>${p.price}₫</td>
                        <td>${p.inventory_qty}</td>
                        <td><span class="badge confirmed">Hiện</span></td>
                        <td>
                            <button class="btn"><i class="fa-solid fa-eye"></i></button>
                            <button class="btn"><i class="fa-solid fa-pen-to-square"></i></button>
                            <button class="btn"><i class="fa-solid fa-trash"></i></button>
                            <button class="btn"
                                    data-id="${p.id}"
                                    data-name="${p.name}"
                                    onClick="openAddProductImageModal(this)">
                                Thêm ảnh
                            </button>
                        </td>
                    </tr>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                    <tr>
                        <td colspan="7">Không có sản phẩm</td>
                    </tr>
                    </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </div>
    </section>
</main>

<div class="modal" id="addProductModal" hidden aria-hidden="true" role="dialog" aria-labelledby="addProductTitle">
    <div class="modal-header">
        <h3 id="addProductTitle">Thêm sản phẩm</h3>
        <button class="btn" data-close-modal aria-label="Đóng">✕</button>
    </div>
    <div class="modal-body">
        <form id="addProductForm" action="add-product" method="post">
            <div class="form-grid">
                <label>
                    Tên sản phẩm
                    <input type="text" name="name" placeholder="Nhập tên sản phẩm" required/>
                </label>
                <label>
                    Trạng thái
                    <select required name="is_active">
                        <option value="true">Đang bán</option>
                        <option value="false">Tạm dừng</option>
                    </select>
                </label>
                <label>
                    Danh mục
                    <select required name="category">
                        <option value="1">Bánh tráng</option>
                        <option value="2">Trái cây sấy</option>
                        <option value="3">Bánh</option>
                        <option value="4">Nước</option>
                        <option value="5">Khô các loại</option>
                        <option value="6">Mứt</option>
                        <option value="7">Kẹo</option>
                        <option value="8">Đậu-hạt dinh dưỡng</option>
                        <option value="9">Trà-nước giải nhiệt</option>
                    </select>

                </label>
                <label>
                    Featured
                    <select required name="featured">
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select>

                </label>
                <label>
                    Giá (₫)
                    <input type="number" name="price" placeholder="Ví dụ: 45000" required/>
                </label>
                <label>
                    Tồn kho
                    <input type="number" name="inventory_qty" placeholder="Ví dụ: 100" required/>
                </label>
                <label class="full">Hình ảnh sản phẩm
                            <input type="text" name="image_url" id="addImageUrl"
                                   placeholder="/assets/images/example.jpg" class="form-control"/>
                </label>
                <label class="full">
                    Mô tả
                    <textarea rows="3" name="description" placeholder="Mô tả ngắn"></textarea>
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
                    <input type="text" value="Rong biển cay giòn" required/>
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
                    <input type="number" value="35700" required/>
                </label>
                <label>
                    Tồn kho
                    <input type="number" value="180" required/>
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
                            <input type="url" id="editImageUrl" placeholder="https://example.com/image.jpg"
                                   class="form-control"/>
                            <button type="button" class="btn-add-url"
                                    onclick="addImageFromUrl('editImageUrl', 'editImagesPreview', 'editMainThumbnail')">
                                <i class="fa-solid fa-plus"></i> Thêm
                            </button>
                        </div>
                        <small class="hint-text">Nhập URL ảnh và nhấn "Thêm". Bạn có thể thêm nhiều ảnh (tối đa
                            10)</small>
                        <div id="editImagesPreview" class="modal-images-preview">
                            <!-- Sample existing image -->
                            <div class="modal-image-item" data-image-id="existing-1">
                                <img src="../../assets/images/Image_Rong_Bien.jpeg" alt="Rong Biển"/>
                                <button type="button" class="modal-btn-remove"><i class="fa-solid fa-xmark"></i>
                                </button>
                                <label class="modal-thumbnail-badge">
                                    <input type="radio" name="editMainThumbnail" value="existing-1" checked/>
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

<div class="modal" id="addProductImageModal" hidden aria-hidden="true" role="dialog">
    <div class="modal-header">
        <h3>Thêm hình ảnh sản phẩm</h3>
        <button class="icon-btn" data-close-modal>✕</button>
    </div>

    <div class="modal-body">
        <span id="productNameInModal" class="name-in-form"></span>
        <form id="addProductImageForm" action="add-product-image" method="post">
            <div class="form-grid">
                <label>
                    <input type="hidden" name="product_id" id="productIdInput">
                </label>

                <label class="full">
                    <span>URL Hình ảnh sản phẩm</span>
                    <input type="url" name="image_url" id="addImageUrl" required>
                </label>

            </div>

            <div class="modal-actions">
                <button class="btn" data-close-modal type="button">Hủy</button>
                <button class="btn primary" type="submit">Thêm hình ảnh sản phẩm</button>
            </div>
        </form>
    </div>
</div>
