<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="main">
    <header class="topbar">
        <div class="header-group">
            <button class="toggle" id="toggleBtn">☰</button>
            <h1>Quản Lý hình ảnh</h1>
        </div>
        <div class="top-actions">
            <form action="image-manager" style="display: flex; gap: 5px;">
                <input type="text" name="search" placeholder="Tìm kiếm hình ảnh..." value="${param.search}"/>
                <button type="submit" class="btn primary">
                    <i class="fa-solid fa-magnifying-glass"></i> Tìm
                </button>
            </form>
            <button class="btn primary"
                    onClick="openModal('add-image-file-modal')">Thêm hình ảnh</button>
        </div>
    </header>

    <section class="content">
        <div class="panel">
            <div class="panel-header">
                <h2>Danh sách hình ảnh(${not empty totalImage? totalImage : 0})</h2>
            </div>
            <div class="table-wrap">
                <table>
                    <thead>
                    <tr>
                        <th>Ảnh</th>
                        <th>Tên ảnh</th>
                        <th>Url</th>
                        <th>Kích thước</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                    <c:when test="${not empty images}">
                    <c:forEach var="i" items="${images}">
                    <tr>
                        <td><img src="${i.fullPath}" alt="${i.fullPath}"
                                 class="product-image"/></td>
                        <td>${i.name}</td>
                        <td>${i.url}</td>
                        <td>${i.size} kb</td>
                        <td>
                            <button class="btn"
                                    data-name="${i.name}"
                                    data-url="${i.fullPath}"
                                    onClick="openRenameImageModal(this)">
                                <i class="fa-solid fa-pen-to-square"></i></button>
                            <form action="delete-image" method="POST" style="display: inline;"
                                  onsubmit="return confirm('Bạn có chắc chắn muốn xóa ảnh ${i.name} không?');">
                                <input type="hidden" name="name" value="${i.name}">
                                <button type="submit" class="btn text-danger">
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            </form>
                            <button class="btn btn-copy"
                                    onclick="copyUrl('${i.url}', this)"
                                    title="Copy link ảnh">
                                <i class="fa-solid fa-copy"></i>
                            </button>
                        </td>
                    </tr>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                    <tr>
                        <td colspan="4">Không có hình ảnh nào</td>
                    </tr>
                    </c:otherwise>
                    </c:choose>
                </table>
                <ul class="pagination">
                    <c:forEach var="p" begin="1" end="${totalPage}">
                        <li class="${p == currentPage ? 'active' : ''}">
                            <a href="${pageContext.request.contextPath}/admin/image-manager?page=${p}${not empty search? "&search=" += search : ""}">
                                    ${p}
                            </a>
                        </li>
                    </c:forEach>
                </ul>

            </div>
        </div>
    </section>
</main>

<div class="modal" id="add-image-file-modal" hidden aria-hidden="true" role="dialog">
    <div class="modal-header">
        <h3>Thêm hình ảnh</h3>
        <button class="icon-btn" data-close-modal>✕</button>
    </div>
    <div class="modal-body">
        <div id="uppy-dashboard"></div>
        <jsp:include page="/WEB-INF/views/partials/uploaded_list.jsp"/>
    </div>
</div>
<div class="modal" id="rename-image-modal" hidden aria-hidden="true" role="dialog">
    <div class="modal-header">
        <h3>Sửa tên hình ảnh</h3>
        <button class="icon-btn" data-close-modal>✕</button>
    </div>
    <div class="modal-body">
        <form action="rename-image" id="rename-image" method="post">
            <img src="" alt="" id="imageInModal">
            <span id="imageNameInModal" class="name-in-form"></span>
            <div class="form-flex">
                <label for="">
                    <input type="hidden" name="name" id="imageNameInput">
                </label>

                <label for="">Nhập tên mới
                    <input type="text" name="newName" id="imageNewNameInput" placeholder="Nhập tên mới">
                </label>
            </div>
            <div class="modal-actions">
                <button class="btn" data-close-modal type="button">Hủy</button>
                <button class="btn primary" type="submit">Lưu sản phẩm</button>
            </div>
        </form>
    </div>
</div>