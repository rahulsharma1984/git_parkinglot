package com.healthedge.knowledge.parking.system;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.healthedge.knowledge.parking.bean.Parking;
import com.healthedge.knowledge.parking.processor.ParkingLotProcessor;

public class ParkingHandler {

	public static void main(String[] args) {

		BlockingQueue<Parking> entryQueue = new ArrayBlockingQueue<Parking>(10);

		BlockingQueue<Parking> exitQueue = new ArrayBlockingQueue<Parking>(10);

		ParkingLotProcessor entryPathProcessor = new ParkingLotProcessor();
		entryPathProcessor.setCarQueue(entryQueue);

		ParkingLotProcessor exitPathProcessor = new ParkingLotProcessor();
		exitPathProcessor.setCarQueue(exitQueue);

		ExecutorService entryService = Executors.newFixedThreadPool(2);
		ExecutorService exitService = Executors.newFixedThreadPool(2);
		entryService.execute(entryPathProcessor);
		exitService.execute(exitPathProcessor);

		long startTime = System.currentTimeMillis();
		Parking entryCarClosed = null;

		while (true) {
			try {
				System.out.println("Total Number of Spaces in Parking are:" + entryPathProcessor.displayTotalSpaces()
						+ ": and available spaces are:" + exitPathProcessor.getAvailableSpaces());
				Random r = new Random();
				int lowNumber = 1000;
				int highNumber = 10000;
				int resultNumber = r.nextInt(highNumber - lowNumber) + lowNumber;

				if (resultNumber % 2 == 0) {
					Parking entryCar = new Parking(1);
					entryQueue.put(entryCar);

					Thread.sleep(resultNumber);
				} else {

					resultNumber = r.nextInt(highNumber - lowNumber) + lowNumber;

					int availableSpaces = exitPathProcessor.getAvailableSpaces();

					if (entryCarClosed != null && availableSpaces == entryPathProcessor.displayTotalSpaces()) {
						System.out.println("All the cars have exited and Entry time is closed. Parking is shut down.");
						break;
					}

					if (availableSpaces < entryPathProcessor.displayTotalSpaces()) {
						Parking exitCar = new Parking(0);
						exitQueue.put(exitCar);
						Thread.sleep(resultNumber);
					}
				}

				long currentTime = System.currentTimeMillis();
				if (entryCarClosed == null && currentTime - startTime > 120 * 1000) {
					entryCarClosed = new Parking(1);
					entryCarClosed.setEntryClosed(true);
					entryQueue.put(entryCarClosed);
					entryService.shutdown();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
