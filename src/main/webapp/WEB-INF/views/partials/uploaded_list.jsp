<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul id="uploaded-list" class="uploaded-list">
<c:if test="${not empty uploadedImages}">
    <c:forEach items="${uploadedImages}" var="i">
        <li>
            <img src="${i.url}" alt="">
            <span>${i.name}</span>
            <span>${i.size}</span>
            <button class="btn btn-copy"
                    onclick="copyUrl('${i.url}', this)"
                    title="Copy link ảnh">
                <i class="fa-solid fa-copy"></i>
            </button>
        </li>
    </c:forEach>
</c:if>
</ul>
