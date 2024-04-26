package com;

import java.util.ArrayList;
import java.util.List;

public class Vehicles {
private String registrationNumber;
private String vehicleType;
private int noOfSeats;
private String serviceNumber;

List<Route> routes=new ArrayList<Route>();


public Vehicles(String registrationNumber, String vehicleType, int noOfSeats, String serviceNumber,
		List<Route> routes) {
	this.registrationNumber = registrationNumber;
	this.vehicleType = vehicleType;
	this.noOfSeats = noOfSeats;
	this.serviceNumber = serviceNumber;
	this.routes = routes;
}

public String getRegistrationNumber() {
	return registrationNumber;
}

public void setRegistrationNumber(String registrationNumber) {
	this.registrationNumber = registrationNumber;
}

public String getVehicleType() {
	return vehicleType;
}

public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}

public int getNoOfSeats() {
	return noOfSeats;
}

public void setNoOfSeats(int noOfSeats) {
	this.noOfSeats = noOfSeats;
}

public String getServiceNumber() {
	return serviceNumber;
}

public void setServiceNumber(String serviceNumber) {
	this.serviceNumber = serviceNumber;
}

public List<Route> getRoutes() {
	return routes;
}

public void setRoutes(List<Route> routes) {
	this.routes = routes;
}

}
