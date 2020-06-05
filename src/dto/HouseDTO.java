package dto;

public class HouseDTO {
	private String tLocation;
	private String house;
	private int totalSeat;
	private int width;
	private int height;
	public String gettLocation() {
		return tLocation;
	}
	public void settLocation(String tLocation) {
		this.tLocation = tLocation;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
