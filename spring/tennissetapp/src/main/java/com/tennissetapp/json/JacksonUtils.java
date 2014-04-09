package com.tennissetapp.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.TypeReference;

public class JacksonUtils {
	protected static ObjectMapper objectMapper = new ObjectMapper();
	protected static MappingJsonFactory jsonFactory = new MappingJsonFactory();
	static TypeReference<Map<String, Object>> objectMapType = new TypeReference<Map<String, Object>>(){};
	
	public static String serialize(Object o) throws IOException {
		StringWriter sw = new StringWriter(); // serialize
		serialize(o, sw);
		sw.close();
		return sw.toString();
	}
	
	public static String serialize(Object o, JsonSerializer<?> serializer) throws IOException {
		if(o == null) return null;
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("SimpleModule",new Version(1,0,0,null));
		module.addSerializer(serializer);
		mapper.registerModule(module);
		
		StringWriter writer = new StringWriter();
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(writer);
		mapper.writeValue(jsonGenerator, o);
		writer.close();
		
		return writer.toString();
	}
	
	public static void serialize(Object o, Writer writer) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(writer);
		mapper.writeValue(jsonGenerator, o);
	}
	
	public static void serialize(Object o, JsonSerializer<?> serializer, Writer writer) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("SimpleModule",new Version(1,0,0,null));
		module.addSerializer(serializer);
		mapper.registerModule(module);
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(writer);
		mapper.writeValue(jsonGenerator, o);
	}
	
	public static Map<String,Object> objectToMap(Object o) 
	throws JsonParseException, JsonMappingException, IOException{
		Map<String,Object> map = objectMapper.convertValue(o, objectMapType);
		return map;
	}
	
	public static Map<String,Object> deserializeObjectMap(String value) 
	throws JsonParseException, JsonMappingException, IOException{
		Map<String,Object> map = objectMapper.readValue(value, objectMapType);
		return map;
	}
	
	public static Map<String,String> deserializeStringMap(String value) 
	throws JsonParseException, JsonMappingException, IOException{
		if(value == null) return null;
		return objectMapper.readValue(value, new TypeReference<Map<String, String>>(){});
	}
	
	public static JsonNode toNode(String value) 
	throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readTree(value);
	}
	
	public static <T> T deserializeAs(String value, Class<T> as) throws JsonParseException, JsonMappingException, IOException{
		if(value == null) return null;
		return objectMapper.readValue(value, as);
	}
}
