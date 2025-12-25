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
        endpoint: 'upload-multi',
        formData: true,
        fieldName: 'file',
        bundle: false,
    });

    uppy.on('complete', result => {
        if (!result.successful.length) return;

        // Tạo mảng chứa tất cả tên file
        const formData = new FormData();
        result.successful.forEach(file => {
            const name = file.response.body; // server trả về ["file1.jpg"] (chuỗi JSON)
            console.log("Server trả về:", name); // in ra từng response
            formData.append("names", name);
        });
        fetch('ajax/uploaded-images', {
            method: 'POST',
            body: formData
        }).then(response => {
            if (!response.ok) throw new Error("Lỗi Server");
            return response.text();
        }).then(html => {
            const listUl = document.getElementById('uploaded-list');
            if (listUl) {
                listUl.innerHTML = html;
            }
        }).catch(err => console.error(err));
    });

});
