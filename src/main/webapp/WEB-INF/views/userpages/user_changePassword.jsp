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
        id="changePasswordForm"
        action="${pageContext.request.contextPath}/changePassword"
        method="post"
        onsubmit="return validateForm()"
      >
        <div id="errorMessage" class="error-message" style="display: none; color: #d32f2f; margin-bottom: 16px; padding: 12px; background-color: #ffebee; border-radius: 8px;"></div>
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

<script>
  function togglePassword(fieldId) {
    const inputField = document.getElementById(fieldId);
    const toggleSpan = inputField.closest('.password-input-wrapper').querySelector('.toggle-password');
    const eyeIcon = toggleSpan.querySelector('.fa-eye');
    const eyeOffIcon = toggleSpan.querySelector('.fa-eye-slash');

    if (inputField.type === 'password') {
      inputField.type = 'text';
      eyeIcon.style.display = 'none';
      eyeOffIcon.style.display = 'inline-block';
    } else {
      inputField.type = 'password';
      eyeIcon.style.display = 'inline-block';
      eyeOffIcon.style.display = 'none';
    }
  }

  function validateForm() {
    const oldPassword = document.getElementById('oldPassword').value.trim();
    const newPassword = document.getElementById('newPassword').value.trim();
    const rePassword = document.getElementById('rePassword').value.trim();
    const errorMessage = document.getElementById('errorMessage');

    // Reset error message
    errorMessage.style.display = 'none';
    errorMessage.textContent = '';

    // Validate empty fields
    if (!oldPassword) {
      showError('Vui lòng nhập mật khẩu cũ');
      return false;
    }

    if (!newPassword) {
      showError('Vui lòng nhập mật khẩu mới');
      return false;
    }

    if (!rePassword) {
      showError('Vui lòng nhập lại mật khẩu mới');
      return false;
    }

    // Validate new password is different from old password
    if (oldPassword === newPassword) {
      showError('Mật khẩu mới phải khác với mật khẩu cũ');
      return false;
    }

    // Validate passwords match
    if (newPassword !== rePassword) {
      showError('Mật khẩu mới không trùng khớp');
      return false;
    }

    // Validate password length
    if (newPassword.length < 6) {
      showError('Mật khẩu mới phải có ít nhất 6 ký tự');
      return false;
    }

    return true;
  }

  function showError(message) {
    const errorMessage = document.getElementById('errorMessage');
    errorMessage.textContent = message;
    errorMessage.style.display = 'block';
    // Scroll to error message
    errorMessage.scrollIntoView({ behavior: 'smooth', block: 'center' });
  }
</script>
