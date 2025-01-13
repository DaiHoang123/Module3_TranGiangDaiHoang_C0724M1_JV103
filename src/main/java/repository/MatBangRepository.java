package repository;

import entity.MatBang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;

public class MatBangRepository {
    public List<MatBang> getAll() {
        List<MatBang> listMatBang = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection()
                .prepareStatement("SELECT * FROM matbangchothue");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maMatBang = rs.getString("MaMatBang");
                String trangThai = rs.getString("TrangThai");
                double dienTich = rs.getDouble("DienTich");
                int tang = rs.getInt("Tang");
                String loaiMatBang = rs.getString("LoaiMatBang");
                long giaTien = rs.getLong("GiaTien");
                Date ngayBatDau = rs.getDate("NgayBatDau");
                Date ngayKetThuc = rs.getDate("NgayKetThuc");
                
                listMatBang.add(new MatBang(maMatBang, trangThai, dienTich, 
                    tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listMatBang;
    }

    public void remove(String maMatBang) {
        try {
            PreparedStatement statement = BaseRepository.getConnection()
                .prepareStatement("DELETE FROM matbangchothue WHERE MaMatBang = ?");
            statement.setString(1, maMatBang);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MatBang> search(String loaiMatBang, Integer tang, Date ngayBD, Date ngayKT) {
        List<MatBang> listMatBang = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM matbangchothue WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Thêm điều kiện tìm kiếm nếu có
        if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
            sql.append(" AND LoaiMatBang = ?");
            params.add(loaiMatBang);
        }
        
        if (tang != null) {
            sql.append(" AND Tang = ?");
            params.add(tang);
        }
        
        if (ngayBD != null) {
            sql.append(" AND NgayBatDau >= ?");
            params.add(ngayBD);
        }
        
        if (ngayKT != null) {
            sql.append(" AND NgayKetThuc <= ?");
            params.add(ngayKT);
        }

        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            // Set các tham số
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof Date) {
                    stmt.setDate(i + 1, new java.sql.Date(((Date) param).getTime()));
                }
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MatBang mb = new MatBang();
                mb.setMaMatBang(rs.getString("MaMatBang"));
                mb.setTrangThai(rs.getString("TrangThai"));
                mb.setDienTich(rs.getDouble("DienTich"));
                mb.setTang(rs.getInt("Tang"));
                mb.setLoaiMatBang(rs.getString("LoaiMatBang"));
                mb.setGiaTien(rs.getLong("GiaTien"));
                mb.setNgayBatDau(rs.getDate("NgayBatDau"));
                mb.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                listMatBang.add(mb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMatBang;
    }

    // Kiểm tra mã mặt bằng đã tồn tại chưa
    public boolean isMatBangExists(String maMB) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT COUNT(*) FROM matbangchothue WHERE MaMatBang = ?")) {
            
            stmt.setString(1, maMB);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thêm mặt bằng mới
    public boolean add(MatBang matBang) {
        String sql = "INSERT INTO matbangchothue (MaMatBang, TrangThai, DienTich, Tang, " +
                    "LoaiMatBang, GiaTien, NgayBatDau, NgayKetThuc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, matBang.getMaMatBang());
            stmt.setString(2, matBang.getTrangThai());
            stmt.setDouble(3, matBang.getDienTich());
            stmt.setInt(4, matBang.getTang());
            stmt.setString(5, matBang.getLoaiMatBang());
            stmt.setLong(6, matBang.getGiaTien());
            stmt.setDate(7, new java.sql.Date(matBang.getNgayBatDau().getTime()));
            stmt.setDate(8, new java.sql.Date(matBang.getNgayKetThuc().getTime()));
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
