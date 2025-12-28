function copyUrl(url, button) {
    navigator.clipboard.writeText(url).then(() => {
        const icon = button.querySelector('i');
        const originalClass = icon.className;

        icon.className = 'fa-solid fa-check';
        button.style.color = '#28a745';

        setTimeout(() => {
            icon.className = originalClass;
            button.style.color = '';
        }, 2000);

    }).catch(err => {
        alert('Không thể copy link!' + err);
    });
}
