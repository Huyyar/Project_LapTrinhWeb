<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



          <section class="profile-content" aria-label="Chi tiết hồ sơ">
            <section
              class="panel profile-section is-active"
              data-section="personal"
            >
              <header class="panel-header">
                <h2>Thông tin cá nhân</h2>
              </header>
              <form class="panel-body form-grid" method="post" action="profile" >
                <label>
                  Họ và tên
                  <input type="text" name="fullname" value="${auth.fullname}" />
                </label>
                <label>
                  Email
                  <input type="email" name="email" value="${auth.email}" />
                </label>
                <label>
                  Số điện thoại
                  <input type="tel" name="phone" value="${auth.phone}" />
                </label>
                <label>
                  Ngày sinh
                  <input type="date" name="birthdate"
                         value="<fmt:formatDate value='${auth.birthdate}' pattern='yyyy-MM-dd' />" />
                </label>
                <label>
                  Giới tính
                  <select name="gender">
                    <option value="">-- Chọn --</option>
                    <option value="male" ${auth.gender == 'male' ? 'selected' : ''}>Nam</option>
                    <option value="female" ${auth.gender == 'female' ? 'selected' : ''}>Nữ</option>
                    <option value="other" ${auth.gender == 'other' ? 'selected' : ''}>Khác</option>
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
