package kz.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.ManagedBean;

import kz.api.model.Location;

@ManagedBean
public class GeoLocationServiceImpl implements GeoLocationService{
	// Заглушка - не удлось найти API
	List<Location> locations = Stream.of(
			new Location("050000", "ALMT", "UTC+6"),
			new Location("050010", "ALMT", "UTC+6"),
			new Location("101000", "MSK", "UTC+2")
			).collect(Collectors.toList());
	
	@Override
	public Location getTimeZoneByZip(String zip) {
		return locations.stream()
				.filter(o->o.getZipCode().equals(zip))
				.findFirst()
				.orElse(new Location());
	}

}
