package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.chiTietHoaDonBO;
import bo.hoaDonBO;
import bo.sachBO;
import model.chiTietHoaDon;

/**
 * Servlet implementation class xoaHoaDonController
 */
@WebServlet("/xoaHoaDonController")
public class xoaHoaDonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xoaHoaDonController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String maHD = request.getParameter("mahdx");

        hoaDonBO hBO = new hoaDonBO();
        chiTietHoaDonBO ctBO = new chiTietHoaDonBO();
        sachBO sBO = new sachBO();

        try {
            long mahd = Long.parseLong(maHD);

            // 1️⃣ Lấy toàn bộ chi tiết hóa đơn
            ArrayList<chiTietHoaDon> dsCT = ctBO.timChiTietHoaDonTheoMaHoaDon(mahd);

            // 2️⃣ Trả sách về kho
            for (chiTietHoaDon ct : dsCT) {
                sBO.capNhatTonKho(ct.getMaSach(), -ct.getSoLuongMua());
            }

            // 3️⃣ Xóa chi tiết hóa đơn
            ctBO.xoaChiTietHoaDonTheoMaHoaDon(mahd, false);

            // 4️⃣ Xóa hóa đơn
            hBO.xoaHoaDonTheoMaHoaDOn(mahd, false);

            response.sendRedirect("thanhToanController");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
