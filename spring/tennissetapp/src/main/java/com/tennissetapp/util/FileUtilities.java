package com.tennissetapp.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.tennissetapp.config.RootConfig;
import com.tennissetapp.persistence.entities.ImageFile;

public class FileUtilities {
	protected static Logger logger = Logger.getLogger(FileUtilities.class);

	public static ImageMetaData writeImageToDisk(FileItem fileItem, String dir) throws Exception{
		String fileName = UUID.randomUUID().toString();
		String dirPath = RootConfig.props.getProperty("tennissetapp.diskresources.imagesPath") + "/" + dir;
		String fileFullPath = RootConfig.props.getProperty("tennissetapp.rootDir") + "/" + dirPath;
		
		BufferedImage bufferedImage = FileUtilities.toImage(fileItem);
		String imageFormat = FileUtilities.getImageFormat(fileItem.getInputStream());
		File file = new File(fileFullPath + "/" + fileName + "." + imageFormat);
		createNewFile(file);
		fileItem.write(file);
		
		//if the image is larger than 50kb create a thumbnail
		if(file.length() > 51200) { //50KB
			File thumbFile = new File(fileFullPath + "/THUMBNAILS/" + fileName + "." + imageFormat);
			Thumbnails.of(file).size(200, 200).keepAspectRatio(true).toFile(thumbFile);
		}
		else{ //otherwise, copy the same image in the THUMBNAILS DIRECTORY
			File thumbFile = new File(fileFullPath + "/THUMBNAILS/" + fileName + "." + imageFormat);
			FileOutputStream out = new FileOutputStream(thumbFile);
			FileInputStream in = new FileInputStream(file);
			org.apache.commons.io.IOUtils.copy(in, out);
			in.close();
		}
		
		
		ImageMetaData m = new ImageMetaData();
		m.width = bufferedImage.getWidth();
		m.height = bufferedImage.getHeight();
		m.format = imageFormat;
		m.fileName = file.getName();
		m.absolutePath = file.getAbsolutePath();
		m.dirPath = dirPath;
		m.size = fileItem.getSize();
		
		return m;
		
	}
	
	public static boolean delete(ImageFile imageFile){
		String fileFullPath = RootConfig.props.getProperty("tennissetapp.rootDir") + "/" + 
				imageFile.getDirPath() + "/" +
				imageFile.getFileName();
		logger.debug("delete.fileFullPath is " + fileFullPath);
		File file = new File(fileFullPath);
		if(file.exists()){
			return file.delete();
		}
		return false;
	}
	
	public static ImageMetaData storeCourtMapFromGoogle(Double lat, Double lng){
		try {
			int width = 512, height = 512;
			String url = "http://maps.googleapis.com/maps/api/staticmap?center=" + 
					lat + "," + lng + "&zoom=13&size=" + width + "x" + height + 
					"&maptype=roadmap&markers=color:red%7Ccolor:red%7Clabel:C%7C" + lat + "," + lng + "&sensor=false&format=jpg";
			//-23.6817851, -46.7098259
			
			String dirPath = RootConfig.props.getProperty("tennissetapp.diskresources.imagesPath") + "/" + ImageFile.SystemFolder.TENNIS_COURTS;
			String fileFullPath = RootConfig.props.getProperty("tennissetapp.rootDir") + "/" + dirPath;
			
			File file = new File(fileFullPath + "/" + UUID.randomUUID() + "." + ImageFile.Format.JPG);
			FileOutputStream out = new FileOutputStream(file);
			logger.debug("FETCHING IMAGE FROM GOOGLE URL: " + url);
			org.apache.commons.io.IOUtils.copy(new URL(url).openStream(),out);
			
			out.close();
			
			ImageMetaData m = new ImageMetaData();
			m.width = width;
			m.height = height;
			m.format = ImageFile.Format.JPG.toString();
			m.fileName = file.getName();
			m.absolutePath = file.getAbsolutePath();
			m.dirPath = dirPath;
			m.size = file.length();
			
			return m;
		} 
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
			throw new RuntimeException("Problem fetching map image");
		}
	}
	
	public static ImageMetaData writeImageToDisk(byte[] imageInByte, String dir) throws Exception{
		String fileName = UUID.randomUUID().toString();
		String dirPath = RootConfig.props.getProperty("tennissetapp.diskresources.imagesPath") + "/" + dir;
		String fileFullPath = RootConfig.props.getProperty("tennissetapp.rootDir") + "/" + dirPath;
		
		InputStream in = new ByteArrayInputStream(imageInByte);
		
		String imageFormat = FileUtilities.getImageFormat(in);
		System.out.println("FileUtilities. " + imageFormat);
		
		in = new ByteArrayInputStream(imageInByte);
		BufferedImage bufferedImage = ImageIO.read(in);
		
		File file = new File(fileFullPath + "/" + fileName + "." + imageFormat);
		createNewFile(file);
		
//		ImageIO.write(bufferedImage, imageFormat, file);
		FileOutputStream out = new FileOutputStream(file);
		out.write(imageInByte);
		out.close();
		
		ImageMetaData m = new ImageMetaData();
		m.width = bufferedImage.getWidth();
		m.height = bufferedImage.getHeight();
		m.format = imageFormat;
		m.fileName = file.getName();
		m.absolutePath = file.getAbsolutePath();
		m.dirPath = dirPath;
		m.size = Long.valueOf(imageInByte.length);
		
		return m;
	}
	
	public static BufferedImage toImage(FileItem fileItem) throws IOException{
		BufferedImage image = ImageIO.read(fileItem.getInputStream());
		return image;
	}
	
	
	
	public static String getImageFormat(Object in) throws IOException{ //File, input stream
		// Create an image input stream on the image
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		
		// Find all image readers that recognize the image format
        Iterator<?> iter = ImageIO.getImageReaders(iis);
        if (!iter.hasNext()) {
            // No readers found
            return null;
        }
        
     // Use the first reader
        ImageReader reader = (ImageReader)iter.next();
        
     // Close stream
        iis.close();
        return reader.getFormatName();

	}
	
	public static String getImageFormat(String path) throws IOException{
		return getImageFormat(new File(path));
	}

	
	public static boolean createNewFile(File file) throws IOException{
		if(!file.exists()){
			File parent = file.getParentFile();
			if(!parent.exists() && !parent.mkdirs()){
			    throw new IllegalStateException("Couldn't create dir: " + parent);
			}
			return file.createNewFile();
		}
		return false;
	}
	
	/*
	 * TODO large image uploads will throw out of memory exception
	 */
	public static void streamImage(String path,OutputStream out,int width) throws IOException{
		File file = new File(path);
//		System.out.println("--->FileUtilities.thumbnail before save: " + path);
		if(file.exists()){
			Thumbnails.of(new File[]{file}).size(width, width).toOutputStream(out);
//			System.out.println("--->FileUtilities.thumbnail after save: " + path);
		}
	}
}
