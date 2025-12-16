package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.loaiBO;
import bo.sachBO;
import model.loai;
import model.sach;

@WebServlet("/trangChuController")
public class trangChuController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loaiBO lbo = new loaiBO();
        sachBO sbo = new sachBO();

        String maloai = request.getParameter("maloai");
        String timKiem = request.getParameter("timKiem");
        int trang = 1; 
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                trang = Integer.parseInt(pageParam);
                if(trang < 1) trang = 1;
            } catch(NumberFormatException e) {
                trang = 1;
            }
        }

        try {
            ArrayList<sach> dssach;
            int totalRows;

            
            if (timKiem != null && !timKiem.trim().isEmpty()) {
                dssach = sbo.getSachTheoTen(timKiem, trang);
                totalRows = sbo.getTotalSachTheoTen(timKiem);
            } else if (maloai != null && !maloai.trim().isEmpty()) {
                dssach = sbo.getSachTheoLoai(maloai, trang);
                totalRows = sbo.getTotalSachTheoLoai(maloai);
            } else {
                dssach = sbo.getSachTheoTrang(trang);
                totalRows = sbo.getTotalSach();
            }

            request.setAttribute("dsloai", lbo.getLoai());
            request.setAttribute("dssach", dssach);
            request.setAttribute("trangHienTai", trang);
            request.setAttribute("totalRows", totalRows);

            RequestDispatcher rd = request.getRequestDispatcher("/views/trangChu.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
