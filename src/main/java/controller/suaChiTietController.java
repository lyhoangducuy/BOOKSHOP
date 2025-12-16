package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.chiTietHoaDonBO;
import bo.gioHangSachBO;
import bo.hoaDonBO;
import bo.sachBO;
import model.chiTietHoaDon;
import model.gioHangSach;

/**
 * Servlet implementation class suaChiTietController
 */
@WebServlet("/suaChiTietController")
public class suaChiTietController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public suaChiTietController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String masach = request.getParameter("maSach");
	    long mahd = Long.parseLong(request.getParameter("mahd"));
	    int soLuongMoi = Integer.parseInt(request.getParameter("soluong"));

	    chiTietHoaDonBO ctBO = new chiTietHoaDonBO();
	    sachBO sBO = new sachBO();

	    try {
	        // 1️⃣ Lấy chi tiết hóa đơn hiện tại
	        chiTietHoaDon ct = ctBO.timChiTietHoaDon(mahd, masach);
	        if (ct == null) {
	            response.sendRedirect("chiTietHoaDonController?mahd=" + mahd);
	            return;
	        }

	        int soLuongCu = ct.getSoLuongMua();

	        // 2️⃣ Không đổi thì thôi
	        if (soLuongMoi == soLuongCu) {
	            response.sendRedirect("chiTietHoaDonController?mahd=" + mahd);
	            return;
	        }

	        // 3️⃣ Tính delta
	        int delta = soLuongMoi - soLuongCu;

	        // 4️⃣ Update kho trước
	        int kqKho = sBO.capNhatTonKho(masach, delta);
	        if (kqKho == 0) {
	            // Không đủ hàng
	            response.sendRedirect("chiTietHoaDonController?mahd=" + mahd + "&err=hethang");
	            return;
	        }

	        // 5️⃣ Update chi tiết hóa đơn
	        ctBO.suaSoLuongSachHoaDon(mahd, masach, soLuongMoi);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    response.sendRedirect("chiTietHoaDonController?mahd=" + mahd);
	}


}
