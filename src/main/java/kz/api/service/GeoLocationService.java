package kz.api.service;

import kz.api.model.Location;

public interface GeoLocationService {
	
	public Location getTimeZoneByZip(String zip);

}
