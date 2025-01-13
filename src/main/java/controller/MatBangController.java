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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MatBangController", urlPatterns = {"", "/"})
public class MatBangController extends HttpServlet {
    private IMatBangService matBangService = new MatBangService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "delete":
                deleteMatBang(req, resp);
                break;
            default:
                searchMatBang(req, resp);
                break;
        }
    }

    private void searchMatBang(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String loaiMatBang = req.getParameter("loaiMatBang");
        String tangStr = req.getParameter("tang");
        String ngayBDStr = req.getParameter("ngayBD");
        String ngayKTStr = req.getParameter("ngayKT");

        Integer tang = null;
        Date ngayBD = null;
        Date ngayKT = null;

        // Chuyển đổi tầng từ String sang Integer
        if (tangStr != null && !tangStr.isEmpty()) {
            try {
                tang = Integer.parseInt(tangStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Chuyển đổi ngày từ String sang Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (ngayBDStr != null && !ngayBDStr.isEmpty()) {
                ngayBD = sdf.parse(ngayBDStr);
            }
            if (ngayKTStr != null && !ngayKTStr.isEmpty()) {
                ngayKT = sdf.parse(ngayKTStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Thực hiện tìm kiếm
        List<MatBang> listMatBang;
        if (loaiMatBang != null || tang != null || ngayBD != null || ngayKT != null) {
            listMatBang = matBangService.search(loaiMatBang, tang, ngayBD, ngayKT);
            req.setAttribute("msg", "Kết quả tìm kiếm");
        } else {
            listMatBang = matBangService.getAll();
            req.setAttribute("msg", "Danh sách mặt bằng");
        }

        // Lưu lại các giá trị tìm kiếm để hiển thị lại trên form
        req.setAttribute("loaiMatBang", loaiMatBang);
        req.setAttribute("tang", tangStr);
        req.setAttribute("ngayBD", ngayBDStr);
        req.setAttribute("ngayKT", ngayKTStr);
        
        req.setAttribute("listMatBang", listMatBang);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private void deleteMatBang(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String maMatBang = req.getParameter("id");
        if (maMatBang != null && !maMatBang.isEmpty()) {
            matBangService.remove(maMatBang);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
