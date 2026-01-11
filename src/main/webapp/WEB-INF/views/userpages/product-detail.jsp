<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="product-detail-page">
    <div class="product-detail-container">
        <div class="product-detail-grid">

            <!-- IMAGE -->
            <div class="product-detail-images">
                <div class="main-image-container">
                    <img id="mainImage"
                         src="<c:choose>
                            <c:when test='${not empty productImages}'>
                                <c:forEach var='img' items='${productImages}'>
                                    <c:if test='${img.is_default}'>${img.image_url}</c:if>
                                </c:forEach>
                            </c:when>
                                <c:otherwise>${product.image_url}</c:otherwise>
                                </c:choose>"
                         alt="${product.name}" />

                </div>
                <div class="product-thumbnails">
                    <c:forEach var="img" items="${productImages}">
                        <img src="${img.image_url}"
                             class="thumbnail ${img.is_default ? 'active' : ''}"
                             onclick="changeMainImage(this, '${img.image_url}')">
                    </c:forEach>
                </div>


            </div>

            <!-- INFO -->
            <div class="product-detail-info">



                <!-- NAME -->
                <h1>${product.name}</h1>

                <!-- PRICE -->
                <div class="product-detail-price">
                    <fmt:formatNumber value="${product.price}" type="number" /> ₫
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

        </div>
    </div>
</div>

<script>
    function addToCart() {
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
