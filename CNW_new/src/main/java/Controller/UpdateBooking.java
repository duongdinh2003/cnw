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

@WebServlet("/UpdateBooking")
public class UpdateBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UpdateBooking() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String destination = "";
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		
		functionBO funcBO = new functionBO();
		Booking booking = funcBO.getBookingById(bookingId);
		Tour tour = funcBO.getTourById(booking.getTour_detail_id());
		
		request.setAttribute("booking", booking);
		request.setAttribute("tour", tour);
		session.setAttribute("username", username);
		session.setAttribute("bookingId", bookingId+"");
		System.out.println("bookingId: "+bookingId);
		destination = "/updateBooking.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String location = request.getParameter("location");
		String datepicker = request.getParameter("bookingdate");
		System.out.println(location);
		System.out.println(datepicker);
		System.out.println(request.getParameter("number_participants"));
		int num_participants = Integer.parseInt(request.getParameter("number_participants"));
		PrintWriter out = response.getWriter();
		String destination = "";
		int bookingId = (int)session.getAttribute("bookingId");
		
		functionBO funcBO = new functionBO();
		int idUser = funcBO.getIDUserByUsername(username);
		
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
			    		+ "    </div>"); // Quay lại trang trước
			    
			} else if (!checkValidTourDate(date, bookingList, funcBO)) {
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				String message = "Không thể chọn trùng ngày với 1 Tour đã được đặt";
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('" + message + "');");
			    out.println("</script>");
			    out.println("<div style=\"padding-left: 700px;\">\r\n"
			    		+ "      <button style=\"background-color: lightgrey;\" onclick=\"window.history.back()\">Quay lại</button>\r\n"
			    		+ "    </div>"); // Quay lại trang trước
			    
			} else {
				
				boolean success = funcBO.updateBooking(bookingId, formattedDate, num_participants);
				if (success) {
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
					destination = "/viewMyTours.jsp";
					session.setAttribute("username", username);
					RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
					rd.forward(request, response);
				} else {
					out.println("<center><h1>Error occurred while update booking.</h1></center>");
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
