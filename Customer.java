import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private static final int ONE_DAY = 60 * 60 * 24;
	private static final int ONE_COUPON = 10;
	private static final int TWO_COUPON = 30;
    private String name;

    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);

    }

	/**
	 * 이 아래 분리가 필요해 보입니당
	 */
	//////////////////////////////////////////////////////////////////////
    public double getCharge(Rental each) {
		double eachCharge = 0;
		int daysRented = getRented(each);

		switch (each.getVideo().getPriceCode()) {
			case Video.REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case Video.NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
			default:
				break;
		}

		return eachCharge;
	}

	public int getRented(Rental each) {
		int daysRented = 0;

		if (each.getStatus() == 1) { // returned Video
			long diff = each.getReturnDate().getTime() - each.getRentDate().getTime();
			daysRented = (int) (diff / (1000 * ONE_DAY)) + 1;
		} else { // not yet returned
			long diff = new Date().getTime() - each.getRentDate().getTime();
			daysRented = (int) (diff / (1000 * ONE_DAY)) + 1;
		}
		return daysRented;
	}

	public int getPoint(Rental each) {
		int eachPoint = 0;
		eachPoint++;

		if (each.getVideo().getPriceCode() == Video.NEW_RELEASE)
			eachPoint++;

		if (getRented(each) > each.getDaysRentedLimit())
			eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty());
    	return eachPoint;
	}
	//////////////////////////////////////////////////////////////////////

	public String getReport() {
        StringBuilder result = new StringBuilder("Customer Report for " + getName() + "\n");

        List<Rental> rentals = getRentals();

        double totalCharge = 0;
        int totalPoint = 0;

        for (Rental each : rentals) {
			double eachCharge = getCharge(each);
			int daysRented = getRented(each);
			int eachPoint = getPoint(each);

            result.append("\t").append(each.getVideo().getTitle()).append("\tDays rented: ").append(daysRented).append("\tCharge: ").append(eachCharge).append("\tPoint: ").append(eachPoint).append("\n");

            totalCharge += eachCharge;

            totalPoint += eachPoint;
        }

        result.append("Total charge: ").append(totalCharge).append("\tTotal Point:").append(totalPoint).append("\n");


        if (totalPoint >= ONE_COUPON) {
            System.out.println("Congrat! You earned one free coupon");
        }
        if (totalPoint >= TWO_COUPON) {
            System.out.println("Congrat! You earned two free coupon");
        }
        return result.toString();
    }
}
