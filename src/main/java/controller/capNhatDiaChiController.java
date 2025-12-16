package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.chiTietHoaDonBO;
import bo.hoaDonBO;

/**
 * Servlet implementation class capNhatDiaChiController
 */
@WebServlet("/capNhatDiaChiController")
public class capNhatDiaChiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public capNhatDiaChiController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String ten=request.getParameter("hoTen");
		String sdt=request.getParameter("sdt");
		String diaChi=request.getParameter("diaChi");
		String mhd=request.getParameter("maHoaDon");
		hoaDonBO hBO=new hoaDonBO();
		if (!ten.isEmpty() || !sdt.isEmpty() || !diaChi.isEmpty()) {
			try {
				hBO.capNhatThongTinNguoiNhan(Long.parseLong(mhd), ten, diaChi, sdt);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		response.sendRedirect("chiTietHoaDonController?mahd="+mhd);
	}

}
