package controller.qlloai;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.loaiBO;

/**
 * Servlet implementation class xoaLoaiController
 */
@WebServlet("/admin/xoaLoaiController")
public class xoaLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xoaLoaiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ml=request.getParameter("maloai");
		loaiBO lbo=new loaiBO();
		try {
			int i=lbo.deleteLoai(ml);
			if (i<0)
				System.out.println("That bai");
			response.sendRedirect("quanLyLoaiController");
				
		} catch (Exception e) {
			response.sendRedirect("quanLyLoaiController");
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
