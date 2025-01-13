<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form Cho Thuê Văn Phòng</title>
    <style>
        .container {
            width: 600px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
        }
        .form-group {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }
        .form-group label {
            width: 150px;
            margin-right: 10px;
        }
        .form-group input[type="text"],
        .form-group select {
            width: 300px;
            padding: 5px;
        }
        .form-group textarea {
            width: 300px;
            height: 60px;
        }
        .date-group {
            display: flex;
            align-items: center;
        }
        .date-input {
            width: 150px !important;
        }
        .buttons {
            text-align: center;
            margin-top: 20px;
        }
        .buttons button {
            margin: 0 10px;
            padding: 5px 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="" method="post">
            <div class="form-group">
                <label>Mã mặt bằng (*)</label>
                <input type="text" name="maMB" required>
            </div>
            
            <div class="form-group">
                <label>Diện tích (*)</label>
                <input type="text" name="dienTich" required>
            </div>
            
            <div class="form-group">
                <label>Trạng thái (*)</label>
                <select name="trangThai" required>
                    <option value="Trống">Trống</option>
                    <option value="Đã thuê">Đã thuê</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Tầng (*)</label>
                <select name="tang" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Loại văn phòng (*)</label>
                <select name="loaiVP" required>
                    <option value="Cho thuê">Cho thuê</option>
                    <option value="Trống">Trống</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Mô tả chi tiết</label>
                <textarea name="moTa"></textarea>
            </div>
            
            <div class="form-group">
                <label>Giá cho thuê (*)</label>
                <input type="text" name="gia" required>
                <span style="margin-left: 10px">VND</span>
            </div>
            
            <div class="form-group">
                <label>Ngày bắt đầu (*)</label>
                <div class="date-group">
                    <input type="date" name="ngayBD" class="date-input" required>
                </div>
            </div>
            
            <div class="form-group">
                <label>Ngày kết thúc (*)</label>
                <div class="date-group">
                    <input type="date" name="ngayKT" class="date-input" required>
                </div>
            </div>
            
            <div class="buttons">
                <button type="submit">Lưu</button>
                <button type="button">Hủy</button>
            </div>
        </form>
    </div>
</body>
</html> 