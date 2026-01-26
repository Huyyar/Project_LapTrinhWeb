<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="main">
    <header class="topbar" id= "top_main">
        <div class="header-group">
            <h1>Quản Lý Người Dùng</h1>
        </div>
        <div class="top-actions">
            <form action="${pageContext.request.contextPath}/admin/users" style="display: flex; gap: 5px;">
                <input type="text" name="search" placeholder="Tìm kiếm người dùng..." value="${param.search}"/>
                <button type="submit" class="btn primary">
                    <i class="fa-solid fa-magnifying-glass"></i> Tìm
                </button>
            </form>
        </div>
    </header>

    <section class="content">
        <div class="panel">
            <div class="panel-header">
                <h2>Danh sách người dùng (${not empty totalUsers? totalUsers : 0})</h2>
            </div>
            <div class="table-wrap">
                <table>
                    <thead>
                    <tr>
                        <th>Tên</th>
                        <th>Email</th>
                        <th>Mật khẩu</th>
                        <th>Vai trò</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                    <c:when test="${not empty users}">
                    <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.fullname}</td>
                        <td>${u.email}</td>
                        <td>
                            <span class="password-display" data-user-id="${u.id}">
                                ••••••••
                            </span>
                        </td>
                        <td>
                            <span class="badge ${u.role == 'admin' ? 'admin-role' : 'user-role'}">
                                ${u.role == 'admin' ? 'Admin' : 'Người dùng'}
                            </span>
                        </td>
                        <td>
                            <span class="status-badge ${u.isIs_active() ? 'status-active' : 'status-locked'}">
                                ${u.isIs_active() ? 'Hoạt động' : 'Bị khóa'}
                            </span>
                        </td>
                        <td class="actions">
                            <button class="btn btn-sm btn-edit" 
                                    data-user-id="${u.id}"
                                    data-email="${u.email}"
                                    data-password="${u.password}"
                                    onclick="openChangePasswordModal(this.dataset.userId, this.dataset.email, this.dataset.password)"
                                    title="Đổi mật khẩu">
                                <i class="fa-solid fa-key"></i> Đổi MK
                            </button>
                            <c:if test="${u.role != 'admin'}">
                                <button class="btn btn-sm btn-lock" 
                                        data-user-id="${u.id}"
                                        data-email="${u.email}"
                                        data-active="${u.isIs_active() ? 'true' : 'false'}"
                                        onclick="lockUser(this.dataset.userId, this.dataset.email, this.dataset.active === 'true')"
                                        title="Khóa/Mở khóa người dùng">
                                    <i class="fa-solid fa-lock"></i> ${u.isIs_active() ? 'Khóa' : 'Mở khóa'}
                                </button>
                                <form action="${pageContext.request.contextPath}/admin/delete-user" method="POST" style="display: inline;"
                                      onsubmit="return confirm('Bạn có chắc chắn muốn xóa tài khoản ${u.fullname} không?');">
                                    <input type="hidden" name="userId" value="${u.id}">
                                    <button type="submit" class="btn btn-sm btn-delete" title="Xóa người dùng">
                                        <i class="fa-solid fa-trash"></i> Xóa
                                    </button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                    <tr>
                        <td colspan="6" style="text-align: center; padding: 20px;">Không có người dùng nào</td>
                    </tr>
                    </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>

<!-- Change Password Modal -->
<div class="modal" id="changePasswordModal" hidden>
    <div class="modal-header">
        <h2>Đổi Mật Khẩu</h2>
        <button class="icon-btn" onclick="closeModal('changePasswordModal')">
            <i class="fa-solid fa-xmark"></i>
        </button>
    </div>
    <div class="modal-body">
        <form id="changePasswordForm" method="POST" action="${pageContext.request.contextPath}/admin/change-user-password">
            <input type="hidden" id="userId" name="userId">
            <div class="form-group">
                <label for="userEmail">Email:</label>
                <input type="text" id="userEmail" readonly style="background-color: #f5f5f5;">
            </div>
            <div class="form-group">
                <label for="newPassword">Mật khẩu mới:</label>
                <div class="password-input-wrapper">
                    <input type="password" id="newPassword" name="newPassword" required>
                    <button type="button" class="toggle-password" onclick="togglePasswordVisibility()">
                        <i class="fa-solid fa-eye"></i>
                    </button>
                </div>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Xác nhận mật khẩu:</label>
                <div class="password-input-wrapper">
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                    <button type="button" class="toggle-password" onclick="toggleConfirmPasswordVisibility()">
                        <i class="fa-solid fa-eye"></i>
                    </button>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn secondary" onclick="closeModal('changePasswordModal')">Hủy</button>
                <button type="submit" class="btn primary">Cập nhật mật khẩu</button>
            </div>
        </form>
    </div>
</div>

<!-- Lock User Modal -->
<div class="modal" id="lockUserModal" hidden>
    <div class="modal-header">
        <h2 id="lockTitle">Khóa Người Dùng</h2>
        <button class="icon-btn" onclick="closeModal('lockUserModal')">
            <i class="fa-solid fa-xmark"></i>
        </button>
    </div>
    <div class="modal-body">
        <p id="lockMessage"></p>
        <form id="lockUserForm" method="POST" action="${pageContext.request.contextPath}/admin/lock-user">
            <input type="hidden" id="lockUserId" name="userId">
            <input type="hidden" id="lockStatus" name="action">
            <div class="modal-footer">
                <button type="button" class="btn secondary" onclick="closeModal('lockUserModal')">Hủy</button>
                <button type="submit" class="btn primary" id="lockSubmitBtn">Xác nhận</button>
            </div>
        </form>
    </div>
</div>
