import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VRManager {

	public List<Customer> customers = new ArrayList<Customer>();
    public List<Customer> getCustomers() {
		return customers;
	}
    
	public List<Video> videos = new ArrayList<Video>();
	public List<Video> getVideos() {
		return videos;
	}

	public VRManager() {
		super();
	}

	public Customer findCustomer(String customerName) {
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.isName(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}
		return foundCustomer;
	}

	public void clearRental(Customer customer) {
		List<Rental> rentals = new ArrayList<Rental>() ;
		customer.setRentals(rentals);
	}

    public void rentVideo(Customer customer, String videoTitle) {
		Video foundVideo = null ;
		for ( Video video: videos ) {
			if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
				foundVideo = video ;
				break ;
			}
		}

		if ( foundVideo == null ) return ;
		
		Rental rental = new Rental(foundVideo) ;
		foundVideo.setRented(true);
		
		List<Rental> customerRentals = foundCustomer.getRentals() ;
		customerRentals.add(rental);
		foundCustomer.setRentals(customerRentals);	
	}
    
    public void registerVideo(String title, int videoType, int priceCode) {
		Date registeredDate = new Date();
		Video video = new Video(title, videoType, priceCode, registeredDate) ;
		videos.add(video) ;
	}

	public void registerCustomer(String name) {
		Customer customer = new Customer(name) ;
		customers.add(customer) ;
	}

    private void init() {
		Customer james = new Customer("James") ;
		Customer brown = new Customer("Brown") ;
		customers.add(james) ;
		customers.add(brown) ;
		
		Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date()) ;
		Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date()) ;
		videos.add(v1) ;
		videos.add(v2) ;
		
		Rental r1 = new Rental(v1) ;
		Rental r2 = new Rental(v2) ;
		
		james.addRental(r1) ;
		james.addRental(r2) ;
	}
}
