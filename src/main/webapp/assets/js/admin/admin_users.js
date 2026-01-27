// User Management Functions

// Helper function to silently refresh user table
function silentlyRefreshTable() {
    console.log('[Admin] Silently refreshing table');
    const cacheUrl = window.location.href + (window.location.href.includes('?') ? '&_t=' : '?_t=') + Date.now();
    
    fetch(cacheUrl, {
        method: 'GET',
        credentials: 'same-origin',
        cache: 'no-store'
    })
    .then(response => {
        if (!response.ok) throw new Error('Failed to fetch');
        return response.text();
    })
    .then(html => {
        const parser = new DOMParser();
        const newDoc = parser.parseFromString(html, 'text/html');
        const newTable = newDoc.querySelector('table tbody');
        const currentTable = document.querySelector('table tbody');
        
        if (newTable && currentTable) {
            currentTable.innerHTML = newTable.innerHTML;
            console.log('[Admin] Table refreshed silently');
        }
    })
    .catch(err => {
        console.warn('[Admin] Silent refresh failed, user can manually refresh:', err);
    });
}

function openChangePasswordModal(btn) {
    console.log('[openChangePasswordModal] Called with:', btn);
    console.log('[openChangePasswordModal] btn type:', typeof btn);
    console.log('[openChangePasswordModal] btn.getAttribute:', btn.getAttribute);
    
    // If btn is null or not a valid element, alert user
    if (!btn || !btn.getAttribute) {
        alert('Lỗi: Không thể lấy thông tin người dùng. Vui lòng tải lại trang!');
        console.error('[openChangePasswordModal] btn is invalid:', btn);
        return;
    }
    
    const userId = btn.getAttribute('data-user-id');
    const email = btn.getAttribute('data-email');
    const password = btn.getAttribute('data-password');
    
    console.log('[openChangePasswordModal] userId:', userId, 'email:', email, 'type of userId:', typeof userId);
    console.log('[openChangePasswordModal] Raw data attributes:', {userId, email, password});
    
    // Validate userId exists and is not empty
    if (!userId || userId.trim() === '') {
        alert('Lỗi: User ID không có giá trị. Dữ liệu người dùng bị thiếu!\nData attributes: ' + JSON.stringify({userId, email}));
        console.error('[openChangePasswordModal] userId is empty:', {userId, email});
        return;
    }
    
    const userIdInput = document.getElementById('userId');
    console.log('[openChangePasswordModal] Before setting - userId input value:', userIdInput.value);
    
    userIdInput.value = userId;
    
    console.log('[openChangePasswordModal] After setting - userId input value:', userIdInput.value);
    
    document.getElementById('userEmail').value = email || '';
    document.getElementById('newPassword').value = '';
    document.getElementById('confirmPassword').value = '';
    
    console.log('[openChangePasswordModal] Form populated:', {
        userId: document.getElementById('userId').value, 
        email: document.getElementById('userEmail').value
    });
    
    openModal('changePasswordModal');
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    const overlay = document.getElementById('modalOverlay');
    modal.setAttribute('hidden', '');
    
    // Check if there are any other open modals
    const allModals = document.querySelectorAll('.modal');
    let hasOpenModals = false;
    for (let m of allModals) {
        if (!m.hasAttribute('hidden')) {
            hasOpenModals = true;
            break;
        }
    }
    
    if (!hasOpenModals) {
        overlay.setAttribute('hidden', '');
    }
}

function togglePasswordVisibility() {
    const input = document.getElementById('newPassword');
    const btn = event.target.closest('.toggle-password');
    
    if (input.type === 'password') {
        input.type = 'text';
        btn.innerHTML = '<i class="fa-solid fa-eye-slash"></i>';
    } else {
        input.type = 'password';
        btn.innerHTML = '<i class="fa-solid fa-eye"></i>';
    }
}

function toggleConfirmPasswordVisibility() {
    const input = document.getElementById('confirmPassword');
    const btn = event.target.closest('.toggle-password');
    
    if (input.type === 'password') {
        input.type = 'text';
        btn.innerHTML = '<i class="fa-solid fa-eye-slash"></i>';
    } else {
        input.type = 'password';
        btn.innerHTML = '<i class="fa-solid fa-eye"></i>';
    }
}

