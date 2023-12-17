package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Booking;
import Model.BEAN.Tour;
import Model.BEAN.User;
import Model.BO.functionBO;

@WebServlet("/getAllBookingServlet")
public class getAllBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public getAllBookingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Booking> booking = null;
		ArrayList<Tour> tourList = new ArrayList<Tour>();
		ArrayList<User> userList = new ArrayList<User>();
        functionBO func = new functionBO();
        booking = func.getAllBookingBO();
        for (int i=0; i<booking.size(); i++) {
        	Tour tour = func.getTourById(booking.get(i).getTour_detail_id());
        	User user = func.getUserById(booking.get(i).getUser_id());
        	tourList.add(tour);
        	userList.add(user);
        }
        request.setAttribute("booking", booking);
        request.setAttribute("tourList", tourList);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("viewListTour_admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
