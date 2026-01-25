<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <main class="main">
          <header class="topbar">
              <div class="header-group">
                  <h1>Quản Lý Đơn Hàng</h1>
              </div>
              <div class="top-actions">
                  <form action="products" style="display: flex; gap: 5px;">
                      <input type="text" name="search" placeholder="Tìm kiếm đơn hàng..." value="${param.search}"/>
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
                                <button
                                        class="btn order-detail-btn"
                                        data-order-id="SH20241101"
                                >
                                    <i class="fa-solid fa-eye"></i>
                                </button>
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
                <ul class="pagination">
                    <c:forEach var="p" begin="1" end="${totalPage}">
                        <li class="${p == currentPage ? 'active' : ''}">
                            <a href="${pageContext.request.contextPath}/admin/orders?page=${p}">
                                    ${p}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
          </div>
        </section>

      </main>
