package com.tennissetapp.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;

import com.google.common.collect.Lists;
import com.tennissetapp.args.AddressArgs;
import com.tennissetapp.args.CreateFavoriteTeacherArgs;
import com.tennissetapp.args.CreateMatchArgs;
import com.tennissetapp.args.CreatePlayerProfileArgs;
import com.tennissetapp.args.CreateProfileArgs;
import com.tennissetapp.args.CreateTennisCenterArgs;
import com.tennissetapp.args.FindByLocationArgs;
import com.tennissetapp.args.CalendarEventsArgs;
import com.tennissetapp.args.MessageArgs;
import com.tennissetapp.args.PlayerProfileArgs;
import com.tennissetapp.args.ScrollArgs;
import com.tennissetapp.args.ScrollByMateAccountIdArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.args.ScrollMessagesArgs;
import com.tennissetapp.args.SearchMatesByNameArgs;
import com.tennissetapp.args.SearchMatchesArgs;
import com.tennissetapp.args.SearchMatesArgs;
import com.tennissetapp.args.SearchByNameOrEmailArgs;
import com.tennissetapp.args.SearchTennisCourtsArgs;
import com.tennissetapp.args.SearchTennisTeachersArgs;
import com.tennissetapp.args.SignupArgs;
import com.tennissetapp.args.UpdateTennisDetailsArgs;
import com.tennissetapp.args.UserAccountPrimaryArgs;
import com.tennissetapp.exception.DateExpriedException;
import com.tennissetapp.exception.DuplicateRecordException;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.forms.ResetPasswordForm;
import com.tennissetapp.forms.SubmitPostForm;
import com.tennissetapp.forms.TeacherProfileForm;
import com.tennissetapp.forms.UpdateProfileImageForm;
import com.tennissetapp.persistence.entities.Address;
import com.tennissetapp.persistence.entities.CalendarEventSelect;
import com.tennissetapp.persistence.entities.CountEntity;
import com.tennissetapp.persistence.entities.CourtSelect;
import com.tennissetapp.persistence.entities.FavoriteTeacher;
import com.tennissetapp.persistence.entities.ImageFile;
import com.tennissetapp.persistence.entities.LatitudeLongitude;
import com.tennissetapp.persistence.entities.Match;
import com.tennissetapp.persistence.entities.MatchMember;
import com.tennissetapp.persistence.entities.MatchSelect;
import com.tennissetapp.persistence.entities.Mate;
import com.tennissetapp.persistence.entities.MateSelect;
import com.tennissetapp.persistence.entities.Notification;
import com.tennissetapp.persistence.entities.TeacherSelect;
import com.tennissetapp.persistence.entities.TennisCenter;
import com.tennissetapp.persistence.entities.TennisCourt;
import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.TennisTeacherProfile;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.persistence.entities.UserPost;
import com.tennissetapp.persistence.entities.ImageFile.Format;
import com.tennissetapp.persistence.entities.UserAccount.AccountStatus;
import com.tennissetapp.persistence.entities.UserAccount.Visibility;
import com.tennissetapp.persistence.entities.UserPostSelect;
import com.tennissetapp.util.FileUtilities;
import com.tennissetapp.util.ImageMetaData;

public class DaoManagerImpl implements DaoManager{
	protected Logger logger = Logger.getLogger(getClass());
	
	private SessionFactory sessionFactory;
	
	@Override
	public Session getSession(){
		try {
			return sessionFactory.getCurrentSession();
		}
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
//			sessionFactory.close();
		}
		return null;
	}
	
	@Override
	public Session getNewSession(){
		try {
			return sessionFactory.openSession();
		}
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
//			sessionFactory.close();
		}
		return null;
	}
	
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void evict(Object entity){
		getSession().evict(entity);
	}

	@Pointcut("execution(* com.tennissetapp.persistence.dao.AuthDAOImpl.create(..))")
	@Override
	public UserAccount createUserAccount(SignupArgs args) {
		UserAccount l = findUserAccountByEmail(args.email);
		if(l != null){
			//this account has a profile already, throw an exception to user
			//saying that this account is taken.
			if(l.getTeacherProfile() != null || l.getPlayerProfile() != null){
//				throw new AccountExistsException();
				throw new DuplicateRecordException();
			}
			//it is possible that the user created an account without completing 
			//his profile. here there is no profile created so it is safe to redirect 
			//the user to create profile after signup.
			else{ 
//				throw new ProfileNotCreatedException();
				return l;
			}
		}
		else{
			l = new UserAccount();
		}
		l.setUsername(args.username);
		
		l.setEmail(args.email);
		l.setPassword(args.password);
		l.setCreatedOn(new Date());
		l.setCreatedByIP(args.ipAddress);
		l.setLoginCount(0);
		l.setStatus(AccountStatus.PENDING_ACTIVATION);
		l.setVisibility(Visibility.HIDE_EMAIL);
		getSession().persist(l);
		return l;
	}
	
	@Override
	public boolean usernameExists(String username){
		Query query = getSession().getNamedQuery("UserAccount.countUsername");
		query.setString("username", username);
		
		return (Long)query.uniqueResult() > 0;
	}
	
