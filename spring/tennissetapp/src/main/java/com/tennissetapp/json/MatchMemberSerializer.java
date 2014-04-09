package com.tennissetapp.json;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import com.tennissetapp.persistence.entities.MatchMember;

public class MatchMemberSerializer extends JsonSerializer<MatchMember>{
	@Override
	public void serialize(MatchMember member, JsonGenerator generator, SerializerProvider provider) 
	throws IOException,JsonProcessingException {
		generator.writeStartObject();
		generator.writeNumberField("matchId",member.getMatchId());
		generator.writeNumberField("userAccountId",member.getUserAccountId());
		generator.writeNumberField("joinedOn",member.getJoinedOn().getTime());
		
		generator.writeStringField("firstName",member.getUserAccount().getFirstName());
		generator.writeStringField("lastName",member.getUserAccount().getLastName());
		generator.writeEndObject();
	}
	
	@Override
	public Class<MatchMember> handledType() {
		return MatchMember.class;
	}
}
