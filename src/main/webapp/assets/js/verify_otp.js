// ===== VERIFY OTP PAGE JAVASCRIPT =====

// JavaScript xử lý logic OTP
document.addEventListener("DOMContentLoaded", function () {
  const inputs = document.querySelectorAll(".otp-input");
  const form = document.getElementById("otpForm");
  const otpValue = document.getElementById("otpValue");

  // Tự động chuyển focus sang ô tiếp theo khi nhập
  inputs.forEach((input, index) => {
    // Khi người dùng nhập số
    input.addEventListener("input", function (e) {
      const value = e.target.value;

      // Chỉ cho phép nhập số
      if (!/^[0-9]$/.test(value)) {
        e.target.value = "";
        return;
      }

      // Chuyển sang ô tiếp theo nếu đã nhập số
      if (value.length === 1 && index < inputs.length - 1) {
        inputs[index + 1].focus();
      }
    });

    // Xử lý khi nhấn Backspace
    input.addEventListener("keydown", function (e) {
      if (e.key === "Backspace") {
        // Nếu ô hiện tại trống, quay lại ô trước
        if (e.target.value === "" && index > 0) {
          inputs[index - 1].focus();
          inputs[index - 1].value = "";
        }
      }
    });

    // Xử lý paste (dán) mã OTP 6 số
    input.addEventListener("paste", function (e) {
      e.preventDefault();
      const pasteData = e.clipboardData.getData("text").trim();

      // Kiểm tra nếu dán đúng 6 chữ số
      if (/^\d{6}$/.test(pasteData)) {
        const digits = pasteData.split("");
        inputs.forEach((inp, idx) => {
          if (idx < digits.length) {
            inp.value = digits[idx];
          }
        });
        // Focus vào ô cuối
        inputs[inputs.length - 1].focus();
      }
    });
  });

  // Khi submit form
  form.addEventListener("submit", function (e) {
    e.preventDefault();

    // Ghép 6 số lại thành chuỗi OTP
    let otp = "";
    inputs.forEach((input) => {
      otp += input.value;
    });

    // Kiểm tra xem đã nhập đủ 6 số chưa
    if (otp.length !== 6) {
      alert("Vui lòng nhập đầy đủ 6 chữ số");
      inputs[0].focus();
      return;
    }

    // Gán giá trị OTP vào hidden input
    otpValue.value = otp;

    // Submit form
    form.submit();
  });

  // Auto focus vào ô đầu tiên khi trang load
  inputs[0].focus();

  // === XỬ LÝ COUNTDOWN TIMER CHO RESEND OTP ===
  const resendLink = document.getElementById("resendLink");
  const countdownElement = document.getElementById("countdown");
  let countdownTime = 60; // 60 giây
  let timer = null;

  // Hàm đếm ngược
  function startCountdown() {
    // Disable link gửi lại
    resendLink.classList.add("disabled");

    // Clear timer cũ nếu có
    if (timer) {
      clearInterval(timer);
    }

    // Cập nhật hiển thị mỗi giây
    timer = setInterval(function () {
      countdownElement.textContent = "(" + countdownTime + "s)";
      countdownTime--;

      // Khi hết thời gian
      if (countdownTime < 0) {
        clearInterval(timer);
        timer = null;
        countdownElement.textContent = "";
        resendLink.classList.remove("disabled");
        countdownTime = 60; // Reset lại
      }
    }, 1000);
  }

  // Bắt đầu đếm ngược khi trang load
  startCountdown();

  // Khi click vào link gửi lại OTP
  resendLink.addEventListener("click", function (e) {
    e.preventDefault(); // Ngăn chặn hành vi mặc định

    // Nếu đang disabled thì không làm gì
    if (resendLink.classList.contains("disabled")) {
      return false;
    }

    // Lấy context path từ body data attribute
    const contextPath = document.body.getAttribute("data-context-path") || "";

    // Gọi AJAX để gửi lại OTP
    fetch(contextPath + "/resend-otp", {
      method: "GET",
      credentials: "include",
    })
      .then((response) => response.text())
      .then((html) => {
        // Tạo element tạm để parse HTML response
        const tempDiv = document.createElement("div");
        tempDiv.innerHTML = html;

        // Lấy thông báo success/error từ response
        const successMsg = tempDiv.querySelector(".success-message");
        const errorMsg = tempDiv.querySelector(".error-message");

        // Xóa thông báo cũ
        const oldSuccess = document.querySelector(".success-message");
        const oldError = document.querySelector(".error-message");
        if (oldSuccess) oldSuccess.remove();
        if (oldError) oldError.remove();

        // Hiển thị thông báo mới
        const header = document.querySelector(".auth-card header");
        if (successMsg) {
          header.insertAdjacentElement("afterend", successMsg);
        } else if (errorMsg) {
          header.insertAdjacentElement("afterend", errorMsg);
        }

        // Reset countdown và bắt đầu lại
        countdownTime = 60;
        startCountdown();
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("Có lỗi xảy ra. Vui lòng thử lại.");
      });

    return false;
  });
});
