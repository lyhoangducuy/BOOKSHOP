package controller.qlloai;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.loaiBO;
import model.loai;

/**
 * Servlet implementation class themLoaiController
 */
@WebServlet("/admin/themLoaiController")
public class themLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public themLoaiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maloai=request.getParameter("maloai");
		String tenloai=request.getParameter("tenloai");
		HttpSession session;
		loaiBO lBO=new loaiBO();
		if (maloai!=null && tenloai!=null && !maloai.isEmpty()&& !tenloai.isEmpty()) {
			try {
				lBO.insertLoai(new loai(maloai, tenloai));
			} catch (Exception e) {
				e.getMessage();
			}
		}
		request.getRequestDispatcher("/views/admin/loai/themLoai.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
