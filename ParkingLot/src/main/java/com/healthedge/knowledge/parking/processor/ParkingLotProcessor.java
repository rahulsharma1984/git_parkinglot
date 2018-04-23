package com.healthedge.knowledge.parking.processor;

import java.util.concurrent.BlockingQueue;

import com.healthedge.knowledge.parking.bean.Parking;
import com.healthedge.knowledge.parking.service.IParkingLotService;
import com.healthedge.knowledge.parking.service.factory.ParkingServiceFactory;

public class ParkingLotProcessor extends Thread {

	private IParkingLotService parkingLotService;

	BlockingQueue<Parking> carQueue;


	public void setCarQueue(BlockingQueue<Parking> carQueue) {
		this.carQueue = carQueue;
	}

	public ParkingLotProcessor() {
		parkingLotService = ParkingServiceFactory.getParkingService();

	}

	public int displayTotalSpaces() {
		return parkingLotService.getTotalSpaces();
	}

	public int getAvailableSpaces() {
		return parkingLotService.getAvailableSpaces();
	}

	public int enterParking() {
		return parkingLotService.updateOccupiedSpace(1);
	}

	public int exitParking() {
		return parkingLotService.updateOccupiedSpace(0);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Parking parking = carQueue.take();

				if (parking.getParkingType() == 1) {
					if (parking.isEntryClosed()) {
						System.out.println("Entry is closed for today");
						continue;
					}
					int availableSpot = getAvailableSpaces();
					if (availableSpot > 0) {
						availableSpot = enterParking();
						System.out.println("Entry done and availableSpot are:" + availableSpot);
					} else {
						System.out.println("can not allow to park as availableSpot are:" + availableSpot);
					}
				} else {
					int availableSpot = exitParking();
					System.out.println("Exit done and availableSpot are:" + availableSpot);
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

}
