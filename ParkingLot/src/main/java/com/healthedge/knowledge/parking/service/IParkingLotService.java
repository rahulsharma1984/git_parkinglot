package com.healthedge.knowledge.parking.service;

import com.healthedge.knowledge.parking.bean.ParkingLot;

public interface IParkingLotService {

	public int getAvailableSpaces();

	public int updateOccupiedSpace(int typeOfEntry);
	
	public void setParkingLot(ParkingLot parkingLot);
	
	public int getTotalSpaces();

}
