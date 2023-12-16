package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Tour;
import Model.BO.functionBO;

@WebServlet("/getAllTour")
public class getAllTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public getAllTour() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Tour> tourArray = null;
        functionBO func = new functionBO();
        tourArray = func.getAllTours();
        request.setAttribute("tourArray", tourArray);
        request.getRequestDispatcher("home_admin.jsp").forward(request, response);
	}

}
