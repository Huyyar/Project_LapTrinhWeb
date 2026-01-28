<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="main">
    <header class="topbar">
        <div class="header-group">
            <h1>Quản Lý Sản Phẩm</h1>
        </div>
        <div class="top-actions">
            <form action="products" style="display: flex; gap: 5px;">
                <input type="text" name="search" placeholder="Tìm kiếm danh mục..." value="${param.search}"/>
                <button type="submit" class="btn primary">
                    <i class="fa-solid fa-magnifying-glass"></i> Tìm
                </button>
            </form>
            <button class="btn primary"
                    onClick="openModal('addProductModal')">Thêm danh mục
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
                        <td>1</td>
                        <td>
                            <button class="btn">
                                <i class="fa-solid fa-pen-to-square"></i>
                            </button>

                            <button type="submit" class="btn text-danger">
                            <i class="fa-solid fa-trash"></i>
                            </button></td>
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