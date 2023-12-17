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

import Model.BEAN.Tour;
import Model.BEAN.User;
import Model.BO.functionBO;

@WebServlet("/SearchTour")
public class SearchTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SearchTour() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String destination = "";
		String search = request.getParameter("search");
		
		functionBO funcBO = new functionBO();
		ArrayList<Tour> tourArray = funcBO.getToursBySearch(search);
		User user = funcBO.getUserByUsername(username);
		request.setAttribute("tourArray", tourArray);
		request.setAttribute("user", user);
		destination = "/home.jsp";
		session.setAttribute("username", username);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
