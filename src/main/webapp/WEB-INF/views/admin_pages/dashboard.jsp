<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <main class="main">
        <header class="topbar">
          <div>
            <h1>Dashboard</h1>
          </div>
          <div class="top-actions">
            <input id="globalSearch" placeholder="Tìm đơn hàng / khách..." />
          </div>
        </header>

        <section class="content">
          <!-- Dashboard Section -->
          <div class="section" id="section-dashboard">
            <h2 class="section-title">Tổng quan hoạt động kinh doanh</h2>

            <div class="stats-grid">
              <div class="stat-card">
                <div class="stat-icon revenue">💰</div>
                <div class="stat-content">
                  <h3>Doanh thu tuần</h3>
                  <div class="stat-value">
                    <fmt:formatNumber value="${stats.weeklyRevenue}" pattern="#,###"/>đ
                  </div>
                  <div class="stat-change positive">+12.5%</div>
                </div>
              </div>

              <div class="stat-card">
                <div class="stat-icon orders">🛒</div>
                <div class="stat-content">
                  <h3>Đơn chờ xử lý</h3>
                  <div class="stat-value">${stats.pendingOrders}</div>
                  <div class="stat-note">
                    <c:choose>
                      <c:when test="${stats.pendingOrders > 0}">Cần xử lý ngay</c:when>
                      <c:otherwise>Không có đơn chờ</c:otherwise>
                    </c:choose>
                  </div>
                </div>
              </div>

              <div class="stat-card">
                <div class="stat-icon products">📦</div>
                <div class="stat-content">
                  <h3>Sản phẩm ẩn</h3>
                  <div class="stat-value">${stats.hiddenProducts}</div>
                  <div class="stat-note">Trên ${stats.totalProducts} sản phẩm</div>
                </div>
              </div>

              <div class="stat-card">
                <div class="stat-icon users">👥</div>
                <div class="stat-content">
                  <h3>Số Người dùng</h3>
                  <div class="stat-value">${stats.totalUsers}</div>
                  <div class="stat-note">
                    Có ${stats.newUsersToday} người dùng tạo tài khoản hôm nay
                  </div>
                </div>
              </div>
            </div>

            <div class="panel">
              <div class="panel-header">
                <h2>Đơn hàng mới nhất</h2>
              </div>
              <div class="table-wrap">
                <table>
                  <thead>
                    <tr>
                      <th>Mã</th>
                      <th>Khách hàng</th>
                      <th>Sản phẩm</th>
                      <th>Tổng tiền</th>
                      <th>Trạng thái</th>
                      <th></th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:choose>
                      <c:when test="${not empty recentOrders}">
                        <c:forEach var="order" items="${recentOrders}">
                          <tr>
                            <td>${order.order_code}</td>
                            <td>
                              ${order.full_name}<br />
                              <small>
                                      ${order.formattedCreatedAt}
                              </small>
                            </td>
                            <td>${dashboardService.getOrderItemCount(order.id)} sản phẩm</td>
                            <td><fmt:formatNumber value="${order.total_amount}" pattern="#,###"/>đ</td>
                            <td>
                              <c:choose>
                                <c:when test="${order.status == 'pending'}">
                                  <span class="badge pending">Chờ xử lý</span>
                                </c:when>
                                <c:when test="${order.status == 'confirmed'}">
                                  <span class="badge confirmed">Đã xác nhận</span>
                                </c:when>
                                <c:when test="${order.status == 'shipping'}">
                                  <span class="badge shipping">Đang giao</span>
                                </c:when>
                                <c:when test="${order.status == 'delivered'}">
                                  <span class="badge delivered">Đã giao</span>
                                </c:when>
                                <c:when test="${order.status == 'cancelled'}">
                                  <span class="badge cancelled">Đã hủy</span>
                                </c:when>
                                <c:otherwise>
                                  <span class="badge">${order.status}</span>
                                </c:otherwise>
                              </c:choose>
                            </td>
                            <td>
                              <a href="${pageContext.request.contextPath}/admin/order-detail?id=${order.id}" class="btn btn-link">Chi tiết</a>
                            </td>
                          </tr>
                        </c:forEach>
                      </c:when>
                      <c:otherwise>
                        <tr>
                          <td colspan="6" style="text-align: center;">Chưa có đơn hàng nào</td>
                        </tr>
                      </c:otherwise>
                    </c:choose>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </section>
      </main>
