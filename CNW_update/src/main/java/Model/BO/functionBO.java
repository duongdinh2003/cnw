package Model.BO;

import java.util.ArrayList;

import Model.BEAN.Booking;
import Model.BEAN.Tour;
import Model.BEAN.User;
import Model.DAO.functionDAO;

public class functionBO {

	functionDAO funcDAO = new functionDAO();
	
	public boolean addTour(String location, String description, String number_day, String filepath, String video_link, String price, String detail_description) {
		return funcDAO.addTour(location, description, number_day, filepath, video_link, price, detail_description);
	}
	
	public ArrayList<Tour> getAllTours() {
		return funcDAO.getAllTours();
	}
	
	public boolean bookingTour(int idTour, int idUser, String bookingDate, int numParticipants) {
		return funcDAO.bookingTour(idTour, idUser, bookingDate, numParticipants);
	}
	
	public int getIDTourByLocation(String location) {
		return funcDAO.getIDTourByLocation(location);
	}
	
	public int getIDUserByUsername(String username) {
		return funcDAO.getIDUserByUsername(username);
	}
	
	public ArrayList<Booking> getBookingTourOfUser(int idUser) {
		return funcDAO.getBookingTourOfUser(idUser);
	}
	
	public User getUserByUsername(String username) {
		return funcDAO.getUserByUsername(username);
	}
	
	public Tour getTourById(int idTour) {
		return funcDAO.getTourById(idTour);
	}
	
	public void cancelTour(int idBooking) {
		funcDAO.cancelTour(idBooking);
	}
	
	public Booking getBookingById(int idBooking) {
		return funcDAO.getBookingById(idBooking);
	}
	
	public ArrayList<Tour> getToursBySearch(String search) {
		return funcDAO.getToursBySearch(search);
	}
	
	public Booking getLastBooking(int idUser) {
		return funcDAO.getLastBooking(idUser);
	}
	
	public boolean updateBooking(int bookingId, String bookingDate, int num_participants) {
		return funcDAO.updateBooking(bookingId, bookingDate, num_participants);
	}
	
	public boolean updateTourBO(String location, String description, String number_day, String filepath, String video_link, String price, String detail_description, int id) {
		return funcDAO.updateTourDAO(location, description, number_day, filepath, video_link, price, detail_description, id);
	}
	
	public boolean deleteBO(int id) {
		return funcDAO.deleteDAO(id);
	}
	
	public ArrayList<Booking> getAllBookingBO() {
		return funcDAO.getAllBookingDAO();
	}
	
	public User getUserById(int idUser) {
		return funcDAO.getUserById(idUser);
	}
}
