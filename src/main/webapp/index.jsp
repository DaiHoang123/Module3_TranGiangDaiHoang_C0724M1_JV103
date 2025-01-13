<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Danh sách mặt bằng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<body>
<div class="container mt-4">
    <div class="card mb-4">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Tìm kiếm mặt bằng</h5>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/" method="get">
                <div class="row g-3">
                    <div class="col-md-3">
                        <label class="form-label">Loại Mặt bằng:</label>
                        <select name="loaiMatBang" class="form-select">
                            <option value="">Tất cả</option>
                            <option value="Văn phòng chia sẻ" ${loaiMatBang == 'Văn phòng chia sẻ' ? 'selected' : ''}>Văn phòng chia sẻ</option>
                            <option value="Văn phòng trọn gói" ${loaiMatBang == 'Văn phòng trọn gói' ? 'selected' : ''}>Văn phòng trọn gói</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">Tầng:</label>
                        <select name="tang" class="form-select">
                            <option value="">Tất cả</option>
                            <c:forEach begin="1" end="15" var="i">
                                <option value="${i}" ${tang == i ? 'selected' : ''}>${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Từ ngày:</label>
                        <input type="date" name="ngayBD" class="form-control" value="${ngayBD}">
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Đến ngày:</label>
                        <input type="date" name="ngayKT" class="form-control" value="${ngayKT}">
                    </div>
                    <div class="col-md-1 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search"></i> Tìm
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="d-flex justify-content-between align-items-center mb-3">
        <h4>${msg}</h4>
        <a href="${pageContext.request.contextPath}/themmatbang" class="btn btn-success">
            <i class="bi bi-plus-circle"></i> Thêm mặt bằng
        </a>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Mã MB</th>
                    <th>Diện tích</th>
                    <th>Trạng thái</th>
                    <th>Tầng</th>
                    <th>Loại văn phòng</th>
                    <th>Giá cho thuê</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listMatBang}" var="mb">
                    <tr>
                        <td>${mb.maMatBang}</td>
                        <td>${mb.dienTich}</td>
                        <td>${mb.trangThai}</td>
                        <td>${mb.tang}</td>
                        <td>${mb.loaiMatBang}</td>
                        <td>${mb.giaTien}</td>
                        <td>${mb.ngayBatDau}</td>
                        <td>${mb.ngayKetThuc}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="confirmDelete('${mb.maMatBang}')">
                                <i class="bi bi-trash"></i> Xóa
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
function confirmDelete(maMatBang) {
    if (confirm("Bạn có chắc chắn muốn xóa mặt bằng với mã số " + maMatBang + " không?")) {
        window.location.href = "${pageContext.request.contextPath}/?action=delete&id=" + maMatBang;
    }
}
</script>
</body>
</html>