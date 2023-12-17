package Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Model.BEAN.Tour;
import Model.BO.functionBO;

@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public updateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		functionBO fun = new functionBO();
		int id =  Integer.parseInt(request.getParameter("id"));
		Tour tour = fun.getTourById(id);
		request.setAttribute("tour", tour);
		session.setAttribute("idtour", id);
	    request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession();
	    String destination = null;
	    int id = (int) session.getAttribute("idtour");
	    String location = request.getParameter("location");
	    String description = request.getParameter("description");
	    String numberOfDays = request.getParameter("number_day");
	    String price = request.getParameter("price");
	    String detail_des = request.getParameter("detail_description");
	    String video_link = request.getParameter("video_link");
	    String path = request.getParameter("newimage");
	    System.out.println("Path: " + path);
	    Part part = request.getPart("newimage");
	    InputStream input = part.getInputStream();
	    System.out.println(input);
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
	    boolean insertSuccess = func.updateTourBO(location, description, numberOfDays, fileName, price, detail_des, video_link, id);

	    if (insertSuccess) {
	        tourArray = func.getAllTours();
	        request.setAttribute("tourArray", tourArray);
	        destination = "/home_admin.jsp";
	        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
	        rd.forward(request, response);
	    } else {
	        PrintWriter out = response.getWriter();
	        out.println("<center><h1>Error occurred while adding tour.</h1></center>");
	    }
	}
	
	private String extractFileName(Part part) {
	    if (part != null) {
	        String contentDisp = part.getHeader("content-disposition");
	        if (contentDisp != null) {
	            String[] items = contentDisp.split(";");
	            for (String s : items) {
	                if (s != null && s.trim().startsWith("filename")) {
	                    return s.substring(s.indexOf("=") + 2, s.length() - 1);
	                }
	            }
	        }
	    }
	    return "";
	}

}
