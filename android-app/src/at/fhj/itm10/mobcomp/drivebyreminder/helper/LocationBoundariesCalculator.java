package at.fhj.itm10.mobcomp.drivebyreminder.helper;

import android.location.Location;
import android.util.Log;

/**
 * Geo location boundaries calculator.
 * 
 * @author Wolfgang Gaar
 * @see http://gis.stackexchange.com/questions/2951/algorithm-for-offsetting-a-latitude-longitude-by-some-amount-of-meters
 */
public class LocationBoundariesCalculator {

	private int distanceInMeters;
	
	private Location location;
	
	private Location minBoundary;
	
	private Location maxBoundary;
	
	
	/**
	 * Latitude degrees for one meter - 40076 / 360 / 60 / 1000000.
	 */
	private static final double DEGREES_PER_METER_LAT = 0.00000185537037037037;
	
	/**
	 * Longitude degrees for one meter - 40076 / 360 / 60 / 100000.
	 */
	private static final double DEGREES_PER_METER_LON = 0.0000185537037037037;

	public LocationBoundariesCalculator(Location baseLocation,
			int distanceInMeters) {
		location = baseLocation;
		this.distanceInMeters = distanceInMeters;
		
		calculate();
	}

	public Location getMinBoundary() {
		return minBoundary;
	}
	
	public Location getMaxBoundary() {
		return maxBoundary;
	}
	
	/**
	 * Calculate geo boundaries.
	 */
	private void calculate() {
		minBoundary = new Location(location);
		maxBoundary = new Location(location);
		
		Log.v(getClass().getSimpleName(), "calculate: distance meters = "
				+ distanceInMeters);
		double distanceLat = DEGREES_PER_METER_LAT * distanceInMeters;
		double distanceLon = DEGREES_PER_METER_LON * distanceInMeters;
		
		minBoundary.setLatitude(minBoundary.getLatitude() - distanceLat);
		minBoundary.setLongitude(minBoundary.getLongitude() - distanceLon);
		
		maxBoundary.setLatitude(maxBoundary.getLatitude() + distanceLat);
		maxBoundary.setLongitude(maxBoundary.getLongitude() + distanceLon);
		
		float[] minResults = new float[3];
		float[] maxResults = new float[3];

		Location.distanceBetween(location.getLatitude(), location.getLongitude(),
				minBoundary.getLatitude(), minBoundary.getLongitude(), minResults);
		Location.distanceBetween(location.getLatitude(), location.getLongitude(),
				maxBoundary.getLatitude(), maxBoundary.getLongitude(), maxResults);

		Log.v(getClass().getSimpleName(), "calculate: min meters = " + minResults[0]);
		Log.v(getClass().getSimpleName(), "calculate: max meters = " + maxResults[0]);
	}
	
}
