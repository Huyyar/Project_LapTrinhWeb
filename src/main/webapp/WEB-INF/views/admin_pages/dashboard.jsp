<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <div class="stat-icon revenue">$</div>
                <div class="stat-content">
                  <h3>Doanh thu tuần</h3>
                  <div class="stat-value">690.450đ</div>
                  <div class="stat-change positive">+12.5%</div>
                </div>
              </div>

              <div class="stat-card">
                <div class="stat-icon orders">🛒</div>
                <div class="stat-content">
                  <h3>Đơn chờ xử lý</h3>
                  <div class="stat-value">1</div>
                  <div class="stat-note">Cần xử lý ngay</div>
                </div>
              </div>

              <div class="stat-card">
                <div class="stat-icon products">📦</div>
                <div class="stat-content">
                  <h3>Sản phẩm ẩn</h3>
                  <div class="stat-value">4</div>
                  <div class="stat-note">Trên 12 sản phẩm</div>
                </div>
              </div>

              <div class="stat-card">
                <div class="stat-icon users">👥</div>
                <div class="stat-content">
                  <h3>Số Người dùng</h3>
                  <div class="stat-value">9</div>
                  <div class="stat-note">
                    Có 5 người dùng tạo tài khoản hôm nay
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
                    <tr>
                      <td>SH20241101</td>
                      <td>Nguyễn Văn A<br /><small>2/11/2024</small></td>
                      <td>2 sản phẩm</td>
                      <td>207.400đ</td>
                      <td><span class="badge pending">Chờ xử lý</span></td>
                      <td><button class="btn btn-link">Chi tiết</button></td>
                    </tr>
                    <tr>
                      <td>SH20241102</td>
                      <td>Trần Thị B<br /><small>1/11/2024</small></td>
                      <td>1 sản phẩm</td>
                      <td>219.800đ</td>
                      <td><span class="badge confirmed">Đã xác nhận</span></td>
                      <td><button class="btn btn-link">Chi tiết</button></td>
                    </tr>
                    <tr>
                      <td>SH20241103</td>
                      <td>Lê Văn C<br /><small>31/10/2024</small></td>
                      <td>3 sản phẩm</td>
                      <td>256.170đ</td>
                      <td><span class="badge shipping">Đang giao</span></td>
                      <td><button class="btn btn-link">Chi tiết</button></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </section>
      </main>
