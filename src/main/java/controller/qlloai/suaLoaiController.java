package controller.qlloai;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.loaiBO;
import model.loai;

/**
 * Servlet implementation class suaLoaiController
 */
@WebServlet("/admin/suaLoaiController")
public class suaLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public suaLoaiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String maloai = request.getParameter("maloai");
        String tenLoai = request.getParameter("tenloai");

        try {
            loaiBO lbo = new loaiBO();

            // --- Nếu submit form ---
            if (tenLoai != null) {
                loai l = new loai(maloai, tenLoai);
                lbo.updateLoai(l);
                response.sendRedirect("suaLoaiController?maloai=" + maloai + "&mess=Thanh cong");
                return;
            }
            if (maloai != null) {
                request.setAttribute("loai", lbo.getTenLoai(maloai));
            }

            String mess = request.getParameter("mess");
            request.setAttribute("mess", mess);

            request.getRequestDispatcher("/views/admin/loai/suaLoai.jsp")
                   .forward(request, response);

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