// Validate password match before submission
document.getElementById('changePasswordForm')?.addEventListener('submit', function(e) {
    e.preventDefault(); // Prevent default form submission
    
    console.log('[Password Change] Form submit triggered');
    
    const userIdInput = document.getElementById('userId');
    const newPassword = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    
    // Check if userId is set FIRST
    console.log('[Password Change] userId value:', userIdInput.value);
    console.log('[Password Change] userId input element:', userIdInput);
    console.log('[Password Change] userId input name:', userIdInput.name);
    
    if (!userIdInput.value || userIdInput.value.trim() === '') {
        alert('Lỗi: User ID chưa được set. Vui lòng bấm nút "Đổi MK" lại!\nCurrent value: "' + userIdInput.value + '"');
        console.error('[Password Change] userId is empty!');
        return false;
    }
    
    if (newPassword !== confirmPassword) {
        alert('Mật khẩu xác nhận không khớp!');
        return false;
    }
    
    if (newPassword.length < 6) {
        alert('Mật khẩu phải có ít nhất 6 ký tự!');
        return false;
    }
    
    // Send request using fetch to maintain admin session
    const formData = new FormData(this);
    
    // Log formData contents for debugging
    console.log('[Password Change] FormData contents:');
    for (let [key, value] of formData.entries()) {
        console.log(`  ${key}: ${value}`);
    }
    
    // Also log the form manually
    console.log('[Password Change] Manual form check:');
    console.log('  userId input value:', document.getElementById('userId').value);
    console.log('  userId input name:', document.getElementById('userId').name);
    
    // Get context path from form action
    const action = this.getAttribute('action');
    
    console.log('[Password Change] Sending AJAX request to:', action);
    
    // Create URLSearchParams instead of FormData for better compatibility
    const params = new URLSearchParams();
    params.append('userId', document.getElementById('userId').value);
    params.append('newPassword', document.getElementById('newPassword').value);
    params.append('confirmPassword', document.getElementById('confirmPassword').value);
    
    console.log('[Password Change] URLSearchParams contents:');
    for (let [key, value] of params.entries()) {
        console.log(`  ${key}: ${value}`);
    }
    
    fetch(action, {
        method: 'POST',
        body: params,
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => {
        console.log('[Password Change] Response status:', response.status);
        console.log('[Password Change] Response headers:', response.headers);
        
        if (response.ok) {
            // Password changed successfully
            alert('Mật khẩu đã được cập nhật thành công!');
            closeModal('changePasswordModal');
            silentlyRefreshTable();
        } else if (response.status === 400) {
            return response.text().then(text => {
                alert('Lỗi: ' + text);
            });
        } else {
            throw new Error('Network response was not ok (status: ' + response.status + ')');
        }
    })
    .catch(error => {
        console.error('[Password Change] Error:', error);
        alert('Có lỗi xảy ra: ' + error.message);
    });
    
    return false;
});

function lockUser(btn) {
    console.log('[lockUser] Called with:', btn);
    
    // If btn is null or not a valid element, alert user
    if (!btn || !btn.getAttribute) {
        alert('Lỗi: Không thể lấy thông tin người dùng. Vui lòng tải lại trang!');
        console.error('[lockUser] btn is invalid:', btn);
        return;
    }
    
    const userId = btn.getAttribute('data-user-id');
    const email = btn.getAttribute('data-email');
    const isActive = btn.getAttribute('data-active') === 'true';
    
    console.log('[lockUser] userId:', userId, 'email:', email, 'isActive:', isActive, 'type of userId:', typeof userId);
    
    // Validate userId exists and is not empty
    if (!userId || userId.trim() === '') {
        alert('Lỗi: User ID không có giá trị. Dữ liệu người dùng bị thiếu!');
        console.error('[lockUser] userId is empty:', {userId, email});
        return;
    }
    
    document.getElementById('lockUserId').value = userId;
    document.getElementById('lockStatus').value = isActive ? 'lock' : 'unlock';
    
    const title = document.getElementById('lockTitle');
    const message = document.getElementById('lockMessage');
    const submitBtn = document.getElementById('lockSubmitBtn');
    
    if (isActive) {
        title.textContent = 'Khóa Người Dùng';
        message.textContent = `Bạn có chắc chắn muốn khóa tài khoản "${email}"? Người dùng sẽ không thể đăng nhập.`;
        submitBtn.textContent = 'Khóa Tài Khoản';
        submitBtn.style.background = '#dc3545';
        submitBtn.style.borderColor = '#dc3545';
    } else {
        title.textContent = 'Mở Khóa Người Dùng';
        message.textContent = `Bạn có chắc chắn muốn mở khóa tài khoản "${email}"? Người dùng sẽ có thể đăng nhập bình thường.`;
        submitBtn.textContent = 'Mở Khóa Tài Khoản';
        submitBtn.style.background = '#0f9d58';
        submitBtn.style.borderColor = '#0f9d58';
    }
    
    console.log('[lockUser] Form populated:', {userId, email, isActive});
    
    openModal('lockUserModal');
}

// Handle lock user form submission via AJAX
document.getElementById('lockUserForm')?.addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Check if userId is set
    const userIdInput = document.getElementById('lockUserId');
    console.log('[Lock User] userId input value before submit:', userIdInput.value);
    if (!userIdInput.value || userIdInput.value.trim() === '') {
        alert('Lỗi: User ID chưa được set. Vui lòng bấm nút "Khóa/Mở khóa" lại!');
        console.error('[Lock User] userId input is empty!');
        return false;
    }
    
    const formData = new FormData(this);
    const action = this.getAttribute('action');
    
    console.log('[Lock User] FormData contents:');
    for (let [key, value] of formData.entries()) {
        console.log(`  ${key}: ${value}`);
    }
    console.log('[Lock User] Sending AJAX request to:', action);
    
    // Create URLSearchParams instead of FormData for better compatibility
    const params = new URLSearchParams();
    params.append('userId', document.getElementById('lockUserId').value);
    params.append('action', document.getElementById('lockStatus').value);
    
    console.log('[Lock User] URLSearchParams contents:');
    for (let [key, value] of params.entries()) {
        console.log(`  ${key}: ${value}`);
    }
    
    fetch(action, {
        method: 'POST',
        body: params,
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => {
        console.log('[Lock User] Response status:', response.status);
        
        if (response.ok) {
            alert('Cập nhật trạng thái người dùng thành công!');
            closeModal('lockUserModal');
            silentlyRefreshTable();
        } else if (response.status === 400) {
            return response.text().then(text => {
                alert('Lỗi: ' + text);
            });
        } else {
            throw new Error('Network response was not ok (status: ' + response.status + ')');
        }
    })
    .catch(error => {
        console.error('[Lock User] Error:', error);
        alert('Có lỗi xảy ra: ' + error.message);
    });
    
    return false;
});

