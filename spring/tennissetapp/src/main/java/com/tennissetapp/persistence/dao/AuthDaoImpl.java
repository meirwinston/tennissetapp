package com.tennissetapp.persistence.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tennissetapp.forms.CreateProfileForm;
import com.tennissetapp.forms.SignupForm;
import com.tennissetapp.forms.PlayerProfileForm;
import com.tennissetapp.forms.UpdateProfileImageForm;
import com.tennissetapp.persistence.entities.Address;
import com.tennissetapp.persistence.entities.ImageFile;
import com.tennissetapp.persistence.entities.ImageFile.Format;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.persistence.entities.UserAccount.AccountStatus;
import com.tennissetapp.persistence.entities.UserAccount.Visibility;

public class AuthDaoImpl implements AuthDao{
//	private SessionFactory sessionFactory;
//	
//	private Session getSession(){
//		try {
//			return sessionFactory.getCurrentSession();
//		}
//		catch (Exception exp) {
//			exp.printStackTrace();
//			sessionFactory.close();
//		}
//		return null;
//	}
//	
//	@Override
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	@Pointcut("execution(* com.tennissetapp.persistence.dao.AuthDAOImpl.create(..))")
//	@Override
//	public UserAccount create(SignupRequest form) {
//		UserAccount l = new UserAccount();
//		l.setEmail(form.getEmail());
//		l.setPassword(form.getPassword());
//		l.setCreatedOn(new Date());
//		l.setCreatedByIP(form.getIpAddress());
//		l.setLoginCount(0);
//		l.setStatus(UserItemStatus.PENDING_ACTIVATION);
//		l.setVisibility(Visibility.HIDE_EMAIL);
//		getSession().persist(l);
//		return l;
////		try {
////			UserAccount l = new UserAccount();
////			l.setEmail(form.getEmail());
////			l.setPassword(form.getPassword());
////			l.setCreatedOn(new Date());
////			l.setCreatedByIP(form.getIpAddress());
////			l.setLoginCount(0);
////			l.setStatus(UserItemStatus.PENDING_ACTIVATION);
////			l.setVisibility(Visibility.HIDE_EMAIL);
////			getSession().persist(l);
////			return l;
////		} catch (Exception exp) {
////			throw new RuntimeException(exp);
////		}
//	}
//	
//	
//	
//	@Override
//	public UserProfile findUserProfile(String email) {
//		Query query = getSession().getNamedQuery("UserProfile.selectByEmail");
//		query.setString("email", email);
//		return (UserProfile)query.uniqueResult();
//	}
//
//	public UserProfile createUserProfile(CreatePlayerProfileRequest form){
//		System.out.println("----> " + form);
//		UserProfile userProfile = null;
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		if(auth == null || auth.getPrincipal() == null){
////			throw new UnauthorizedException("Not authorized to open this page");
////		}
//		Query query = getSession().getNamedQuery("UserAccount.selectByEmail");
//		
//		query.setString("email", form.getEmail());
//		
//		UserAccount userAccount = (UserAccount)query.uniqueResult();
//		if(userAccount != null){
//			userProfile = userAccount.getUserProfile();
//			if(userProfile == null){
//				userProfile = newUserProfile(userAccount);
//			}
//			
//			userProfile.setProfileType(UserProfile.ProfileType.valueOf(form.getAccountType()));
//			userProfile.setBirthDate(form.getBirthYear() + "-" + form.getBirthMonth() + "-" + form.getBirthDay());
//			userProfile.setFirstName(form.getFirstName());
//			userProfile.setLastName(form.getLastName());
//			if(StringUtils.isNotEmpty(form.getGender())){
//				userProfile.setGender(UserProfile.Gender.valueOf(form.getGender()));
//			}
//			else{
//				userProfile.setGender(null);
//			}
//			
//		}
//		else{
//			throw new EntityNotFoundException("User account could not be found in the system");
//		}
//		
//		return userProfile;
//	}
//	
//	private UserProfile newUserProfile(UserAccount userAccount){
//		UserProfile profile = new UserProfile();
//		profile.setCreatedOn(new Date());
//		profile.setEmail(userAccount.getEmail());
////		profile.setUserAccount(userAccount);
//		getSession().persist(profile);
//		userAccount.setUserProfile(profile);
//		
//		return profile;
//	}
//	private Address extractAddress(UpdatePlayerProfileRequest form){
//		Address address = new Address();
//		address.setLatitude(form.getLatitude());
//		address.setLongitude(form.getLongitude());
//		address.setCountry(form.getCountry());
//		address.setCountryShortName(form.getCountryShortName());
//		address.setPostalCode(form.getPostalCode());
//		address.setStreetNumber(form.getStreetNumber());
//		address.setRoute(form.getRoute());
//		address.setRouteShortName(form.getRouteShortName());
//		address.setLocality(form.getLocality());
//		address.setLocalityShortName(form.getLocalityShortName());
//		address.setAdministrativeAreaLevel1(form.getAdministrativeAreaLevel1());
//		address.setAdministrativeAreaLevel1ShortName(form.getAdministrativeAreaLevel1ShortName());
//		address.setAdministrativeAreaLevel2(form.getAdministrativeAreaLevel2());
//		address.setAdministrativeAreaLevel2ShortName(form.getAdministrativeAreaLevel2ShortName());
//		
//		return address;
//	}
//	public void updateAccount(UpdatePlayerProfileRequest form){
//		System.out.println("----> " + form);
//		
//		UserAccount userAccount = (UserAccount)getSession().get(UserAccount.class, form.getEmail());
//		if(userAccount != null){
//			Address address = extractAddress(form);
//			getSession().persist(address);
//			
//			UserProfile profile = userAccount.getUserProfile();
//			if(profile == null){
//				profile = newUserProfile(userAccount);
//			}
//			profile.setAddress(address);
//			profile.setAvailableWeekdayAfternoon(form.getWeekdayAvailabilityAfternoonCheck());
//			profile.setAvailableWeekdayEvening(form.getWeekdayAvailabilityEveningCheck());
//			profile.setAvailableWeekdayMorning(form.getWeekdayAvailabilityMorningCheck());
//			profile.setAvailableWeekendAfternoon(form.getWeekendAvailabilityAfternoonCheck());
//			profile.setAvailableWeekendEvening(form.getWeekendAvailabilityEveningCheck());
//			profile.setAvailableWeekendMorning(form.getWeekendAvailabilityMorningCheck());
//			profile.setHand(Hand.valueOf(form.getHand()));
//			profile.setLevelOfPlay(form.getLevelOfPlay());
//			profile.setPlayDoubles(form.getDoublesCheck());
//			profile.setPlaySingles(form.getSinglesCheck());
//			profile.setPlayFullMatch(form.getFullMatchCheck());
//			profile.setPlayPoints(form.getPointsCheck());
//			profile.setPlayHittingAround(form.getHittingAroundCheck());
//		}
//		else{
//			throw new EntityNotFoundException("User account could not be found in the system");
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<UserAccount> listLogins() {
//		Query query;
//		try {
//			query = getSession().getNamedQuery("UserAccount.selectAll");
//			return query.list();
//			
//		} catch (Exception exp) {
//			throw new RuntimeException(exp);
//		}
//	}
//	
////	@Override
////	public Long loginAccountId(){
////		if(SecurityContextHolder.getContext().getAuthentication() != null){
////			Session s = getSession();
////			Query query = s.getNamedQuery("UserAccount.selectIdByEmail");
////			query.setString("email", SecurityContextHolder.getContext().getAuthentication().getName());
////			System.out.println("Principal " + SecurityContextHolder.getContext().getAuthentication().getName());
////			
////			return (Long)query.uniqueResult();
////		}
////		return null;
////	}
//
//	@Override
//	public ImageFile updateProfileImage(UpdateProfileImageRequest form) {
//		Session s = getSession();
//		
//		if(form.getEmail() != null){
//			Query query = s.getNamedQuery("UserAccount.selectProfileByEmail");
//			query.setString("email", form.getEmail());
//			ImageFile imageFile = (ImageFile)query.uniqueResult();
//			if(imageFile != null){
//				imageFile.setHeight(form.getImageMetaData().getHeight());
//				imageFile.setWidth(form.getImageMetaData().getWidth());
//				imageFile.setFormat(Format.valueOf(form.getImageMetaData().getFormat()));
//				imageFile.setFileName(form.getImageMetaData().getFileName());
//				imageFile.setSize(form.getImageMetaData().getSize());
//				imageFile.setCreatedOn(new Date());
//			}
//			else{
//				imageFile = new ImageFile();
//				imageFile.setHeight(form.getImageMetaData().getHeight());
//				imageFile.setWidth(form.getImageMetaData().getWidth());
//				imageFile.setFormat(Format.valueOf(form.getImageMetaData().getFormat()));
//				imageFile.setFileName(form.getImageMetaData().getFileName());
//				imageFile.setSize(form.getImageMetaData().getSize());
//				imageFile.setCreatedOn(new Date());
//				s.persist(imageFile);
//				
//				query = s.getNamedQuery("UserAccount.updateProfileImageFile");
//				query.setString("email", form.getEmail());
//				query.setLong("profileImageFileId", imageFile.getImageFileId());
//				//
//			}
//		}
//		return null;
//	}
//
	
}
