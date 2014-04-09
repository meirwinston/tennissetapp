package com.tennissetapp.util;

import org.springframework.beans.factory.annotation.Value;

public class EnvironmentProperties {
	@Value(value="${tennissetapp.globalUrl}")
	private String globalUrl;
	
	@Value(value="${tennissetapp.diskresources.imagesUrl}")
	private String imagesUrl;
	
	@Value(value="${tennissetapp.diskresources.imagesPath}")
	private String imagesPath;
	
	@Value(value="${tennissetapp.rootDir}")
	private String rootDir;
	
	private static EnvironmentProperties instance;
	
	public EnvironmentProperties(){
		if(instance == null){
			instance = this;
		}
	}
	
	public static EnvironmentProperties getInstance(){
		return instance;
	}

	public String getGlobalUrl() {
		return globalUrl;
	}

	public void setGlobalUrl(String globalUrl) {
		this.globalUrl = globalUrl;
	}

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}

	public String getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}
	

}
