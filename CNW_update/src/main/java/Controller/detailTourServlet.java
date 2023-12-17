package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Tour;
import Model.BO.functionBO;

@WebServlet("/detailTourServlet")
public class detailTourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public detailTourServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		functionBO fun = new functionBO();
		int id =  Integer.parseInt(request.getParameter("tourId"));
		Tour tour = fun.getTourById(id);
		request.setAttribute("tour_detail", tour);
	    request.getRequestDispatcher("detailTour.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
