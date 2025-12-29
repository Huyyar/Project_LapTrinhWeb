<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
          <section class="profile-content" aria-label="Chi tiết hồ sơ">
            <section
              class="panel profile-section is-active"
              data-section="personal"
            >
              <header class="panel-header">
                <h2>Thông tin cá nhân</h2>
              </header>
              <form class="panel-body form-grid">
                <label>
                  Họ và tên
                  <input type="text" name="name" placeholder="Nguyễn Văn A" />
                </label>
                <label>
                  Email
                  <input type="email" name="email" placeholder="Nguyen@example.com" />
                </label>
                <label>
                  Số điện thoại
                  <input type="tel" name="tel" placeholder="0123 456 789" />
                </label>
                <label>
                  Ngày sinh
                  <input type="date" />
                </label>
                <label>
                  Giới tính
                  <select>
                    <option value="">-- Chọn --</option>
                    <option value="female" name="female">Nữ</option>
                    <option value="male" name="male">Nam</option>
                    <option value="other" name="other">Khác</option>
                  </select>
                </label>
                <div class="form-actions">
                  <button type="reset" class="btn-secondary">Hủy</button>
                  <button type="submit" class="btn-primary">
                    Lưu thay đổi
                  </button>
                </div>
              </form>
            </section>
          </section>
