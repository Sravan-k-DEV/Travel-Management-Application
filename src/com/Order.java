package com;

public class Order {
	private int orderId;
	private Route route;
	private double orderAmount;
	private String orderStatus;
	private Journey requestedJourneyPlan;
	private boolean isPayementDone;
	private int noOfPassengers;
	
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Journey getRequestedJourneyPlan() {
		return requestedJourneyPlan;
	}
	public void setRequestedJourneyPlan(Journey requestedJourneyPlan) {
		this.requestedJourneyPlan = requestedJourneyPlan;
	}
	public boolean isPayementDone() {
		return isPayementDone;
	}
	public void setPayementDone(boolean isPayementDone) {
		this.isPayementDone = isPayementDone;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", route=" + route + ", orderAmount=" + orderAmount + ", orderStatus="
				+ orderStatus + ", requestedJourneyPlan=" + requestedJourneyPlan + ", isPayementDone=" + isPayementDone
				+ ", noOfPassengers=" + noOfPassengers + "]";
	}
	
	
	
}
