package com.tennissetapp.forms;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import com.tennissetapp.args.Arguments;

public abstract class AbstractForm {
	protected Logger logger = Logger.getLogger(getClass());
	protected ClassToInstanceMap<Arguments> arguments = MutableClassToInstanceMap.create();
	
	public Map<String,Object> toMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field f : fields){
			try {
				map.put(f.getName(), f.get(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch(IllegalAccessException e){
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public <T extends Arguments> T getArguments(Class<T> type) {
		return arguments.getInstance(type);
	}

	public void setArguments(Arguments args) {
		this.arguments.put(args.getClass(), args);
	}

	@Override
	public String toString(){
		return toMap().toString();
	}
}
