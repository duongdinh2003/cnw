package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		try {
			response.setContentType("text/html;charset=UTF-8");
			Date date = inputDate.parse(datepicker);
			String formattedDate = outputDate.format(date);
			
			boolean success = funcBO.bookingTour(idTour, idUser, formattedDate, numParticipants);
			if (success) {
				ArrayList<Tour> tourArray = funcBO.getAllTours();
				User user = funcBO.getUserByUsername(username);
				request.setAttribute("tourArray", tourArray);
				request.setAttribute("user", user);
				destination = "/home.jsp";
				session.setAttribute("username", username);
				response.setCharacterEncoding("UTF-8");
				RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
				rd.forward(request, response);
			} else {
				out.println("<center><h1>Error occurred while booking tour.</h1></center>");
				out.println(location);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
		}
	}

}
