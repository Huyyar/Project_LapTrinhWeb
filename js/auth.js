const ADMIN_EMAIL = "admin@snackhub.vn";
const KEY_IS_LOGGED_IN = "isLoggedIn";
const KEY_USER = "user";
const KEY_IS_ADMIN = "isAdmin";

function getLoginState() {
  return sessionStorage.getItem(KEY_IS_LOGGED_IN) === "true";
}

function setLoginState(isLoggedIn, userData = null, redirectPath = null) {
  sessionStorage.setItem(KEY_IS_LOGGED_IN, isLoggedIn);
  if (isLoggedIn && userData) {
    sessionStorage.setItem(KEY_USER, JSON.stringify(userData));
    sessionStorage.setItem(
      KEY_IS_ADMIN,
      userData.role === "admin" ? "true" : "false"
    );
  } else {
    sessionStorage.removeItem(KEY_USER);
    sessionStorage.removeItem(KEY_IS_ADMIN);
  }

  if (redirectPath) {
    window.location.href = redirectPath;
  }
}


function updateNavUI() {
  const isLoggedIn = getLoginState();
  const navAuth = document.querySelector(".nav-auth");
  const navActions = document.querySelector(".nav-actions");
  const isSubPage = window.location.pathname.includes("/pages/");
  const avatarPath = isSubPage
    ? "../assets/icons/icon_user.png"
    : "assets/icons/icon_user.png";
  const orderHistoryPath = isSubPage
    ? "order_history.html"
    : "pages/order_history.html";
  const profilePath = isSubPage ? "profile.html" : "pages/profile.html";

  
  const oldUserProfile = document.querySelector(".user-profile");
  if (oldUserProfile) {
    oldUserProfile.remove();
  }

  if (isLoggedIn) {
  
    if (navAuth) navAuth.style.display = "none";


    const userProfile = document.createElement("div");
    userProfile.className = "user-profile";
    userProfile.innerHTML = `
      <button class="user-icon-btn" aria-label="Tài khoản người dùng">
        <img src="${avatarPath}" alt="User Avatar" class="user-avatar">
      </button>
      <div class="user-dropdown">
        <a href="${profilePath}">Profile</a>
        <a href="${orderHistoryPath}">Lịch sử mua hàng</a>
        <button id="logout-btn">Đăng xuất</button>
      </div>
    `;

  
    if (navActions) {
      navActions.insertBefore(
        userProfile,
        navActions.querySelector(".cart-button")
      );
    }

  
    const logoutBtn = document.getElementById("logout-btn");
    if (logoutBtn) {
      logoutBtn.addEventListener("click", () => {
        setLoginState(false);
        updateNavUI();
        window.location.reload();
      });
    }
  } else {
    
    if (navAuth) navAuth.style.display = "flex";
  }
}


function handleAuthForms() {

  const authForm = document.querySelector(".auth-form");
  if (!authForm) {
    return;
  }

  authForm.addEventListener("submit", (event) => {
    event.preventDefault(); 

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


document.addEventListener("DOMContentLoaded", () => {
  if (
    document.body.dataset.page === "login" ||
    document.body.dataset.page === "signup"
  ) {
    handleAuthForms();
  }

  updateNavUI();
});
