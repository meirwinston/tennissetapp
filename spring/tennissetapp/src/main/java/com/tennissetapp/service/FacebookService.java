package com.tennissetapp.service;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.Location;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;

import com.tennissetapp.Constants;
import com.tennissetapp.args.SignupArgs;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.forms.CreateProfileForm;
import com.tennissetapp.forms.SignupForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.ImageFile;
import com.tennissetapp.persistence.entities.ImageFile.Format;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.util.FileUtilities;
import com.tennissetapp.util.ImageMetaData;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.UserAccountServiceValidator;

public class FacebookService {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected DaoManager daoManager;
	
	@Inject
	protected ConnectionRepository connectionRepository;
	
	/**
	 * fetch user info from facebook
	 * create new UserAccount and persist
	 * create new ProfileImage and persist
	 * 
	 * @param socialProfile
	 * @param request
	 * @return
	 */
//	@Transactional
//	public UserAccount signup(UserProfile socialProfile, HttpServletRequest request){
//		logger.debug("--->signup");
//		SignupForm signupForm = new SignupForm();
//		signupForm.setEmail(socialProfile.getEmail());
//		signupForm.setIpAddress(TennisSetAppUtils.getClientIP(request));
////		String pass = TennisSetAppUtils.generateRandomPassword();
////		signupForm.setPassword(pass);
////		signupForm.setConfirmPassword(pass);
//		signupForm.setUsername(socialProfile.getUsername());
//		UserAccount userAccount = daoManager.createUserAccount(signupForm);
//		userAccount.setFirstName(socialProfile.getFirstName());
//		userAccount.setLastName(socialProfile.getLastName());
//		
//		CreateProfileForm profileForm = new CreateProfileForm();
//		profileForm.setUserAccountId(userAccount.getUserAccountId());
//		profileForm.setAgreesToTerms(false);
//		
//		
////		com.tennissetapp.persistence.entities.UserProfile userProfile = daoManager.createUserProfile(profileForm);
////		userAccount.setUserProfile(userProfile);
//		//--
//		
//		userAccount.setCreatedByIP(TennisSetAppUtils.getClientIP(request));
//		Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
//		logger.debug("--API -->" + facebookConnection);
//		if (facebookConnection != null) {
//			Facebook facebook = facebookConnection.getApi();
//			
//			FacebookProfile facebookProfile = facebook.userOperations().getUserProfile();
//			if(StringUtils.isNotEmpty(facebookProfile.getMiddleName())){
//				userAccount.setMiddleName(facebookProfile.getMiddleName());
//			}
//			if(StringUtils.isNotEmpty(facebookProfile.getGender())){
//				logger.debug("--Facebook user gender: " + facebookProfile.getGender());
//				userAccount.setGender(UserAccount.Gender.valueOf(StringUtils.upperCase(facebookProfile.getGender())));
//			}
//			if(facebookProfile.getHometown() != null){
////				Address address = new Address();
////				address.setLocality(facebookProfile.getHometown().getName());
////				daoManager.persist(address);
////				userAccount.setAddress(address);
//				
//				logger.debug("NEEDS TO BE COMPLETED if(facebookProfile.getHometown)...");
//			}
//			if(facebookProfile.getLocation() != null){
//				Location location = facebook.fetchObject(facebookProfile.getLocation().getId(), Location.class);
//				logger.debug("--Facebook user Location-->" + location.getCity() + ", " + location.getLatitude());
//			}
//			else{
//				logger.debug("---->Location is null!");
//			}
//			if(StringUtils.isNotEmpty(facebookProfile.getBirthday())){ //middle-endian formatted date MM/dd/yyyy
//				logger.debug("--Facebook user birthday: " + facebookProfile.getBirthday());
//				String[] arr = StringUtils.split(facebookProfile.getBirthday(),'/');
//				userAccount.setBirthDate(arr[2] + "-" + arr[0] + "-" + arr[1]); 
//			}
//			else{
//				logger.debug("---->No birthday!");
//			}
//			
//			
//			if(StringUtils.isNotEmpty(facebookProfile.getAbout())){
//				logger.debug("--Facebook user about: ");
//				userAccount.setAboutMe(facebookProfile.getAbout());
//			}
//			if(facebookProfile.getTimezone() != null){
//				userAccount.setTimezone(facebookProfile.getTimezone());
//			}
//			
//			byte[] profileImage = facebook.userOperations().getUserProfileImage(ImageType.LARGE);
//			if(profileImage != null){
//				try {
//					logger.debug("--->fetching profile image");
//					ImageMetaData mdata = FileUtilities.writeImageToDisk(profileImage, ImageFile.SystemFolder.PROFILE_PHOTOS.toString());
//					
//					ImageFile imageFile = new ImageFile();
//					imageFile.setCreatedOn(new Date());
//					imageFile.setWidth(mdata.getWidth());
//					imageFile.setHeight(mdata.getHeight());
//					imageFile.setFileName(mdata.getFileName());
//					imageFile.setFormat(Format.valueOf(mdata.getFormat()));
//					imageFile.setSize(mdata.getSize());
//					imageFile.setOwnerId(userAccount.getUserAccountId());
//					imageFile.setDirPath(mdata.getDirPath());
//					
//					daoManager.persist(imageFile);
//					
//					userAccount.setProfileImageFileId(imageFile.getImageFileId());
//					userAccount.setProfileImageFile(imageFile);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//		}
//		else{
//		}
//		request.getSession().setAttribute(Constants.SessionAttributeKey.USER_ID, socialProfile.getEmail());
//		return userAccount;
//	}
	