// Handle delete user form submission via AJAX
document.querySelectorAll('form[action*="/admin/delete-user"]').forEach(form => {
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        if (!confirm('Bạn có chắc chắn muốn xóa tài khoản này không?')) {
            return false;
        }
        
        const action = this.getAttribute('action');
        const userId = this.querySelector('input[name="userId"]').value;
        
        // Create URLSearchParams instead of FormData for better compatibility
        const params = new URLSearchParams();
        params.append('userId', userId);
        
        console.log('[Delete User] URLSearchParams contents:');
        for (let [key, value] of params.entries()) {
            console.log(`  ${key}: ${value}`);
        }
        console.log('[Delete User] Sending AJAX request to:', action);
        
        fetch(action, {
            method: 'POST',
            body: params,
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
        .then(response => {
            console.log('[Delete User] Response status:', response.status);
            
            if (response.ok) {
                alert('Xóa tài khoản thành công!');
                silentlyRefreshTable();
            } else if (response.status === 400) {
                return response.text().then(text => {
                    alert('Lỗi: ' + text);
                });
            } else {
                throw new Error('Network response was not ok (status: ' + response.status + ')');
            }
        })
        .catch(error => {
            console.error('[Delete User] Error:', error);
            alert('Có lỗi xảy ra: ' + error.message);
        });
        
        return false;
    });
});

// Close modal when clicking overlay
document.getElementById('modalOverlay')?.addEventListener('click', function(e) {
    if (e.target === this) {
        const allModals = document.querySelectorAll('.modal');
        allModals.forEach(modal => {
            modal.setAttribute('hidden', '');
        });
        this.setAttribute('hidden', '');
    }
});

// Close modal with Escape key
document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape') {
        const allModals = document.querySelectorAll('.modal');
        const overlay = document.getElementById('modalOverlay');
        allModals.forEach(modal => {
            modal.setAttribute('hidden', '');
        });
        overlay.setAttribute('hidden', '');
    }
});

