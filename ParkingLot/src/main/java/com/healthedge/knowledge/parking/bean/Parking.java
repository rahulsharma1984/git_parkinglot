package com.healthedge.knowledge.parking.bean;

public class Parking {
	
	public Parking(int typeofParking) {
		this.setParkingType(typeofParking);
	}

	private int parkingType;

	private boolean isEntryClosed;

	public boolean isEntryClosed() {
		return isEntryClosed;
	}

	public void setEntryClosed(boolean isEntryClosed) {
		this.isEntryClosed = isEntryClosed;
	}

	public int getParkingType() {
		return parkingType;
	}

	public void setParkingType(int parkingType) {
		this.parkingType = parkingType;
	}

}
