package controller.hoaDonThanhToan;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.hoaDonBO;
import model.hoaDon;

/**
 * Servlet implementation class timKiemHoaDon
 */
@WebServlet("/admin/timKiemHoaDon")
public class timKiemHoaDon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public timKiemHoaDon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key=request.getParameter("timKiem");
		String page=request.getParameter("page");
		hoaDonBO hBO=new hoaDonBO();
		ArrayList<hoaDon> ds=new ArrayList<hoaDon>();
		try {
			
			for (hoaDon h: hBO.timHoaDonTheoMaKh(Long.parseLong(key))) {
				ds.add(h);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("dsHoaDon", ds);
		request.getRequestDispatcher("views/admin/"+page+"/"+page+".jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
