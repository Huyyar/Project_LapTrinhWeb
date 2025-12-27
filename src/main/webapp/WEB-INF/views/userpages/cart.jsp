<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="cart-page">
    <div class="container">
        <header><h1>Giỏ hàng của bạn</h1>
            <p> Kiểm tra lại các món snack đã chọn, điều chỉnh số lượng và tiến hành thanh toán chỉ với vài bước. </p>
        </header>
        <div class="payment-steps">
            <div class="payment-step active">Giỏ hàng</div>
            <div class="payment-step">Thông tin giao hàng</div>
            <div class="payment-step">Hoàn tất</div>
        </div>
        <section class="content">
            <div class="cart-actions">
                <button class="select ${sessionScope.cart.isChoseAll? "active" : ""}"
                        onClick="handleChoseAllItem()"
                ><i class="fa-solid fa-check"></i></button>
                <button onClick="handleDeleteAllItem()"><i class="fa-regular fa-trash-can"></i></button>
            </div>
            <div class="items">
                <c:choose>
                    <c:when test="${not empty sessionScope.cart.items}">
                        <c:forEach items="${sessionScope.cart.items}" var="ci">
                            <div class="item">
                                <button class="select ${ci.isChose? "active" : ""}"
                                        onclick="handleChoseItem(${ci.product.id})"><i
                                        class="fa-solid fa-check"></i>
                                </button>
                                <div class="product"><img
                                        src="${pageContext.request.contextPath}/${ci.product.image_url}" alt="Yogurt"/>
                                    <div class="info"><span class="name">${ci.product.name}</span> <span
                                            class="price">${ci.product.price}</span></div>
                                </div>
                                <div class="quantity">
                                    <div class="quantity-container">
                                        <button class="btn" onClick="handleUpQty(${ci.product.id}, -1)">-</button>
                                        <span>${ci.qty}</span>
                                        <button class="btn" onClick="handleUpQty(${ci.product.id}, 1)">+</button>
                                    </div>
                                    <button class="delete"
                                            onClick="handleDeleteItem(${ci.product.id})"
                                    ><i class="fa-regular fa-trash-can"></i></button>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <span>Chưa thêm sản phẩm</span>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="empty" style="display: none"><p>Giỏ hàng của bạn chưa có sản phẩm nào!</p> <a
                    href="../../index.jsp" class="btn"><i class="fa-solid fa-cart-shopping"></i> Tiếp tục mua sắm! </a>
            </div>
            <footer>
                <span>
                    Thành tiền: ${not empty sessionScope.cart.totalPrice? sessionScope.cart.totalPrice : 0}đ
                </span>
                <a class="btn" href="checkout"> Thanh
                    toán </a>
            </footer>
        </section>
    </div>
</main>