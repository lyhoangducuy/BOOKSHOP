package controller.qlsach;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.sachBO;
import model.sach;

/**
 * Servlet implementation class suaSachController
 */
@WebServlet("/admin/suaSachController")
public class suaSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public suaSachController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maSach1=request.getParameter("maSach1");
		String maSach=request.getParameter("maSach");
		String tenSach = request.getParameter("tenSach");
		String gia = request.getParameter("gia");
		String soLuong = request.getParameter("soLuong");
		String loaiSach = request.getParameter("loaiSach");
		String tacGia = request.getParameter("tacGia");
		String soTap = request.getParameter("soTap"); 
		sachBO sBO=new sachBO();
		try {
			if (maSach1!=null) {
				request.setAttribute("sach",sBO.getSachTheoMa(maSach1) );
				request.getRequestDispatcher("/views/admin/sach/suaSach.jsp").forward(request, response);
			}
			if (maSach!=null) {
				sach cu = sBO.getSachTheoMa(maSach);

	            sach s = new sach(
	                maSach,
	                tenSach,
	                Integer.parseInt(soLuong),
	                Long.parseLong(gia),
	                loaiSach,
	                Integer.parseInt(soTap),
	                cu.getAnh(),          // giữ nguyên ảnh cũ
	                cu.getNgayNhap(),     // giữ nguyên ngày nhập
	                tacGia
	            );
	            int n=sBO.updateSach(s);
				response.sendRedirect("suaSachController?maSach1="+maSach);
			}
            
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
