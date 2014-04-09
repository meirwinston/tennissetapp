package com.tennissetapp.json;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import com.tennissetapp.persistence.entities.Mate;

public class MateSerializer extends JsonSerializer<Mate>{

	@Override
	public void serialize(Mate mate, JsonGenerator gen, SerializerProvider provider) 
	throws IOException,JsonProcessingException {
		gen.writeStartObject();
		if(mate.getCreatedOn() != null){
			gen.writeNumberField("createdOn", mate.getCreatedOn().getTime());
		}
		if(mate.getMateAccountId() != null){
			gen.writeNumberField("mateAccountId", mate.getMateAccountId());
		}
		if(mate.getUserAccountId() != null){
			gen.writeNumberField("userAccountId", mate.getUserAccountId());
		}
		gen.writeEndObject();
	}
	
	@Override
	public Class<Mate> handledType() {
		return Mate.class;
	}

}
