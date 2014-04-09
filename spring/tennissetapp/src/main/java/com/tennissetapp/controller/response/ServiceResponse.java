package com.tennissetapp.controller.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ServiceResponse extends HashMap<String,Object>{
	private static final long serialVersionUID = 1L;
//	public enum Status{
//		SUCCESS,
//		FAIL
//	}
	
	public ServiceResponse(){}
	
	public ServiceResponse(BindingResult result){
		if(result.getFieldErrorCount() > 0){
			Map<String,Object> errorMap = new HashMap<String,Object>();
			for(FieldError err : result.getFieldErrors()){
				errorMap.put(err.getField(), err.getDefaultMessage());
			}
			this.put("errors", errorMap);
		}
		
	}
	
	public void putError(String field,String message){
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String,Object>)this.get("errors");
		if(map == null){
			map = new HashMap<String,Object>();
			this.put("errors", map);
		}
		map.put(field, message);
		
	}
	
//	public void setStatus(Status status){
//		this.put("status", status);
//	}
	
//	public void setForwardUrl(String url){
//		this.put("forwardUrl", url);
//	}
	
}
