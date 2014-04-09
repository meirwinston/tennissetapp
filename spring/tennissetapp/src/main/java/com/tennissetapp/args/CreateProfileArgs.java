package com.tennissetapp.args;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

import com.tennissetapp.persistence.entities.ImageFile;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.util.EnvironmentProperties;

public class CreateProfileArgs {
	public Long userAccountId;
	public String profileType;
	public Boolean agreesToTerms;
	public String firstName;
	public String lastName;
	public Integer birthDay;
	public Integer birthMonth;
	public Integer birthYear;
	public String gender;
	
	public String photoItemId;
	public String profilePhotoUrl;
	public String profileFileItemId; //FileItemId
	public FileItem fileItem;
	
	public CreateProfileArgs(){}
	
	public CreateProfileArgs(UserAccount userAccount){
		initialize(userAccount);
	}
	
	public void initialize(UserAccount userAccount){
		this.userAccountId = userAccount.getUserAccountId();
		if(userAccount.getPlayerProfile() != null){
			this.profileType = "PLAYER_PROFILE";
		}
		else if(userAccount.getPlayerProfile() != null){
			this.profileType = "TEACHER_PROFILE";
		}
		
		this.agreesToTerms = null;
		this.firstName = userAccount.getFirstName();
		this.lastName = userAccount.getLastName();
		if(userAccount.getBirthDate() != null){
			String[] arr = StringUtils.split(userAccount.getBirthDate(),'-');
			this.birthDay = Integer.valueOf(arr[2]);
			this.birthMonth = Integer.valueOf(arr[1]);
			this.birthYear = Integer.valueOf(arr[0]);
		}
		if(userAccount.getGender() != null){
			this.gender = userAccount.getGender().toString();
		}
		
		//TODO
//		if(userAccount.getProfileImageFile() != null){
//			this.profilePhotoUrl = 
//				EnvironmentProperties.getInstance().getImagesUrl() +
//				"/" +
//				ImageFile.SystemFolder.PROFILE_PHOTOS + 
//				"/" + 
//				userAccount.getProfileImageFile().getFileName();
//			
//		}
		
	}
}
