package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.BEAN.Tour;
import Model.BO.functionBO;

@WebServlet("/addTour")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class addTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIR = "images";
    
    public addTour() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String destination = null;
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String numberOfDays = request.getParameter("number_day");
        String price = request.getParameter("price");
	    String detail_des = request.getParameter("detail_description");
	    String video_link = request.getParameter("video_link");
        Part part = request.getPart("image");
        String fileName = extractFileName(part);
        ArrayList<Tour> tourArray = null;
        String uploadPath = "D:/testPBL/CNW/src/main/webapp/images"; // Đường dẫn tuyệt đối đến thư mục lưu trữ ảnh

        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        part.write(savePath);
        functionBO func = new functionBO();
        // Gọi hàm truy vấn ở file khác để chèn dữ liệu vào CSDL
        boolean insertSuccess = func.addTour(location, description, numberOfDays, fileName, video_link, price, detail_des);

        if (insertSuccess) {
        	tourArray = func.getAllTours();
        	request.setAttribute("tourArray", tourArray);
            destination = "/home_admin.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(request, response);
        } else {
            out.println("<center><h1>Error occurred while adding tour.</h1></center>");
        }
	}
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
