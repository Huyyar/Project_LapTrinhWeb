<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="admin-contact">
    <header class="contact-header">
        <h1 class="page-title">Quản Lý Liên Hệ</h1>

        <div class="count-pill">
            ${fn:length(contacts)} tin nhắn
        </div>
    </header>

    <section class="contact-content">

        <div class="panel">
            <div class="panel-header">
                <h2>Danh sách tin nhắn</h2>
            </div>

            <div class="table-wrap">
                <table>
                    <thead>
                    <tr>
                        <th>Tên</th>
                        <th>Email</th>
                        <th class="subject-col">Nội dung</th>
                        <th>Ngày gửi</th>

                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${contacts}" var="c">
                        <tr>
                            <td>${c.fullName}</td>
                            <td>${c.email}</td>
                            <td class="subject-col">
                                    ${c.message}
                            </td>
                            <td>
                                <c:if test="${not empty c.createdAt}">
                                    <fmt:formatDate value="${c.createdAt}" pattern="dd/MM/yyyy HH:mm"/>
                                </c:if>
                            </td>


                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </section>
</div>
