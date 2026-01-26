<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="order-history-page">
    <div class="container">
        <header>
            <h1>Đơn hàng của bạn</h1>
            <p>
                Cảm ơn bạn đã tin tưởng SnackHub. Chúng tôi đang chuẩn bị món ngon
                cho bạn.
            </p>
        </header>

        <div class="order-filters" aria-label="Lọc trạng thái đơn hàng">
            <a href="order-history" class="filter-btn
${empty status? "is-active" : ""}">Tất cả</a>
            <a href="order-history?status=processing" class="filter-btn
            ${status == "processing" ? "is-active" : ""}">Đang xử lý</a>
            <a href="order-history?status=delivering" class="filter-btn
${status == "delivering" ? "is-active" : ""}">Đang giao</a>
            <a href="order-history?status=delivered" class="filter-btn
${status == "delivered" ? "is-active" : ""}">Đã giao</a>
            <a href="order-history?status=cancelled" class="filter-btn
${status == "cancelled" ? "is-active" : ""}">Đã hủy</a>
        </div>

        <section class="order-list" aria-label="Danh sách đơn hàng">
            <c:choose>
                <c:when test="${not empty orders}">
                    <c:forEach var="o" items="${orders}">
                        <article class="order-card">
                            <header class="order-card-header">
                                <div>
                                    <span class="order-code">${o.order_code}</span>
                                    <time datetime="2024-11-02">${o.formattedCreatedAt}</time>
                                </div>
                                <span class="order-status
                                ${o.status=="processing" || o.status=="delivering" ? "status-pending" :
                                o.status == "delivered" ? "status-done" : o.status == "cancelled" ? "status-cancel" : ""}">${o.statusStr}</span>
                            </header>
                            <ul class="order-summary">
                                <c:forEach var="i" items="${o.order_items}">
                                    <li>
                                        <div class="left">
                                            <img src="${i.product_image_url}" alt="${i.product_name}"/>
                                            <span class="item-name">${i.product_name}</span>
                                        </div>
                                        <div class="right">
                                            <span class="item-qty">x${i.quantity}</span>
                                            <span class="item-price">${i.price}₫</span>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <footer class="order-card-footer">
                                <div class="order-total">
                                    <span>Tổng thanh toán</span>
                                    <strong>${o.total_amount}₫</strong>
                                </div>
                                <div class="order-actions">
                                    <form action="order-detail" method="POST" target="_blank">
                                        <input type="hidden" name="orderId" value="${o.id}">
                                        <button type="submit" class="link-detail" >Xem chi tiết</button>
                                    </form>
                                    <c:choose>
                                        <c:when test="${o.status =='cancelled' || o.status == 'delivered'}">
                                            <button type="button" class="btn-reorder">Mua lại đơn hàng</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn-reorder">Hủy đơn hàng</button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </footer>
                        </article>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <span>Chưa có đơn hàng nào</span>
                </c:otherwise>
            </c:choose>
        </section>
        <ul class="pagination">
            <c:forEach var="p" begin="1" end="${totalPage}">
                <li class="${p == currentPage ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/order-history?page=${p}">
                            ${p}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</main>

