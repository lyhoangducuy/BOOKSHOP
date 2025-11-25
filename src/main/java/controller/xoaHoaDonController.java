package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.chiTietHoaDonBO;
import bo.hoaDonBO;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maHD=request.getParameter("mahdx");
		hoaDonBO hBO=new hoaDonBO();
		chiTietHoaDonBO cBO=new chiTietHoaDonBO();
		try {
			hBO.xoaHoaDonTheoMaHoaDOn(Long.parseLong(maHD), false);
			cBO.xoaChiTietHoaDonTheoMaHoaDon(Long.parseLong(maHD), false);
			RequestDispatcher rd=request.getRequestDispatcher("thanhToanController");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
