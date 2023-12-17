package Model.BEAN;

import java.sql.Date;

public class Booking {

	private int booking_id;
	private int tour_detail_id;
	private int user_id;
	private Date booking_date;
	private int num_participants;
	
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public int getTour_detail_id() {
		return tour_detail_id;
	}
	public void setTour_detail_id(int tour_detail_id) {
		this.tour_detail_id = tour_detail_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	public int getNum_participants() {
		return num_participants;
	}
	public void setNum_participants(int num_participants) {
		this.num_participants = num_participants;
	}
	
	@Override
	public String toString() {
		return "Booking [booking_id=" + booking_id + ", tour_detail_id=" + tour_detail_id + ", user_id=" + user_id
				+ ", booking_date=" + booking_date + ", num_participants=" + num_participants + "]";
	}
	
}
