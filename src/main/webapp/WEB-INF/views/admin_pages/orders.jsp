<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <main class="main">
          <header class="topbar">
              <div class="header-group">
                  <h1>Quản Lý Đơn Hàng</h1>
              </div>
              <div class="top-actions">
                  <form action="orders" style="display: flex; gap: 5px;">
                      <label for="order-status">Trạng thái đơn hàng:</label>
                      <select name="status" id="order-status">
                          <option value="all" ${empty status ? 'selected' : ''}>Tất cả đơn hàng</option>
                          <option value="processing" ${status == 'processing' ? 'selected' : ''}>Đang xử lý</option>
                          <option value="delivering" ${status == 'delivering' ? 'selected' : ''}>Đang giao hàng</option>
                          <option value="delivered" ${status == 'delivered' ? 'selected' : ''}>Đã hoàn thành</option>
                          <option value="cancelled" ${status == 'cancelled' ? 'selected' : ''}>Đã hủy</option>
                      </select>
                      <button type="submit" class="btn primary">
                          <i class="fa-solid fa-magnifying-glass"></i> Tìm
                      </button>
                  </form>
              </div>
          </header>

        <section class="content">
          <div class="panel" style="width: 100%">
            <div class="panel-header"><h2>Danh sách đơn hàng</h2></div>
            <div class="table-wrap">
              <table>
                <thead>
                  <tr>
                    <th>Mã đơn</th>
                    <th>Khách hàng</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                    <th>Ngày đặt</th>
                    <th>Hành động</th>
                  </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty orders}">
                        <c:forEach var="o" items="${orders}">
                        <tr>
                            <td>${o.order_code}</td>
                            <td>${o.full_name}<br /><small>${o.phone}</small></td>
                            <td>${o.total_amount}</td>
                            <td>
                                <span class="order-status
                                ${o.status=="processing" || o.status=="delivering" ? "status-pending" :
                                o.status == "delivered" ? "status-done" : o.status == "cancelled" ? "status-cancel" : ""}">${o.statusStr}</span>
                            </td>
                            <td>${o.formattedCreatedAt}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/order-detail" method="POST" target="_blank">
                                    <input type="hidden" name="orderId" value="${o.id}">
                                    <button
                                            class="btn order-detail-btn"
                                            type="submit"
                                    >
                                        <i class="fa-solid fa-eye"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7">Không có đơn hàng</td>
                        </tr>
                    </c:otherwise>
                </c:choose>

                </tbody>
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
                                <a href="${pageContext.request.contextPath}/admin/orders?page=1${not empty status? "&status=" += status : ""}">
                                    Quay lại trang đầu
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="p" begin="${beginPage}" end="${endPage}">
                            <li class="${p == currentPage ? 'active' : ''}">
                                <a href="${pageContext.request.contextPath}/admin/orders?page=${p}${not empty status? "&status=" += status : ""}">
                                        ${p}
                                </a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage != totalPage}">
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/orders?page=${totalPage}${not empty status? "&status=" += status : ""}">
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
