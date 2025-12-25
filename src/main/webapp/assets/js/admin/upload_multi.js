document.addEventListener('DOMContentLoaded', () => {
    const { Dashboard, XHRUpload } = Uppy;

    const uppy = new Uppy.Uppy({
        id: 'imageUploader',
        autoProceed: false, // Để người dùng nhấn nút Upload mới chạy
        debug: true,
        restrictions: {
            maxFileSize: 2000000, // 2MB
            maxNumberOfFiles: 10,
            allowedFileTypes: ['image/*']
        },
        locale: Uppy.locales.vi_VN // Chuyển sang tiếng Việt
    });

    // Sử dụng giao diện Dashboard
    uppy.use(Dashboard, {
        target: '#uppy-dashboard',
        inline: true, // Hiển thị trực tiếp trên trang
        showProgressDetails: true,
        height: 350,
        width: '100%',
        note: 'Chỉ chấp nhận file ảnh dưới 2MB, tối đa 10 ảnh',
        proudlyDisplayPoweredByUppy: false,
        metaFields: [
            { id: 'name', name: 'Tên File', placeholder: 'Nhập tên file mới' }
        ]
    });

    // Cấu hình gửi file về Servlet
    uppy.use(XHRUpload, {
        endpoint: 'upload-handler', // URL Servlet của bạn
        formData: true,
        fieldName: 'file', // Tương đương name="file" trong form truyền thống
    });

    // Xử lý sau khi upload xong
    uppy.on('complete', (result) => {
        console.log('Upload thành công:', result.successful);
        if (result.successful.length > 0) {
            // Tự động load lại trang hoặc gọi Ajax lấy danh sách ảnh mới
            // location.reload();
        }
    });
});