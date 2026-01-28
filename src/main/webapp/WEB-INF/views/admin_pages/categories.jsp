<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="main">
    <header class="topbar">
        <div class="header-group">
            <h1>Quản Lý Sản Phẩm</h1>
        </div>
        <div class="top-actions">
            <form action="categories" style="display: flex; gap: 5px;">
                <input type="text" name="search" placeholder="Tìm kiếm danh mục..." value="${search}"/>
                <button type="submit" class="btn primary">
                    <i class="fa-solid fa-magnifying-glass"></i> Tìm
                </button>
            </form>
            <button class="btn primary"
                    onClick="openModal('addCategoryModal')">Thêm danh mục
            </button>
        </div>
    </header>

    <section class="content">
        <div class="panel">
            <div class="panel-header">
                <h2>Danh sách danh mục(${not empty totalCategory? totalCategory : 0})</h2>
            </div>
            <div class="table-wrap">
                <table>
                    <thead>
                    <tr>
                        <th>Thứ tự</th>
                        <th>Tên danh mục</th>
                        <th>Số lượng sản phẩm</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                    <c:when test="${not empty categories}">
                    <c:forEach var="c" items="${categories}">
                    <tr>
                        <td>${c.display_order}</td>
                        <td>${c.name}</td>
                        <td>${c.totalProduct}</td>
                        <td>
                            <button class="btn"
                                    data-id="${c.id}"
                                    data-name="${c.name}"
                                    data-displayOrder="${c.display_order}"
                                    onClick="openEditCategoryModal(this)"
                            >
                                <i class="fa-solid fa-pen-to-square"></i>
                            </button>

                            <form action="delete-category" method="POST" style="display: inline;"
                                  onsubmit="return confirm('Bạn có chắc chắn muốn xóa sản phẩm ${p.name} không?');">
                                <input type="hidden" name="id" value="${c.id}">
                                <button type="submit" class="btn text-danger">
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            </form></td>
                    </tr>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                    <tr>
                        <td colspan="2">Không có danh mục</td>
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
                                <a href="${pageContext.request.contextPath}/admin/categories?page=1${not empty search? "&search=" += search : ""}">
                                    Quay lại trang đầu
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="p" begin="${beginPage}" end="${endPage}">
                            <li class="${p == currentPage ? 'active' : ''}">
                                <a href="${pageContext.request.contextPath}/admin/categories?page=${p}${not empty search? "&search=" += search : ""}">
                                        ${p}
                                </a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage != totalPage}">
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/categories?page=${totalPage}${not empty search? "&search=" += search : ""}">
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
<div class="modal" id="addCategoryModal" hidden aria-hidden="true" role="dialog" aria-labelledby="addProductTitle">
    <div class="modal-header">
        <h3>Thêm danh mục</h3>
        <button class="btn" data-close-modal aria-label="Đóng">✕</button>
    </div>
    <div class="modal-body">
        <form action="add-category" method="post">
            <div class="form-grid">
                <label class="full">
                    Tên sản phẩm
                    <input type="text" name="name"
                           placeholder="Nhập tên danh mục" required/>
                </label>
                <label class="full">Số thứ tự hiển thị
                    <input type="number" name="display_order"
                           placeholder="vd:1" required/>
                </label>

            </div>
            <div class="modal-actions">
                <button class="btn" data-close-modal type="button">Hủy</button>
                <button class="btn primary" type="submit">Thêm danh mục</button>
            </div>
        </form>
    </div>
</div>
<div class="modal" id="editCategoryModal" hidden aria-hidden="true" role="dialog" aria-labelledby="addProductTitle">
    <div class="modal-header">
        <h3>Thêm sản phẩm</h3>
        <button class="btn" data-close-modal aria-label="Đóng">✕</button>
    </div>
    <div class="modal-body">
        <form id="editCategoryForm" action="update-category" method="post">
            <div class="form-grid">
                <input type="hidden" name="id">
                <label class="full">
                    Tên danh mục
                    <input type="text" name="name"
                           placeholder="Nhập tên danh mục" required/>
                </label>
                <label class="full">
                    Số thứ tự hiển thị
                    <input type="number" name="display_order"
                           placeholder="vd:1" required/>
                </label>
            </div>
            <div class="modal-actions">
                <button class="btn" data-close-modal type="button">Hủy</button>
                <button class="btn primary" type="submit">Lưu danh mục</button>
            </div>
        </form>
    </div>
</div>
