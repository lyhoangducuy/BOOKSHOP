package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.lichSuMuaHangBO;
import bo.loaiBO;
import model.khachHang;
import model.lichSuMuaHang;

/**
 * Servlet implementation class lichSuMuaHangController
 */
@WebServlet("/lichSuMuaHangController")
public class lichSuMuaHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lichSuMuaHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            khachHang kh = (khachHang) session.getAttribute("khachHang");
            if(kh == null) {
                response.sendRedirect("dangNhapController");
                return;
            }

            int pageSize = 6;
            int trang = 1;
            String pageParam = request.getParameter("page");
            if(pageParam != null && !pageParam.isEmpty()) {
                try { trang = Integer.parseInt(pageParam); } catch(Exception e) { trang=1; }
            }

            lichSuMuaHangBO lsbo = new lichSuMuaHangBO();
            ArrayList<lichSuMuaHang> allLS = lsbo.getlichsu(kh.getMakh());

            // Phân trang
            int totalRows = allLS.size();
            int fromIndex = (trang-1)*pageSize;
            int toIndex = Math.min(fromIndex + pageSize, totalRows);
            ArrayList<lichSuMuaHang> dslsPage = new ArrayList<>();
            if(fromIndex < totalRows) dslsPage.addAll(allLS.subList(fromIndex, toIndex));

            request.setAttribute("dsls", dslsPage);
            request.setAttribute("totalRows", totalRows);

            loaiBO lbo = new loaiBO();
            request.setAttribute("dsloai", lbo.getLoai());

            request.getRequestDispatcher("/views/lichSuMuaHang.jsp").forward(request, response);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
