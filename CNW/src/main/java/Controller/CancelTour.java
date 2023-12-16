package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.Booking;
import Model.BEAN.Tour;
import Model.BEAN.User;
import Model.BO.functionBO;

@WebServlet("/CancelTour")
public class CancelTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CancelTour() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String destination = "";
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		
		functionBO funcBO = new functionBO();
		
		Booking booking = funcBO.getBookingById(bookingId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date nextDay = calendar.getTime();
		
		int idUser = funcBO.getIDUserByUsername(username);
		ArrayList<Booking> bookingArray = funcBO.getBookingTourOfUser(idUser);
		ArrayList<Tour> tourArray = funcBO.getAllTours();
		ArrayList<Tour> myTourArray = new ArrayList<Tour>();
		for (int i=0; i<bookingArray.size(); i++) {
			Tour tour = funcBO.getTourById(bookingArray.get(i).getTour_detail_id());
			myTourArray.add(tour);
		}
		
		User user = funcBO.getUserByUsername(username);
		request.setAttribute("bookingArray", bookingArray);
		request.setAttribute("tourArray", tourArray);
		request.setAttribute("myTourArray", myTourArray);
		request.setAttribute("user", user);
		session.setAttribute("username", username);
		
		if (booking.getBooking_date().compareTo(nextDay) > 0) {
			funcBO.cancelTour(bookingId);
			destination = "/viewMyTours.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String message = "Không thể hủy Tour trước 1 ngày khởi hành";
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('" + message + "');");
		    out.println("</script>");
		    out.println("<div style=\"padding-left: 700px;\">\r\n"
		    		+ "      <button style=\"background-color: lightgrey;\" onclick=\"window.history.back()\">Quay lại</button>\r\n"
		    		+ "    </div>"); // Chuyển hướng về trang chính
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
