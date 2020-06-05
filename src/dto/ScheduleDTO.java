package dto;

public class ScheduleDTO {
	private String tLocation;
	private String house;
	private String startTime;
	private String endTime;
	private String mTitle;
	private int totalSeat;
	
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	
}
