<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- CSS và JS cho Slideshow Management --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/admin_slideshow.css">
<script src="${pageContext.request.contextPath}/assets/js/admin/admin_slideshow.js" defer></script>

<main class="main">
    <%-- Thông báo thành công/lỗi --%>
    <c:if test="${not empty param.success}">
        <div class="alert alert-success">
            <i class="fa-solid fa-circle-check"></i>
            <c:choose>
                <c:when test="${param.success == 'add'}">Thêm slide thành công!</c:when>
                <c:when test="${param.success == 'update'}">Cập nhật slide thành công!</c:when>
                <c:when test="${param.success == 'delete'}">Xóa slide thành công!</c:when>
            </c:choose>
        </div>
    </c:if>
    <c:if test="${not empty param.error}">
        <div class="alert alert-error">
            <i class="fa-solid fa-circle-xmark"></i>
            <c:choose>
                <c:when test="${param.error == 'add_failed'}">Thêm slide thất bại!</c:when>
                <c:when test="${param.error == 'update_failed'}">Cập nhật slide thất bại!</c:when>
                <c:when test="${param.error == 'delete_failed'}">Xóa slide thất bại!</c:when>
                <c:otherwise>Có lỗi xảy ra. Vui lòng thử lại!</c:otherwise>
            </c:choose>
        </div>
    </c:if>
    
    <header class="topbar">
        <div class="header-group">
            <h1>Quản Lý Slideshow</h1>
        </div>
        <div class="top-actions">
            <button class="btn primary" onClick="openModal('addSlideModal')">
                <i class="fa-solid fa-plus"></i> Thêm Slide mới
            </button>
        </div>
    </header>

    <section class="content">
        <div class="panel">
            <div class="panel-header">
                <h2>Danh sách Slides (${not empty totalSlides ? totalSlides : 0})</h2>
            </div>
            <div class="table-wrap">
                <table>
                    <thead>
                    <tr>
                        <th>Ảnh xem trước</th>
                        <th>Tiêu đề</th>
                        <th>Thứ tự</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty slides}">
                            <c:forEach var="slide" items="${slides}">
                                <tr>
                                    <td>
                                        <img src="${slide.imageUrl}" alt="${slide.title}" class="slide-preview"/>
                                    </td>
                                    <td>${slide.title}</td>
                                    <td>
                                        <span class="priority-badge">${slide.priority}</span>
                                    </td>
                                    <td>
                                        <span class="badge ${slide.active ? 'active' : 'inactive'}">
                                            ${slide.active ? 'Hiển thị' : 'Ẩn'}
                                        </span>
                                    </td>
                                    <td>
                                        <button class="btn"
                                                data-id="${slide.id}"
                                                data-title="${slide.title}"
                                                data-description="${slide.description}"
                                                data-imageUrl="${slide.imageUrl}"
                                                data-priority="${slide.priority}"
                                                data-active="${slide.active ? '1' : '0'}"
                                                onClick="openEditSlideModal(this)">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </button>

                                        <form action="${pageContext.request.contextPath}/admin/delete-slide" method="POST"
                                              style="display: inline;"
                                              onsubmit="return confirm('Bạn có chắc chắn muốn xóa slide ${slide.title} không?');">
                                            <input type="hidden" name="slideId" value="${slide.id}">
                                            <button type="submit" class="btn text-danger">
                                                <i class="fa-solid fa-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5" style="text-align: center;">Chưa có slide nào</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>

                <c:if test="${totalPages > 1}">
                    <ul class="pagination">
                        <c:forEach var="p" begin="1" end="${totalPages}">
                            <li class="${p == currentPage ? 'active' : ''}">
                                <a href="${pageContext.request.contextPath}/admin/slideshow?page=${p}">
                                    ${p}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>
    </section>
</main>

<!-- Add Slide Modal -->
<div class="modal" id="addSlideModal" hidden aria-hidden="true" role="dialog" aria-labelledby="addSlideTitle">
    <div class="modal-header">
        <h3 id="addSlideTitle">Thêm Slide mới</h3>
        <button class="btn" data-close-modal aria-label="Đóng">✕</button>
    </div>
    <div class="modal-body">
        <form id="addSlideForm" action="${pageContext.request.contextPath}/admin/add-slide" method="post">
            <div class="form-grid">
                <label class="full">
                    <span>Tiêu đề Slide</span>
                    <input type="text" name="title" placeholder="Nhập tiêu đề slide" required/>
                </label>

                <label class="full">
                    <span>Mô tả</span>
                    <textarea name="description" rows="3" placeholder="Nhập mô tả cho slide (không bắt buộc)"></textarea>
                </label>

                <label class="full">
                    <span>URL Hình ảnh</span>
                    <input type="text" name="image_url" placeholder="/assets/images/slide1.jpg" required/>
                </label>

                <label>
                    <span>Thứ tự hiển thị</span>
                    <input type="number" name="priority" placeholder="0" value="0" required/>
                </label>

                <label>
                    <span>Trạng thái</span>
                    <select name="active" required>
                        <option value="1" selected>Hiển thị</option>
                        <option value="0">Ẩn</option>
                    </select>
                </label>
            </div>
            <div class="modal-actions">
                <button class="btn" data-close-modal type="button">Hủy</button>
                <button class="btn primary" type="submit">Lưu Slide</button>
            </div>
        </form>
    </div>
</div>

<!-- Edit Slide Modal -->
<div class="modal" id="editSlideModal" hidden aria-hidden="true" role="dialog" aria-labelledby="editSlideTitle">
    <div class="modal-header">
        <h3 id="editSlideTitle">Chỉnh sửa Slide</h3>
        <button class="btn" data-close-modal aria-label="Đóng">✕</button>
    </div>
    <div class="modal-body">
        <form id="editSlideForm" action="${pageContext.request.contextPath}/admin/update-slide" method="post">
            <div class="form-grid">
                <input type="hidden" name="id"/>
                <label class="full">
                    <span>Tiêu đề Slide</span>
                    <input type="text" name="title" placeholder="Nhập tiêu đề slide" required/>
                </label>

                <label class="full">
                    <span>Mô tả</span>
                    <textarea name="description" rows="3" placeholder="Nhập mô tả cho slide (không bắt buộc)"></textarea>
                </label>

                <label class="full">
                    <span>URL Hình ảnh</span>
                    <input type="text" name="image_url" placeholder="/assets/images/slide1.jpg" required/>

                </label>

                <label>
                    <span>Thứ tự hiển thị</span>
                    <input type="number" name="priority" placeholder="0" required/>
                </label>

                <label>
                    <span>Trạng thái</span>
                    <select name="active" required>
                        <option value="1">Hiển thị</option>
                        <option value="0">Ẩn</option>
                    </select>
                </label>
            </div>
            <div class="modal-actions">
                <button class="btn" data-close-modal type="button">Hủy</button>
                <button class="btn primary" type="submit">Cập nhật</button>
            </div>
        </form>
    </div>
</div>
