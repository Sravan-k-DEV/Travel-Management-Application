package com;

import java.time.LocalDate;

public class Journey {
	private String source;
	private String destination;
	private LocalDate journeyDate;
	private Integer numberOfPassengers;
	
	public Journey(String source, String destination, LocalDate journeyDate, Integer numberOfPassengers) {
		this.source = source;
		this.destination = destination;
		this.journeyDate = journeyDate;
		this.numberOfPassengers = numberOfPassengers;
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
	public LocalDate getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}
	public Integer getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(Integer numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
}