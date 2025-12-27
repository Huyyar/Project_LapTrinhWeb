document.addEventListener("DOMContentLoaded", () => {
  const menuButtons = document.querySelectorAll(".profile-menu .menu-item");
  const sections = document.querySelectorAll(".profile-section");

  if (!menuButtons.length || !sections.length) {
    return;
  }

  menuButtons.forEach((button) => {
    button.addEventListener("click", () => {
      const targetSection = button.dataset.target;
      if (!targetSection) {
        return;
      }

      menuButtons.forEach((item) => item.classList.remove("is-active"));
      button.classList.add("is-active");

      sections.forEach((section) => {
        const isMatch = section.dataset.section === targetSection;
        section.classList.toggle("is-active", isMatch);
      });
    });
  });
});
