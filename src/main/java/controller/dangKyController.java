package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.khachHangBO;
import model.khachHang;

/**
 * Servlet implementation class dangKyController
 */
@WebServlet("/dangKyController")
public class dangKyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangKyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tendn=request.getParameter("txtTendn");
		String matkhau=request.getParameter("txtMatkhau");
		String nhaplai=request.getParameter("txtNhaplai");
		String hovaten=request.getParameter("txtHovaten");
		String diachi=request.getParameter("txtDiachi");
		String sdt=request.getParameter("txtSdt");
		String email=request.getParameter("sdt");
		
		if (matkhau!=null && nhaplai!=null && nhaplai.equals(matkhau)==false)
		{
			request.setAttribute("msg2", "Mật khẩu không trùng khớp !");
			RequestDispatcher rd=request.getRequestDispatcher("/views/auth/dangKy.jsp");
			rd.forward(request, response);
			return;
		}
		
		khachHangBO khBO=new khachHangBO();
		Boolean check=khBO.taoKhachHang(tendn, hovaten, diachi, sdt, email, matkhau);
		
		if (check==false && matkhau!=null && tendn!=null && nhaplai!=null)
		{
			request.setAttribute("msg3", "Tên người dùng đã tồn tại !");
			RequestDispatcher rd=request.getRequestDispatcher("/views/auth/dangKy.jsp");
			rd.forward(request, response);
			return;
		}else if (check==true){
			request.setAttribute("msg3", "Tạo tài khoản thành công !");
			RequestDispatcher rd=request.getRequestDispatcher("/views/auth/dangKy.jsp");
			rd.forward(request, response);
			return;
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("/views/auth/dangKy.jsp");
			rd.forward(request, response);
		}
	}

}
