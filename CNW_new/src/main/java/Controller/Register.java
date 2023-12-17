package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.RegisterBO;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = null;
		request.setCharacterEncoding("UTF-8");
	    String fullname = request.getParameter("full_name");
	    String address = request.getParameter("address");
	    String phone_number = request.getParameter("phone");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String username = request.getParameter("username");
	    String role = request.getParameter("role");
	    RegisterBO user = new RegisterBO();
	    user.registerUser(fullname, address, phone_number, email, username, password, role);
	    destination = "/Login.jsp";
	    response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

}
