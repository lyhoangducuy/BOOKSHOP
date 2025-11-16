package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.loaiBO;
import bo.sachBO;
import model.loai;
import model.sach;

/**
 * Servlet implementation class trangChuController
 */
@WebServlet("/trangChuController")
public class trangChuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public trangChuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loaiBO lbo=new loaiBO();
		sachBO sbo=new sachBO();
		String maloai=(String)request.getParameter("maloai");
		String timKiem=(String)request.getParameter("timKiem");
		try {
			ArrayList<sach> dssach;

			if (timKiem != null && !timKiem.trim().isEmpty()) {
			    dssach = sbo.getSachTheoTen(timKiem);
			} else if (maloai != null && !maloai.trim().isEmpty()) {
			    dssach = sbo.getSachTheoLoai(maloai);
			} else {
			    dssach = sbo.getSach();
			}

			request.setAttribute("dsloai", lbo.getLoai());
			request.setAttribute("dssach", dssach);

			RequestDispatcher rd=request.getRequestDispatcher("/views/trangChu.jsp");
			rd.forward(request, response);
			return;
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
