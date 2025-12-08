// UI-only Contact Management page
(function () {
  if (document.readyState === "loading") {
    document.addEventListener("DOMContentLoaded", init);
  } else {
    init();
  }

  function init() {
    const searchInput = document.getElementById("searchInput");
    const statusFilter = document.getElementById("statusFilter");
    const tableBody = document.getElementById("contactTable");
    const countEl = document.getElementById("messageCount");

    const state = {
      contacts: sampleContacts(),
      filtered: [],
      selected: null,
      deleting: null,
      replyText: "",
    };

    const ui = createModalShells();

    const statusLabels = { new: "Mới", read: "Đã đọc", replied: "Đã trả lời" };

    function sampleContacts() {
      return [
        {
          id: 1,
          name: "Nguyễn Văn A",
          email: "nguyenvana@email.com",
          phone: "0901234567",
          subject: "Hỏi về sản phẩm hạt dinh dưỡng",
          message:
            "Xin chào, tôi muốn hỏi về nguồn gốc và hạn sử dụng của hạt dinh dưỡng trong combo Healthy Mix. Cảm ơn.",
          status: "new",
          createdAt: "2025-11-02T14:30:00+07:00",
        },
        {
          id: 2,
          name: "Trần Thị B",
          email: "tranb@example.com",
          phone: "0912345678",
          subject: "Đơn hàng chậm giao",
          message:
            "Đơn hàng SH20241102 chưa được giao, vui lòng kiểm tra giúp.",
          status: "read",
          createdAt: "2025-11-01T10:10:00+07:00",
          readAt: "2025-11-01T11:00:00+07:00",
        },
        {
          id: 3,
          name: "Lê Văn C",
          email: "levanc@example.com",
          subject: "Hợp tác phân phối",
          message:
            "Tôi muốn trao đổi về việc phân phối sản phẩm tại miền Trung.",
          status: "replied",
          createdAt: "2025-10-31T08:45:00+07:00",
          readAt: "2025-10-31T09:00:00+07:00",
          reply: "Cảm ơn anh đã quan tâm, chúng tôi sẽ liên hệ thêm chi tiết.",
          repliedAt: "2025-10-31T10:15:00+07:00",
        },
      ];
    }

    function filterAndRender() {
      const q = (searchInput.value || "").trim().toLowerCase();
      const st = statusFilter.value || "all";
      state.filtered = state.contacts.filter((c) => {
        const matchText =
          !q ||
          [c.name, c.email, c.subject].some((v) =>
            (v || "").toLowerCase().includes(q)
          );
        const matchStatus = st === "all" || c.status === st;
        return matchText && matchStatus;
      });
      renderTable();
      countEl.textContent = `${state.filtered.length} tin nhắn`;
    }

    function renderTable() {
      tableBody.innerHTML = state.filtered
        .map(
          (c) => `
        <tr>
          <td>${c.name}</td>
          <td>${c.email}</td>
          <td class="subject-col"><span class="subject-text">${
            c.subject
          }</span></td>
          <td>${formatDateShort(c.createdAt)}</td>
          <td><span class="badge ${c.status}">${
            statusLabels[c.status] || ""
          }</span></td>
          <td class="text-right">
            <div class="action-buttons">
              <button class="icon-btn" title="Xem chi tiết" data-action="detail" data-id="${
                c.id
              }"><i class="fa-solid fa-eye"></i></button>
              <button class="icon-btn" title="Trả lời" data-action="reply" data-id="${
                c.id
              }"><i class="fa-solid fa-reply"></i></button>
              <button class="icon-btn danger" title="Xóa" data-action="delete" data-id="${
                c.id
              }"><i class="fa-solid fa-trash"></i></button>
            </div>
          </td>
        </tr>
      `
        )
        .join("");
      tableBody.querySelectorAll("button[data-action]").forEach((btn) => {
        btn.addEventListener("click", handleRowAction);
      });
    }

    function handleRowAction(e) {
      const id = Number(e.currentTarget.dataset.id);
      const action = e.currentTarget.dataset.action;
      const contact = state.contacts.find((c) => c.id === id);
      if (!contact) return;
      state.selected = contact;
      if (action === "detail") {
        markAsRead(contact);
        openDetail(contact);
      } else if (action === "reply") {
        markAsRead(contact);
        openReply(contact);
      } else if (action === "delete") {
        state.deleting = contact;
        openDelete(contact);
      }
    }

    function markAsRead(contact) {
      if (contact.status === "new") {
        contact.status = "read";
        contact.readAt = new Date().toISOString();
        filterAndRender();
      }
    }

    // Detail dialog
    function openDetail(contact) {
      const { detailModal, detailBody, detailStatus, detailReplyBtn } = ui;
      detailBody.innerHTML = `
        <div class="detail-grid">
          <div>
            <span class="label">Tên</span>
            <div class="value">${contact.name}</div>
          </div>
          <div>
            <span class="label">Email</span>
            <div class="value">${contact.email}</div>
          </div>
          ${
            contact.phone
              ? `<div><span class="label">Số điện thoại</span><div class="value">${contact.phone}</div></div>`
              : ""
          }
          <div>
            <span class="label">Ngày gửi</span>
            <div class="value">${formatDateFull(contact.createdAt)}</div>
          </div>
        </div>
        <div>
          <span class="label">Tiêu đề</span>
          <div class="value">${contact.subject}</div>
        </div>
        <div>
          <span class="label">Nội dung</span>
          <div class="box">${escapeHTML(contact.message)}</div>
        </div>
        ${
          contact.reply
            ? `
          <div>
            <span class="label">Phản hồi của bạn</span>
            <div class="box reply">${escapeHTML(contact.reply)}${
                contact.repliedAt
                  ? `<div style="margin-top:6px;color:var(--text-muted);font-size:0.9rem;">${formatDateFull(
                      contact.repliedAt
                    )}</div>`
                  : ""
              }</div>
          </div>
        `
            : ""
        }
      `;
      detailStatus.className = `badge ${contact.status}`;
      detailStatus.textContent = statusLabels[contact.status] || "";
      detailReplyBtn.onclick = () => {
        closeDetail();
        openReply(contact);
      };
      showModal(detailModal);
    }

    function closeDetail() {
      hideModal(ui.detailModal);
    }

    // Reply dialog
    function openReply(contact) {
      const { replyModal, replyMsgBox, replyTextarea, replySendBtn } = ui;
      replyMsgBox.innerHTML = `<div class="box" style="max-height:128px; overflow-y:auto;">${escapeHTML(
        contact.message
      )}</div>`;
      document.getElementById(
        "replyName"
      ).textContent = `${contact.name} (${contact.email})`;
      document.getElementById("replySubject").textContent = contact.subject;
      replyTextarea.value = contact.reply || "";
      toggleSendState();
      replySendBtn.onclick = () => {
        const text = replyTextarea.value.trim();
        if (!text) return;
        contact.reply = text;
        contact.status = "replied";
        contact.repliedAt = new Date().toISOString();
        contact.readAt = contact.readAt || contact.repliedAt;
        state.replyText = "";
        filterAndRender();
        hideModal(replyModal);
        showToast("Đã gửi phản hồi thành công");
      };
      replyTextarea.oninput = toggleSendState;
      showModal(replyModal);

      function toggleSendState() {
        const text = replyTextarea.value.trim();
        replySendBtn.disabled = !text;
        replySendBtn.style.opacity = text ? "1" : "0.6";
        replySendBtn.style.cursor = text ? "pointer" : "not-allowed";
      }
    }

    function closeReply() {
      hideModal(ui.replyModal);
    }

    // Delete confirm
    function openDelete(contact) {
      const { deleteModal, deleteName, deleteConfirmBtn } = ui;
      deleteName.textContent = contact.name;
      deleteConfirmBtn.onclick = () => {
        state.contacts = state.contacts.filter((c) => c.id !== contact.id);
        state.deleting = null;
        filterAndRender();
        hideModal(deleteModal);
        showToast(`Đã xóa tin nhắn từ ${contact.name}`);
      };
      showModal(deleteModal);
    }
    function closeDelete() {
      hideModal(ui.deleteModal);
    }

    // Modal helpers
    function showModal(modal) {
      ui.mask.style.display = "block";
      modal.style.display = "block";
    }
    function hideModal(modal) {
      ui.mask.style.display = "none";
      modal.style.display = "none";
    }

    function createModalShells() {
      const wrapper = document.createElement("div");
      wrapper.innerHTML = `
        <div class="modal-overlay" id="contactMask"></div>
        <div class="modal-shell" id="contactDetail">
          <div class="modal-header">
            <h3 class="modal-title">Chi tiết tin nhắn liên hệ</h3>
            <button class="icon-btn" id="detailClose">✕</button>
          </div>
          <div class="modal-body" id="detailBody"></div>
          <div class="modal-footer">
            <div class="modal-actions-left">
              <span class="badge" id="detailStatus">Mới</span>
            </div>
            <div class="modal-actions-right">
              <button class="pill-btn primary" id="detailReplyBtn"><i class="fa-solid fa-reply"></i> Trả lời</button>
            </div>
          </div>
        </div>
        <div class="modal-shell" id="contactReply">
          <div class="modal-header">
            <h3 class="modal-title">Trả lời tin nhắn</h3>
            <button class="icon-btn" id="replyClose">✕</button>
          </div>
          <div class="modal-body" style="display:grid; gap:12px;">
            <div>
              <span class="label">Người gửi</span>
              <div class="value" id="replyName"></div>
            </div>
            <div>
              <span class="label">Tiêu đề</span>
              <div class="value" id="replySubject"></div>
            </div>
            <div>
              <span class="label">Tin nhắn gốc</span>
              <div id="replyMsgBox"></div>
            </div>
            <div>
              <span class="label">Nội dung phản hồi</span>
              <textarea class="textarea-reply" id="replyTextarea" placeholder="Nhập nội dung phản hồi..." rows="6"></textarea>
            </div>
          </div>
          <div class="modal-footer">
            <button class="pill-btn outline" id="replyCancel">Hủy</button>
            <button class="pill-btn primary" id="replySend"><i class="fa-solid fa-envelope"></i> Gửi phản hồi</button>
          </div>
        </div>
        <div class="modal-shell" id="contactDelete">
          <div class="modal-header">
            <h3 class="modal-title">Xác nhận xóa</h3>
            <button class="icon-btn" id="deleteClose">✕</button>
          </div>
          <div class="modal-body">
            <p style="margin:0; color:var(--text);">Bạn có chắc chắn muốn xóa tin nhắn từ <strong id="deleteName"></strong> không? Hành động này không thể hoàn tác.</p>
          </div>
          <div class="modal-footer confirm-actions">
            <button class="pill-btn outline" id="deleteCancel">Hủy</button>
            <button class="btn-danger" id="deleteConfirm">Xóa</button>
          </div>
        </div>
        <div class="order-toast" id="contactToast" hidden>Đã cập nhật</div>
      `;
      document.body.appendChild(wrapper);
      const mask = document.getElementById("contactMask");
      return {
        mask,
        detailModal: document.getElementById("contactDetail"),
        detailBody: document.getElementById("detailBody"),
        detailStatus: document.getElementById("detailStatus"),
        detailReplyBtn: document.getElementById("detailReplyBtn"),
        replyModal: document.getElementById("contactReply"),
        replyMsgBox: document.getElementById("replyMsgBox"),
        replyTextarea: document.getElementById("replyTextarea"),
        replySendBtn: document.getElementById("replySend"),
        deleteModal: document.getElementById("contactDelete"),
        deleteName: document.getElementById("deleteName"),
        deleteConfirmBtn: document.getElementById("deleteConfirm"),
      };
    }

    // Bind overlay closes
    document.body.addEventListener("click", (e) => {
      if (e.target.id === "contactMask") {
        hideAllModals();
      }
      if (e.target.id === "detailClose") hideModal(ui.detailModal);
      if (e.target.id === "replyClose") hideModal(ui.replyModal);
      if (e.target.id === "replyCancel") hideModal(ui.replyModal);
      if (e.target.id === "deleteClose" || e.target.id === "deleteCancel")
        hideModal(ui.deleteModal);
    });

    document.addEventListener("keydown", (e) => {
      if (e.key === "Escape") hideAllModals();
    });

    function hideAllModals() {
      hideModal(ui.detailModal);
      hideModal(ui.replyModal);
      hideModal(ui.deleteModal);
    }

    // Toast
    function showToast(msg) {
      const toast = document.getElementById("contactToast");
      toast.textContent = msg;
      toast.hidden = false;
      requestAnimationFrame(() => toast.classList.add("show"));
      setTimeout(() => {
        toast.classList.remove("show");
        setTimeout(() => (toast.hidden = true), 180);
      }, 1700);
    }

    // Utils
    function formatDateShort(iso) {
      try {
        return new Date(iso).toLocaleDateString("vi-VN");
      } catch {
        return iso;
      }
    }
    function formatDateFull(iso) {
      try {
        return new Date(iso).toLocaleString("vi-VN", { hour12: false });
      } catch {
        return iso;
      }
    }
    function escapeHTML(str) {
      const map = {
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        '"': "&quot;",
        "'": "&#39;",
        "'": "&#39;",
      };
      return (str || "").replace(/[&<>"']/g, (ch) => map[ch] || ch);
    }

    // initial render
    filterAndRender();
    searchInput.addEventListener("input", filterAndRender);
    statusFilter.addEventListener("change", filterAndRender);
  }
})();
