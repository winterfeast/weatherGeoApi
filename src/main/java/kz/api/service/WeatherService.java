package kz.api.service;

import kz.api.model.WeatherResponse;

public interface WeatherService {
	
	public WeatherResponse getTemperatureByCityId(Long cityId);

}
