<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

          <section class="profile-content">
            <section class="panel profile-section is-active">
              <header class="panel-header">
                <h2>Đổi mật khẩu</h2>
                <p>Cập nhật mật khẩu tài khoản của bạn</p>
              </header>

              <div class="panel-body">
                <form class="form-stack">
                  <label>
                    Mật khẩu cũ
                    <input
                      type="password"
                      placeholder="Nhập mật khẩu hiện tại"
                    />
                  </label>

                  <label>
                    Mật khẩu mới
                    <input type="password" placeholder="Nhập mật khẩu mới" />
                  </label>

                  <label>
                    Nhập lại mật khẩu mới
                    <input
                      type="password"
                      placeholder="Nhập lại mật khẩu mới"
                    />
                  </label>

                  <button type="submit" class="btn-primary">
                    Đổi mật khẩu
                  </button>
                </form>
              </div>
            </section>
          </section>
