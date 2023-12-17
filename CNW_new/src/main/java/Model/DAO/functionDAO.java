package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.BEAN.Booking;
import Model.BEAN.Tour;
import Model.BEAN.User;
import context.DBContext;

public class functionDAO {

	public boolean addTour(String location, String description, String number_day, String filepath) {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean isExist = false;
		try {
			conn = DBContext.openConnection();
			String sql = "INSERT INTO tour_detail(location, description, number_day, image) VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, location);
			stmt.setString(2, description);
			stmt.setString(3, number_day);
			stmt.setString(4, filepath);
			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				isExist = true;
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("MySQL JDBC driver not found.");
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("An error occurred while connecting to the database.");
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("An unexpected error occurred.");
			ex.printStackTrace();
		}

		return isExist;
	}

	public ArrayList<Tour> getAllTours() {
		ArrayList<Tour> list = new ArrayList<Tour>();

		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select * from tour_detail";
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				Tour tour = new Tour();
				tour.setDetail_id(rs.getInt(1));
				tour.setLocation(rs.getString(2));
				tour.setDescription(rs.getString(3));
				tour.setNumber_day(rs.getString(4));
				tour.setImage(rs.getString(5));
				list.add(tour);
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean bookingTour(int idTour, int idUser, String bookingDate, int numParticipants) {
		boolean success = false;
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "insert into booking (tour_detail_id, user_id, booking_date, num_participants) values ("
					+ idTour + ", " + idUser + ", '" + bookingDate + "', " + numParticipants + ")";
			
			int rowsAffected = stm.executeUpdate(query);
			
			if (rowsAffected > 0) {
				success = true;
			}
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public int getIDTourByLocation(String location) {
		int id = 0;
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select detail_id from tour_detail where location = '"+location+"'";
			ResultSet rs = stm.executeQuery(query);
			
			if (rs.next()) {
				id = rs.getInt("detail_id");
			}
			
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public int getIDUserByUsername(String username) {
		int id = 0;
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select user_id from user where username = '"+username+"'";
			ResultSet rs = stm.executeQuery(query);
			
			if (rs.next()) {
				id = rs.getInt("user_id");
			}
			
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public ArrayList<Booking> getBookingTourOfUser(int idUser) {
		ArrayList<Booking> list = new ArrayList<Booking>();
		
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select * from booking where user_id = "+idUser;
			ResultSet rs = stm.executeQuery(query);
			
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setBooking_id(rs.getInt(1));
				booking.setTour_detail_id(rs.getInt(2));
				booking.setUser_id(rs.getInt(3));
				booking.setBooking_date(rs.getDate(4));
				booking.setNum_participants(rs.getInt(5));
				list.add(booking);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public User getUserByUsername(String username) {
		User user = new User();
		
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select * from user where username = '"+username+"'";
			ResultSet rs = stm.executeQuery(query);
			
			if (rs.next()) {
				user.setUser_id(rs.getInt(1));
				user.setFull_name(rs.getString(2));
				user.setAddress(rs.getString(3));
				user.setPhone_number(rs.getString(4));
				user.setUsername(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setRole(rs.getString(8));
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public Tour getTourById(int idTour) {
		Tour tour = new Tour();
		
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select * from tour_detail where detail_id = "+idTour;
			ResultSet rs = stm.executeQuery(query);
			
			if (rs.next()) {
				tour.setDetail_id(rs.getInt(1));
				tour.setLocation(rs.getString(2));
				tour.setDescription(rs.getString(3));
				tour.setNumber_day(rs.getString(4));
				tour.setImage(rs.getString(5));
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tour;
	}
	
	public void cancelTour(int idBooking) {
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "delete from booking where booking_id = "+idBooking;
			int rowsAffected = stm.executeUpdate(query);
			
			if (rowsAffected > 0) {
				System.out.println("Cancel tour successfully");
			} else {
				System.out.println("Cancel tour failed");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Booking getBookingById(int idBooking) {
		Booking booking = new Booking();
		
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select * from booking where booking_id = "+idBooking;
			ResultSet rs = stm.executeQuery(query);
			
			if (rs.next()) {
				booking.setBooking_id(rs.getInt(1));
				booking.setTour_detail_id(rs.getInt(2));
				booking.setUser_id(rs.getInt(3));
				booking.setBooking_date(rs.getDate(4));
				booking.setNum_participants(rs.getInt(5));
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return booking;
	}
	
	public ArrayList<Tour> getToursBySearch(String search) {
		ArrayList<Tour> list = new ArrayList<Tour>();
		
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select * from tour_detail where location like '%"+search+"%'";
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				Tour tour = new Tour();
				tour.setDetail_id(rs.getInt(1));
				tour.setLocation(rs.getString(2));
				tour.setDescription(rs.getString(3));
				tour.setNumber_day(rs.getString(4));
				tour.setImage(rs.getString(5));
				list.add(tour);
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Booking getLastBooking(int idUser) {
		Booking booking = new Booking();
		
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "SELECT * FROM booking WHERE user_id = "+idUser+" ORDER BY booking_date DESC LIMIT 1";
			ResultSet rs = stm.executeQuery(query);
			
			if (rs.next()) {
				booking.setBooking_id(rs.getInt(1));
				booking.setTour_detail_id(rs.getInt(2));
				booking.setUser_id(rs.getInt(3));
				booking.setBooking_date(rs.getDate(4));
				booking.setNum_participants(rs.getInt(5));
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return booking;
	}
	
	public boolean updateBooking(int bookingId, String bookingDate, int num_participants) {
		boolean success = false;
		
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "update booking set booking_date = '"+bookingDate+"', num_participants = "+num_participants+" where booking_id = "+bookingId;
			
			int rowsAffected = stm.executeUpdate(query);
			
			if (rowsAffected > 0) {
				success = true;
			}
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
//	public static void main(String[] args) {
//		functionDAO dao = new functionDAO();
//		User user = dao.getUserByUsername("dinh");
//		Booking booking = dao.getLastBooking(1);
//		System.out.println(user);
//		System.out.println(booking);
//	}
	
}
