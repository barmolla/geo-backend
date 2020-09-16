package ar.com.fravega.challenge.service;

import ar.com.fravega.challenge.dto.PointDTO;

public interface DistanceCalculatorStrategy {

	/**
	 * Returns distance expressed in kilometers.
	 * 
	 * @param from: origin point
	 * @param to: destination point
	 * @return the distance
	 */
	Double getDistance(PointDTO from, PointDTO to);
}
