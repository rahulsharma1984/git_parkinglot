package com.healthedge.knowledge.parking.service.factory;

import com.healthedge.knowledge.parking.service.IParkingLotService;
import com.healthedge.knowledge.parking.service.impl.ParkingLotServiceImpl;

public class ParkingServiceFactory implements Cloneable {

	private static IParkingLotService parkingLotService;

	private ParkingServiceFactory() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public synchronized static IParkingLotService getParkingService() {
		synchronized (ParkingServiceFactory.class) {
			if (parkingLotService == null) {
				parkingLotService = new ParkingLotServiceImpl();
			}
		}
		
		return parkingLotService;
	}

}
