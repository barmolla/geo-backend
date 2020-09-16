package ar.com.fravega.challenge.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.fravega.challenge.dto.PointDTO;

/**
 * https://www.movable-type.co.uk/scripts/latlong.html
 */
@Component
@Qualifier("LawOfCosines")
public class LawOfCosinesDistanceCalculatorStrategyImpl implements DistanceCalculatorStrategy {

	@Override
	public Double getDistance(PointDTO from, PointDTO to) {
		Double lat1 = from.getLatitude(),
				lat2 = to.getLatitude(),
				lon1 = from.getLongitude(),
				lon2 = to.getLongitude();
		String unit = "K";

		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0.0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));

			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;

			if (unit == "K") {
				dist = dist * 1.609344;
			} else if (unit == "N") {
				dist = dist * 0.8684;
			}

			return (dist);
		}
	}

}