//	@Override
//	public TennisPlayerProfile createPlayerProfile(CreateProfileArgs args){
//		TennisPlayerProfile profile = null;
//		UserAccount userAccount = find(UserAccount.class,args.userAccountId);
//		if(userAccount != null){
//			profile = userAccount.getPlayerProfile();
//			if(profile == null){
//				profile = newPlayerProfile(userAccount);
//			}
//			userAccount.setBirthDate(args.birthYear + "-" + args.birthMonth + "-" + args.birthDay);
//			userAccount.setFirstName(args.firstName);
//			userAccount.setLastName(args.lastName);
//			if(StringUtils.isNotEmpty(args.gender)){
//				userAccount.setGender(UserAccount.Gender.valueOf(args.gender));
//			}
//			else{
//				userAccount.setGender(null);
//			}
//			
//			if(args.fileItem != null){
//				try {
//					ImageMetaData img = FileUtilities.writeImageToDisk(args.fileItem, ImageFile.SystemFolder.PROFILE_PHOTOS.toString());
//					UpdateProfileImageForm f = new UpdateProfileImageForm();
//					f.setImageMetaData(img);
//					f.setUserAccountId(args.userAccountId);
//					if(img != null){
//						ImageFile imageFile = updatePlayerProfileImage(f);
//						if(imageFile != null){
////							userAccount.setProfileImageFile(imageFile);
////							userAccount.setProfileImageFileId(imageFile.getImageFileId());
//							profile.setProfileImageFile(imageFile);
//							profile.setProfileImageFileId(imageFile.getImageFileId());
//						}
//					}
//				} catch (Exception e) {
//					logger.error(e.getMessage(), e);
//				}
//			}
//		}
//		else{
//			throw new EntityNotFoundException("User account could not be found in the system");
//		}
//		
//		return profile;
//	}
	
	@Override
	public UserAccount updateAccountPrimaryFields(UserAccountPrimaryArgs args){
		UserAccount userAccount = find(UserAccount.class,args.userAccountId);
		if(userAccount != null){
			userAccount.setBirthDate(args.birthYear + "-" + args.birthMonth + "-" + args.birthDay);
			userAccount.setFirstName(args.firstName);
			userAccount.setLastName(args.lastName);
			userAccount.setGender(args.gender);
			
			//-- address
			Address address = insertAddress(args,0.0001);
			if(address != null){
				userAccount.setAddressLatitude(address.getLatitude());
				userAccount.setAddressLongitude(address.getLongitude());
			}
			
		}
		else{
			throw new EntityNotFoundException("User account could not be found in the system");
		}
		
		return userAccount;
	}
	
	@Override
	public TennisPlayerProfile createPlayerProfile(CreatePlayerProfileArgs args){
		TennisPlayerProfile profile = null;
		UserAccount userAccount = find(UserAccount.class,args.userAccountId);
		if(userAccount != null){
			profile = userAccount.getPlayerProfile();
			if(profile == null){
				profile = newPlayerProfile(userAccount);
			}
			userAccount.setBirthDate(args.birthYear + "-" + args.birthMonth + "-" + args.birthDay);
			userAccount.setFirstName(args.firstName);
			userAccount.setLastName(args.lastName);
			userAccount.setGender(args.gender);
			
			//-- address
			Address address = insertAddress(args,0.0001);
			if(address != null){
				userAccount.setAddressLatitude(address.getLatitude());
				userAccount.setAddressLongitude(address.getLongitude());
			}
			
			profile.setHand(args.hand);
			profile.setAvailableWeekdayAfternoon(args.weekdayAvailabilityAfternoonCheck);
			profile.setAvailableWeekdayEvening(args.weekdayAvailabilityEveningCheck);
			profile.setAvailableWeekdayMorning(args.weekdayAvailabilityMorningCheck);
			profile.setAvailableWeekendAfternoon(args.weekendAvailabilityAfternoonCheck);
			profile.setAvailableWeekendEvening(args.weekendAvailabilityEveningCheck);
			profile.setAvailableWeekendMorning(args.weekendAvailabilityMorningCheck);
			profile.setLevelOfPlay(args.levelOfPlay);
			profile.setPlayDoubles(args.doublesCheck);
			profile.setPlaySingles(args.singlesCheck);
			profile.setPlayFullMatch(args.fullMatchCheck);
			profile.setPlayPoints(args.pointsCheck);
			profile.setPlayHittingAround(args.HittingAroundCheck);
			profile.setAboutMe(args.aboutMe);

			if(args.fileItem != null){
				try {
					ImageMetaData img = FileUtilities.writeImageToDisk(args.fileItem, ImageFile.SystemFolder.PROFILE_PHOTOS.toString());
					UpdateProfileImageForm f = new UpdateProfileImageForm();
					f.setImageMetaData(img);
					f.setUserAccountId(args.userAccountId);
					if(img != null){
						ImageFile imageFile = updatePlayerProfileImage(f);
						if(imageFile != null){
							profile.setProfileImageFile(imageFile);
							profile.setProfileImageFileId(imageFile.getImageFileId());
						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			
			if(args.favoriteCourts != null && args.favoriteCourts.size() > 0){
				List<TennisCenter> list = new ArrayList<TennisCenter>();
				for(Long courtId : args.favoriteCourts){
					TennisCenter c = find(TennisCenter.class,courtId);
					if(c != null){
						list.add(c);
					}
				}
				profile.setFavouriteCourts(list);
			}
		}
		else{
			throw new EntityNotFoundException("User account could not be found in the system");
		}
		
		return profile;
	}
	
	@Override
	public TennisPlayerProfile updateTennisDetails(UpdateTennisDetailsArgs args){
		TennisPlayerProfile profile = null;
		UserAccount userAccount = find(UserAccount.class,args.userAccountId);
		if(userAccount != null){
			profile = userAccount.getPlayerProfile();
			if(profile == null){
				profile = newPlayerProfile(userAccount);
			}
			profile.setHand(args.hand);
			profile.setAvailableWeekdayAfternoon(args.weekdayAvailabilityAfternoonCheck);
			profile.setAvailableWeekdayEvening(args.weekdayAvailabilityEveningCheck);
			profile.setAvailableWeekdayMorning(args.weekdayAvailabilityMorningCheck);
			profile.setAvailableWeekendAfternoon(args.weekendAvailabilityAfternoonCheck);
			profile.setAvailableWeekendEvening(args.weekendAvailabilityEveningCheck);
			profile.setAvailableWeekendMorning(args.weekendAvailabilityMorningCheck);
			profile.setLevelOfPlay(args.levelOfPlay);
			profile.setPlayDoubles(args.doublesCheck);
			profile.setPlaySingles(args.singlesCheck);
			profile.setPlayFullMatch(args.fullMatchCheck);
			profile.setPlayPoints(args.pointsCheck);
			profile.setPlayHittingAround(args.HittingAroundCheck);

		}
		else{
			throw new EntityNotFoundException("User account could not be found in the system");
		}
		
		return profile;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSelect> findFavoriteTeachers(@Valid ScrollByUserAccountIdArgs args){
		logger.debug("findFavoriteTeachers: " + args);
		Query query = getSession().getNamedQuery("FavoriteTeacher.selectByUserAccountId");
		query.setLong("userAccountId", args.userAccountId);
		query.setFirstResult((int)args.firstResult);
		query.setMaxResults(args.maxResults);
		
		List<FavoriteTeacher> l = query.list();
		List<TeacherSelect> list = Lists.newArrayListWithCapacity(l.size());
		for(FavoriteTeacher f : l){
			TeacherSelect t = new TeacherSelect();
			TennisTeacherProfile teacherProfile = f.getTeacherProfile();
			UserAccount teacherAccount = f.getTeacherProfile().getUserAccount();
			
			t.setAdministrativeAreaLevel1(teacherAccount.getAddress().getAdministrativeAreaLevel1ShortName());
			t.setAdministrativeAreaLevel2(teacherAccount.getAddress().getAdministrativeAreaLevel2ShortName());
			t.setAvailableWeekdayAfternoon(teacherProfile.isAvailableWeekdayAfternoon());
			t.setAvailableWeekdayEvening(teacherProfile.isAvailableWeekdayEvening());
			t.setAvailableWeekdayMorning(teacherProfile.isAvailableWeekdayMorning());
			t.setAvailableWeekendAfternoon(teacherProfile.isAvailableWeekendAfternoon());
			t.setAvailableWeekendEvening(teacherProfile.isAvailableWeekendEvening());
			t.setAvailableWeekendMorning(teacherProfile.isAvailableWeekendMorning());
			t.setCertified(teacherProfile.getCertified());
			t.setCertifyingOrganization(teacherProfile.getCertifyingOrganization());
			t.setClinicRates(teacherProfile.getClinicRates());
			t.setClub(teacherProfile.getClub());
			t.setCreatedOn(teacherProfile.getCreatedOn());
			t.setCurrency(teacherProfile.getCurrency());
			t.setFirstName(teacherAccount.getFirstName());
			t.setLastName(teacherAccount.getLastName());
			t.setLatitude(teacherAccount.getAddressLatitude());
			t.setLocality(teacherAccount.getAddress().getLocalityShortName());
			t.setLongitude(teacherAccount.getAddressLongitude());
			t.setProfileImage(teacherProfile.getProfileImageFile().getFileName());
			t.setRoute(teacherAccount.getAddress().getRouteShortName());
			t.setSpecialtyAdults(teacherProfile.getSpecialtyAdults());
			t.setSpecialtyJuniors(teacherProfile.getSpecialtyJuniors());
			t.setSpecialtyTurnaments(teacherProfile.getSpecialtyTurnaments());
			t.setUserAccountId(teacherAccount.getUserAccountId());
			t.setUsptaCertified(teacherProfile.getUsptaCertified());
			t.setUsptrCertified(teacherProfile.getUsptrCertified());
			t.setYearsOfExperience(teacherProfile.getYearsOfExperience());
			list.add(t);
		}
		
		return list;
	}
	
	@Override
	public FavoriteTeacher createFavoriteTeacher(CreateFavoriteTeacherArgs args){
		Session s = getSession();
		FavoriteTeacher t;
		
		FavoriteTeacher.Key key = new FavoriteTeacher.Key(args.userAccountId, args.teacherProfileId);
		t = (FavoriteTeacher)s.get(FavoriteTeacher.class, key);
		if(t == null){
			t = new FavoriteTeacher();
			t.setCreatedOn(args.createdOn);
			t.setTeacherProfileId(args.teacherProfileId);
			t.setUserAccountId(args.userAccountId);
			s.persist(t);
		}
		else{
			throw new DuplicateRecordException("This teacher is already in your favorite list");
		}
		
		return t;
	}
	
	@Override
	public int removeFavoriteTeacher(Long userAccountId, Long teacherProfileId){
		Session s = getSession();
		FavoriteTeacher t;
		
		FavoriteTeacher.Key key = new FavoriteTeacher.Key(userAccountId, teacherProfileId);
		t = (FavoriteTeacher)s.get(FavoriteTeacher.class, key);
		if(t != null){
			s.delete(t);
			return 1;
		}
		return 0;
	}
	
	@Override
	public int deleteFavoriteTeachers(Long userAccountId){
		Query query = getSession().getNamedQuery("FavoriteTeacher.deleteByUserAccountId");
		query.setLong("userAccountId", userAccountId);
		
		return query.executeUpdate();
	}
	
	private String buildSearchTennisTeachersQuery(SearchTennisTeachersArgs args, boolean count){
		StringBuilder sb = new StringBuilder();
		if(count){
			sb.append("SELECT COUNT(t.userAccountId)");
		}
		else{
			sb.append("SELECT t.*, u.firstName,u.lastName,a.longitude,a.latitude,a.route, a.locality, a.administrativeAreaLevel1ShortName AS administrativeAreaLevel1,a.administrativeAreaLevel2ShortName AS administrativeAreaLevel2,");
			sb.append("(SELECT i.fileName FROM image_files AS i WHERE i.imageFileId = t.profileImageFileId) AS profileImage ");
			if(args.latitude != null && args.longitude != null){
				sb.append(",geoDistance(u.addressLatitude,u.addressLongitude," + args.latitude + "," + args.longitude + ") AS distance ");
			}
		}
		
		
		sb.append("FROM teacher_profiles AS t ");
		sb.append("INNER JOIN user_accounts AS u ON u.userAccountId = t.userAccountId ");
		sb.append("INNER JOIN addresses AS a ON a.latitude = u.addressLatitude AND a.longitude = u.addressLongitude ");
		
		StringBuilder whereSb = new StringBuilder();
		if(args.teacherCertified != null){
			whereSb.append("(t.certified = ");
			whereSb.append(args.teacherCertified);
			whereSb.append(" OR ");
			whereSb.append("t.usptaCertified = ");
			whereSb.append(args.teacherCertified);
			whereSb.append(" OR ");
			whereSb.append("t.usptrCertified = ");
			whereSb.append(args.teacherCertified);
			whereSb.append(") AND ");
		}
		if(args.availableWeekdayAfternoon){
			whereSb.append("t.availableWeekdayAfternoon = ");
			whereSb.append(args.availableWeekdayAfternoon);
			whereSb.append(" AND ");
		}
		if(args.availableWeekdayEvening){
			whereSb.append("t.availableWeekdayEvening = ");
			whereSb.append(args.availableWeekdayEvening);
			whereSb.append(" AND ");
		}
		if(args.availableWeekdayMorning){
			whereSb.append("t.availableWeekdayMorning = ");
			whereSb.append(args.availableWeekdayMorning);
			whereSb.append(" AND ");
		}
		if(args.availableWeekendAfternoon){
			whereSb.append("t.availableWeekendAfternoon = ");
			whereSb.append(args.availableWeekendAfternoon);
			whereSb.append(" AND ");
		}
		if(args.availableWeekendEvening){
			whereSb.append("t.availableWeekendEvening = ");
			whereSb.append(args.availableWeekendEvening);
			whereSb.append(" AND ");
		}
		if(args.availableWeekendMorning){
			whereSb.append("t.availableWeekendMorning = ");
			whereSb.append(args.availableWeekendMorning);
			whereSb.append(" AND ");
		}
		
		//specialty
		if(args.specialtyAdults){
			whereSb.append("t.specialtyAdults = ");
			whereSb.append(args.specialtyAdults);
			whereSb.append(" AND ");
		}
		if(args.specialtyJuniors){
			whereSb.append("t.specialtyJuniors = ");
			whereSb.append(args.specialtyJuniors);
			whereSb.append(" AND ");
		}
		if(args.specialtyTurnaments){
			whereSb.append("t.specialtyTurnaments = ");
			whereSb.append(args.specialtyTurnaments);
			whereSb.append(" AND ");
		}
		
		//rates
		if(args.currency != null){
			whereSb.append("t.currency = '");
			whereSb.append(args.currency);
			whereSb.append("' AND ");
		}
		
		if(whereSb.length() > 4){ //if ends with 'AND '
			whereSb.setLength(whereSb.length() - 4);
			sb.append("WHERE ");
			sb.append(whereSb);
		}
		if(args.latitude != null && args.longitude != null){
			if(count){
				if(whereSb.length() > 0){
					sb.append("AND ");
				}
				else{
					sb.append("WHERE ");
				}
				sb.append("geoDistance(u.addressLatitude,u.addressLongitude," + args.latitude + "," + args.longitude + ") <= ");
				sb.append(args.maxDistance);
				sb.append(" ");
			}
			else{
				sb.append("HAVING distance <= ");
				sb.append(args.maxDistance);
				sb.append(" ");
				sb.append("ORDER BY distance ");
				if(args.clinicRates != null){
					sb.append(", t.clinicRates=");
					sb.append(args.clinicRates);
					sb.append(" ");
				}
				sb.append("LIMIT ");
				sb.append(args.firstResult);
				sb.append(",");
				sb.append(args.maxResults);
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSelect> searchTennisTeachers(SearchByNameOrEmailArgs args){
		Query query = getSession().getNamedQuery("TeacherSelect.searchByNameOrEmail");
		query.setString("nameOrEmail", args.nameOrEmail);
		
		query.setLong("firstResult", args.firstResult);
		query.setInteger("maxResults", args.maxResults);
		return query.list();
	}
	
	@Override
	public Long countTennisTeachers(SearchByNameOrEmailArgs args){
		Query query = getSession().getNamedQuery("TeacherSelect.countByNameOrEmail");
		query.setString("nameOrEmail", args.nameOrEmail);
		
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSelect> searchTennisTeachers(SearchTennisTeachersArgs args) {
		logger.debug("searchTennisTeachers " + buildSearchTennisTeachersQuery(args, false));
		Query query = getSession().createSQLQuery(buildSearchTennisTeachersQuery(args, false));
		query.setResultTransformer(new AliasToBeanResultTransformer(TeacherSelect.class));
		return query.list();
	}

	@Override
	public Long countSearchTennisTeachers(SearchTennisTeachersArgs args) {
		logger.debug("countSearchTennisTeachers " + buildSearchTennisTeachersQuery(args, true));
		Query query = getSession().createSQLQuery(buildSearchTennisTeachersQuery(args, true));
		return (Long)query.uniqueResult();
	}

	@Override
	public TennisTeacherProfile createTeacherProfile(CreateProfileArgs args){
		TennisTeacherProfile profile = null;
		UserAccount userAccount = find(UserAccount.class,args.userAccountId);
		if(userAccount != null){
			profile = userAccount.getTeacherProfile();
			if(profile == null){
				profile = newTeacherProfile(userAccount);
			}
			userAccount.setBirthDate(args.birthYear + "-" + args.birthMonth + "-" + args.birthDay);
			userAccount.setFirstName(args.firstName);
			userAccount.setLastName(args.lastName);
			if(StringUtils.isNotEmpty(args.gender)){
				userAccount.setGender(UserAccount.Gender.valueOf(args.gender));
			}
			else{
				userAccount.setGender(null);
			}
		}
		else{
			throw new EntityNotFoundException("User account could not be found in the system");
		}
		
		return profile;
	}
	
	private TennisPlayerProfile newPlayerProfile(UserAccount userAccount){
		TennisPlayerProfile profile = new TennisPlayerProfile();
		profile.setCreatedOn(new Date());
		profile.setUserAccountId(userAccount.getUserAccountId());
		profile.setUserAccount(userAccount);
		getSession().persist(profile);
		userAccount.setPlayerProfile(profile);
		return profile;
	}
	
	protected TennisTeacherProfile newTeacherProfile(UserAccount userAccount){
		TennisTeacherProfile profile = new TennisTeacherProfile();
		profile.setCreatedOn(new Date());
		profile.setUserAccountId(userAccount.getUserAccountId());
		profile.setUserAccount(userAccount);
		getSession().persist(profile);
		userAccount.setTeacherProfile(profile);
		
		return profile;
	}
	public static Address extractAddress(AddressArgs args){
		Address address = new Address();
		
		address.setLatitude(args.latitude);
		address.setLongitude(args.longitude);
		address.setCountry(args.country);
		address.setCountryShortName(args.countryShortName);
		address.setPostalCode(args.postalCode);
		address.setStreetNumber(args.streetNumber);
		address.setRoute(args.route);
		address.setRouteShortName(args.routeShortName);
		address.setLocality(args.locality);
		address.setLocalityShortName(args.localityShortName);
		address.setSublocality(args.sublocality);
		address.setPolitical(args.political);
		address.setPoliticalShortName(args.politicalShortName);
		address.setNeighborhood(args.neighborhood);
		address.setNeighborhoodShortName(args.neighborhoodShortName);
		address.setSublocalityShortName(args.sublocalityShortName);
		address.setAdministrativeAreaLevel1(args.administrativeAreaLevel1);
		address.setAdministrativeAreaLevel1ShortName(args.administrativeAreaLevel1ShortName);
		address.setAdministrativeAreaLevel2(args.administrativeAreaLevel2);
		address.setAdministrativeAreaLevel2ShortName(args.administrativeAreaLevel2ShortName);
		
		return address;
	}
	
//	@SuppressWarnings("unchecked")
	@Override
	public Address insertAddress(Address address,double distance){
		logger.debug("insertAddress " + address);
		LatitudeLongitude ll = new LatitudeLongitude(address.getLatitude(),address.getLongitude());
		Address dbAddress = (Address) getSession().get(Address.class, ll);
		if(dbAddress == null){
			getSession().persist(address);
		}
		
		return address;
		
//		Query query = getSession().getNamedQuery("Address.findNearby");
//		query.setDouble("latitude", address.getLatitude());
//		query.setDouble("longitude", address.getLongitude());
//		query.setDouble("distance", distance); //one
//		
//		List<Address> list = query.list();
//		if(list.size() > 0){
//			logger.debug("--->insertAddress found nearby addresses " + list.size() + ", the first address is " + list.get(0));
//			address = list.get(0);
//		}
//		else{
//			getSession().persist(address);
//		}
//		return address;
	}
	
	@Override
	public Address insertAddress(AddressArgs args, double distance){
		Address address = extractAddress(args);
		address.setCreatedOn(new Date());
		return insertAddress(address,distance);
	}
	@Override
	public UserAccount updateAccount(PlayerProfileArgs args){
//		logger.debug("updateAccount: " + args);
		UserAccount userAccount = (UserAccount)getSession().get(UserAccount.class, args.userAccountId);
//		logger.debug("updateAccount:userAccount: " + userAccount);
		if(userAccount != null){
			Address address = insertAddress(args,0.0001);
//			logger.debug("updateAccount:address: " + address);
			getSession().flush();
			TennisPlayerProfile profile = userAccount.getPlayerProfile();
//			logger.debug("updateAccount:profile: " + profile);
			if(profile == null){
				profile = newPlayerProfile(userAccount);
			}
			if(address != null){
				userAccount.setAddressLatitude(address.getLatitude());
				userAccount.setAddressLongitude(address.getLongitude());
			}
			
			profile.setHand(args.hand);
			profile.setAvailableWeekdayAfternoon(args.weekdayAvailabilityAfternoonCheck);
			profile.setAvailableWeekdayEvening(args.weekdayAvailabilityEveningCheck);
			profile.setAvailableWeekdayMorning(args.weekdayAvailabilityMorningCheck);
			profile.setAvailableWeekendAfternoon(args.weekendAvailabilityAfternoonCheck);
			profile.setAvailableWeekendEvening(args.weekendAvailabilityEveningCheck);
			profile.setAvailableWeekendMorning(args.weekendAvailabilityMorningCheck);
			profile.setLevelOfPlay(args.levelOfPlay);
			profile.setPlayDoubles(args.doublesCheck);
			profile.setPlaySingles(args.singlesCheck);
			profile.setPlayFullMatch(args.fullMatchCheck);
			profile.setPlayPoints(args.pointsCheck);
			profile.setPlayHittingAround(args.HittingAroundCheck);
			
			//favorite courts
//			logger.debug("THESE ARE COURTS " + args.favouriteCourts);
			if(args.favouriteCourts != null && args.favouriteCourts.size() > 0){
				List<TennisCenter> list = new ArrayList<TennisCenter>();
				for(Long courtId : args.favouriteCourts){
					TennisCenter c = find(TennisCenter.class,courtId);
					if(c != null){
						list.add(c);
					}
				}
				profile.setFavouriteCourts(list);
			}
			
//			logger.debug("updateAccount:done!");
		}
		else{
			throw new EntityNotFoundException("User account could not be found in the system");
		}
		return userAccount;
	}
	//NOT YET COMPLETED
	@Override
	public UserAccount updateAccount(TeacherProfileForm form){
		logger.debug(form);
		return null;
//		UserAccount userAccount = (UserAccount)getSession().get(UserAccount.class, form.getEmail());
//		if(userAccount != null){
//			Address address = insertAddress(form,0.0001);
//			
//			TennisTeacherProfile profile = userAccount.getTeacherProfile();
//			if(profile == null){
//				profile = newTeacherProfile(userAccount);
//			}
//			if(address != null){
//				userAccount.setAddressLatitude(address.getLatitude());
//				userAccount.setAddressLongitude(address.getLongitude());
//			}
//			
//
//			profile.setAvailableWeekdayAfternoon(form.getWeekdayAvailabilityAfternoonCheck() != null);
//			profile.setAvailableWeekdayEvening(form.getWeekdayAvailabilityEveningCheck() != null);
//			profile.setAvailableWeekdayMorning(form.getWeekdayAvailabilityMorningCheck() != null);
//			profile.setAvailableWeekendAfternoon(form.getWeekendAvailabilityAfternoonCheck() != null);
//			profile.setAvailableWeekendEvening(form.getWeekendAvailabilityEveningCheck() != null);
//			profile.setAvailableWeekendMorning(form.getWeekendAvailabilityMorningCheck() != null);
//			
//			profile.setCertified(form.getCertified() != null);
//			profile.setCertifyingOrganization(form.getCertifyingOrganization());
//			profile.setClinicRates(Float.valueOf(form.getClinicRates()));
//			profile.setClub(form.getClub());
//			profile.setCurrency(form.getCurrency());
//			profile.setModifiedOn(new Date());
//			profile.setProfileImageFile(userAccount.getProfileImageFile());
//			profile.setProfileImageFileId(userAccount.getProfileImageFileId());
//			profile.setSpecialityAdults(form.getSpecialityAdults() != null);
//			profile.setSpecialityJuniors(form.getSpecialityJuniors() != null);
//			profile.setSpecialityTurnaments(form.getSpecialityTurnaments() != null);
//			profile.setUsptaCertified(form.getUsptaCertified() != null);
//			profile.setUsptrCertified(form.getUsptrCertified() != null);
//			profile.setYearsOfExperience(Float.valueOf(form.getYearsOfExperience()));
//		}
//		else{
//			throw new EntityNotFoundException("User account could not be found in the system");
//		}
//		return userAccount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAccount> listLogins() {
		Query query;
		try {
			query = getSession().getNamedQuery("UserAccount.selectAll");
			return query.list();
			
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
//	@Override
//	public Long loginAccountId(){
//		if(SecurityContextHolder.getContext().getAuthentication() != null){
//			Session s = getSession();
//			Query query = s.getNamedQuery("UserAccount.selectIdByEmail");
//			query.setString("email", SecurityContextHolder.getContext().getAuthentication().getName());
//			System.out.println("Principal " + SecurityContextHolder.getContext().getAuthentication().getName());
//			
//			return (Long)query.uniqueResult();
//		}
//		return null;
//	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Mate> scrollUserMates(ScrollByUserAccountIdArgs args){
		Query query = getSession().getNamedQuery("Mate.select");
		query.setLong("userAccountId", args.userAccountId);
		query.setFirstResult((int)args.firstResult);
		query.setMaxResults(args.maxResults);
		
		return query.list();
	}
	
	@Override
	public Long countUserMates(Long userAccountId){
		Query query = getSession().getNamedQuery("Mate.count");
		query.setLong("userAccountId", userAccountId);
		return (Long)query.uniqueResult();
	}
	
	@Override
	public Mate addMate(Long userAccountId, Long mateAccountId){
		Mate mate = new Mate();
		mate.setUserAccountId(userAccountId);
		mate.setMateAccountId(mateAccountId);
		mate.setCreatedOn(new Date());
		
		getSession().persist(mate);
		
		return mate;
	}
	
	@Override
	public Mate findMateByUserAndMateIds(Long userAccountId, Long mateAccountId){
		Query query = getSession().getNamedQuery("Mate.findByUserAndMate");
		query.setLong("userAccountId", userAccountId);
		query.setLong("mateAccountId", mateAccountId);
		
		return (Mate)query.uniqueResult();
	}

	//TODO
	//check form condition to choose whether to update player profile or teacher
	@Override
	public ImageFile updatePlayerProfileImage(UpdateProfileImageForm form) {
		Session s = getSession();
		ImageFile imageFile = null;
		
		if(form.getUserAccountId() != null){
			Query query = s.getNamedQuery("TennisPlayerProfile.findProfileImageFile");
			query.setLong("userAccountId", form.getUserAccountId());
			imageFile = (ImageFile)query.uniqueResult();
			if(imageFile != null){
				if(FileUtilities.delete(imageFile)){
					delete(imageFile);
				}
			}
			imageFile = new ImageFile();
			imageFile.setHeight(form.getImageMetaData().getHeight());
			imageFile.setWidth(form.getImageMetaData().getWidth());
			imageFile.setFormat(Format.valueOf(form.getImageMetaData().getFormat().toUpperCase()));
			imageFile.setFileName(form.getImageMetaData().getFileName());
			imageFile.setSize(form.getImageMetaData().getSize());
			imageFile.setCreatedOn(new Date());
			imageFile.setOwnerId(form.getUserAccountId());
			s.persist(imageFile);
			imageFile.setDirPath(form.getImageMetaData().getDirPath());
			
			query = s.getNamedQuery("TennisPlayerProfile.updateProfileImageFile");
			query.setLong("userAccountId", form.getUserAccountId());
			query.setLong("profileImageFileId", imageFile.getImageFileId());
		}
		
		return imageFile;
	}
	
	@Override
	public UserPost insertUserPost(MessageArgs args) {
		UserPost userPost = new UserPost();
		userPost.setContent(args.message);
		userPost.setPostedOn(args.createdOn);
		userPost.setToUserAccountId(args.toUserAccountId);
		userPost.setUserAccountId(args.userAccountId);
		userPost.setStatus(UserPost.Status.PENDING);
		getSession().persist(userPost);
		
		return userPost;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> scrollUserPosts(ScrollMessagesArgs args) {
		Query query = getSession().getNamedQuery("UserPost.selectByRecipient");
		query.setLong("userAccountId", args.userAccountId);
		query.setFirstResult((int)args.firstResult);
		query.setMaxResults(args.maxResults);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> scrollNotifications(ScrollMessagesArgs args) {
		Query query = getSession().getNamedQuery("Notification.selectByRecipient");
		query.setLong("userAccountId", args.userAccountId);
		query.setFirstResult((int)args.firstResult);
		query.setMaxResults(args.maxResults);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> scrollMateConversation(ScrollByMateAccountIdArgs args){
		Query query;
		if(args.startDate == null){
			query = getSession().getNamedQuery("UserPost.selectConversation");
			query.setLong("userAccountId", args.userAccountId);
			query.setLong("mateAccountId", args.mateAccountId);
			query.setFirstResult((int)args.firstResult);
			query.setMaxResults(args.maxResults);
		}
		else{
//			logger.debug("scrollMateConversation " + args.startDate);
			query = getSession().getNamedQuery("UserPost.selectConversationByDate");
			query.setLong("userAccountId", args.userAccountId);
			query.setLong("mateAccountId", args.mateAccountId);
			query.setFirstResult((int)args.firstResult);
			query.setMaxResults(args.maxResults);
			query.setTimestamp("startDate", args.startDate);
			
		}
//		query.setResultTransformer(new AliasToBeanResultTransformer(UserPostSelect.class));
		return query.list();
	}
	
	@Override
	public Long countUserPosts(Long userAccountId) {
		Query query = getSession().getNamedQuery("UserPost.countByRecipient");
		query.setLong("userAccountId", userAccountId);
		return (Long)query.uniqueResult();
	}

	@Override
	public void flush(){
		getSession().flush();
	}
	
	@Override
	public void persist(Object entity){
		getSession().persist(entity);
	}
	
	@Override
	public Object merge(Object entity){
		return getSession().merge(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> clazz, Serializable id) {
		return (T)getSession().get(clazz, id);
	}
	
	@Override
	public void delete(Object entity) {
		getSession().delete(entity);
	}
	
	@Override
	public TennisPlayerProfile findPlayerProfileByEmail(String email){
		Query query = getSession().getNamedQuery("TennisPlayerProfile.findByEmail");
		query.setString("email", email);
		
		return (TennisPlayerProfile)query.uniqueResult();
	}
	
	@Override
	public UserAccount findUserAccountByActivationToken(String activationToken){
		Query query = getSession().getNamedQuery("UserAccount.findByActivationTocken");
		query.setString("activationToken", activationToken);
		
		return (UserAccount)query.uniqueResult();
	}
	
	@Override
	public UserAccount findUserAccountByUsername(String username){
		Query query = getSession().getNamedQuery("UserAccount.findByUsername");
		query.setString("username",username);
		return (UserAccount)query.uniqueResult();
	}
	
	@Override
	public UserAccount findUserAccountByEmail(String email){
		Query query = getSession().getNamedQuery("UserAccount.findByEmail");
		query.setString("email",email);
		return (UserAccount)query.uniqueResult();
	}
	
	@Override
	public Long findUserAccountIdByEmail(String email){
//		Query query = getSession().getNamedQuery("UserAccount.findIdByEmail");
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("UserAccount.findIdByEmail");
		query.setString("email",email);
		Long id = (Long)query.uniqueResult();
		session.close();
		return id;
	}
	
	@Override
	public UserAccount activateUserAccount(String activationToken){
		UserAccount account = findUserAccountByActivationToken(activationToken);
		account.setStatus(AccountStatus.ACTIVE);
		account.setActivationTokenExpires(null);
		account.setActivationToken(null);
		return account;
	}
	
	@Override
	public void resetPassword(ResetPasswordForm form){
		logger.debug("resetPassword " + form);
		UserAccount userAccount = findUserAccountByActivationToken(form.getToken());
		if(userAccount == null || userAccount.getActivationTokenExpires() == null || userAccount.getActivationToken() == null){
			throw new NotAuthorizedException();
		}
		if(userAccount.getActivationTokenExpires().after(new Date())){
			userAccount.setPassword(form.getPassword());
			userAccount.setStatus(AccountStatus.ACTIVE);
		}
		else{
			throw new DateExpriedException();
		}
//		Query query = getSession().getNamedQuery("UserAccount.updatePassword");
//		query.setString("password", form.getPassword());
//		query.setString("email", form.getEmail());
//		return query.executeUpdate();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> findNearbyAddresses(Double latitude,Double longitude, Double distance){
		Query query = getSession().getNamedQuery("Address.findNearby");
		query.setDouble("latitude", latitude);
		query.setDouble("longitude", longitude);
		query.setDouble("distance", distance);
		query.setMaxResults(10);
		
		return query.list();	
	}
	
	@Override
	public Long countSearchTennisCenters(SearchTennisCourtsArgs args){
		logger.debug("countSearchTennisCenters " + args);
		
		StringBuilder surfaceSb = new StringBuilder();
		if(args.carpet){
			surfaceSb.append("t2.surface = 'CARPET'");
		}
		if(args.clay){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'CLAY'");
		}
		if(args.concrete){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'CONCRETE'");
		}
		if(args.grass){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'GRASS'");
		}
		if(args.hard){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'HARD'");
		}
		if(args.synthetic){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'SYNTHETIC'");
		}
		
		StringBuilder placementSb = new StringBuilder();
		if(args.indoor){
			placementSb.append("t2.placement = 'INDOOR'");
			
			if(args.outdoor){
				placementSb.append(" OR ");
			}
		}
		if(args.outdoor){
			placementSb.append("t2.placement = 'OUTDOOR'");
		}
		
		StringBuilder nameSb = new StringBuilder();
		if(args.courtName != null){
			nameSb.append("`name` LIKE CONCAT('%',:courtName,'%')");
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(DISTINCT t1.tennisCenterId) FROM tennis_centers AS t1 ");
		sb.append("LEFT JOIN tennis_courts t2 ON t1.tennisCenterId = t2.tennisCenterId ");
//		sb.append("LEFT JOIN image_files AS i ON t1.imageFileId = i.imageFileId ");
		sb.append("INNER JOIN addresses AS a ON a.latitude = t1.latitude AND a.longitude = t1.longitude ");
		
		if(nameSb.length() > 0 || placementSb.length() > 0 || surfaceSb.length() > 0){
			sb.append("WHERE ");
			if(nameSb.length() > 0){
				sb.append("(");
				sb.append(nameSb);
				sb.append(") ");
			}
			if(placementSb.length() > 0){
				if(nameSb.length() > 0) sb.append("AND ");
				sb.append("(");
				sb.append(placementSb);
				sb.append(") ");
			}
			if(surfaceSb.length() > 0){
				if(nameSb.length() > 0 || placementSb.length() > 0) sb.append("AND ");
				sb.append("(");
				sb.append(surfaceSb);
				sb.append(") ");
			}
		}
		
		logger.info("THE QUERY " + sb.toString());
		Query query = getSession().createSQLQuery(sb.toString());
		if(nameSb.length() > 0){
			query.setString("courtName", args.courtName);
		}
		
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TennisCourt> findCourts(Long tennisCenterId){
		Query query = getSession().getNamedQuery("TennisCourt.selectByTennisCenterId");
		query.setLong("tennisCenterId", tennisCenterId);
		
		return (List<TennisCourt>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourtSelect> searchTennisCenters(SearchTennisCourtsArgs args){
		logger.debug("searchTennisCenters " + args);
		
		StringBuilder surfaceSb = new StringBuilder();
		if(args.carpet){
			surfaceSb.append("t2.surface = 'CARPET'");
		}
		if(args.clay){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'CLAY'");
		}
		if(args.concrete){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'CONCRETE'");
		}
		if(args.grass){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'GRASS'");
		}
		if(args.hard){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'HARD'");
		}
		if(args.synthetic){
			if(surfaceSb.length() > 0) surfaceSb.append(" OR ");
			surfaceSb.append("t2.surface = 'SYNTHETIC'");
		}
		
		StringBuilder placementSb = new StringBuilder();
		if(args.indoor){
			placementSb.append("t2.placement = 'INDOOR'");
			
			if(args.outdoor){
				placementSb.append(" OR ");
			}
		}
		if(args.outdoor){
			placementSb.append("t2.placement = 'OUTDOOR'");
		}
		
		StringBuilder nameSb = new StringBuilder();
		if(args.courtName != null){
			nameSb.append("`name` LIKE CONCAT('%',:courtName,'%')");
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT t1.tennisCenterId AS tennisCenterId, t1.name AS `name`, t1.website AS website, a.latitude AS latitude, a.longitude AS longitude, a.locality AS locality,a.route AS route,t1.phoneNumber AS phoneNumber,a.administrativeAreaLevel1ShortName AS administrativeAreaLevel1,a.postalCode AS postalCode, i.fileName AS imageFile FROM tennis_centers AS t1 ");
		
		sb.append("LEFT JOIN tennis_courts t2 ON t1.tennisCenterId = t2.tennisCenterId ");
		sb.append("LEFT JOIN image_files AS i ON t1.imageFileId = i.imageFileId ");
		sb.append("INNER JOIN addresses AS a ON a.latitude = t1.latitude AND a.longitude = t1.longitude ");
		
		if(nameSb.length() > 0 || placementSb.length() > 0 || surfaceSb.length() > 0){
			sb.append("WHERE ");
			if(nameSb.length() > 0){
				sb.append("(");
				sb.append(nameSb);
				sb.append(") ");
			}
			if(placementSb.length() > 0){
				if(nameSb.length() > 0) sb.append("AND ");
				sb.append("(");
				sb.append(placementSb);
				sb.append(") ");
			}
			if(surfaceSb.length() > 0){
				if(nameSb.length() > 0 || placementSb.length() > 0) sb.append("AND ");
				sb.append("(");
				sb.append(surfaceSb);
				sb.append(") ");
			}
		}
		
		sb.append("ORDER BY geoDistance(t1.latitude,t1.longitude,:lat,:lng) ");
		sb.append("LIMIT :firstResult,:maxResults ");
		
		logger.info("THE QUERY " + sb.toString());
		Query query = getSession().createSQLQuery(sb.toString());
		query.setDouble("lat", args.latitude);
		query.setDouble("lng", args.longitude);
		query.setLong("firstResult", args.firstResult);
		query.setInteger("maxResults", args.maxResults);
		if(nameSb.length() > 0){
			query.setString("courtName", args.courtName);
		}
		
		query.setResultTransformer(new AliasToBeanResultTransformer(CourtSelect.class));
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourtSelect> nearbyTennisCenters(FindByLocationArgs args){
		logger.debug("scrollTennisCenters " + args);
		Query query = getSession().getNamedQuery("CourtSelect.scroll");
		query.setDouble("latitude", args.latitude);
		query.setDouble("longitude", args.longitude);
		query.setLong("firstResult", args.firstResult);
		query.setInteger("maxResults", args.maxResults);
		query.setDouble("maxDistance", args.maxDistance);
		
		List<CourtSelect> list = query.list();
//		for(CourtSelect c : list){
//			getSession().evict(c);
//			if(StringUtils.isNotEmpty(c.getImageFile())){
//				c.setImageFile(TennisSetAppUtils.courtImageFileUrl(c.getImageFile()));
//			}
//		}
		return list;
	}
	
	@Override
	public long countNearbyTennisCenters(Double latitude, Double longitude, Double distance){
		Query query = getSession().getNamedQuery("CourtSelect.count");
		query.setDouble("latitude", latitude);
		query.setDouble("longitude", longitude);
		query.setDouble("maxDistance", distance);
		return (Long)query.uniqueResult();
	}
	
	@Override
	public LatitudeLongitude userLatLng(String userEmail){
		Query query = getSession().getNamedQuery("LatitudeLongitude.findByUserEmail");
		query.setString("email", userEmail);
		return (LatitudeLongitude)query.uniqueResult();
	}
	
	@Override
	public LatitudeLongitude userLatLng(Long userAccountId){
		Query query = getSession().getNamedQuery("LatitudeLongitude.findByUserAccountId");
		query.setLong("userAccountId", userAccountId);
		return (LatitudeLongitude)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TennisCenter findTennisCourt(Double latitude, Double longitude){
		Query query = getSession().getNamedQuery("TennisCourt.findByLatLng");
		query.setDouble("latitude", latitude);
		query.setDouble("longitude", longitude);
		List<TennisCenter> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		else return null;
	}
	
	//TODO complete
	@Override
	public TennisCenter createTennisCenter(CreateTennisCenterArgs args){
		Session s = getSession();
		TennisCenter t = new TennisCenter();
		
		Address address = insertAddress(args,1d);
		t.setAddress(address);
		t.setLatitude(address.getLatitude());
		t.setLongitude(address.getLongitude());
		t.setName(args.tennisCenterName);
		t.setPhoneNumber(args.phoneNumber);
		t.setWebsite(args.website);
		t.setCreatedOn(args.createdOn);
		s.persist(t);
		
		
		//fetch google map image for the tennis center
		ImageMetaData meta = FileUtilities.storeCourtMapFromGoogle(address.getLatitude(), address.getLongitude());
		if(meta != null){
			ImageFile imageFile = new ImageFile();
			imageFile.setCreatedOn(new Date());
			imageFile.setDirPath(meta.getDirPath());
			imageFile.setFileName(meta.getFileName());
			imageFile.setFormat(ImageFile.Format.valueOf(meta.getFormat()));
			imageFile.setHeight(meta.getHeight());
			imageFile.setWidth(meta.getWidth());
//			imageFile.setOwnerId(f.get)
			imageFile.setSize(meta.getSize());
			
			persist(imageFile);
			t.setImage(imageFile);
		}
		
		//populate indoor courts
		List<TennisCourt> courts = new ArrayList<TennisCourt>();
		if(args.indoorSurface != null){
			for(int i = 0 ; i < args.indoorSurface.size() ; i++){
				TennisCourt court = new TennisCourt();
				court.setNumberOfCourts(Integer.valueOf(args.numberOfIndoorCourts.get(i)));
				court.setPlacement(TennisCourt.Placement.INDOOR);
				court.setTennisCenter(t);
				court.setSurface(TennisCourt.Surface.valueOf(args.indoorSurface.get(i)));
				courts.add(court);
				s.persist(court);
			}
		}
		
		//populate outdoor courts
		if(args.outdoorSurface != null){
			for(int i = 0 ; i < args.outdoorSurface.size() ; i++){
				TennisCourt court = new TennisCourt();
				court.setNumberOfCourts(Integer.valueOf(args.numberOfOutdoorCourts.get(i)));
				court.setPlacement(TennisCourt.Placement.OUTDOOR);
				court.setTennisCenter(t);
				
				court.setSurface(TennisCourt.Surface.valueOf(args.outdoorSurface.get(i)));
				courts.add(court);
				s.persist(court);
			}
		}
		if(courts.size() > 0){
			t.setTennisCourts(courts);
//			System.out.println("courts.size " + courts.size());
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarEventSelect> getCalendarEvents(CalendarEventsArgs args){
		Query query = getSession().getNamedQuery("CalendarEventSelect.selectByUserAccountId");
		query.setDate("fromDate", args.fromDate);
		query.setDate("toDate", args.toDate);
		query.setLong("userAccountId",args.userAccountId);
		
		return query.list();
	}
	
	@Override
	public CalendarEventSelect getNextCalendarEvent(CalendarEventsArgs args){
		Query query = getSession().getNamedQuery("CalendarEventSelect.selectNextEvent");
		query.setDate("fromDate", args.fromDate);
		query.setLong("userAccountId",args.userAccountId);
		
		return (CalendarEventSelect)query.uniqueResult();
	}
	
//	@Override
//	public List<MatchSelect> scrollPlayerMatches(PlayerCalendarArgs args) {
//		List<MatchSelect> list = Lists.newArrayList();
//		list.addAll(scrollPlayerInitiatedMatches(args));
//		list.addAll(scrollMatchMembers(args));
//		
//		Collections.sort(list, new Comparator<MatchSelect>() {
//			@Override
//			public int compare(MatchSelect o1, MatchSelect o2) {
//				return o1.getStartDate().compareTo(o2.getStartDate());
//			}
//		});
//		if(args.maxResults < list.size()){
//			return list.subList(0, args.maxResults);
//		}
//		else{
//			return list;
//		}
//	}
//	
//	@Override
//	public Long countPlayerMatches(Long userAccountId) {
//		return countMatchMembers(userAccountId) + countPlayerInitiatedMatches(userAccountId);
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MatchSelect> scrollPlayerInitiatedMatches(PlayerCalendarArgs args) {
//		Session s = getSession();
//		Query query = s.getNamedQuery("MatchSelect.selectByUserId");
//		query.setLong("orgenizerId", args.userAccountId);
//		query.setFirstResult((int)args.firstResult);
//		query.setMaxResults(args.maxResults);
//		query.setResultTransformer(new AliasToBeanResultTransformer(MatchSelect.class));
//		return query.list();
//	}
//	
//	@Override
//	public List<MatchSelect> scrollMatchMembers(PlayerCalendarArgs args) {
//		Session s = getSession();
//		Query query = s.getNamedQuery("MatchSelect.selectMembersByUserId");
//		query.setLong("userAccountId", args.userAccountId);
//		query.setFirstResult((int)args.firstResult);
//		query.setMaxResults(args.maxResults);
//		query.setResultTransformer(new AliasToBeanResultTransformer(MatchSelect.class));
//		return query.list();
//	}
//
//	@Override
//	public Long countMatchMembers(Long userAccountId) {
//		Session s = getSession();
//		Query query = s.getNamedQuery("MatchSelect.countMembersByUserId");
//		query.setLong("userAccountId", userAccountId);
//		return (Long)query.uniqueResult();
//	}
//
//	@Override
//	public Long countPlayerInitiatedMatches(Long userAccountId) {
//		Session s = getSession();
//		Query query = s.getNamedQuery("MatchSelect.countByUserId");
//		query.setLong("orgenizerId", userAccountId);
//		return (Long)query.uniqueResult();
//	}

	@Override
	public Match createMatch(CreateMatchArgs args){
		Match match = new Match();
		match.setOrgenizerId(args.orgenizerId);
//		match.setName(args.name);
		match.setTennisCenterId(args.tennisCenterId);
		match.setLevelOfPlayMin(args.levelOfPlayMin);
		match.setLevelOfPlayMax(args.levelOfPlayMax);
		
		match.setPlayDoubles(args.playDoubles);
		match.setPlayFullMatch(args.playFullMatch);
		match.setPlayHittingAround(args.playHittingAround);
		match.setPlayPoints(args.playPoints);
		match.setPlaySingles(args.playSingles);
		match.setVisibility(args.visibility);
		
		match.setStartDate(args.startTime);
		match.setEndDate(args.endTime);
		
		persist(match);
		
		return match;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MatchSelect> findNearbyMatches(FindByLocationArgs args) {
//		
//		Query query = getSession().getNamedQuery("MatchSelect.scroll");
//		query.setDouble("latitude", args.latitude);
//		query.setDouble("longitude", args.longitude);
//		query.setDouble("distance", args.distance);
//
//		query.setLong("firstResult", args.offset);
//		query.setInteger("maxResults", args.maxResults);
//		return query.list();		
//	}
	
	
	
	@Override
	public Long countNearbyMatches(Double latitude,Double longitude, Double distance) {
		Query query = getSession().getNamedQuery("Match.count");
		query.setDouble("latitude", latitude);
		query.setDouble("longitude", longitude);
		query.setDouble("distance", distance);
		return (Long)query.uniqueResult();		
	}
	
	@Override
	public boolean matchMemberExists(Long matchId, Long userAccountId) {
		Query query = getSession().getNamedQuery("MatchMember.countById");
		query.setLong("matchId", matchId);
		query.setLong("userAccountId", userAccountId);
		
		return ((Long)query.uniqueResult()) > 0;
	}

	@Override
	public Long countNearbyMates(Double latitude,Double longitude, Double distance) {
		Query query = getSession().getNamedQuery("UserAccount.countNearBy");
		
		query.setDouble("latitude", latitude);
		query.setDouble("longitude", longitude);
		query.setDouble("distance", distance);
		
		return (Long)query.uniqueResult();
	}
	
	@Override
	public Long countNearbyTeachers(Double latitude,Double longitude, Double distance) {
		Query query = getSession().getNamedQuery("TeacherSelect.count");
		query.setDouble("latitude", latitude);
		query.setDouble("longitude", longitude);
		query.setDouble("maxDistance", distance);
		return (Long)query.uniqueResult();
	}

	@Override
	public List<TeacherSelect> findNearbyTennisTeachers(FindByLocationArgs args) {
		logger.debug("findNearbyTennisTeachers " + args);
		Query query = getSession().getNamedQuery("TeacherSelect.nearby");
		query.setDouble("latitude", args.latitude);
		query.setDouble("longitude", args.longitude);
		query.setLong("firstResult", args.firstResult);
		query.setInteger("maxResults", args.maxResults);
		query.setDouble("maxDistance", args.maxDistance);
		
		List<TeacherSelect> list = query.list();
		return list;
	}

	@Override
	public MatchSelect findMatch(Long matchId){
		Session s = getSession();
		Query query = s.getNamedQuery("MatchSelect.find");
		query.setLong("matchId", matchId);
		query.setResultTransformer(new AliasToBeanResultTransformer(MatchSelect.class));
		MatchSelect match = (MatchSelect)query.uniqueResult();
		
		return match;
	}
	
	@Override
	public List<MatchMember> findMatchMembers(Long matchId){
		Session s = getSession();
		
		Query query = s.getNamedQuery("MatchMember.selectByMatchId");
		query.setLong("matchId", matchId);
		return query.list(); 
	}
	
	@Override
	public MatchMember joinMatch(Long matchId, Long userAccountId){
		Session s = getSession();
		MatchMember matchMember = new MatchMember();
		matchMember.setMatchId(matchId);
		matchMember.setUserAccountId(userAccountId);
		matchMember.setJoinedOn(new Date());
		s.persist(matchMember);
		
		return matchMember;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Match> searchMatches(SearchMatchesArgs args){
		Session s = getSession();
		Criteria criteria = s.createCriteria(Match.class);
//		criteria.setProjection(Projections.sqlProjection("SQRT(2)", new String[]{"distance"}, new Type[]{org.hibernate.type.DoubleType.INSTANCE}));
		criteria.setProjection(Projections.sqlProjection("SQRT(2) AS distance", new String[]{}, new Type[]{}));
		//--start/end time
		if(args.startTime != null){
			criteria.add(Property.forName("start").ge(args.startTime));
		}
		if(args.endTime != null){
			criteria.add(Property.forName("end").le(args.endTime));
		}
		
		//--type of play
		criteria.add(Property.forName("playDoubles").eq(args.playDoubles));
		criteria.add(Property.forName("playSingles").eq(args.playSingles));
		criteria.add(Property.forName("playPoints").eq(args.playPoints));
		criteria.add(Property.forName("playFullMatch").eq(args.playFullMatch));
		criteria.add(Property.forName("playHittingAround").eq(args.playHittingAround));
		
		//--level of play
		if(args.levelOfPlayMin != null){
			criteria.add(Property.forName("levelOfPlayMin").ge(args.levelOfPlayMin));
		}
		if(args.levelOfPlayMax != null){
			criteria.add(Property.forName("levelOfPlayMax").le(args.levelOfPlayMax));
		}
		
		//--location
		//changes the join from 'left outer join' to 'inner join'
		//but the native query does not realy have the 'c' alias
		criteria.createAlias("tennisCenter", "c"); //inner join by default
		criteria.add(Restrictions.sqlRestriction("SQRT(POW(111 * (latitude - " + args.latitude + "), 2) + POW(111 * (" + args.longitude + " - longitude) * COS(latitude / 57.29577951308232), 2)) < " + args.distance));
//		criteria.addOrder(Order.)
		
		/*
		 c.createAlias("dokument.role", "role"); // inner join by default
c.createAlias("role.contact", "contact");
c.add(Restrictions.eq("contact.lastName", "Test"));
		 */
//		System.out.println("list size: "  + criteria.list().size());
//		List<MatchSelect> list = null;
		
		return criteria.list();
	}
	
	@Override
	public Long countSearchMatchItems(SearchMatchesArgs args){
		Session s = getSession();
		
		Query query = s.createSQLQuery("CALL countSearchMatches(:latitude,:longitude,:distance);");
		query.setDouble("latitude", args.latitude);
		query.setDouble("longitude", args.longitude);
		query.setDouble("distance", args.distance);
		
		return (Long)query.uniqueResult();
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MatchSelect> searchMatchItems(SearchMatchesArgs args){
		Session s = getSession();
		Query query = s.getNamedQuery("MatchSelect.search");
		query.setDouble("latitude", args.latitude);
		query.setDouble("longitude", args.longitude);
		query.setDouble("distance", args.distance);
		query.setBoolean("playSingles", args.playSingles);
		query.setBoolean("playDoubles", args.playDoubles);
		query.setBoolean("playPoints", args.playPoints);
		query.setBoolean("playFullMatch", args.playFullMatch);
		query.setBoolean("playHittingAround", args.playHittingAround);
		query.setFloat("levelOfPlayMin", args.levelOfPlayMin);
		query.setFloat("levelOfPlayMax", args.levelOfPlayMax);
		query.setLong("firstResult", args.firstResult);
		query.setInteger("maxResults", args.maxResults);
		logger.debug("Searching matches..." + args);
		return query.list();
	}
	
	@Override
	public Long countSearchMateByName(String nameOrEmail){
		Session s = getSession();
		
		Query query = s.getNamedQuery("MateSelect.countMatesByNameOrEmail");
		query.setString("nameOrEmail", nameOrEmail);
		
		return ((CountEntity)query.uniqueResult()).getCount();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MateSelect> searchMateItems(SearchMatesByNameArgs args){
		Query query = getSession().getNamedQuery("MateSelect.searchMatesByNameOrEmail");
		query.setString("nameOrEmail", args.nameOrEmail);
		
		query.setLong("firstResult", args.firstResult);
		query.setInteger("maxResults", args.maxResults);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MateSelect> searchMateItems(SearchMatesArgs args) {
		logger.debug("searchMateItems " + args);
		Session s = getSession();
		Query query = s.getNamedQuery("MateSelect.search");
		query.setDouble("latitude", args.latitude);
		query.setDouble("longitude", args.longitude);
		query.setDouble("distance", args.distance);
		query.setBoolean("playSingles", args.playSingles);
		query.setBoolean("playDoubles", args.playDoubles);
		query.setBoolean("playPoints", args.playPoints);
		query.setBoolean("playFullMatch", args.playFullMatch);
		query.setBoolean("playHittingAround", args.playHittingAround);
		query.setFloat("levelOfPlayMin", args.levelOfPlayMin);
		query.setFloat("levelOfPlayMax", args.levelOfPlayMax);
		query.setLong("firstResult", args.firstResult);
		query.setInteger("maxResults", args.maxResults);
//		logger.debug("Searching mates..." + args);
		List<MateSelect> list = query.list();

		return list;
	}	
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MatchSelect> searchMatchesItems(SearchMatchesArgs args){
//		Session s = getSession();
//		Criteria criteria = s.createCriteria(Match.class);
//		
//		ProjectionList l = Projections.projectionList();
//		l.add(Projections.sqlProjection("SQRT(POW(111 * (c1_.latitude - " + args.latitude + "), 2) + POW(111 * (" + args.longitude + " - c1_.longitude) * COS(c1_.latitude / 57.29577951308232), 2)) < " + args.distance + " AS distance", new String[]{"distance"}, new Type[]{org.hibernate.type.DoubleType.INSTANCE}));
//		
//		l.add(Projections.property("matchId"),"matchId");
//		l.add(Projections.property("name"),"name");
//		l.add(Projections.property("playSingles"),"playSingles");
//		l.add(Projections.property("playDoubles"),"playDoubles");
//		l.add(Projections.property("playFullMatch"),"playFullMatch");
//		l.add(Projections.property("playPoints"),"playPoints");
//		l.add(Projections.property("u.firstName"),"orgenizerFirstName");
//		l.add(Projections.property("u.lastName"),"orgenizerLastName");
//		l.add(Projections.property("playHittingAround"),"playHittingAround");
//		l.add(Projections.property("visibility"),"visibility");
//		
//		l.add(Projections.property("c.latitude"),"latitude");
//		l.add(Projections.property("c.longitude"),"longitude");
//		
//		l.add(Projections.property("c.name"),"tennisCenterName");
//		l.add(Projections.property("a.localityShortName"),"address");
//		l.add(Projections.property("levelOfPlayMin"),"levelOfPlayMin");
//		l.add(Projections.property("levelOfPlayMax"),"levelOfPlayMax");
//		l.add(Projections.property("start"),"start");
//		
//		
//		criteria.setProjection(l);
//		//--start/end time
//		if(args.startTime != null){
//			criteria.add(Property.forName("start").ge(args.startTime));
//		}
//		if(args.endTime != null){
//			criteria.add(Property.forName("end").le(args.endTime));
//		}
//		
//		//--type of play, OR between
//		criteria.add(Restrictions.disjunction()
//		.add(Property.forName("playDoubles").eq(args.playDoubles))
//		.add(Property.forName("playSingles").eq(args.playSingles))
//		.add(Property.forName("playPoints").eq(args.playPoints))
//		.add(Property.forName("playFullMatch").eq(args.playFullMatch))
//		.add(Property.forName("playHittingAround").eq(args.playHittingAround)));
//		
////		criteria.add(Property.forName("distance").le(args.distance));
//		//--level of play
//		if(args.levelOfPlayMin != null){
//			criteria.add(Property.forName("levelOfPlayMin").ge(args.levelOfPlayMin));
//		}
//		if(args.levelOfPlayMax != null){
//			criteria.add(Property.forName("levelOfPlayMax").le(args.levelOfPlayMax));
//		}
//		
//		//--location
//		//changes the join from 'left outer join' to 'inner join'
//		//but the native query does not realy have the 'c' alias
//		criteria.createAlias("tennisCenter", "c"); //inner join by default
////		l.add(Projections.sqlProjection("SQRT(POW(111 * (" + c. + ".latitude - " + args.latitude + "), 2) + POW(111 * (" + args.longitude + " - " + c.getAlias() + ".longitude) * COS(" + c.getAlias() + ".latitude / 57.29577951308232), 2)) < " + args.distance + " AS distance", new String[]{"distance"}, new Type[]{org.hibernate.type.DoubleType.INSTANCE}));
//		
//		criteria.createAlias("orgenizer", "u"); //inner join by default
//		criteria.createAlias("c.address", "a");
//		criteria.setResultTransformer(new AliasToBeanResultTransformer(MatchSelect.class));
//		criteria.addOrder(new Order("distance",true){
//			private static final long serialVersionUID = 1L;
//			
//			@Override
//			public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
//		        return "distance ASC";
//		    }
//		});
//		criteria.setMaxResults(args.maxResults);
//		criteria.setFirstResult(args.offset.intValue());
//		return criteria.list();
//	}


	@Override
	public List<?> test(SearchMatchesArgs args){
		Query query = getSession().getNamedQuery("MatchSelect.test");
		return query.list();
	}	
	
	@Override
	public Long testScalar(){
		Query query = getSession().getNamedQuery("MatchSelect.test");
		logger.debug("testScalar: " + query.list());
		return 0l;
	}	

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MatchSelect> searchMatchesItems_(SearchMatchesArgs args){
//		Session s = getSession();
//		
//		List<String> restrictions = new ArrayList<String>();
//		
//		//--start/end time
//		if(args.startTime != null){
//			restrictions.add("start >= '" + TennisSetAppUtils.dateFormat.format(args.startTime) + "'");
//			
//		}
//		if(args.endTime != null){
//			restrictions.add("end <= '" + TennisSetAppUtils.dateFormat.format(args.endTime) + "'");
//		}
//		
//		//--type of play
//		restrictions.add("playDoubles = " + args.playDoubles);
//		restrictions.add("playSingles = " + args.playSingles);
//		restrictions.add("playPoints = " + args.playPoints);
//		restrictions.add("playFullMatch = " + args.playFullMatch);
//		restrictions.add("playHittingAround = " + args.playHittingAround);
//		
//		//--level of play
//		if(args.levelOfPlayMin != null){
//			restrictions.add("levelOfPlayMin >= " + args.levelOfPlayMin);
//		}
//		if(args.levelOfPlayMax != null){
//			restrictions.add("levelOfPlayMax <= " + args.levelOfPlayMax);
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("SELECT m.matchId, m.name, m.playSingles, m.playDoubles, m.playFullMatch, m.playPoints, ");
//		sb.append("u.firstName as orgenizerFirstName, u.lastName as orgenizerLastName, ");
//		sb.append("c.name as tennisCenterName, a.localityShortName as address,");
//		sb.append("m.levelOfPlayMin, m.levelOfPlayMax, m.start, ");
//		sb.append("m.playHittingAround, m.visibility, a.latitude, a.longitude, ");
//		sb.append("SQRT(POW(111 * (a.latitude - :latitude), 2) + POW(111 * (:longitude - a.longitude) * COS(a.latitude / 57.29577951308232), 2)) AS distance ");
//		sb.append("FROM Addresses AS a ");
//		sb.append("INNER JOIN tennis_centers AS c ON c.latitude = a.latitude AND c.longitude = a.longitude ");
//		sb.append("INNER JOIN Matches AS m ON m.tennisCenterId = c.tennisCenterId ");
//		sb.append("INNER JOIN UserAccounts AS u ON u.userAccountId = m.orgenizerId ");
//		if(restrictions.size() > 0){
//			sb.append("WHERE ");
//			for(int i = 0 ; i < restrictions.size() ; i++){
//				sb.append(restrictions.get(i));
//				if(i+1 < restrictions.size()){
//					sb.append(" AND ");
//				}
//				else{
//					sb.append(" ");
//				}
//			}
//		}
//		sb.append("HAVING distance <= " + args.distance + " ");
//		sb.append("ORDER BY distance ");
//		sb.append("LIMIT :offset,:maxResults");
//		
//		Query query = s.createSQLQuery(sb.toString());
//		query.setResultTransformer(new AliasToBeanResultTransformer(MatchSelect.class));
//		query.setDouble("latitude", args.latitude);
//		query.setDouble("longitude", args.longitude);
//		query.setLong("offset", args.offset);
//		query.setInteger("maxResults", args.maxResults);
//		
//		return query.list();
//	}
	
}
