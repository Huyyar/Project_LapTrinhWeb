const ADMIN_EMAIL = "admin@snackhub.vn";
// Dùng lưu trạng thái đăng nhập 
function getLoginState() {
  return sessionStorage.getItem("isLoggedIn") === "true";
}

function setLoginState(
  isLoggedIn,
  userData = null,
  redirectPath = "../index.html"
) {
  sessionStorage.setItem("isLoggedIn", isLoggedIn);
  if (isLoggedIn && userData) {
    sessionStorage.setItem("user", JSON.stringify(userData));
    sessionStorage.setItem(
      "isAdmin",
      userData.role === "admin" ? "true" : "false"
    );
  } else {
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("isAdmin");
  }

  if (redirectPath) {
    window.location.href = redirectPath;
  }
}

// chỉnh lại thanh điều hướng kkhi đăng nhập
function updateNavUI() {
  const isLoggedIn = getLoginState();
  const navAuth = document.querySelector(".nav-auth");
  const navActions = document.querySelector(".nav-actions");
  const isSubPage = window.location.pathname.includes("/pages/");
  const avatarPath = isSubPage
    ? "../assets/icons/icon_user.png"
    : "assets/icons/icon_user.png";

  // Xóa icon  cũ mỗi lần đăng nhập  để tránh trùng lặp
  const oldUserProfile = document.querySelector(".user-profile");
  if (oldUserProfile) {
    oldUserProfile.remove();
  }

  if (isLoggedIn) {
    // Ẩn nút Đăng nhập , đăng kí 
    if (navAuth) navAuth.style.display = "none";

    // Tạo và chèn icon người dùng vào thanh nav
    const userProfile = document.createElement("div");
    userProfile.className = "user-profile";
    userProfile.innerHTML = `
      <button class="user-icon-btn" aria-label="Tài khoản người dùng">
        <img src="${avatarPath}" alt="User Avatar" class="user-avatar">
      </button>
      <div class="user-dropdown">
        <a href="#">Profile</a>
        <a href="#">Sản phẩm yêu thích</a>
        <a href="#">Lịch sử mua hàng</a>
        <button id="logout-btn">Đăng xuất</button>
      </div>
    `;

    // Chèn icon vào trước nút giỏ hàng
    if (navActions) {
      navActions.insertBefore(
        userProfile,
        navActions.querySelector(".cart-button")
      );
    }

    // Thêm sự kiện cho nút đăng xuất
    const logoutBtn = document.getElementById("logout-btn");
    if (logoutBtn) {
      logoutBtn.addEventListener("click", () => {
        sessionStorage.removeItem("isLoggedIn");
        sessionStorage.removeItem("user");
        sessionStorage.removeItem("isAdmin");
        updateNavUI(); // Cập nhật lại UI 
        window.location.reload(); // Tải lại trang để xóa trạng thái hoàn toàn
      });
    }
  } else {
    // Hiển thị lại các nút "Đăng nhập" / "Đăng kí"
    if (navAuth) navAuth.style.display = "flex";
  }
}

// Gắn sự kiện submit cho các form
function handleAuthForms() {
  // Tìm form trên trang hiện tại
  const authForm = document.querySelector(".auth-form");
  if (!authForm) {
    return;
  }

  authForm.addEventListener("submit", (event) => {
    event.preventDefault(); // Ngăn form gửi đi

    const pageType = document.body.dataset.page;

    if (pageType === "login") {
      const emailInput = document.getElementById("login-email");
      const passwordInput = document.getElementById("login-password");

      if (!emailInput || !passwordInput) {
        return;
      }

      const email = emailInput.value.trim();
      const password = passwordInput.value.trim();

      if (!email || !password) {
        alert("Vui lòng nhập đầy đủ email và mật khẩu!");
        return;
      }

      if (email === ADMIN_EMAIL) {
        setLoginState(true, { email, role: "admin" }, "admin.html");
        return;
      }

      setLoginState(true, { email, role: "user" }, "../index.html");
    } else if (pageType === "signup") {
      const emailInput = document.getElementById("signup-email");
      if (!emailInput) {
        return;
      }

      const email = emailInput.value.trim();
      setLoginState(true, { email, role: "user" }, "../index.html");
    }
  });
}

// Chạy các hàm cần thiết khi trang đã tải xong
document.addEventListener("DOMContentLoaded", () => {
  // Chỉ chạy handleAuthForms trên trang login và register
  if (
    document.body.dataset.page === "login" ||
    document.body.dataset.page === "signup"
  ) {
    handleAuthForms();
  }
  // updateNavUI chạy trên mọi trang
  updateNavUI();
});
