package controller.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.vaiTro;

@WebFilter("/admin/*")
public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        // CHƯA ĐĂNG NHẬP
        if (session == null || session.getAttribute("vaiTro") == null) {
            response.sendRedirect(request.getContextPath() + "/dangNhapController");
            return;
        }

        vaiTro vt = (vaiTro) session.getAttribute("vaiTro");

        // KHÔNG PHẢI ADMIN
        if (!vt.isQuyen()) {
        	 request.getRequestDispatcher("/views/404.jsp")
             .forward(request, response);
        	 return;
        }

        // HỢP LỆ → CHO ĐI TIẾP
        chain.doFilter(request, response);
    }
}
