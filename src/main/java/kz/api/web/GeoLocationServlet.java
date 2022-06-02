package kz.api.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import kz.api.model.Location;
import kz.api.service.GeoLocationService;

@WebServlet("/geolocation")
public class GeoLocationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(GeoLocationServlet.class);
	private Gson gson = new Gson();
	
	@Inject
	GeoLocationService geoLocationService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		
		Location zipcode = this.gson.fromJson(request.getReader(), Location.class);  
		
		logger.info("Getting time_zone with zipCode '{}'", zipcode.getCode());
		
		Location result = geoLocationService.getTimeZoneByZip(zipcode.getZipCode());
		
        String answer = this.gson.toJson(result);        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(answer);
        out.flush();
    }
}
