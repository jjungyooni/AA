import java.util.Scanner;

public class VRUI {
	private VRManager vrManager = new VRManager();

	private static Scanner scanner = new Scanner(System.in) ;
	
	public static void main(String[] args) {
		VRUI ui = new VRUI() ;
		
		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: ui.listCustomers() ; break ;
				case 2: ui.listVideos() ; break ;
				case 3: ui.registerCustomer() ; break ;
				case 4: ui.registerVideo() ; break ;
				case 5: ui.rentVideo() ; break ;
				case 6: ui.returnVideo() ; break ;
				case 7: ui.getCustomerReport() ; break; 
				case 8: ui.clearRentals() ; break ;
//				case -1: vrManager.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}
		
	public void clearRentals() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;
		
		Customer foundCustomer = vrManager.findCustomer(customerName);
		if ( foundCustomer == null) {
			System.out.println("No customer found") ;
			return;
		}
		
		System.out.println("Name: " + foundCustomer.getName() +
				"\tRentals: " + foundCustomer.getRentals().size()) ;
		for ( Rental rental: foundCustomer.getRentals() ) {
			System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
			System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
		}

		vrManager.clearRental(foundCustomer);
	}

	public void returnVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;
		
		Customer foundCustomer = vrManager.findCustomer(customerName);
		if ( foundCustomer == null) {
			System.out.println("No customer found") ;
			return;
		}
		
		System.out.println("Enter video title to return: ") ;
		String videoTitle = scanner.next() ;
			
		vrManager.returnVideo(foundCustomer, videoTitle);	
	}

	public void listVideos() {
		System.out.println("List of videos");
		
		for ( Video video: vrManager.getVideos() ) {
			System.out.println("Price code: " + video.getPriceCode() +"\tTitle: " + video.getTitle()) ;
		}
		System.out.println("End of list");
	}

	public void listCustomers() {
		System.out.println("List of customers");
		for ( Customer customer: vrManager.getCustomers() ) {
			System.out.println("Name: " + customer.getName() +
					"\tRentals: " + customer.getRentals().size()) ;
			for ( Rental rental: customer.getRentals() ) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
			}
		}
		System.out.println("End of list");
	}

	public void getCustomerReport() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;
		
		Customer foundCustomer = vrManager.findCustomer(customerName);
		if ( foundCustomer == null) {
			System.out.println("No customer found") ;
			return;
		}

		String result = foundCustomer.getReport() ;
		System.out.println(result);
	}

	public void rentVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;
		
		Customer foundCustomer = vrManager.findCustomer(customerName);
		if ( foundCustomer == null) {
			System.out.println("No customer found") ;
			return;
		}
		
		System.out.println("Enter video title to rent: ") ;
		String videoTitle = scanner.next() ;

		vrManager.rentVideo(foundCustomer, videoTitle);
	}

	private void registerVideo() {
		System.out.println("Enter video title to register: ") ;
		String title = scanner.next() ;
			
		System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
		int videoType = scanner.nextInt();
			
		System.out.println("Enter price code( 1 for Regular, 2 for New Release ):") ;
		int priceCode = scanner.nextInt();
		
		vrManager.registerVideo(title, videoType, priceCode);
	}

	private void registerCustomer() {
		System.out.println("Enter customer name: ") ;
		String name = scanner.next();			
		vrManager.registerCustomer(name);
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");
		
		int command = scanner.nextInt() ;
		
		return command ;
	}
}
