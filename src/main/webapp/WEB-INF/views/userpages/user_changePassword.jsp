<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="profile-content">
  <section class="panel profile-section is-active">
    <header class="panel-header">
      <h2>Đổi mật khẩu</h2>
      <p>Cập nhật mật khẩu tài khoản của bạn</p>
    </header>

    <div class="panel-body">
      <form
        class="form-stack"
        action="${pageContext.request.contextPath}/changePassword"
        method="post"
      >
        <label>
          Mật khẩu cũ
          <div class="password-input-wrapper">
            <input
              type="password"
              id="oldPassword"
              name="oldPassword"
              placeholder="Nhập mật khẩu hiện tại"
            />
            <span
              class="toggle-password"
              onclick="togglePassword('oldPassword')"
            >
              <i class="fas fa-eye eye-icon" style="display: inline-block"></i>
              <i
                class="fas fa-eye-slash eye-off-icon"
                style="display: none"
              ></i>
            </span>
          </div>
        </label>

        <label>
          Mật khẩu mới
          <div class="password-input-wrapper">
            <input
              type="password"
              id="newPassword"
              name="newPassword"
              placeholder="Nhập mật khẩu mới"
            />
            <span
              class="toggle-password"
              onclick="togglePassword('newPassword')"
            >
              <i class="fas fa-eye eye-icon" style="display: inline-block"></i>
              <i
                class="fas fa-eye-slash eye-off-icon"
                style="display: none"
              ></i>
            </span>
          </div>
        </label>

        <label>
          Nhập lại mật khẩu mới
          <div class="password-input-wrapper">
            <input
              type="password"
              id="rePassword"
              name="rePassword"
              placeholder="Nhập lại mật khẩu mới"
            />
            <span
              class="toggle-password"
              onclick="togglePassword('rePassword')"
            >
              <i class="fas fa-eye eye-icon" style="display: inline-block"></i>
              <i
                class="fas fa-eye-slash eye-off-icon"
                style="display: none"
              ></i>
            </span>
          </div>
        </label>

        <button type="submit" class="btn-primary">Đổi mật khẩu</button>
      </form>
    </div>
  </section>
</section>
