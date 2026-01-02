(function () {
  "use strict";

  // API Endpoint
  const API_BASE_URL = "https://provinces.open-api.vn/api";

  // Cache data để tránh gọi API nhiều lần
  let provincesData = [];
  let districtsCache = {};
  let wardsCache = {};

  // Khởi tạo modal khi DOM đã sẵn sàng
  document.addEventListener("DOMContentLoaded", function () {
    initAddressModal();
    loadProvinces();
    initAddressActions();
  });

  function initAddressModal() {
    // Lấy các elements
    const addButton = document.querySelector('.btn-outline[type="button"]');
    const modalOverlay = document.getElementById("addressModalOverlay");
    const modal = document.getElementById("addressModal");
    const closeButton = document.getElementById("closeAddressModal");
    const cancelButton = document.getElementById("cancelAddressBtn");
    const form = document.getElementById("addressForm");

    // Kiểm tra xem các elements có tồn tại không
    if (!addButton || !modalOverlay || !modal) {
      return;
    }

    // Mở modal khi click "Thêm địa chỉ mới"
    addButton.addEventListener("click", function (e) {
      e.preventDefault();
      resetFormForAdd();
      openModal();
    });

    // Đóng modal khi click nút X
    if (closeButton) {
      closeButton.addEventListener("click", function (e) {
        e.preventDefault();
        closeModal();
      });
    }

    // Đóng modal khi click nút "Hủy"
    if (cancelButton) {
      cancelButton.addEventListener("click", function (e) {
        e.preventDefault();
        closeModal();
      });
    }

    // Đóng modal khi click vào overlay
    modalOverlay.addEventListener("click", function (e) {
      if (e.target === modalOverlay) {
        closeModal();
      }
    });

    // Xử lý form submit
    if (form) {
      form.addEventListener("submit", function (e) {
        e.preventDefault();
        handleFormSubmit();
      });
    }

    // Xử lý validation real-time
    setupFormValidation();

    // Xử lý dropdown tỉnh/quận/phường (placeholder cho tương lai)
    setupLocationDropdowns();
  }

  /**
   * Mở modal
   */
  function openModal() {
    const modalOverlay = document.getElementById("addressModalOverlay");
    const modal = document.getElementById("addressModal");

    // Ngăn scroll body khi modal mở
    document.body.style.overflow = "hidden";

    // Hiển thị overlay và modal
    modalOverlay.classList.add("active");
    modal.classList.add("active");

    // Focus vào input đầu tiên
    setTimeout(() => {
      const firstInput = modal.querySelector('input[type="text"]');
      if (firstInput) {
        firstInput.focus();
      }
    }, 300);
  }

  /**
   * Đóng modal
   */
  function closeModal() {
    const modalOverlay = document.getElementById("addressModalOverlay");
    const modal = document.getElementById("addressModal");
    const form = document.getElementById("addressForm");

    // Cho phép scroll body trở lại
    document.body.style.overflow = "";

    // Ẩn modal và overlay
    modalOverlay.classList.remove("active");
    modal.classList.remove("active");

    // Reset form sau khi đóng
    setTimeout(() => {
      if (form) {
        form.reset();
        resetFormForAdd();
        clearFormErrors();
      }
    }, 300);
  }

  /**
   * Reset form về trạng thái thêm mới
   */
  function resetFormForAdd() {
    const form = document.getElementById("addressForm");
    if (form) {
      form.reset();
    }
    
    // Reset hidden inputs
    document.getElementById("addressId").value = "";
    document.getElementById("formAction").value = "add";
    
    // Reset title
    const modalHeader = document.querySelector(".address-modal-header h2");
    if (modalHeader) {
      modalHeader.textContent = "Thêm địa chỉ mới";
    }
    
    // Reset dropdowns
    const districtSelect = document.getElementById("district");
    const wardSelect = document.getElementById("ward");
    if (districtSelect) {
      districtSelect.innerHTML = '<option value="">Chọn Quận/Huyện</option>';
      districtSelect.disabled = true;
    }
    if (wardSelect) {
      wardSelect.innerHTML = '<option value="">Chọn Phường/Xã</option>';
      wardSelect.disabled = true;
    }
    
    clearFormErrors();
  }

  /**
   * Xử lý submit form
   */
  function handleFormSubmit() {
    const form = document.getElementById("addressForm");
    const submitBtn = document.getElementById("submitAddressBtn");

    // Validate form
    if (!validateForm()) {
      return;
    }

    // Disable submit button để tránh double-click
    submitBtn.disabled = true;
    submitBtn.textContent = "Đang xử lý...";


    form.submit();
  }

  /**
   * Validate form
   */
  function validateForm() {
    const recipientName = document.getElementById("recipientName").value.trim();
    const recipientPhone = document
      .getElementById("recipientPhone")
      .value.trim();
    const province = document.getElementById("province").value;
    const district = document.getElementById("district").value;
    const ward = document.getElementById("ward").value;
    const addressDetail = document.getElementById("addressDetail").value.trim();

    // Clear errors trước
    clearFormErrors();

    let isValid = true;

    // Validate tên người nhận
    if (!recipientName) {
      showError("recipientName", "Vui lòng nhập tên người nhận");
      isValid = false;
    }

    // Validate số điện thoại
    if (!recipientPhone) {
      showError("recipientPhone", "Vui lòng nhập số điện thoại");
      isValid = false;
    }

    // Validate tỉnh/thành
    if (!province) {
      showError("province", "Vui lòng chọn Tỉnh/Thành phố");
      isValid = false;
    }

    // Validate quận/huyện
    if (!district) {
      showError("district", "Vui lòng chọn Quận/Huyện");
      isValid = false;
    }

    // Validate phường/xã
    if (!ward) {
      showError("ward", "Vui lòng chọn Phường/Xã");
      isValid = false;
    }

    // Validate địa chỉ cụ thể
    if (!addressDetail) {
      showError("addressDetail", "Vui lòng nhập địa chỉ cụ thể");
      isValid = false;
    }

    return isValid;
  }

  /**
   * Hiển thị lỗi cho input
   */
  function showError(fieldId, message) {
    const field = document.getElementById(fieldId);
    if (!field) return;

    field.style.borderColor = "#fe2c55";

    // Tạo error message element nếu chưa có
    let errorElement = field.parentElement.querySelector(".error-message");
    if (!errorElement) {
      errorElement = document.createElement("span");
      errorElement.className = "error-message";
      errorElement.style.color = "#fe2c55";
      errorElement.style.fontSize = "13px";
      errorElement.style.marginTop = "6px";
      errorElement.style.display = "block";
      field.parentElement.appendChild(errorElement);
    }

    errorElement.textContent = message;
  }

  /**
   * Xóa tất cả errors
   */
  function clearFormErrors() {
    const inputs = document.querySelectorAll(
      ".address-form-input, .address-form-select, .address-form-textarea"
    );
    inputs.forEach((input) => {
      input.style.borderColor = "";
    });

    const errorMessages = document.querySelectorAll(".error-message");
    errorMessages.forEach((error) => {
      error.remove();
    });
  }

  /**
   * Setup validation real-time
   */
  function setupFormValidation() {
    const inputs = document.querySelectorAll(
      ".address-form-input, .address-form-select, .address-form-textarea"
    );

    inputs.forEach((input) => {
      input.addEventListener("blur", function () {
        // Xóa error khi user bắt đầu nhập
        this.style.borderColor = "";
        const errorMessage = this.parentElement.querySelector(".error-message");
        if (errorMessage) {
          errorMessage.remove();
        }
      });

      input.addEventListener("input", function () {
        // Xóa error khi user nhập liệu
        this.style.borderColor = "";
        const errorMessage = this.parentElement.querySelector(".error-message");
        if (errorMessage) {
          errorMessage.remove();
        }
      });
    });
  }

  /**
   * Setup location dropdowns (Tỉnh/Quận/Phường)
   */
  function setupLocationDropdowns() {
    const provinceSelect = document.getElementById("province");
    const districtSelect = document.getElementById("district");
    const wardSelect = document.getElementById("ward");

    if (!provinceSelect || !districtSelect || !wardSelect) {
      return;
    }

    // Xử lý khi chọn tỉnh/thành phố
    provinceSelect.addEventListener("change", async function () {
      const provinceCode = this.value;
      const provinceName = this.options[this.selectedIndex].text;

      // Lưu tên tỉnh vào hidden input
      const provinceNameInput = document.getElementById("provinceName");
      if (provinceNameInput) {
        provinceNameInput.value = provinceCode ? provinceName : "";
      }

      // Reset district và ward
      districtSelect.innerHTML = '<option value="">Chọn Quận/Huyện</option>';
      districtSelect.disabled = true;
      wardSelect.innerHTML = '<option value="">Chọn Phường/Xã</option>';
      wardSelect.disabled = true;

      // Reset hidden inputs
      const districtNameInput = document.getElementById("districtName");
      const wardNameInput = document.getElementById("wardName");
      if (districtNameInput) districtNameInput.value = "";
      if (wardNameInput) wardNameInput.value = "";

      if (!provinceCode) {
        return;
      }

      // Load danh sách quận/huyện
      await loadDistricts(provinceCode);
    });

    // Xử lý khi chọn quận/huyện
    districtSelect.addEventListener("change", async function () {
      const districtCode = this.value;
      const districtName = this.options[this.selectedIndex].text;

      // Lưu tên quận/huyện vào hidden input
      const districtNameInput = document.getElementById("districtName");
      if (districtNameInput) {
        districtNameInput.value = districtCode ? districtName : "";
      }

      // Reset ward
      wardSelect.innerHTML = '<option value="">Chọn Phường/Xã</option>';
      wardSelect.disabled = true;

      // Reset hidden input
      const wardNameInput = document.getElementById("wardName");
      if (wardNameInput) wardNameInput.value = "";

      if (!districtCode) {
        return;
      }

      // Load danh sách phường/xã
      await loadWards(districtCode);
    });

    // Xử lý khi chọn phường/xã
    wardSelect.addEventListener("change", function () {
      const wardCode = this.value;
      const wardName = this.options[this.selectedIndex].text;

      // Lưu tên phường/xã vào hidden input
      const wardNameInput = document.getElementById("wardName");
      if (wardNameInput) {
        wardNameInput.value = wardCode ? wardName : "";
      }
    });
  }

  /**
   * Load danh sách tỉnh/thành phố từ API
   */
  async function loadProvinces() {
    try {
      const response = await fetch(`${API_BASE_URL}/p/`);

      if (!response.ok) {
        throw new Error("Không thể tải danh sách tỉnh/thành phố");
      }

      provincesData = await response.json();

      // Cập nhật dropdown
      const provinceSelect = document.getElementById("province");
      if (provinceSelect && provincesData.length > 0) {
        provinceSelect.innerHTML =
          '<option value="">Chọn Tỉnh/Thành phố</option>';

        provincesData.forEach((province) => {
          const option = document.createElement("option");
          option.value = province.code;
          option.textContent = province.name;
          provinceSelect.appendChild(option);
        });
      }
    } catch (error) {
      console.error("Error loading provinces:", error);
      alert("Không thể tải danh sách tỉnh/thành phố. Vui lòng thử lại!");
    }
  }

  /**
   * Load danh sách quận/huyện theo mã tỉnh
   */
  async function loadDistricts(provinceCode) {
    const districtSelect = document.getElementById("district");

    if (!districtSelect) {
      return;
    }

    // Kiểm tra cache
    if (districtsCache[provinceCode]) {
      populateDistricts(districtsCache[provinceCode]);
      return;
    }

    // Hiển thị loading
    districtSelect.innerHTML = '<option value="">Đang tải...</option>';
    districtSelect.disabled = true;

    try {
      const response = await fetch(`${API_BASE_URL}/p/${provinceCode}?depth=2`);

      if (!response.ok) {
        throw new Error("Không thể tải danh sách quận/huyện");
      }

      const provinceData = await response.json();
      const districts = provinceData.districts || [];

      // Lưu vào cache
      districtsCache[provinceCode] = districts;

      // Cập nhật dropdown
      populateDistricts(districts);
    } catch (error) {
      districtSelect.innerHTML = '<option value="">Chọn Quận/Huyện</option>';
      districtSelect.disabled = false;
      alert("Không thể tải danh sách quận/huyện. Vui lòng thử lại!");
    }
  }

  /**
   * Populate districts vào dropdown
   */
  function populateDistricts(districts) {
    const districtSelect = document.getElementById("district");

    if (!districtSelect) {
      return;
    }

    districtSelect.innerHTML = '<option value="">Chọn Quận/Huyện</option>';

    if (districts.length > 0) {
      districts.forEach((district) => {
        const option = document.createElement("option");
        option.value = district.code;
        option.textContent = district.name;
        districtSelect.appendChild(option);
      });

      districtSelect.disabled = false;
    }
  }

  /**
   * Load danh sách phường/xã theo mã quận/huyện
   */
  async function loadWards(districtCode) {
    const wardSelect = document.getElementById("ward");

    if (!wardSelect) {
      return;
    }

    // Kiểm tra cache
    if (wardsCache[districtCode]) {
      populateWards(wardsCache[districtCode]);
      return;
    }

    // Hiển thị loading
    wardSelect.innerHTML = '<option value="">Đang tải...</option>';
    wardSelect.disabled = true;

    try {
      const response = await fetch(`${API_BASE_URL}/d/${districtCode}?depth=2`);

      if (!response.ok) {
        throw new Error("Không thể tải danh sách phường/xã");
      }

      const districtData = await response.json();
      const wards = districtData.wards || [];

      // Lưu vào cache
      wardsCache[districtCode] = wards;

      // Cập nhật dropdown
      populateWards(wards);
    } catch (error) {
      console.error("Error loading wards:", error);
      wardSelect.innerHTML = '<option value="">Chọn Phường/Xã</option>';
      wardSelect.disabled = false;
      alert("Không thể tải danh sách phường/xã. Vui lòng thử lại!");
    }
  }

  /**
   * Populate wards vào dropdown
   */
  function populateWards(wards) {
    const wardSelect = document.getElementById("ward");

    if (!wardSelect) {
      return;
    }

    wardSelect.innerHTML = '<option value="">Chọn Phường/Xã</option>';

    if (wards.length > 0) {
      wards.forEach((ward) => {
        const option = document.createElement("option");
        option.value = ward.code;
        option.textContent = ward.name;
        wardSelect.appendChild(option);
      });

      wardSelect.disabled = false;
    }
  }

  /**
   * Khởi tạo xử lý các button trong danh sách địa chỉ
   */
  function initAddressActions() {
    const addressList = document.querySelector(".address-list");
    if (!addressList) return;

    // Event delegation - bắt sự kiện trên container cha
    addressList.addEventListener("click", function (e) {
      const target = e.target;

      // XỬ LÝ NÚT XÓA
      if (target.classList.contains("btn-delete")) {
        const addressId = target.getAttribute("data-id");
        handleDeleteAddress(addressId);
      }

      // XỬ LÝ NÚT ĐẶT MẶC ĐỊNH
      if (target.classList.contains("btn-set-default")) {
        const addressId = target.getAttribute("data-id");
        handleSetDefault(addressId);
      }

      // XỬ LÝ NÚT SỬA
      if (target.classList.contains("btn-edit")) {
        const addressId = target.getAttribute("data-id");
        handleEditAddress(addressId);
      }
    });
  }

  /**
   * Xử lý XÓA địa chỉ
   */
  function handleDeleteAddress(addressId) {
    // Confirm trước khi xóa
    if (!confirm("Bạn có chắc muốn xóa địa chỉ này?")) {
      return;
    }

    // Redirect đến URL xóa
    const contextPath =
      document.querySelector("body").dataset.contextPath || "";
    window.location.href = `${contextPath}/address?action=delete&id=${addressId}`;
  }

  /**
   * Xử lý ĐẶT MẶC ĐỊNH
   */
  function handleSetDefault(addressId) {
    const contextPath =
      document.querySelector("body").dataset.contextPath || "";
    window.location.href = `${contextPath}/address?action=setDefault&id=${addressId}`;
  }

  /**
   * Xử lý SỬA địa chỉ
   */
  async function handleEditAddress(addressId) {
    try {
      // Lấy thẻ article chứa data
      const addressCard = document.querySelector(`[data-address-id="${addressId}"]`);
      if (!addressCard) {
        throw new Error("Không tìm thấy thông tin địa chỉ");
      }
      
      // Lấy data từ data attributes
      const addressData = {
        id: addressCard.getAttribute("data-address-id"),
        recipientName: addressCard.getAttribute("data-recipient-name"),
        recipientPhone: addressCard.getAttribute("data-recipient-phone"),
        province: addressCard.getAttribute("data-province"),
        district: addressCard.getAttribute("data-district"),
        ward: addressCard.getAttribute("data-ward"),
        provinceCode: addressCard.getAttribute("data-province-code"),
        districtCode: addressCard.getAttribute("data-district-code"),
        wardCode: addressCard.getAttribute("data-ward-code"),
        addressDetail: addressCard.getAttribute("data-address-detail"),
        defaultAddress: addressCard.getAttribute("data-default-address") === "true"
      };
      
      // Điền dữ liệu vào form
      document.getElementById("addressId").value = addressData.id;
      document.getElementById("formAction").value = "update";
      document.getElementById("recipientName").value = addressData.recipientName;
      document.getElementById("recipientPhone").value = addressData.recipientPhone;
      document.getElementById("addressDetail").value = addressData.addressDetail;
      document.getElementById("defaultAddress").checked = addressData.defaultAddress;
      
      // Thay đổi tiêu đề modal
      const modalHeader = document.querySelector(".address-modal-header h2");
      if (modalHeader) {
        modalHeader.textContent = "Sửa địa chỉ";
      }
      
      // Load tỉnh/thành phố trước
      await loadProvinces();
      
      // Chọn tỉnh và load quận/huyện
      const provinceSelect = document.getElementById("province");
      if (provinceSelect && addressData.provinceCode) {
        provinceSelect.value = addressData.provinceCode;
        document.getElementById("provinceName").value = addressData.province;
        
        // Load quận/huyện
        await loadDistricts(addressData.provinceCode);
        
        // Chọn quận/huyện và load phường/xã
        const districtSelect = document.getElementById("district");
        if (districtSelect && addressData.districtCode) {
          districtSelect.value = addressData.districtCode;
          document.getElementById("districtName").value = addressData.district;
          
          // Load phường/xã
          await loadWards(addressData.districtCode);
          
          // Chọn phường/xã
          const wardSelect = document.getElementById("ward");
          if (wardSelect && addressData.wardCode) {
            wardSelect.value = addressData.wardCode;
            document.getElementById("wardName").value = addressData.ward;
          }
        }
      }
      
      // Mở modal
      openModal();
    } catch (error) {
      console.error("Lỗi khi tải thông tin địa chỉ:", error);
      alert("Có lỗi xảy ra khi tải thông tin địa chỉ. Vui lòng thử lại.");
    }
  }
})();
