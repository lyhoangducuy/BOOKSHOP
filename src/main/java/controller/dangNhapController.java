package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bo.khachHangBO;
import dao.vaiTroDAO;
import model.khachHang;
import model.vaiTro;
import nl.captcha.Captcha;

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
        	String daDangNhap= (String)session.getAttribute("daDangNhap");
        	Integer dangNhapSai=(Integer) session.getAttribute("dangNhapSai");
        	
        	if (daDangNhap!=null && daDangNhap.equals("1"))
        	{
        		request.getRequestDispatcher("trangChuController").forward(request, response);
        		return;
        	}
        	if (dangNhapSai==null)
        	{
        		session.setAttribute("dangNhapSai", 0);
        		dangNhapSai=0;
        		RequestDispatcher rd=request.getRequestDispatcher("/views/auth/dangNhap.jsp");
            	rd.forward(request, response);
            	return;
        	}
        	if (dangNhapSai>=3)
        	{
        		session.setAttribute("hienCapCha",true);
        		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
                String captchaAnswer = request.getParameter("traLoi");
                 
                if (captchaAnswer==null || captchaAnswer.trim().isEmpty())
                {
                	RequestDispatcher rd=request.getRequestDispatcher("/views/auth/dangNhap.jsp");
                	rd.forward(request, response);
                	return;
                }
                if (!captcha.isCorrect(captchaAnswer)) {
                    request.setAttribute("captchaMsg", "Captcha không đúng!");
                    request.getRequestDispatcher("/views/auth/dangNhap.jsp").forward(request, response);
                    return;
                }
        		
        	}
        	
            String tendn = request.getParameter("txtUser");
            String matKhau = request.getParameter("txtPass");
            khachHangBO khBO = new khachHangBO();
            khachHang kh = khBO.kiemTraDangNhap(tendn, matKhau);
            if (tendn == null || matKhau == null) {
                RequestDispatcher rd = request.getRequestDispatcher("/views/auth/dangNhap.jsp");
                rd.forward(request, response);
                return;
            }
            if (kh != null ) {
            	
            	vaiTroDAO vtDao = new vaiTroDAO();
                vaiTro vt = vtDao.kiemTraDangNhap(tendn, matKhau);

                if (vt != null) {
                    session.setAttribute("vaiTro", vt);
                    session.setAttribute("khachHang", kh);
                    session.setAttribute("dangNhapSai", 0);
                    session.setAttribute("hienCapCha", false);
                    session.setAttribute("daDangNhap", "1");

                    if (vt.isQuyen()) {
                        response.sendRedirect(request.getContextPath() + "/admin/trangChuAdminController");
                    } else {
                        // USER
                        response.sendRedirect("trangChuController");
                    }


                    return;
                
                }

                response.sendRedirect("trangChuController");
                return;
            } 
            if (kh==null){
                request.setAttribute("msg", "Sai thông tin đăng nhập!");
                dangNhapSai++;
                session.setAttribute("dangNhapSai", dangNhapSai);
                RequestDispatcher rd = request.getRequestDispatcher("/views/auth/dangNhap.jsp");
                rd.forward(request, response); // login thất bại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
