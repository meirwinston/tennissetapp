package com.tennissetapp.util;

public class ImageMetaData {
	public Integer width;
	public Integer height;
	public String format;
	public String fileName;
	public String absolutePath;
	public String dirPath;
	public Long size;
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getDirPath() {
		return dirPath;
	}
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "ImageMetaData [width=" + width + ", height=" + height
				+ ", format=" + format + ", fileName=" + fileName
				+ ", absolutePath=" + absolutePath + ", dirPath=" + dirPath
				+ ", size=" + size + "]";
	}
	
	
}
