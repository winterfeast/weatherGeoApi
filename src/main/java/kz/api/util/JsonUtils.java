package kz.api.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
	
	public static Map<String, Object> jsonToMap(String str) {
		
		Map<String, Object> map = new Gson().fromJson(
				str, new TypeToken<Map<String, Object>>(){}.getType());
		return map;
		
	}

}
