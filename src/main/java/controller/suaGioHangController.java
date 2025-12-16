package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.gioHangSachBO;
import model.gioHangSach;

/**
 * Servlet implementation class suaGioHangController
 */
@WebServlet("/suaGioHangController")
public class suaGioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public suaGioHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String masach=request.getParameter("maSach");
		String magh=request.getParameter("magh");
		String sl=request.getParameter("soluong");
		HttpSession session=request.getSession();
		ArrayList<gioHangSach>  ghBO = (ArrayList<gioHangSach>) session.getAttribute("gioHang");
		gioHangSachBO gBO=new gioHangSachBO();
		for (gioHangSach g: ghBO) {
			if (g.getMaSach().equals(masach)) {
				g.setSoLuong(Integer.parseInt(sl));
				try {
					gBO.capNhatSoLuong(Long.parseLong(magh), masach, Integer.parseInt(sl));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		session.setAttribute("gioHang", ghBO);
		response.sendRedirect("gioHangController");
		
	}

}
