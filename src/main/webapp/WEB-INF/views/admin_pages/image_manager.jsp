<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="main">
    <header class="topbar">
        <div class="header-group">
            <button class="toggle" id="toggleBtn">☰</button>
            <h1>Quản Lý hình ảnh</h1>
        </div>
        <div class="top-actions">
            <input placeholder="Tìm kiếm hình ảnh..."/>
            <button class="btn primary" id="add-image-file-btn">Thêm hình ảnh</button>
        </div>
    </header>

    <section class="content">
        <div class="panel">
            <div class="panel-header">
                <h2>Danh sách hình ảnh</h2>
            </div>
            <div class="table-wrap">
                <table>
                    <thead>
                    <tr>
                        <th>Ảnh</th>
                        <th>Tên ảnh</th>
                        <th>Kích thước</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                    <c:when test="${not empty images}">
                    <c:forEach var="i" items="${images}">
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/${i.url}" alt="${i.url}"
                                 class="product-image"/></td>
                        <td>${i.name}</td>
                        <td>${i.size} kb</td>
                        <td>
                            <button class="btn"><i class="fa-solid fa-pen-to-square"></i></button>
                            <button class="btn"><i class="fa-solid fa-trash"></i></button>
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
    </div>
</div>