document.getElementById('logoutBtn').addEventListener('click', function(e) {
    e.preventDefault();

    const url = this.href;

    if (confirm("Bạn có chắc chắn muốn đăng xuất?")) {
        fetch(url, {
            method: 'GET'
        })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert("Có lỗi xảy ra khi đăng xuất.");
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Không thể kết nối tới máy chủ.");
            });
    }
});