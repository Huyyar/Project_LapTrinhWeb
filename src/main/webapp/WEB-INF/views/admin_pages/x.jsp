<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý File Chuyên Nghiệp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/dropzone@5/dist/min/dropzone.min.css" type="text/css" />
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <style>
        .dropzone { border: 2px dashed #0087F7; border-radius: 10px; background: #f8f9fa; min-height: 150px; }
        .img-preview { width: 50px; height: 50px; object-fit: cover; border-radius: 5px; border: 1px solid #ddd; cursor: pointer; }
        .btn-copy { color: #fff; background-color: #6c757d; border-color: #6c757d; }
        .btn-copy:hover { background-color: #5a6268; }
    </style>
</head>
<body class="bg-light">

<div class="container py-5">
    <h2 class="mb-4"><i class="fas fa-images text-primary"></i> Quản lý kho ảnh (Assets)</h2>

    <div class="card mb-4 shadow-sm">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/admin/file-manager" class="dropzone" id="myDropzone"></form>
            <div class="text-end mt-2">
                <button class="btn btn-success" onclick="location.reload()"><i class="fas fa-sync"></i> Làm mới danh sách</button>
            </div>
        </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">
            <table id="fileTable" class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>Ảnh</th>
                    <th>Tên file</th>
                    <th>Kích thước</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${files}" var="img">
                    <tr>
                        <td>
                            <img src="${img.url}" class="img-preview"
                                 onclick="copyToClipboard('${img.url}')"
                                 title="Click để copy đường dẫn"
                                 onerror="this.src='https://placehold.co/50x50?text=Error'">
                        </td>
                        <td><strong>${img.name}</strong></td>
                        <td>${Math.round(img.size / 1024)} KB</td>
                        <td>
                            <button class="btn btn-sm btn-copy" onclick="copyToClipboard('${img.url}')" title="Copy đường dẫn ảnh">
                                <i class="fas fa-copy"></i> Copy Path
                            </button>

                            <button class="btn btn-sm btn-warning" onclick="renameFile('${img.name}')">
                                <i class="fas fa-edit"></i>
                            </button>

                            <a href="${pageContext.request.contextPath}/admin/file-manager?action=delete&name=${img.name}"
                               class="btn btn-sm btn-danger" onclick="return confirm('Xóa file này?')">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://unpkg.com/dropzone@5/dist/min/dropzone.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
