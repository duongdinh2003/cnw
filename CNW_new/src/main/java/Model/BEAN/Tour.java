package Model.BEAN;

public class Tour {

	private int detail_id;
	private String location;
	private String description;
	private String number_day;
	private String image;
	
	public int getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNumber_day() {
		return number_day;
	}
	public void setNumber_day(String number_day) {
		this.number_day = number_day;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Tour [detail_id=" + detail_id + ", location=" + location + ", description=" + description
				+ ", number_day=" + number_day + ", image=" + image + "]";
	}
	
}
