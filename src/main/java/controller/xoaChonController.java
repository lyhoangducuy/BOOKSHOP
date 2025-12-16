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
 * Servlet implementation class xoaChonController
 */
@WebServlet("/xoaChonController")
public class xoaChonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xoaChonController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String masach=request.getParameter("maSach");
		String magh=request.getParameter("magh");
		gioHangSachBO gBO=new gioHangSachBO();
		HttpSession session=request.getSession();
		ArrayList<gioHangSach>  ghBO = (ArrayList<gioHangSach>) session.getAttribute("gioHang");
		for (gioHangSach g: ghBO) {
			if (g.getMaSach().equals(masach)) {
				try {
					gBO.xoaGioHangSach(Long.parseLong(magh), masach);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ghBO.remove(g);
				break;
			}
		}
		;
		session.setAttribute("gioHang", ghBO);
		response.sendRedirect("gioHangController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
