package controller;

import entity.MatBang;
import service.IMatBangService;
import service.MatBangService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ThemMatBangController", urlPatterns = {"/themmatbang"})
public class ThemMatBangController extends HttpServlet {
    private IMatBangService matBangService = new MatBangService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/ThemMatBang.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        try {
            // Lấy dữ liệu từ form
            String maMB = req.getParameter("maMB");
            String trangThai = req.getParameter("trangThai");
            double dienTich = Double.parseDouble(req.getParameter("dienTich"));
            int tang = Integer.parseInt(req.getParameter("tang"));
            String loaiMatBang = req.getParameter("loaiMatBang");
            long giaTien = Long.parseLong(req.getParameter("giaTien"));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayBD = sdf.parse(req.getParameter("ngayBD"));
            Date ngayKT = sdf.parse(req.getParameter("ngayKT"));

            // Kiểm tra mã mặt bằng đã tồn tại
            if (matBangService.isMatBangExists(maMB)) {
                req.setAttribute("error", "Mã mặt bằng đã tồn tại!");
                req.getRequestDispatcher("/ThemMatBang.jsp").forward(req, resp);
                return;
            }

            // Tạo đối tượng MatBang mới
            MatBang matBang = new MatBang(maMB, trangThai, dienTich, tang, 
                                        loaiMatBang, giaTien, ngayBD, ngayKT);

            // Thêm mới và kiểm tra kết quả
            boolean result = matBangService.add(matBang);
            
            if (result) {
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                req.setAttribute("error", "Không thể thêm mặt bằng!");
                req.setAttribute("matBang", matBang);
                req.getRequestDispatcher("/ThemMatBang.jsp").forward(req, resp);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            req.getRequestDispatcher("/ThemMatBang.jsp").forward(req, resp);
        }
    }
}
