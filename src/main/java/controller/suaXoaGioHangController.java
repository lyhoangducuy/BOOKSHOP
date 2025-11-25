package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bo.gioHangBO;
import bo.sachBO;
import model.sach;

@WebServlet("/suaXoaGioHangController")
public class suaXoaGioHangController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public suaXoaGioHangController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        gioHangBO ghBO = (gioHangBO) session.getAttribute("gioHang");
        if (ghBO == null) {
            response.sendRedirect("gioHangController");
            return;
        }

        // 1. Xóa 1 sản phẩm theo link "Xóa"
        String maSachXoaChon = request.getParameter("maSachXoaChon");
        if (maSachXoaChon != null) {
            ghBO.Xoa(maSachXoaChon);
        }

        // 2. Xóa nhiều sản phẩm theo checkbox
        String xoaCheckBox = request.getParameter("xoaCheckBox");
        if (xoaCheckBox != null) {
            String[] dsCheckBox = request.getParameterValues("cxoa");
            if (dsCheckBox != null) {
                for (String ma : dsCheckBox) {
                    ghBO.Xoa(ma);
                }
            }
        }

        // 3. Cập nhật số lượng từ nút "+"
        String maSachSua = request.getParameter("butSua"); // name button
        if (maSachSua != null) {
            String slStr = request.getParameter(maSachSua); // lấy số lượng theo tên sách
            if (slStr != null) {
                try {
                    int sl = Integer.parseInt(slStr);
                    if (sl > 0) {
                        sachBO sBO = new sachBO();
                        sach s = sBO.getSachTheoMa(maSachSua);
                        if (s != null) {
                            ghBO.themVaoGioHang(maSachSua, s.getTenSach(), s.getAnh(), sl, s.getGia());
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        session.setAttribute("gioHang", ghBO);

        // 4. Thanh toán
        String thanhToan = request.getParameter("thanhToan");
        if (thanhToan != null) {
            response.sendRedirect("thanhToanController");
            return;
        }

        // Quay lại hiển thị giỏ hàng
        response.sendRedirect("hienThiGioHangController");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
