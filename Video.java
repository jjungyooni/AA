import java.util.Date;

public class Video {
	private String title ;

	private int priceCode ;
	private int videoType ;
	private Date registeredDate ;
	private boolean rented ;
	
	public Video(String title, int videoType, int priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setVideoType(videoType) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	public int getLateReturnPointPenalty() {
		return VideoPanalty.fromInteger(videoType).getValue();
	}
	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public int getVideoType() {return videoType;}

	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}
}
