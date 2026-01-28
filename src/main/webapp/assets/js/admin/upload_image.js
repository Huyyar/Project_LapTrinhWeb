document.addEventListener('DOMContentLoaded', () => {
    const targetNode = document.querySelector('#uppy-dashboard');
    if (!targetNode) return;

    // Chỉ xóa 1 lần trước khi mount
    targetNode.innerHTML = '';

    const uppy = new Uppy.Uppy({
        id: 'imageUploader',
        autoProceed: false,
        debug: true,
        restrictions: {
            maxFileSize: 2 * 1024 * 1024,
            maxNumberOfFiles: 10,
            allowedFileTypes: ['image/*']
        },
    });

    uppy.use(Uppy.Dashboard, {
        target: targetNode,
        inline: true,
        showProgressDetails: true,
        height: 350,
        width: '100%',
        proudlyDisplayPoweredByUppy: false,
        metaFields: [{ id: 'name', name: 'Tên File', placeholder: 'Nhập tên file mới' }]
    });

    uppy.use(Uppy.XHRUpload, {
        endpoint: 'upload-image',
        formData: true,
        fieldName: 'file',
        bundle: false,
    });

    uppy.on('complete', (result) => {
        if (!result.successful || result.successful.length === 0) return;

        const params = new URLSearchParams();

        result.successful.forEach(file => {
            // file.response.body bây giờ là Object { fileName: "abc.jpg" }
            if (file.response && file.response.body && file.response.body.fileName) {
                const nameFromServer = file.response.body.fileName;
                console.log("Đã nhận tên file từ Object:", nameFromServer);
                params.append("names", nameFromServer);
            }
        });

        // Chỉ gọi Ajax nếu có tên file hợp lệ
        if (params.has("names")) {
            fetch('ajax/uploaded-images', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: params.toString()
            })
                .then(res => res.text())
                .then(html => {
                    const listUl = document.getElementById('uploaded-list');
                    if (listUl) {
                        listUl.innerHTML = html;
                    }
                })
                .catch(err => console.error("Lỗi fetch list:", err));
        }
    });
});
