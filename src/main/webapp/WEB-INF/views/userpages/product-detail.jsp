<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://project.hcmuaf.edu.vn/functions" %>

<c:set var="mainImageUrl" value="${product.image_url}" />

<c:if test="${not empty productImages}">
    <c:forEach var="img" items="${productImages}">
        <c:if test="${img.is_default}">
            <c:set var="mainImageUrl" value="${img.imgPath}" />
        </c:if>
    </c:forEach>
</c:if>

<div class="product-detail-page">
    <div class="product-detail-container">
        <div class="product-detail-grid">

            <!-- IMAGE -->
            <div class="product-detail-images">
                <div class="main-image-container">
                    <img id="mainImage"
                         src="${mainImageUrl}"
                         alt="${product.name}" />


                </div>
                <div class="product-thumbnails">
                    <c:forEach var="img" items="${productImages}">
                        <img src="${img.imgPath}"
                             class="thumbnail ${img.is_default ? 'active' : ''}"
                             onclick="changeMainImage(this, '${img.imgPath}')">
                    </c:forEach>
                </div>


            </div>

            <!-- INFO -->
            <div class="product-detail-info">



                <!-- NAME -->
                <h1>${product.name}</h1>

                <!-- PRICE -->
                <div class="product-detail-price">
                    ${fn:formatPrice(product.price)}
                </div>

                <!-- STOCK STATUS -->
                <c:choose>
                    <c:when test="${product.inventory_qty > 0}">
                        <div class="product-stock-status in-stock">
                            <span class="status-dot"></span>
                            <span>Tình trạng: Còn hàng (${product.inventory_qty})</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="product-stock-status out-stock">
                            <span class="status-dot"></span>
                            <span>Tình trạng: Hết hàng</span>
                        </div>
                    </c:otherwise>
                </c:choose>

                <!-- DESCRIPTION -->
                <p class="product-detail-description">
                    ${product.description}
                </p>

                <!-- QUANTITY -->
                <div class="product-quantity">
                    <label for="qtyInput">Số lượng</label>
                    <input type="number"
                           min="1"
                           max="${product.inventory_qty}"
                           value="1"
                           id="qtyInput">
                </div>

                <!-- ACTION -->
                <div class="product-detail-actions">
                    <button class="btn btn-primary" onclick="addToCart()">
                        <i class="fa-solid fa-cart-plus"></i>
                        Thêm vào giỏ hàng
                    </button>
                </div>

                <!-- BACK -->
                <div class="product-detail-links">
                    <a href="${pageContext.request.contextPath}/products" class="back-link">
                        ← Quay lại
                    </a>
                </div>
            </div>
            <!-- comment -->
            <div class = "product-detail-comment">
                <div class="product-comments">

                    <h3>Bình luận:</h3>

                    <c:forEach var="c" items="${comments}">
                        <div class="comment-item">

                            <!-- Avatar -->
                            <div class="comment-avatar">
                                <img src="${c.user.avatar_url != null
                                    ? c.user.avatar_url
                                    : pageContext.request.contextPath + '/assets/img/default-avatar.png'}">
                            </div>

                            <!-- Content -->
                            <div class="comment-body">
                                <div class="comment-header">
                                    <strong>${c.user.fullname}</strong>
                                    <span class="comment-date">
                                        <fmt:formatDate value="${c.createdAt}" pattern="dd/MM/yyyy HH:mm"/>
                                    </span>
                                </div>
                                <p>${c.content}</p>
                                <!-- ADMIN REPLY -->

                                <!-- ADMIN REPLY -->
                                <c:if test="${not empty c.replies}">
                                    <div class="comment-replies">
                                        <c:forEach var="r" items="${c.replies}">
                                            <div class="comment-reply">

                                                <div class="reply-header">
                                                    <span class="admin-badge">Quản trị viên</span>
                                                    <span class="comment-date">
                                                        <fmt:formatDate value="${r.createdAt}" pattern="dd/MM/yyyy HH:mm"/>
                                                    </span>
                                                </div>

                                                <p class="reply-content">${r.content}</p>

                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>



                            </div>

                        </div>
                    </c:forEach>
                    <!-- COMMENT MESSAGE -->
                    <c:if test="${param.comment == 'success'}">
                        <p class="comment-success">
                            Bình luận đã được gửi và đang chờ quản trị viên duyệt.
                        </p>
                        <script>
                            // Reload page sau 3 giây để lấy comment đã được duyệt
                            setTimeout(function() {
                                // Xóa comment parameter khỏi URL, giữ lại id
                                const url = new URL(window.location);
                                url.searchParams.delete('comment');
                                window.history.replaceState({}, document.title, url.toString());
                                location.reload();
                            }, 3000);
                        </script>
                    </c:if>

                    <c:if test="${param.error == 'empty'}">
                        <p class="comment-error">
                            Nội dung bình luận không được để trống.
                        </p>
                    </c:if>

                </div>



                <c:choose>
                    <c:when test="${empty sessionScope.auth}">
                        <p class="login-warning">Vui lòng đăng nhập để bình luận.</p>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/add-comment"
                              method="post"
                              class="comment-form">

                            <input type="hidden" name="productId" value="${product.id}">

                            <textarea name="content"
                                      placeholder="Viết bình luận của bạn..."
                                      ></textarea>

                            <button type="submit">Gửi bình luận</button>
                        </form>
                    </c:otherwise>
                </c:choose>






            </div>


        </div>
    </div>
</div>

<script>
    function addToCart() {
        // Kiểm tra đăng nhập
        <c:if test="${empty sessionScope.auth}">
            window.location.href = "${pageContext.request.contextPath}/login";
            return;
        </c:if>
        
        const qty = document.getElementById("qtyInput").value;
        window.location.href =
            "${pageContext.request.contextPath}/add-cart?id=${product.id}&qty=" + qty;
    }
    function changeMainImage(thumbnail, imageUrl) {
        document.getElementById("mainImage").src = imageUrl;
        const thumbnails = document.querySelectorAll(".product-thumbnails .thumbnail");
        thumbnails.forEach(img => img.classList.remove("active"));
        thumbnail.classList.add("active");
    }
</script>
