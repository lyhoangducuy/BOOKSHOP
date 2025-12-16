package controller.thanhToan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.hoaDonBO;
import model.hoaDon;
import model.phuongThucThanhToan;

/**
 * Servlet implementation class ketQuaThanhToan
 */
@WebServlet("/ketQuaThanhToan")
public class ketQuaThanhToan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ketQuaThanhToan() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phuongThucStr = request.getParameter("pttt");
		String totalBill = request.getParameter("totalBill");
		String idUser = request.getParameter("idUser");
		String idOrder = request.getParameter("idOrder");
		hoaDonBO h=new hoaDonBO();
		try {
			hoaDon hd=h.timHoaDonTheoMa(Long.parseLong(idOrder));
			
			if (hd.getDiaChiGiaoHang().isEmpty() || hd.getSoDienThoai().isEmpty() || hd.getTenNguoiNhan().isEmpty()) {
				response.sendRedirect("chiTietHoaDonController?mahd="+idOrder);
				return;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Lưu attribute để JSP hiển thị
		request.setAttribute("totalBill", totalBill);
		request.setAttribute("idUser", idUser);
		request.setAttribute("idOrder", idOrder);

		// Chuyển string sang Enum, gán mặc định là TIEN_MAT nếu null hoặc không hợp lệ
		phuongThucThanhToan pt = phuongThucThanhToan.TIEN_MAT;
		if (phuongThucStr != null) {
			try {
				pt = phuongThucThanhToan.valueOf(phuongThucStr);
			} catch (IllegalArgumentException e) {
				pt = phuongThucThanhToan.TIEN_MAT;
			}
		}
		try {
			h.capNhatThongTinThanhToan(Long.parseLong(idOrder), pt);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("pttt", pt);

		// Forward theo loại thanh toán
		if (pt == phuongThucThanhToan.NGAN_HANG) {
			
			request.getRequestDispatcher("/payment").forward(request, response);
		} else {
			

			request.getRequestDispatcher("/views/ketQuaThanhToan.jsp").forward(request, response);
		}
	}

}
