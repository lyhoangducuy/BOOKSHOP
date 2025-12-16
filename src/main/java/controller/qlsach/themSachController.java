package controller.qlsach;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bo.loaiBO;
import bo.sachBO;
import model.sach;

/**
 * Servlet implementation class themSachController
 */
@WebServlet("/admin/themSachController")
public class themSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public themSachController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loaiBO lBO=new loaiBO();
		HttpSession http=request.getSession();
		try {
			http.setAttribute("dsLoai", lBO.getLoai());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/admin/sach/themSach.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Khởi tạo FileUpload
		DiskFileItemFactory  factory = new DiskFileItemFactory();
        ServletFileUpload  upload = new ServletFileUpload(factory);

        // Lưu thông tin sách
        String maSach = "", tenSach = "", maLoai = "", tacGia = "";
        int soLuong = 0, soTap = 0;
        long gia = 0;
        LocalDate ngayNhap = null;

        String fileName = "noimage.jpg"; // tên ảnh

        try {
            // Parse request
            List<FileItem> items = upload.parseRequest(request);

            // Lấy đường dẫn thư mục /files để lưu ảnh
            String dirUrl = request.getServletContext().getRealPath("") + File.separator + "image_sach";

            File dir = new File(dirUrl);
            if (!dir.exists()) dir.mkdir();

            // Duyệt qua các item
            for (FileItem item : items) {

                // Nếu là file upload
            	if (!item.isFormField()) {

            	    String uploadedName = item.getName();

            	    // CÓ CHỌN ẢNH
            	    if (uploadedName != null && !uploadedName.trim().isEmpty()) {

            	        fileName = new File(uploadedName).getName(); // tránh path Windows

            	        String filePath = dirUrl + File.separator + fileName;
            	        File uploadedFile = new File(filePath);
            	        item.write(uploadedFile);
            	    }
            	    // KHÔNG CHỌN ẢNH → GIỮ fileName = "noimage.jpg"
            	}


                // Nếu là text input
                else {
                    String field = item.getFieldName();
                    String value = item.getString("UTF-8");

                    switch (field) {
                        case "maSach":  maSach = value; break;
                        case "tenSach": tenSach = value; break;
                        case "maLoai":  maLoai = value; break;
                        case "tacGia":  tacGia = value; break;
                        case "soLuong": soLuong = Integer.parseInt(value); break;
                        case "gia":     gia = Long.parseLong(value); break;
                        case "soTap":   soTap = Integer.parseInt(value); break;
                        case "ngayNhap": ngayNhap = LocalDate.parse(value); break;
                    }
                }
            }
            String anh = "image_sach/" + fileName;
            // Tạo object sách
            sach s = new sach(
                maSach, tenSach, soLuong, gia, maLoai, soTap,
                anh, ngayNhap, tacGia
            );

            // Gọi BO để insert DB
            sachBO sBO = new sachBO();
            int n = sBO.insertSach(s);

            if (n > 0) {
                response.sendRedirect(request.getContextPath()+"/admin/quanLySachController");
                return;
            } else {
                request.setAttribute("mess", "Lỗi thêm sách!");
                request.getRequestDispatcher("views/admin/sach/themSach.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mess", "Lỗi xử lý form/ảnh!");
            request.getRequestDispatcher("views/admin/sach/themSach.jsp").forward(request, response);
        }
	}

}
