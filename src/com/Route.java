package com;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Route {
	private int routeId;
	private String source;
	private String destination;
	private LocalDate journeyDateRoute;
	private double ticketPrice;
	private int noOfSeatsAvailable;
	private List<Vehicles> vehiclesMappedToThisRoute=new ArrayList<>(); 
	
	public int getRouteId() {
		return routeId;
	}



	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}



	public Route(int routeId,String source, String destination, LocalDate journeyDateRoute, double ticketPrice, int noOfSeatsAvailable) {
		this.routeId=routeId;
		this.source = source;
		this.destination = destination;
		this.journeyDateRoute = journeyDateRoute;
		this.ticketPrice = ticketPrice;
		this.noOfSeatsAvailable = noOfSeatsAvailable;
	}
	
	
	
	@Override
	public String toString() {
		return "Route [routeId="+ routeId + ", source=" + source + ", destination=" + destination + ", journeyDateRoute=" + journeyDateRoute
				+ ", ticketPrice=" + ticketPrice + ", noOfSeatsAvailable=" + noOfSeatsAvailable + "]";
	}



	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDate getJourneyDateRoute() {
		return journeyDateRoute;
	}
	public void setJourneyDateRoute(LocalDate journeyDateRoute) {
		this.journeyDateRoute = journeyDateRoute;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public int getNoOfSeatsAvailable() {
		return noOfSeatsAvailable;
	}
	public void setNoOfSeatsAvailable(int noOfSeatsAvailable) {
		this.noOfSeatsAvailable = noOfSeatsAvailable;
	}
}
