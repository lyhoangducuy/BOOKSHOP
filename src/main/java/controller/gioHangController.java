package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bo.gioHangBO;
import bo.gioHangSachBO;
import bo.sachBO;
import model.gioHang;
import model.gioHangSach;
import model.khachHang;
import model.sach;

@WebServlet("/gioHangController")
public class gioHangController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        khachHang kh = (khachHang) session.getAttribute("khachHang");
        String maSachMua = request.getParameter("maSachMua");
        String soLuongMua = request.getParameter("soLuongMua");

        ArrayList<gioHangSach> ghBO = (ArrayList<gioHangSach>) session.getAttribute("gioHang");
        if (ghBO == null) {
            ghBO = new ArrayList<>(); // khởi tạo giỏ rỗng nếu chưa có
            session.setAttribute("gioHang", ghBO);
        }

        sachBO sBO = new sachBO();
        gioHangBO gBO = new gioHangBO();
        gioHangSachBO ghsBO = new gioHangSachBO();

        try {
            // Thêm sách vào giỏ
            if (maSachMua != null && soLuongMua != null) {
                sach sach = sBO.getSachTheoMa(maSachMua);
                boolean exists = false;
                for (gioHangSach g : ghBO) {
                    if (g.getMaSach().equals(maSachMua)) {
                        g.setSoLuong(g.getSoLuong() + Integer.parseInt(soLuongMua));
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    ghBO.add(new gioHangSach(0, maSachMua, Integer.parseInt(soLuongMua), sach.getGia()));
                }
                session.setAttribute("gioHang", ghBO);
            }

            // Nếu đã đăng nhập, đồng bộ với CSDL
            if (kh != null) {
                gioHang gh = gBO.taoGioHangValid(kh.getMakh());
                ArrayList<gioHangSach> sessionGH = new ArrayList<>(ghBO);

                for (gioHangSach g : sessionGH) {
                    if (ghsBO.kiemTraTonTai(gh.getMaGioHang(), g.getMaSach()) != null) {
                        ghsBO.capNhatSoLuong(gh.getMaGioHang(), g.getMaSach(), g.getSoLuong());
                    } else {
                        ghsBO.themGioHangSach(gh.getMaGioHang(), g);
                    }
                }

                // Lấy giỏ hàng từ CSDL và lưu vào session
                ghBO = ghsBO.getGioHangSachTheoMaGioHang(gh.getMaGioHang());
                session.setAttribute("gioHang", ghBO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("hienThiGioHangController");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
