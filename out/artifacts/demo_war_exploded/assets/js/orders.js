// UI-only order detail dialog with sample data
(function () {
  if (document.readyState === "loading") {
    document.addEventListener("DOMContentLoaded", init);
  } else {
    init();
  }

  function init() {
    const {
      overlay,
      modal,
      closeBtn,
      title,
      statusBadge,
      customerInfoEl,
      orderMetaEl,
      productsEl,
      totalsEl,
      timelineEl,
      actionsEl,
      toastEl,
    } = ensureModal();

    const statusMeta = {
      pending: {
        label: "Chờ xử lý",
        badge: "pending",
        timeline: "timeline-pending",
        icon: "fa-box",
      },
      confirmed: {
        label: "Đã xác nhận",
        badge: "confirmed",
        timeline: "timeline-confirmed",
        icon: "fa-check",
      },
      shipping: {
        label: "Đang giao",
        badge: "shipping",
        timeline: "timeline-shipping",
        icon: "fa-truck",
      },
      completed: {
        label: "Hoàn thành",
        badge: "completed",
        timeline: "timeline-completed",
        icon: "fa-check",
      },
      cancelled: {
        label: "Đã hủy",
        badge: "cancelled",
        timeline: "timeline-cancelled",
        icon: "fa-xmark",
      },
    };

    // Sample data; adapt to real data source as needed
    const orderData = {
      SH20241101: {
        id: "SH20241101",
        customer: {
          name: "Nguyễn Văn A",
          email: "a.nguyen@example.com",
          phone: "0901234567",
          address: "123 Lê Lợi, Quận 1, TP.HCM",
          note: "Giao giờ hành chính",
        },
        status: "pending",
        placedAt: "2024-11-02T10:15:00+07:00",
        updatedAt: "2024-11-02T10:15:00+07:00",
        products: [
          { name: "Rong biển cay giòn", price: 35700, qty: 3 },
          { name: "Bắp rang caramel", price: 55000, qty: 2 },
        ],
        shippingFee: 20000,
        timeline: [
          {
            status: "pending",
            time: "2024-11-02T10:15:00+07:00",
            note: "Tạo đơn",
          },
        ],
      },
      SH20241102: {
        id: "SH20241102",
        customer: {
          name: "Trần Thị B",
          email: "b.tran@example.com",
          phone: "0912345678",
          address: "45 Nguyễn Huệ, Quận 1, TP.HCM",
          note: "",
        },
        status: "confirmed",
        placedAt: "2024-11-01T09:05:00+07:00",
        updatedAt: "2024-11-01T10:10:00+07:00",
        products: [
          { name: "Khoai tây mật ong bơ", price: 45760, qty: 2 },
          { name: "Nước yuzu sủi mát", price: 33000, qty: 3 },
        ],
        shippingFee: 25000,
        timeline: [
          {
            status: "pending",
            time: "2024-11-01T09:05:00+07:00",
            note: "Tạo đơn",
          },
          {
            status: "confirmed",
            time: "2024-11-01T10:10:00+07:00",
            note: "Đã xác nhận",
          },
        ],
      },
      SH20241103: {
        id: "SH20241103",
        customer: {
          name: "Lê Văn C",
          email: "c.le@example.com",
          phone: "0923456789",
          address: "78 Hai Bà Trưng, Quận 3, TP.HCM",
          note: "",
        },
        status: "shipping",
        placedAt: "2024-10-31T14:20:00+07:00",
        updatedAt: "2024-10-31T16:00:00+07:00",
        products: [
          { name: "Da cá trứng muối", price: 69000, qty: 2 },
          { name: "Matcha Mochi", price: 58900, qty: 1 },
        ],
        shippingFee: 30000,
        timeline: [
          {
            status: "pending",
            time: "2024-10-31T14:20:00+07:00",
            note: "Tạo đơn",
          },
          {
            status: "confirmed",
            time: "2024-10-31T14:40:00+07:00",
            note: "Đã xác nhận",
          },
          {
            status: "shipping",
            time: "2024-10-31T16:00:00+07:00",
            note: "Đang giao",
          },
        ],
      },
    };

    const actionMap = {
      pending: [
        { key: "cancel", label: "Hủy đơn", tone: "danger", icon: "fa-xmark" },
        {
          key: "confirm",
          label: "Xác nhận",
          tone: "primary",
          icon: "fa-check",
        },
      ],
      confirmed: [
        {
          key: "ship",
          label: "Bắt đầu giao hàng",
          tone: "primary",
          icon: "fa-truck",
        },
      ],
      shipping: [
        {
          key: "complete",
          label: "Đã giao hàng",
          tone: "primary",
          icon: "fa-check",
        },
      ],
      completed: [],
      cancelled: [],
    };

    const nextStatus = {
      confirm: "confirmed",
      ship: "shipping",
      complete: "completed",
      cancel: "cancelled",
    };

    function ensureModal() {
      let overlay = document.getElementById("orderModalOverlay");
      let modal = document.getElementById("orderModal");
      let toast = document.getElementById("orderToast");

      if (!overlay || !modal) {
        const wrapper = document.createElement("div");
        wrapper.innerHTML = `
          <div class="order-modal-overlay" id="orderModalOverlay" hidden></div>
          <div class="order-modal" id="orderModal" hidden aria-hidden="true" role="dialog" aria-labelledby="orderModalTitle">
            <div class="order-modal__header">
              <div>
                <p class="order-modal__eyebrow">Chi tiết đơn hàng</p>
                <h2 id="orderModalTitle">Chi tiết đơn hàng</h2>
              </div>
              <button class="order-icon-btn" id="orderModalClose" aria-label="Đóng">✕</button>
            </div>

            <div class="order-modal__body">
              <div class="order-info-grid">
                <div class="order-card" id="customerCard">
                  <div class="order-card__header">
                    <h3>Thông tin khách hàng</h3>
                  </div>
                  <div class="order-card__content" id="customerInfo"></div>
                </div>

                <div class="order-card" id="orderMetaCard">
                  <div class="order-card__header">
                    <h3>Thông tin đơn hàng</h3>
                    <span class="badge" id="orderStatusBadge">Trạng thái</span>
                  </div>
                  <div class="order-card__content" id="orderMeta"></div>
                </div>
              </div>

              <div class="order-card">
                <div class="order-card__header">
                  <h3>Sản phẩm</h3>
                </div>
                <div class="order-card__content" id="orderProducts"></div>
                <div class="order-totals" id="orderTotals"></div>
              </div>

              <div class="order-card">
                <div class="order-card__header">
                  <h3>Lịch sử trạng thái</h3>
                </div>
                <div class="order-card__content" id="orderTimeline"></div>
              </div>
            </div>

            <div class="order-modal__footer" id="orderActions"></div>
          </div>
          <div class="order-toast" id="orderToast" hidden>Đã cập nhật trạng thái đơn hàng</div>
        `;
        document.body.appendChild(wrapper);
        overlay = document.getElementById("orderModalOverlay");
        modal = document.getElementById("orderModal");
        toast = document.getElementById("orderToast");
      }

      return {
        overlay,
        modal,
        closeBtn: document.getElementById("orderModalClose"),
        title: document.getElementById("orderModalTitle"),
        statusBadge: document.getElementById("orderStatusBadge"),
        customerInfoEl: document.getElementById("customerInfo"),
        orderMetaEl: document.getElementById("orderMeta"),
        productsEl: document.getElementById("orderProducts"),
        totalsEl: document.getElementById("orderTotals"),
        timelineEl: document.getElementById("orderTimeline"),
        actionsEl: document.getElementById("orderActions"),
        toastEl: toast,
      };
    }

    const detailBtns = document.querySelectorAll(".order-detail-btn");
    detailBtns.forEach((btn) => {
      btn.addEventListener("click", () => {
        const id = btn.dataset.orderId;
        openModal(id);
      });
    });

    overlay.addEventListener("click", closeModal);
    closeBtn.addEventListener("click", closeModal);
    document.addEventListener("keydown", (e) => {
      if (e.key === "Escape") closeModal();
    });

    // Ensure hidden on load
    setModalVisibility(false);

    function openModal(orderId) {
      const data = orderData[orderId];
      if (!data) return;
      render(data);
      setModalVisibility(true);
    }

    function closeModal() {
      setModalVisibility(false);
    }

    function setModalVisibility(show) {
      const displayValue = show ? "grid" : "none";
      overlay.hidden = !show;
      modal.hidden = !show;
      overlay.style.display = show ? "block" : "none";
      modal.style.display = displayValue;
      modal.setAttribute("aria-hidden", show ? "false" : "true");
      document.body.style.overflow = show ? "hidden" : "";
    }

    function render(data) {
      title.textContent = `Chi tiết đơn hàng ${data.id}`;
      renderStatusBadge(data.status);
      renderCustomer(data.customer);
      renderOrderMeta(data);
      renderProducts(data);
      renderTimeline(data);
      renderActions(data);
    }

    function renderStatusBadge(status) {
      const meta = statusMeta[status] || statusMeta.pending;
      statusBadge.className = `badge ${meta.badge}`;
      statusBadge.textContent = meta.label;
    }

    function renderCustomer(cus) {
      customerInfoEl.innerHTML = `
        <div class="order-meta-row"><span class="label">Tên</span><span class="value">${
          cus.name
        }</span></div>
        <div class="order-meta-row"><span class="label">Email</span><span class="value">${
          cus.email
        }</span></div>
        <div class="order-meta-row"><span class="label">Số điện thoại</span><span class="value">${
          cus.phone
        }</span></div>
        <div class="order-meta-row"><span class="label">Địa chỉ</span><span class="value">${
          cus.address
        }</span></div>
        ${
          cus.note
            ? `<div class="order-meta-row"><span class="label">Ghi chú</span><span class="value">${cus.note}</span></div>`
            : ""
        }
      `;
    }

    function renderOrderMeta(data) {
      orderMetaEl.innerHTML = `
        <div class="order-meta-row"><span class="label">Mã đơn</span><span class="value">${
          data.id
        }</span></div>
        <div class="order-meta-row"><span class="label">Ngày đặt</span><span class="value">${formatDate(
          data.placedAt
        )}</span></div>
        <div class="order-meta-row"><span class="label">Cập nhật</span><span class="value">${formatDate(
          data.updatedAt
        )}</span></div>
      `;
    }

    function renderProducts(data) {
      productsEl.innerHTML = data.products
        .map((p) => {
          const subtotal = p.price * p.qty;
          return `
          <div class="order-product-item">
            <div>
              <div class="name">${p.name}</div>
              <div class="meta">${formatCurrency(p.price)} × ${p.qty}</div>
            </div>
            <div class="amount">${formatCurrency(subtotal)}</div>
          </div>
        `;
        })
        .join("");

      const subtotal = data.products.reduce((s, p) => s + p.price * p.qty, 0);
      const shipping = data.shippingFee || 0;
      const total = subtotal + shipping;
      totalsEl.innerHTML = `
        <div class="order-total-row muted"><span>Tạm tính</span><span>${formatCurrency(
          subtotal
        )}</span></div>
        <div class="order-total-row muted"><span>Phí vận chuyển</span><span>${formatCurrency(
          shipping
        )}</span></div>
        <div class="order-total-row emphasis"><span>Tổng cộng</span><span>${formatCurrency(
          total
        )}</span></div>
      `;
    }

    function renderTimeline(data) {
      const items = data.timeline.map((item) => {
        const meta = statusMeta[item.status] || statusMeta.pending;
        return `
          <div class="order-timeline-item">
            <div class="order-timeline-icon ${
              meta.timeline
            }"><i class="fa-solid ${meta.icon}"></i></div>
            <div class="order-timeline-content">
              <p class="order-timeline-title">${meta.label}</p>
              <p class="order-timeline-time">${formatDate(item.time)}</p>
              ${
                item.note
                  ? `<p class="order-timeline-note">${item.note}</p>`
                  : ""
              }
            </div>
          </div>
        `;
      });
      timelineEl.innerHTML = `<div class="order-timeline">${items.join(
        ""
      )}</div>`;
    }

    function renderActions(data) {
      actionsEl.innerHTML = "";
      const left = document.createElement("div");
      left.className = "order-actions-left";
      const right = document.createElement("div");
      right.className = "order-actions-right";

      const actions = actionMap[data.status] || [];
      actions.forEach((act) => {
        const btn = document.createElement("button");
        btn.className = `order-btn ${
          act.tone === "primary"
            ? "primary"
            : act.tone === "danger"
            ? "danger"
            : ""
        }`;
        btn.innerHTML = `<i class="fa-solid ${act.icon}"></i><span>${act.label}</span>`;
        btn.dataset.action = act.key;
        btn.addEventListener("click", () => handleAction(data, act.key));
        if (act.tone === "danger") {
          left.appendChild(btn);
        } else {
          right.appendChild(btn);
        }
      });

      actionsEl.appendChild(left);
      actionsEl.appendChild(right);
    }

    function handleAction(data, actionKey) {
      const next = nextStatus[actionKey];
      if (!next) return;
      data.status = next;
      data.updatedAt = new Date().toISOString();
      data.timeline.push({
        status: next,
        time: data.updatedAt,
        note: labelByAction(actionKey),
      });
      render(data);
      showToast("Đã cập nhật trạng thái đơn hàng");
    }

    function labelByAction(action) {
      switch (action) {
        case "confirm":
          return "Đã xác nhận";
        case "ship":
          return "Bắt đầu giao hàng";
        case "complete":
          return "Đã giao hàng";
        case "cancel":
          return "Đã hủy đơn";
        default:
          return "";
      }
    }

    function formatCurrency(v) {
      return `${Number(v || 0).toLocaleString("vi-VN")}₫`;
    }

    function formatDate(iso) {
      try {
        const d = new Date(iso);
        return d.toLocaleString("vi-VN", { hour12: false });
      } catch (e) {
        return iso;
      }
    }

    function showToast(msg) {
      toastEl.textContent = msg;
      toastEl.hidden = false;
      requestAnimationFrame(() => {
        toastEl.classList.add("show");
      });
      setTimeout(() => {
        toastEl.classList.remove("show");
        setTimeout(() => (toastEl.hidden = true), 200);
      }, 1800);
    }
  }
})();
