import java.util.ArrayList;
import java.util.List;

public class Customer {
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


    public String getReport() {
        StringBuilder result = new StringBuilder("Customer Report for " + getName() + "\n");

        List<Rental> rentals = getRentals();

        double totalCharge = 0;
        int totalPoint = 0;

        for (Rental each : rentals) {
            double eachCharge = each.getCharge();
            int daysRented = each.getRented();
            int eachPoint = each.getPoint();

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
