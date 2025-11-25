package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.gioHangBO;
import bo.sachBO;
import model.gioHang;
import model.sach;

/**
 * Servlet implementation class gioHangController
 */
@WebServlet("/gioHangController")
public class gioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gioHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String maSachMua=request.getParameter("maSachMua");
		String soLuongMua=request.getParameter("soLuongMua");
		String giaSachMua=request.getParameter("giaSachMua");
		String tenSachMua=request.getParameter("tenSachMua");
		String anhSachMua=request.getParameter("anhSachMua");
		gioHangBO ghBO=(gioHangBO) session.getAttribute("gioHang");
		if (ghBO==null)
			ghBO=new gioHangBO();
		
		if (maSachMua!=null)
			try {
				ghBO.themVaoGioHang(maSachMua,tenSachMua, anhSachMua, Integer.parseInt(soLuongMua), Long.parseLong(giaSachMua));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		session.setAttribute("gioHang",ghBO);
		response.sendRedirect("hienThiGioHangController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
