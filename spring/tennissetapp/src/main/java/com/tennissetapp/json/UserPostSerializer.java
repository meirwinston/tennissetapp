package com.tennissetapp.json;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tennissetapp.persistence.entities.UserPost;

public class UserPostSerializer extends JsonSerializer<UserPost>{
	Logger logger = Logger.getLogger(getClass());
	@Override
	public void serialize(UserPost userPost, JsonGenerator g, SerializerProvider provider) 
	throws IOException,JsonProcessingException {
		g.writeStartObject();
		try{
			g.writeNumberField("userPostId", userPost.getUserPostId());
			g.writeNumberField("userAccountId", userPost.getUserAccountId());
			g.writeNumberField("toUserAccountId", userPost.getToUserAccountId());
			g.writeStringField("content", userPost.getContent());
			g.writeNumberField("postedOn", userPost.getPostedOn().getTime());
			if(userPost.getPlayerProfile() != null && userPost.getPlayerProfile().getProfileImageFile() != null){
				g.writeStringField("userProfileImage", userPost.getPlayerProfile().getProfileImageFile().getFileName());
			}
			g.writeStringField("status", userPost.getStatus().toString());
			g.writeStringField("userFirstName", userPost.getUserAccount().getFirstName());
			g.writeStringField("userLastName", userPost.getUserAccount().getLastName());
		
		}
		
		catch(Exception exp){
			logger.error(exp.getMessage(), exp);
		}
		finally{
			g.writeEndObject();
		}
		
	}
	
	@Override
	public Class<UserPost> handledType() {
		return UserPost.class;
	}

}
