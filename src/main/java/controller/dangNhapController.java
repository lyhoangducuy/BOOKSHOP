package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bo.khachHangBO;
import model.khachHang;

@WebServlet("/dangNhapController")
public class dangNhapController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Không cần doGet()
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	HttpSession session = request.getSession();

            String tendn = request.getParameter("txtUser");
            String matKhau = request.getParameter("txtPass");
            
            khachHangBO khBO = new khachHangBO();
            khachHang kh = khBO.kiemTraDangNhap(tendn, matKhau);
            if (session.getAttribute("khachHang")==null && kh==null)
            {
            	 RequestDispatcher rd = request.getRequestDispatcher("/views/auth/dangNhap.jsp");
                 rd.forward(request, response);
                 return;
            }
            if (kh != null ) {
                session.setAttribute("khachHang", kh);
                session.setAttribute("daDangNhap", "1");
                response.sendRedirect("trangChuController");
            } else {
                request.setAttribute("msg", "Sai thông tin đăng nhập!");
                RequestDispatcher rd = request.getRequestDispatcher("/views/auth/dangNhap.jsp");
                rd.forward(request, response); // login thất bại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