	@Transactional
	public ServiceResponse signup(UserProfile socialProfile, HttpServletRequest request){
		logger.debug("--->signup");
		SignupForm signupForm = new SignupForm();
		signupForm.setEmail(socialProfile.getEmail());
//		signupForm.setIpAddress(TennisSetAppUtils.getClientIP(request));
		signupForm.setUsername(socialProfile.getUsername());
		
		BindException errors = new BindException(signupForm,"signupForm");
		new UserAccountServiceValidator().validate(signupForm, errors,false);
		logger.debug("signup " + signupForm.getArguments(SignupArgs.class) + ", binding results: " + errors);
		if(errors.getErrorCount() > 0){
			return new ServiceResponse(errors);
		}
		
		UserAccount userAccount = daoManager.createUserAccount(signupForm.getArguments(SignupArgs.class));
		
		userAccount.setFirstName(socialProfile.getFirstName());
		userAccount.setLastName(socialProfile.getLastName());
		
		CreateProfileForm profileForm = new CreateProfileForm();
		profileForm.setUserAccountId(userAccount.getUserAccountId().toString());
		profileForm.setAgreesToTerms(null);
		
		//--
		
		userAccount.setCreatedByIP(TennisSetAppUtils.getClientIP(request));
		Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
		logger.debug("--API -->" + facebookConnection);
		if (facebookConnection != null) {
			Facebook facebook = facebookConnection.getApi();
			
			FacebookProfile facebookProfile = facebook.userOperations().getUserProfile();
			if(StringUtils.isNotEmpty(facebookProfile.getMiddleName())){
				userAccount.setMiddleName(facebookProfile.getMiddleName());
			}
			if(StringUtils.isNotEmpty(facebookProfile.getGender())){
				logger.debug("--Facebook user gender: " + facebookProfile.getGender());
				userAccount.setGender(UserAccount.Gender.valueOf(StringUtils.upperCase(facebookProfile.getGender())));
			}
			if(facebookProfile.getHometown() != null){
//				Address address = new Address();
//				address.setLocality(facebookProfile.getHometown().getName());
//				daoManager.persist(address);
//				userAccount.setAddress(address);
				
				logger.debug("NEEDS TO BE COMPLETED if(facebookProfile.getHometown)...");
			}
			if(facebookProfile.getLocation() != null){
				Location location = facebook.fetchObject(facebookProfile.getLocation().getId(), Location.class);
				logger.debug("--Facebook user Location-->" + location.getCity() + ", " + location.getLatitude());
			}
			else{
				logger.debug("---->Location is null!");
			}
			if(StringUtils.isNotEmpty(facebookProfile.getBirthday())){ //middle-endian formatted date MM/dd/yyyy
				logger.debug("--Facebook user birthday: " + facebookProfile.getBirthday());
				String[] arr = StringUtils.split(facebookProfile.getBirthday(),'/');
				userAccount.setBirthDate(arr[2] + "-" + arr[0] + "-" + arr[1]); 
			}
			else{
				logger.debug("---->No birthday!");
			}
			
			
//			if(StringUtils.isNotEmpty(facebookProfile.getAbout())){
//				logger.debug("--Facebook user about: ");
//				userAccount.setAboutMe(facebookProfile.getAbout());
//			}
			if(facebookProfile.getTimezone() != null){
				userAccount.setTimezone(facebookProfile.getTimezone());
			}
			byte[] profileImage = facebook.userOperations().getUserProfileImage(ImageType.LARGE);
			if(profileImage != null){
				try {
					logger.debug("fetching profile image");
					ImageMetaData mdata = FileUtilities.writeImageToDisk(profileImage, ImageFile.SystemFolder.PROFILE_PHOTOS.toString());
					ImageFile imageFile = new ImageFile();
					imageFile.setCreatedOn(new Date());
					imageFile.setWidth(mdata.getWidth());
					imageFile.setHeight(mdata.getHeight());
					imageFile.setFileName(mdata.getFileName());
					imageFile.setFormat(Format.valueOf(mdata.getFormat()));
					imageFile.setSize(mdata.getSize());
					imageFile.setOwnerId(userAccount.getUserAccountId());
					imageFile.setDirPath(mdata.getDirPath());
					//TODO profile image is not in account
//					daoManager.persist(imageFile);
//					userAccount.setProfileImageFileId(imageFile.getImageFileId());
//					userAccount.setProfileImageFile(imageFile);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		else{
		}
//		request.getSession().setAttribute(Constants.SessionAttributeKey.USER_ID, socialProfile.getEmail());
		
		ServiceResponse response = new ServiceResponse();
		response.put(Constants.SessionAttributeKey.USER_ID, userAccount.getUserAccountId());
		return response;
	}

}

/*
Override
public void call(Session session, SessionState state,
Exception exception) {
updateView();
if (session.isOpened()) {
fbAccessToken = session.getAccessToken();
// make request to get facebook user info
Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
@Override
public void onCompleted(GraphUser user, Response response) {
   Log.i("fb", "fb user: "+ user.toString());

   String fbId = user.getId();
   String fbAccessToken = fbAccessToken;
   String fbName = user.getName();
   String gender = user.asMap().get("gender").toString();
   String email = user.asMap().get("email").toString();

   Log.i("fb", userProfile.getEmail());
}
});
}
}

///////////////upload photo
* byte[] data = null;
try {
ContentResolver cr = mainActivity.getContentResolver();
InputStream fis = cr.openInputStream(photoUri);
Bitmap bi = BitmapFactory.decodeStream(fis);
ByteArrayOutputStream baos = new ByteArrayOutputStream();
bi.compress(Bitmap.CompressFormat.JPEG, 100, baos);
data = baos.toByteArray();              
} catch (FileNotFoundException e) {
e.printStackTrace();
}     
Bundle params = new Bundle(); 
params.putString("method", "photos.upload");          
params.putByteArray("picture", data);
*/