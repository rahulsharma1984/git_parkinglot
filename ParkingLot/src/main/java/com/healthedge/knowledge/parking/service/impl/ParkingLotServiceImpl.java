package com.healthedge.knowledge.parking.service.impl;

import com.healthedge.knowledge.parking.bean.ParkingLot;
import com.healthedge.knowledge.parking.service.IParkingLotService;

public class ParkingLotServiceImpl implements IParkingLotService {

	private ParkingLot parkingLot = new ParkingLot(5);

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	private ParkingLot getParkingLot() {
		return this.parkingLot;
	}

	public int getAvailableSpaces() {
		return getParkingLot().getAvailableSpaces();
	}

	public int getTotalSpaces() {
		return getParkingLot().getTotalNumberOfSpaces();
	}

	public int updateOccupiedSpace(int typeOfEntry) {
		// 0 is for exit and 1 is for entry
		if (typeOfEntry == 0) {
			getParkingLot().setAvailableSpaces(getParkingLot().getAvailableSpaces() + 1);
		} else {
			getParkingLot().setAvailableSpaces(getParkingLot().getAvailableSpaces() - 1);
		}
		return getParkingLot().getAvailableSpaces();
	}

}
