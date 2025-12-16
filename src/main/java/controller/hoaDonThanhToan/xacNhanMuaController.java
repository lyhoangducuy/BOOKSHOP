package controller.hoaDonThanhToan;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.hoaDonBO;
import model.trangThaiHoaDon;

/**
 * Servlet implementation class xacNhanMuaController
 */
@WebServlet("/admin/xacNhanMuaController")
public class xacNhanMuaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xacNhanMuaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Lấy mã hóa đơn
        String maHoaDonStr = request.getParameter("maHoaDon");
        long maHoaDon = Long.parseLong(maHoaDonStr);
        
        hoaDonBO hBO = new hoaDonBO();
        String thongBao = "";
        
        try {
            // 2. Gọi hàm DAO để cập nhật trạng thái 'damua' thành TRUE (1)
            boolean success = hBO.capNhatTrangThaiDaMua(maHoaDon, true); 
            
            if (success) {
                thongBao = "Xac nhan thanh toan " + maHoaDon + " thanh cong!";
            } else {
                thongBao = "Loi kh cap nhat dc " + maHoaDon;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            thongBao = "Loi";
        }
        
        // 3. Chuyển hướng về trang quản lý hóa đơn chờ
        request.setAttribute("popup", thongBao);
        
        // Chuyển hướng đến Controller hiển thị danh sách hóa đơn chưa mua
        RequestDispatcher rd = request.getRequestDispatcher("/admin/hoaDonChuaThanToanController");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
