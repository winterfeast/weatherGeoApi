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

import kz.api.model.WeatherResponse;
import kz.api.service.WeatherService;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(WeatherServlet.class);
	
	private Gson gson = new Gson();
	
	@Inject
	WeatherService weatherService;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html");
		
		long city_id = Long.parseLong(request.getParameter("city_id"));
		
		logger.info("Getting weather with city_code '{}'", city_id);
		
		WeatherResponse weather = weatherService.getTemperatureByCityId(city_id);
		
		
		String jsonStr = this.gson.toJson(weather);
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonStr);
        out.flush();
    }
	

}
