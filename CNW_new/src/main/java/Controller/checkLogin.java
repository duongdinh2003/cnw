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
import Model.BO.checkLoginBO;
import Model.BO.functionBO;

@WebServlet("/checkLogin")
public class checkLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public checkLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destination = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		checkLoginBO bo = new checkLoginBO();
		
		if (bo.isValidUser(username, password, "Client")) {
			functionBO funcBO = new functionBO();
			ArrayList<Tour> tourArray = funcBO.getAllTours();
			User user = funcBO.getUserByUsername(username);
			request.setAttribute("tourArray", tourArray);
			request.setAttribute("user", user);
			destination = "/home.jsp";
			
		} else if (bo.isValidUser(username, password, "Admin")) {
			functionBO funcBO = new functionBO();
			ArrayList<Tour> tourArray = funcBO.getAllTours();
			request.setAttribute("tourArray", tourArray);
			destination = "/home_admin.jsp";

		} else {
			destination = "/Login.jsp";
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("username", username);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

}
