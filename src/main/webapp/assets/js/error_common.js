// Common error page utilities
document.addEventListener("DOMContentLoaded", function () {
  // Add animation to error container
  const container = document.querySelector(".error-container");
  if (container) {
    container.style.opacity = "0";
    container.style.transform = "translateY(20px)";

    setTimeout(function () {
      container.style.transition = "all 0.5s ease";
      container.style.opacity = "1";
      container.style.transform = "translateY(0)";
    }, 100);
  }

  // Handle back button
  const backButtons = document.querySelectorAll('[data-action="back"]');
  backButtons.forEach(function (button) {
    button.addEventListener("click", function (e) {
      e.preventDefault();
      window.history.back();
    });
  });

  // Handle reload button
  const reloadButtons = document.querySelectorAll('[data-action="reload"]');
  reloadButtons.forEach(function (button) {
    button.addEventListener("click", function (e) {
      e.preventDefault();
      window.location.reload();
    });
  });
});
