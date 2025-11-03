async function loadPage(page) {
    const main = document.getElementById("main-content");
    main.classList.add("fade");
    try {
        const res = await fetch(`../pages/${page}.html`);
        if (!res.ok) throw new Error("Không tìm thấy trang");
        const html = await res.text();
        main.innerHTML = html;
    } catch {
        main.innerHTML = "<h3>404</h3><p>Không tìm thấy trang!</p>";
    }
    setTimeout(() => main.classList.remove("fade"), 200);
}

function router() {
    const params = new URLSearchParams(location.search);
    const page = params.get("page") || "home"; // mặc định home
    loadPage(page);
}

window.addEventListener("popstate", router); // khi bấm Back/Forward
window.addEventListener("load", router);     // khi mở trang

// Khi click link, đổi URL mà không reload
document.querySelectorAll("nav a").forEach(a => {
    a.addEventListener("click", e => {
        e.preventDefault();
        const href = a.getAttribute("href");
        history.pushState({}, "", href);
        router();
    });
});