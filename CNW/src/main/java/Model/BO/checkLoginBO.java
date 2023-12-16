package Model.BO;

import Model.DAO.checkLoginDAO;

public class checkLoginBO {

	checkLoginDAO dao = new checkLoginDAO();
	
	public boolean isValidUser(String username, String password, String role) {
		return dao.isExistedUser(username, password, role);
	}
}
