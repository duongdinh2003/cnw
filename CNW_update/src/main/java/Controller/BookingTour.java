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

@WebServlet("/BookingTour")
public class BookingTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BookingTour() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String username = (String)session.getAttribute("username");
		String location = request.getParameter("location");
		String datepicker = request.getParameter("bookingDate");
		int numParticipants = Integer.parseInt(request.getParameter("num_participants"));
		PrintWriter out = response.getWriter();
		String destination = "";
		
		functionBO funcBO = new functionBO();
		int idTour = funcBO.getIDTourByLocation(location);
		int idUser = funcBO.getIDUserByUsername(username);
		System.out.println("Username = " + username);
		System.out.println(location);
		System.out.println(idTour);
		SimpleDateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Booking> bookingList = funcBO.getBookingTourOfUser(idUser);
		
		try {
			Date date = inputDate.parse(datepicker);
			String formattedDate = outputDate.format(date);
			
			Date currentDate = new Date();
			
			if (date.compareTo(currentDate) < 0) {
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				String message = "Ngày không hợp lệ. Vui lòng chọn lại ngày khởi hành";
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('" + message + "');");
			    out.println("</script>");
			    out.println("<div style=\"padding-left: 700px;\">\r\n"
			    		+ "      <button style=\"background-color: lightgrey;\" onclick=\"window.history.back()\">Quay lại</button>\r\n"
			    		+ "    </div>"); // Chuyển hướng về trang chính
			    
			} else if (!checkValidTourDate(date, bookingList, funcBO)) {
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				String message = "Không thể đặt Tour trùng ngày với 1 Tour đã được đặt";
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('" + message + "');");
			    out.println("</script>");
			    out.println("<div style=\"padding-left: 700px;\">\r\n"
			    		+ "      <button style=\"background-color: lightgrey;\" onclick=\"window.history.back()\">Quay lại</button>\r\n"
			    		+ "    </div>"); // Chuyển hướng về trang chính
			    
			} else {
				
				boolean success = funcBO.bookingTour(idTour, idUser, formattedDate, numParticipants);
				if (success) {
					ArrayList<Tour> tourArray = funcBO.getAllTours();
					User user = funcBO.getUserByUsername(username);
					request.setAttribute("tourArray", tourArray);
					request.setAttribute("user", user);
					destination = "/home.jsp";
					session.setAttribute("username", username);
					RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
					rd.forward(request, response);
				} else {
					out.println("<center><h1>Error occurred while booking tour.</h1></center>");
					out.println(location);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
		}
	}
	
	private boolean checkValidTourDate(Date checkDate, ArrayList<Booking> bookingList, functionBO funcBO) {
		for (int i = 0; i < bookingList.size(); i++) {
			Tour tour = funcBO.getTourById(bookingList.get(i).getTour_detail_id());
			String number_day = tour.getNumber_day();
			String day[] = number_day.split(" ");
			int numDay = Integer.parseInt(day[0]);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(bookingList.get(i).getBooking_date());
			calendar.add(Calendar.DAY_OF_MONTH, numDay);
			Date doneTourDate = calendar.getTime();
			
			if (checkDate.compareTo(doneTourDate) < 0) {
				return false;
			}
		}
		
		return true;
	}

}
