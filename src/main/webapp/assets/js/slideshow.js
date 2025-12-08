document.addEventListener("DOMContentLoaded", () => {
  const slideshow = document.querySelector(".hero-slideshow");
  if (!slideshow) {
    return;
  }

  const slides = Array.from(slideshow.querySelectorAll(".hero-slide"));
  if (slides.length <= 1) {
    slides[0]?.classList.add("is-active");
    return;
  }

  const prevButton = slideshow.querySelector("[data-hero-prev]");
  const nextButton = slideshow.querySelector("[data-hero-next]");
  const dotsContainer = slideshow.querySelector("[data-hero-dots]");
  const AUTO_DELAY = 4000;
  let activeIndex = slides.findIndex((slide) =>
    slide.classList.contains("is-active")
  );
  if (activeIndex === -1) {
    activeIndex = 0;
    slides[0].classList.add("is-active");
  }

  const dots = slides.map((_, index) => {
    if (!dotsContainer) {
      return null;
    }
    const dot = document.createElement("button");
    dot.type = "button";
    dot.className = "hero-slide-dot";
    dot.setAttribute("aria-label", `Xem slide ${index + 1}`);
    dot.addEventListener("click", () => {
      showSlide(index);
      restartTimer();
    });
    dotsContainer.appendChild(dot);
    return dot;
  });

  function updateDots() {
    dots.forEach((dot, index) => {
      if (!dot) {
        return;
      }
      dot.classList.toggle("is-active", index === activeIndex);
    });
  }

  function showSlide(newIndex) {
    slides[activeIndex].classList.remove("is-active");
    activeIndex = (newIndex + slides.length) % slides.length;
    slides[activeIndex].classList.add("is-active");
    updateDots();
  }

  prevButton?.addEventListener("click", () => {
    showSlide(activeIndex - 1);
    restartTimer();
  });

  nextButton?.addEventListener("click", () => {
    showSlide(activeIndex + 1);
    restartTimer();
  });

  const playNext = () => {
    showSlide(activeIndex + 1);
  };

  let timer = setInterval(playNext, AUTO_DELAY);

  function restartTimer() {
    clearInterval(timer);
    timer = setInterval(playNext, AUTO_DELAY);
  }

  updateDots();
});
