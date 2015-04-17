package com.gemt.granite.utility;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

@Component
public class JsonTimeSerializer extends JsonSerializer<Object>{
	
	@Override
	public void serialize(Object time, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException{
		float temp = (float)time;		
		int hours = (int)temp;		
		temp = temp - hours;
		temp = temp * 60;
        int mins = (int)(temp);
        temp = temp - mins;
        temp = temp * 60;
        
        int secs = (int)(temp);
        		
		gen.writeString((hours>9?hours:"0"+hours)+":"+(mins>9?mins:"0"+mins)+":"+(secs>9?secs:"0"+secs));		
	}	
}
