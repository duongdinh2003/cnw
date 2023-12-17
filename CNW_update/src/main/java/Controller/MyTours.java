package Controller;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/MyTours")
public class MyTours extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MyTours() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String destination = "";
		
		functionBO funcBO = new functionBO();
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
		destination = "/viewMyTours.jsp";
		session.setAttribute("username", username);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

}
