<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://project.hcmuaf.edu.vn/functions" %>
<main class="main">
    <header class="topbar">
        <div class="header-group">
            <h1>Quản Lý Sản Phẩm</h1>
        </div>
        <div class="top-actions">
            <form action="products" style="display: flex; gap: 5px;">
                <input type="text" name="search" placeholder="Tìm kiếm sản phẩm..." value="${param.search}"/>
                <button type="submit" class="btn primary">
                    <i class="fa-solid fa-magnifying-glass"></i> Tìm
                </button>
            </form>
            <button class="btn primary"
                    onClick="openModal('addProductModal')">Thêm sản phẩm
            </button>
        </div>
    </header>

    <section class="content">
        <div class="panel">
            <div class="panel-header">
                <h2>Danh sách sản phẩm(${not empty totalProduct? totalProduct : 0})</h2>
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
                        <td><img src="${p.imgPath}" alt="${p.name}"
                                 class="product-image"/></td>
                        <td>${p.name}</td>
                        <td>${p.category}</td>
                        <td>${fn:formatPriceCompact(p.price)}</td>
                        <td>${p.inventory_qty}</td>
                        <td>
                            <span class="badge ${p.is_active ? 'active' : 'inactive'}">
                                    ${p.is_active ? 'Đang bán' : 'Tạm dừng'}
                            </span>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/product-detail" method="get" style="display: inline;">
                                <input type="hidden" name="id" value="${p.id}">
                                <button class="btn" type="submit"><i class="fa-solid fa-eye"></i></button>
                            </form>


                            <button class="btn"
                                    data-id="${p.id}"
                                    data-name="${p.name}"
                                    data-isActive="${p.is_active}"
                                    data-categoryId="${p.category_id}"
                                    data-featured="${p.featured}"
                                    data-description="${p.description}"
                                    data-price="${p.price}"
                                    data-imageUrl="${p.image_url}"
                                    data-inventoryQty="${p.inventory_qty}"
                                    onClick="openEditProductModal(this)"
                            ><i class="fa-solid fa-pen-to-square"></i></button>

                            <form action="delete-product" method="POST" style="display: inline;"
                                  onsubmit="return confirm('Bạn có chắc chắn muốn xóa sản phẩm ${p.name} không?');">
                                <input type="hidden" name="productId" value="${p.id}">
                                <button type="submit" class="btn text-danger">
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            </form>

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
                <c:if test="${totalPage > 0}">
                    <ul class="pagination">
                        <c:set var="maxVisible" value="5" />
                        <c:set var="half" value="2" /> <c:set var="beginPage" value="${currentPage - half > 1 ? currentPage - half : 1}" />
                        <c:set var="endPage" value="${beginPage + maxVisible - 1 > totalPage ? totalPage : beginPage + maxVisible - 1}" />

                        <c:if test="${endPage - beginPage < maxVisible - 1 && totalPage > maxVisible}">
                            <c:set var="beginPage" value="${endPage - maxVisible + 1 > 1 ? endPage - maxVisible + 1 : 1}" />
                        </c:if>
                        <c:if test="${currentPage != 1}">
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/products?page=1${not empty search? "&search=" += search : ""}">
                                    Quay lại trang đầu
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="p" begin="${beginPage}" end="${endPage}">
                            <li class="${p == currentPage ? 'active' : ''}">
                                <a href="${pageContext.request.contextPath}/admin/products?page=${p}${not empty search? "&search=" += search : ""}">
                                        ${p}
                                </a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage != totalPage}">
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/products?page=${totalPage}${not empty search? "&search=" += search : ""}">
                                    Về trang cuối(${totalPage})
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </c:if>
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
                        <c:choose>
                            <c:when test="${not empty categories}">
                                <c:forEach var="c" items="${categories}">
                                <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <span>Không có danh mục nào</span>
                            </c:otherwise>
                        </c:choose>
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

<div class="modal" id="editProductModal" hidden aria-hidden="true" role="dialog" aria-labelledby="addProductTitle">
    <div class="modal-header">
        <h3 id="addProductTitle">Thêm sản phẩm</h3>
        <button class="btn" data-close-modal aria-label="Đóng">✕</button>
    </div>
    <div class="modal-body">
        <form id="editProductForm" action="update-product" method="post">
            <div class="form-grid">
                <input type="hidden" name="id">
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
                        <c:choose>
                            <c:when test="${not empty categories}">
                                <c:forEach var="c" items="${categories}">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <span>Không có danh mục nào</span>
                            </c:otherwise>
                        </c:choose>
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
                    <input type="text" name="image_url" id="addImageUrl" required>
                </label>

            </div>

            <div class="modal-actions">
                <button class="btn" data-close-modal type="button">Hủy</button>
                <button class="btn primary" type="submit">Thêm hình ảnh sản phẩm</button>
            </div>
        </form>
    </div>
</div>
