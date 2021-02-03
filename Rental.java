import java.util.Date;

public class Rental {
	private static final int ONE_DAY = 60 * 60 * 24;
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
	public int getPriceCodeofVodeo(Video video) {
		return video.getPriceCode();
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
		daysRented = (int) (diff / (1000 * ONE_DAY)) + 1;
		if ( daysRented <= 2) return limit ;

		return VideoLimit.fromInteger(video.getVideoType()).getValue() ;
	}

	public double getCharge() {
		double eachCharge = 0;
		int daysRented = getRented();

		switch (PriceType.fromInteger(getPriceCodeofVodeo(getVideo()))) {
			case REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
			default:
				break;
		}

		return eachCharge;
	}

	public int getRented() {
		int daysRented = 0;

		if (getStatus() == 1) { // returned Video
			long diff = getReturnDate().getTime() - getRentDate().getTime();
			daysRented = (int) (diff / (1000 * ONE_DAY)) + 1;
		} else { // not yet returned
			long diff = new Date().getTime() - getRentDate().getTime();
			daysRented = (int) (diff / (1000 * ONE_DAY)) + 1;
		}
		return daysRented;
	}

	public int getPoint() {
		int eachPoint = 0;
		eachPoint++;

		if (PriceType.NEW_RELEASE.equals(PriceType.fromInteger(getPriceCodeofVodeo(getVideo()))))
			eachPoint++;

		if (getRented() > getDaysRentedLimit())
			eachPoint -= Math.min(eachPoint, getVideo().getLateReturnPointPenalty());
		return eachPoint;
	}
}
