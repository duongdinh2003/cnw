package Model.BO;

import Model.DAO.RegisterDAO;

public class RegisterBO {

	RegisterDAO user = new RegisterDAO();
	public boolean registerUser(String fullname, String address, String phone_number, String email, String username, String password, String role) {
        return user.registerUser(fullname, address, phone_number, email, username, password, role);
    }
}
