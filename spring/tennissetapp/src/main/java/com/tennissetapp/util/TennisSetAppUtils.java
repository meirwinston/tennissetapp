package com.tennissetapp.util;

import java.io.File;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.tennissetapp.auth.AppUserDetails;
import com.tennissetapp.persistence.entities.ImageFile;
import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.TennisTeacherProfile;

public class TennisSetAppUtils {
	protected static Logger logger = Logger.getLogger(TennisSetAppUtils.class);
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	public static String getClientIP(HttpServletRequest request){
//		String ip = request.getHeader("X-FORWARDED-FOR"); //returns ip address of client system
//		if(ip == null){
//			ip = request.getHeader("VIA"); //returns gateway;
//		}
//		if(ip == null){
//			ip = request.getRemoteAddr();
//		}
//		return ip;
		
		String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("VIA"); //returns gateway;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip; 
	}
	
	
//	public static String getClientIP(NativeWebRequest request){
//		String ip = request.getHeader("X-FORWARDED-FOR"); //returns ip address of client system
//		if(ip == null){
//			ip = request.getHeader("VIA"); //returns gateway;
//		}
//		return ip;
//	}
	
	
	
//	public static String userName(HttpServletRequest request) throws UnauthorizedException{
//		Principal principal = request.getUserPrincipal();
////		request.getSession().setAttribute("uri", request.getRequestURI());
//		if(principal != null){
//			return principal.getName();
//		}
//		else{
//			throw new UnauthorizedException("User is not logged in");
//		}
//	}
	
//	public static UserAccount userAccount(HttpServletRequest request) throws UnauthorizedException{
//		UserAccount userAccount = (UserAccount)request.getSession().getAttribute(UserAccount.class.getSimpleName());
//		if(userAccount != null){
//			return userAccount;
//		}
//		else{
//			throw new UnauthorizedException("You are not logged in",request.getRequestURI());
//		}
//	}
	
//	public static UserDetails userDetails(HttpServletRequest request) throws UnauthorizedException{
//		UserDetails userDetails = null;
//		if(request.getUserPrincipal() != null){
//			userDetails = (UserDetails)((Authentication)request.getUserPrincipal()).getPrincipal();
//		}
//		else{
//			throw new UnauthorizedException("You are not logged in",request.getRequestURI());
//		}
//		return userDetails;
//	}
	
	public boolean isLoggedIn(HttpServletRequest request){
		return request.getUserPrincipal() != null;
//		return request.getSession().getAttribute(UserAccount.class.getSimpleName()) != null;
	}
	
	public static String extractUrl(HttpServletRequest request){
		StringBuffer buf = request.getRequestURL();
		@SuppressWarnings("unchecked")
		Set<Entry<String,String[]>> paramSet = request.getParameterMap().entrySet();
		if(paramSet != null && paramSet.size() > 0){
			buf.append('?');
			for(Entry<String,String[]> e : paramSet){
				buf.append(e.getKey());
				if(e.getValue().length > 0){
					buf.append('=');				
					buf.append(e.getValue()[0]);
				}
				buf.append('&');
			}
		}
		return buf.toString();
	}
	
	public static String getTmpDir(){
		return System.getProperty("java.io.tmpdir");
	}
	
	public static String generateRandomPassword(){
		return UUID.randomUUID().toString().substring(0, 8);
	}
	
	
//	public static String extractProfileImageUrl(UserAccount userAccount){
//		String url = null;
//		if(userAccount.getProfileImageFile() != null){
//			url = imageFileUrl(userAccount.getProfileImageFile().getFileName());
//		}
//		return url;
//	}
	
	public static String extractProfileImageUrl(TennisPlayerProfile profile){
		String url = null;
		if(profile != null && profile.getProfileImageFile() != null){
//			ImageFile imageFile = profile.getProfileImageFile();
			
			url = profileImageFileUrl(profile.getProfileImageFile().getFileName());
		}
		return url;
	}
	
	public static String extractProfileImageUrl(TennisTeacherProfile profile){
		String url = null;
		if(profile.getProfileImageFile() != null){
			url = profileImageFileUrl(profile.getProfileImageFile().getFileName());
		}
		return url;
	}
	
//	public static String extractUrl(HttpServletRequest request){
//		StringBuffer buf = request.getRequestURL();
//		@SuppressWarnings("unchecked")
//		Set<Entry<String,String[]>> paramSet = request.getParameterMap().entrySet();
//		if(paramSet != null && paramSet.size() > 0){
//			buf.append('?');
//			for(Entry<String,String[]> e : paramSet){
//				buf.append(e.getKey());
//				if(e.getValue().length > 0){
//					buf.append('=');				
//					buf.append(e.getValue()[0]);
//				}
//				buf.append('&');
//			}
//		}
//		return buf.toString();
//	}
	
//	String dirPath = RootConfig.props.getProperty("tennissetapp.diskresources.imagesPath") + "/" + dir;
//	String fileFullPath = RootConfig.props.getProperty("tennissetapp.rootDir") + "/" + dirPath;
	
	//returns thumbnail if exists otherwise the whole image
	public static String profileImageFileUrl(String imageFileName){
		String url = null;
		if(imageFileName != null){
			EnvironmentProperties env = EnvironmentProperties.getInstance();
			String thumbPath = env.getRootDir() + "/" + env.getImagesPath() + "/" + ImageFile.SystemFolder.PROFILE_PHOTOS + "/" + ImageFile.SystemFolder.THUMBNAILS + "/" + imageFileName;
			//diskresources/images/PROFILE_PHOTOS/THUMBNAILS/8ea7e216-c9c9-4125-a4cb-b3b73d5d2c14.JPEG
			boolean thumnailExists = new File(thumbPath).exists();
			
			url = env.getImagesUrl() +
			"/" +
			ImageFile.SystemFolder.PROFILE_PHOTOS + 
			(thumnailExists ? ("/" + ImageFile.SystemFolder.THUMBNAILS + "/") : "/") +
			imageFileName;
			
			logger.debug("PROFILE IMAGE URL is " + url + ", the thumbnail is: " + thumbPath + ", exists: " + thumnailExists);
		}
		
		return url;
	}
	
	public static String courtImageFileUrl(String imageFileName){
		String url = null;
		if(imageFileName != null){
			url = EnvironmentProperties.getInstance().getImagesUrl() +
			"/" +
			ImageFile.SystemFolder.TENNIS_COURTS + 
			"/" + 
			imageFileName;
		}
		return url;
	}
	
	public static Date toDateTime(String datetime){
		try {
			return dateFormat.parse(datetime);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	public static AppUserDetails cast(Principal principal){
		AppUserDetails userDetails = (AppUserDetails)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
		return userDetails;
	}
	
//	public static AppUserDetails cast(Principal principal){
//		AppUserDetails userDetails = (AppUserDetails)((UsernamePasswordAuthenticationToken)principal).getDetails();
//		return userDetails;
//	}
	
	
//	public static String mysqlDateFormat(String d){
//		String[] arr = StringUtils.split(d,'/');
//		return arr[2] + "-" + arr[0] + "-" + arr[1];
//	}
}
