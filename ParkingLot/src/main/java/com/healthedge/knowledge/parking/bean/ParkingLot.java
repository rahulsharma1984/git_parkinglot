package com.healthedge.knowledge.parking.bean;

public class ParkingLot {

	private int totalNumberOfSpaces;

	private int availableSpaces;

	public ParkingLot() {

	}

	public ParkingLot(int totalNumberOfSpaces) {
		this.setTotalNumberOfSpaces(totalNumberOfSpaces);
		this.setAvailableSpaces(totalNumberOfSpaces);
	}

	public int getTotalNumberOfSpaces() {
		return totalNumberOfSpaces;
	}

	public void setTotalNumberOfSpaces(int totalNumberOfSpaces) {
		this.totalNumberOfSpaces = totalNumberOfSpaces;
	}

	public int getAvailableSpaces() {
		return availableSpaces;
	}

	public void setAvailableSpaces(int availableSpaces) {
		this.availableSpaces = availableSpaces;
	}

}
