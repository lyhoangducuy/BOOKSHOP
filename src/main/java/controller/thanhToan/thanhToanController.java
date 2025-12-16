package controller.thanhToan;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.hoaDonDAO;
import dao.chiTietHoaDonDAO;
import model.gioHang;
import model.gioHangSach;
import model.hoaDon;
import model.khachHang;
import bo.chiTietHoaDonBO;
import bo.gioHangBO;
import bo.gioHangSachBO;
import bo.hoaDonBO;
import bo.sachBO;

@WebServlet("/thanhToanController")
public class thanhToanController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	khachHang kh=(khachHang)session.getAttribute("khachHang");
    	if (kh==null)
    		response.sendRedirect("dangNhapController");
    	hoaDonBO hdBO=new hoaDonBO();
    	String thanhToanGio=request.getParameter("thanhToan");
    	String mahdt=(String )request.getParameter("mahd");
    	
    		try {
    			
    			
    			if (thanhToanGio!=null) {
    				hoaDon hd = hdBO.themHoaDon(kh.getMakh());
        			
	    			chiTietHoaDonBO cBO=new chiTietHoaDonBO();
	    	    	ArrayList<gioHangSach> gh= (ArrayList<gioHangSach>)session.getAttribute("gioHang");
	    	    	sachBO sBO=new sachBO();
	    			for (gioHangSach g:gh)
	    			{
	    				if (sBO.getSachTheoMa(g.getMaSach()).getSoLuong()>=g.getSoLuong()) {
	    					sBO.giamSoLuong(g.getMaSach(), g.getSoLuong());
	    					cBO.themChiTietHoaDon(hd.getMaHoaDon(),g.getMaSach() , g.getSoLuong());
	    				}else {
	    					
	    				}
	    			}
	    			gioHangSachBO bb=new gioHangSachBO();
	    			gioHangBO gio=new gioHangBO();
	    			session.removeAttribute("gioHang");
	    			long mgio=gio.getGioHangTheoMaKH(kh.getMakh()).getMaGioHang();
	    			bb.xoaAll(mgio);
	    			request.setAttribute("mahd", hd.getMaHoaDon());
	    			
	    			
	    			
    			}else {
    				
                   
    				RequestDispatcher rd=request.getRequestDispatcher("/views/thanhToan.jsp");
    				rd.forward(request, response);
    				return;
    			}
    			
    			
    			RequestDispatcher rd = request.getRequestDispatcher("/views/chiTietHoaDon.jsp");
                rd.forward(request, response);
                return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
