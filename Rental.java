import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( RentStatus.RETURNED.equals(status)) {
			this.status = RentStatus.RETURNED.getValue();
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		long diff = 0;
		int daysRented ;
		if(RentStatus.RETURNED.equals(getStatus())) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();

		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();

		}
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		if ( daysRented <= 2) return limit ;

		return VideoLimit.fromInteger(video.getVideoType()).getValue() ;
	}
}
