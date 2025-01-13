<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thêm Mặt Bằng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h5 class="mb-0">Thêm Mặt Bằng Mới</h5>
                </div>
                <div class="card-body">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:if>
                    
                    <form id="matBangForm" action="${pageContext.request.contextPath}/themmatbang" 
                          method="post" onsubmit="return validateForm()" class="needs-validation" novalidate>
                        <div class="mb-3">
                            <label class="form-label">Mã mặt bằng (*)</label>
                            <input type="text" name="maMB" class="form-control" required>
                            <div class="invalid-feedback">
                                Mã mặt bằng phải có định dạng XXX-XX-XX (X là số hoặc chữ in hoa)
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Trạng thái (*)</label>
                            <select name="trangThai" class="form-select" required>
                                <option value="">Chọn trạng thái</option>
                                <option value="Trống">Trống</option>
                                <option value="Hạ tầng">Hạ tầng</option>
                                <option value="Đầy đủ">Đầy đủ</option>
                            </select>
                            <div class="invalid-feedback">Vui lòng chọn trạng thái</div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Diện tích (*)</label>
                            <input type="number" name="dienTich" class="form-control" min="20" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Tầng (*)</label>
                            <select name="tang" class="form-select" required>
                                <% for(int i = 1; i <= 15; i++) { %>
                                    <option value="<%= i %>"><%= i %></option>
                                <% } %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Loại mặt bằng (*)</label>
                            <select name="loaiMatBang" class="form-select" required>
                                <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
                                <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Giá cho thuê (*)</label>
                            <input type="number" name="giaTien" class="form-control" min="1000000" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Ngày bắt đầu (*)</label>
                            <input type="date" name="ngayBD" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Ngày kết thúc (*)</label>
                            <input type="date" name="ngayKT" class="form-control" required>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-save"></i> Lưu
                            </button>
                            <button type="button" class="btn btn-secondary" 
                                    onclick="window.location.href='${pageContext.request.contextPath}/'">
                                <i class="bi bi-x-circle"></i> Hủy
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
function validateForm() {
    // Kiểm tra mã mặt bằng
    var maMB = document.getElementsByName("maMB")[0].value;
    var maMBPattern = /^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$/;
    if (!maMBPattern.test(maMB)) {
        alert("Mã mặt bằng phải có định dạng XXX-XX-XX với X là số hoặc chữ in hoa");
        return false;
    }

    // Kiểm tra diện tích
    var dienTich = document.getElementsByName("dienTich")[0].value;
    if (dienTich <= 20) {
        alert("Diện tích phải lớn hơn 20m2");
        return false;
    }

    // Kiểm tra giá tiền
    var giaTien = document.getElementsByName("giaTien")[0].value;
    if (giaTien < 1000000) {
        alert("Giá tiền phải lớn hơn 1.000.000 VNĐ");
        return false;
    }

    // Kiểm tra ngày
    var ngayBD = new Date(document.getElementsByName("ngayBD")[0].value);
    var ngayKT = new Date(document.getElementsByName("ngayKT")[0].value);
    
    if (isNaN(ngayBD.getTime()) || isNaN(ngayKT.getTime())) {
        alert("Ngày phải đúng định dạng dd/mm/yyyy");
        return false;
    }
    
    var minEndDate = new Date(ngayBD);
    minEndDate.setMonth(minEndDate.getMonth() + 6);
    
    if (ngayKT < minEndDate) {
        alert("Ngày kết thúc phải sau ngày bắt đầu ít nhất 6 tháng!");
        return false;
    }
    
    return true;
}
</script>
</body>
</html>