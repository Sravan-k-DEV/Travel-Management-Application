package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainFile {

	private static List<User> users = new ArrayList<User>();

	public static List<User> getUsers() {
		return users;
	}

	public static void setUsers(List<User> users) {
		MainFile.users = users;
	}

	private static Map<String, Integer> userInvalidLoginAttempt = new HashMap<>();
	private static List<Route> routes = new ArrayList<Route>();
	private static List<Order> orders = new ArrayList<Order>();
	private static List<Vehicles> vehicles = new ArrayList<>();

	public static void main(String[] args) {
		Vehicles vehicle = new Vehicles("vn01", "bus", 40, "sn01", null);
		routes.add(new Route(1, "hyderabad", "chennai", LocalDate.now(), 500.2, 40));
		routes.add(new Route(2, "goa", "hyderabad", LocalDate.now(), 600.4, 40));
		routes.add(new Route(3, "hyderabad", "goa", LocalDate.parse("2023-12-31", DateTimeFormatter.ISO_LOCAL_DATE),
				1000.00, 40));
		routes.add(new Route(4, "hyderabad", "goa", LocalDate.parse("2023-12-30", DateTimeFormatter.ISO_LOCAL_DATE),
				1000.50, 40));
		vehicle.routes = routes;

		if (displayCompanyLogo()) {
			showMenuOptions();
		} else {
			System.out.println("404 ERROR!!!");
		}
	}

	private static boolean displayCompanyLogo() {
		String path = "C:\\Users\\SRAVAN KUMAR YADAV\\eclipse-workspace\\ABC Travels(Project-2)\\src\\com\\company_logo.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			return true; // returns true, since its reads the text
		} catch (IOException e) {
			System.out.println(e.fillInStackTrace());
		}
		return false; // returns false, since it failed to read the text

	}

	private static void showMenuOptions() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n Menu options: ");
			System.out.println("1. New User Registration");
			System.out.println("2. Login");
			System.out.println("3. Plan Journey");
			System.out.println("4. Re-Schedule Journey");
			System.out.println("5. Exit");
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				registerNewAdmin();
				break;
			case 2:
				login();
				break;
			case 3:
				planJourney();
				break;
			case 4:
				reScheduleJourney();
				break;
			case 5:
				System.out.println("exiting");
			default:
				System.out.println("vist again!!!");

			}
		} while (choice != 5);
	}

	private static void reScheduleJourney() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n Re-Schedule Journey: ");
		System.out.println("Enter OrderId: ");
		int orderIdToChangeDate = scanner.nextInt();

		System.out.println("Enter the date you want to travel: ");
		String changedJourneyDate = scanner.nextLine();

		LocalDate changedJourneyDateToLoalDate = LocalDate.parse(changedJourneyDate, DateTimeFormatter.ISO_LOCAL_DATE);
		for (Order order : orders) {
			if (order.getOrderId() == orderIdToChangeDate) {
				List<Route> matchingPlanningJourneyRoutes = getRoutes(order.getRoute().getSource(),
						order.getRoute().getDestination(), changedJourneyDateToLoalDate);

				Scanner scanner1 = new Scanner(System.in);
				System.out.println("enter routeId or routeNumber: ");
				int routeNumber = scanner1.nextInt();

				System.out.println(getOrder(scanner1, changedJourneyDateToLoalDate, 2, matchingPlanningJourneyRoutes,
						routeNumber));

			}

		}
	}

	private static Order planJourney() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n plan journey ");
		System.out.println("enter source: ");
		String source = scanner.nextLine();
		System.out.println("enter destination: ");
		String destinatin = scanner.nextLine();
		System.out.println("enter journey date(YYYY-MM-DD): ");
		String journeyDateString = scanner.nextLine();

		LocalDate plannedJouneyDate = null;
		plannedJouneyDate = LocalDate.parse(journeyDateString, DateTimeFormatter.ISO_LOCAL_DATE);

		System.out.println("enter number of passengers: ");
		int numberOfPassengers = scanner.nextInt();

		System.out.println("details receveid... please wait!!!!");
		boolean isRouteAvailablePlannedjourneyDate = false;

		List<Route> matchingPlanningJourneyRoutes = getRoutes(source, destinatin, plannedJouneyDate);

		System.out.println("Available matching routes: \n" + matchingPlanningJourneyRoutes);
		System.out.println("Enter the route ID to confirm your trip: ");
		int routeid = scanner.nextInt();

		Order order = getOrder(scanner, plannedJouneyDate, numberOfPassengers, matchingPlanningJourneyRoutes, routeid);
		if (order != null) {
			orders.add(order);
			return order;
		}
		return null;

	}

	private static Order getOrder(Scanner scanner, LocalDate plannedJouneyDate, int numberOfPassengers,
			List<Route> matchingPlanningJourneyRoutes, int routeid) {
		for (Route route : matchingPlanningJourneyRoutes) {
			if (route.getRouteId() == routeid) {
				Order order = new Order();
				double bookingCost = (route.getTicketPrice()) * numberOfPassengers;
				if ((plannedJouneyDate.getDayOfWeek().equals(DayOfWeek.SATURDAY))
						|| (plannedJouneyDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
					bookingCost = bookingCost + 200;
					double gst = bookingCost * 10 / 100;
					bookingCost = gst + bookingCost;
				}
				System.out.println("booking cost for this route is " + bookingCost);
				order.setOrderAmount(bookingCost);
				order.setOrderId(1);
				order.setRoute(route);
				order.setRequestedJourneyPlan(null);
				order.setOrderStatus("Created");

				System.out.println("Check your Order :\n" + order);
				System.out.println("Enter the Numnber 1 to confirm your order: ");
				int confirmNumber = scanner.nextInt();
				if (confirmNumber == 1) {
					order.setOrderStatus("Confirmed");
					route.setNoOfSeatsAvailable(route.getNoOfSeatsAvailable() - numberOfPassengers);
				}
				System.out.println("Your Confirmed Order is:\n" + order);
				System.out.println("routes seats are decremented:: " + route);
				return order;
			}
		}
		return null;
	}

	private static List<Route> getRoutes(String source, String destinatin, LocalDate plannedJouneyDate) {

		boolean isRouteAvailablePlannedjourneyDate;
		List<Route> matchingPlanningJourneyRoutes = new ArrayList<Route>();

		for (Route route : routes) {
			if (route.getSource().equals(source)) {
				if (route.getDestination().equals(destinatin)) {
					System.out.println("route is available");
					if (route.getNoOfSeatsAvailable() > 0) {

						if (route.getJourneyDateRoute().equals(plannedJouneyDate)) {
							System.out.println("Day of the Journey: " + plannedJouneyDate.getDayOfWeek());

							System.out.println("journey date is present");
							isRouteAvailablePlannedjourneyDate = true;
							matchingPlanningJourneyRoutes.add(route);

						}

					} else {
						System.out.println("route and seats are not available!!!");
					}
				}
			}
		}
		return matchingPlanningJourneyRoutes;
	}

	private static User login() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n User Login");
		System.out.println("enter username: ");
		String userName = scanner.nextLine();
		System.out.println("enter password: ");
		String password = scanner.nextLine();
		for (User user : users) {
			Integer existingCount = userInvalidLoginAttempt.get(user.getEmail());
			int count = existingCount != null ? existingCount : 0;
			if (count >= 5) {
				System.out.println("acc is locked");
				return null;

			}
			if (user.getEmail().equals(userName)) {
				if (password.equals(user.getPassword())) {
					System.out.println("login successfull!!!");
					return user;
				} else {
					userInvalidLoginAttempt.put(user.getEmail(), ++count);
					System.out.println("\n invalid:: attempts::" + count);
					return null;
				}
			} else {
				userInvalidLoginAttempt.put(user.getEmail(), ++count);
				System.out.println("\n invalid:: attempts::" + count);
				System.out.println("\n Invalid UserName");
				return null;

			}

		}
		System.out.println("User not Found!!");
		return null;

	}

	private static void registerNewAdmin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n New User Registration");

		System.out.print("Enter first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter mobile number: ");
		String mobileNumber = scanner.nextLine();
		System.out.print("Enter gender: ");
		String gender = scanner.nextLine();
		System.out.print("Enter email: ");
		String email = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		User newUser = new User(firstName, lastName, mobileNumber, gender, email, password, 0, "ACTIVE");
//		users.add(newUser);
		try {
			isUserExist(newUser);
		} catch (UserAlreadyExistException ex) {
			System.out.println("User with this email already exist!!!");
			return;
		}

		userInvalidLoginAttempt.put(email, 0); // Initialize invalid login count
		users.add(newUser);

		System.out.println("User registered successfully!");
	}

	private static void isUserExist(User newUser) {
		for (User exisitingUser : users) {
			if (exisitingUser.getEmail().equals(newUser.getEmail())) {
				throw new UserAlreadyExistException();
			}
		}
	}

}
