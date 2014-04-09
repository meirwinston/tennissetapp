package com.tennissetapp.json;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import com.tennissetapp.persistence.entities.TennisCourt;

public class TennisCourtSerializer extends JsonSerializer<TennisCourt>{

	@Override
	public void serialize(TennisCourt court, JsonGenerator gen, SerializerProvider provider) 
	throws IOException,JsonProcessingException {
		gen.writeStartObject();
		if(court.getNumberOfCourts() != null){
			gen.writeNumberField("numberOfCourts", court.getNumberOfCourts());
		}
		if(court.getTennisCenterId() != null){
			gen.writeNumberField("tennisCenterId", court.getTennisCenterId());
		}
		if(court.getTennisCourtId() != null){
			gen.writeNumberField("tennisCourtId", court.getTennisCourtId());
		}
		if(court.getSurface() != null){
			gen.writeStringField("surface", court.getSurface().toString());
		}
		if(court.getPlacement() != null){
			gen.writeStringField("placement", court.getPlacement().toString());
		}
		gen.writeEndObject();
	}
	
	@Override
	public Class<TennisCourt> handledType() {
		return TennisCourt.class;
	}

}
