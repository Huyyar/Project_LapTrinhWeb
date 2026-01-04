
    function togglePassword(inputId) {
    const input = document.getElementById(inputId);
    if (!input) {
    console.error('Input not found:', inputId);
    return;
}

    const wrapper = input.parentElement;
    const eyeIcon = wrapper.querySelector('.eye-icon');
    const eyeOffIcon = wrapper.querySelector('.eye-off-icon');
    
}

    if (input.type === 'password') {
    input.type = 'text';
    eyeIcon.style.display = 'none';
    eyeOffIcon.style.display = 'inline-block';
} else {
    input.type = 'password';
    eyeIcon.style.display = 'inline-block';
    eyeOffIcon.style.display = 'none';
}
}