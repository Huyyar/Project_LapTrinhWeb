<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://project.hcmuaf.edu.vn/functions" %>
<main>
    <section class="hero" id="home">
        <div class="hero-slideshow">
            <c:choose>
                <c:when test="${not empty slides}">
                    <c:forEach var="slide" items="${slides}" varStatus="status">
                        <div class="hero-slide ${status.first ? 'is-active' : ''}" data-hero-slide>
                            <img
                                src="${slide.imageUrl}"
                                alt="${slide.title}"
                            />
                            <div class="hero-slide-content">
                                <span class="hero-slide-chip">${slide.title}</span>
                                <c:if test="${not empty slide.description}">
                                    <h3>${slide.description}</h3>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <!-- Fallback slide nếu không có dữ liệu -->
                    <div class="hero-slide is-active">
                        <img
                            src="assets/images/slideshow_halloween.jpg"
                            alt="Chào mừng đến SnackHub"
                        />
                        <div class="hero-slide-content">
                            <span class="hero-slide-chip">SnackHub</span>
                            <h3>Chào mừng đến với SnackHub</h3>
                            <p>Khám phá thế giới snack đa dạng của chúng tôi</p>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

            <button
                    class="hero-slide-nav prev"
                    type="button"
                    data-hero-prev
                    aria-label="Chuyển về slide trước"
            >
                &lsaquo;
            </button>
            <button
                    class="hero-slide-nav next"
                    type="button"
                    data-hero-next
                    aria-label="Chuyển sang slide tiếp theo"
            >
                &rsaquo;
            </button>
        </div>
    </section>

    <div class="content">
        <div class="container">
            <div class="section-header stack">
                <div class="section-title">
                    <h2>Sản phẩm nổi bật hôm nay</h2>
                    <p class="section-subtitle">
                        Cùng khám các ưu đãi và món mới hấp dẫn.
                    </p>
                </div>
            </div>

            <div class="product-grid" data-product-grid="">
                <c:choose>
                    <c:when test="${not empty products}">
                        <c:forEach var="p" items="${products}">
                            <article class="product-card" data-product-id=${p.id}>
                                <div class="product-head">
                                    <button
                                            class="wishlist-icon"
                                            onclick="toggleProductWishlist(1)"
                                            title="Thêm vào yêu thích"
                                    >
                                        <i class="fa-regular fa-heart"></i>
                                        <i class="fa-solid fa-heart"></i>
                                    </button>
                                </div>

                                <div class="product-image">
                                    <img
                                            src="${p.imgPath}"
                                            alt="${p.name}"
                                            loading="lazy"
                                    />
                                    <span class="product-tag">${p.category}</span>
                                </div>
                                <div class="product-content">
                                    <h3>${p.name}</h3>
                                    <p class="product-description">${p.description}</p>
                                    <div class="product-price">
                                        <span class="product-price-current">${fn:formatPrice(p.price)}</span>
                                    </div>
                                </div>
                                <div class="card-actions">
                                    <a
                                            class="btn btn-outline"
                                            href="product-detail?id=${p.id}"
                                    >Xem chi tiết</a
                                    >
                                    <a href="add-cart?id=${p.id}&qty=1&page=home" class="btn btn-primary">Thêm vào giỏ</a>
                                </div>
                            </article>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <span>Không có sản phẩm</span>
                    </c:otherwise>
                </c:choose>
            </div>
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
                            <a href="${pageContext.request.contextPath}/home?page=1">
                                Quay lại trang đầu
                            </a>
                        </li>
                    </c:if>

                    <c:forEach var="p" begin="${beginPage}" end="${endPage}">
                        <li class="${p == currentPage ? 'active' : ''}">
                            <a href="${pageContext.request.contextPath}/home?page=${p}">
                                    ${p}
                            </a>
                        </li>
                    </c:forEach>
                    <c:if test="${currentPage != totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/home?page=${totalPage}">
                                Về trang cuối(${totalPage})
                            </a>
                        </li>
                    </c:if>
                </ul>
            </c:if>
        </div>
    </div>
</main>