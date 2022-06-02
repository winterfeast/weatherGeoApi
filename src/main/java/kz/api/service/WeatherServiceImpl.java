package kz.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import kz.api.config.Property;
import kz.api.model.WeatherResponse;
import kz.api.util.JsonUtils;

@ManagedBean
public class WeatherServiceImpl implements WeatherService{

	@Inject
	@Property("weather.apiURL")
	private String apiURL;
	
	@Override
	public WeatherResponse getTemperatureByCityId(Long cityId) {
		StringBuilder result = new StringBuilder();
		WeatherResponse response = new WeatherResponse();
		try {
			URL url = new URL(apiURL + "&id=" + cityId);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = rd.readLine()) != null) 
				result.append(line);
			
			rd.close();
			
			Map<String, Object> data = JsonUtils.jsonToMap(result.toString());
			Map<String, Object> weather = JsonUtils.jsonToMap(data.get("main").toString());
			
			if(weather != null && !weather.isEmpty()) {
				response.setTemp((double) weather.getOrDefault("temp", 0.0));
				response.setFeelsLike((double) weather.getOrDefault("feels_like", 0.0));
				response.setTempMax((double) weather.getOrDefault("temp_max",0.0));
				response.setTempMin((double) weather.getOrDefault("temp_min",0.0));
				response.setPressure((double) weather.getOrDefault("pressure",0.0));
				response.setHumidity((double) weather.getOrDefault("humidity",0.0));
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}

}
