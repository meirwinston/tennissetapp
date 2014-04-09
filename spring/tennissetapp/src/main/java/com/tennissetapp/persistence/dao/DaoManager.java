package com.tennissetapp.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
import com.tennissetapp.forms.ResetPasswordForm;
import com.tennissetapp.forms.TeacherProfileForm;
import com.tennissetapp.forms.UpdateProfileImageForm;
import com.tennissetapp.persistence.entities.Address;
import com.tennissetapp.persistence.entities.CalendarEventSelect;
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
import com.tennissetapp.persistence.entities.UserPostSelect;

public interface DaoManager {
	void setSessionFactory(SessionFactory sessionFactory);
	Session getSession();//for tests
	Session getNewSession(); //For tests
	
	void evict(Object entity);
	UserAccount createUserAccount(SignupArgs args);
	boolean usernameExists(String username);
	UserPost insertUserPost(MessageArgs args);
	List<UserPost> scrollUserPosts(ScrollMessagesArgs args);
	List<UserPost> scrollMateConversation(ScrollByMateAccountIdArgs args);
	Long countUserPosts(Long userAccountId);
	
	List<Notification> scrollNotifications(ScrollMessagesArgs args);
	
	UserAccount updateAccount(PlayerProfileArgs args);
	UserAccount updateAccount(TeacherProfileForm form);
	List<UserAccount> listLogins();
	
	Address insertAddress(Address address, double distance);
	Address insertAddress(AddressArgs args, double distance);
	ImageFile updatePlayerProfileImage(UpdateProfileImageForm img);
	void persist(Object entity);
	Object merge(Object entity);
	
	<T> T find(Class<T> clazz, Serializable id);
	void delete(Object entity);
	
//	TennisPlayerProfile createPlayerProfile(CreateProfileArgs args);
	TennisTeacherProfile createTeacherProfile(CreateProfileArgs args);
	
	TennisPlayerProfile findPlayerProfileByEmail(String email);
	UserAccount activateUserAccount(String activationToken);
	UserAccount findUserAccountByActivationToken(String activationToken);
	UserAccount findUserAccountByUsername(String username);
	UserAccount findUserAccountByEmail(String email);
	Long findUserAccountIdByEmail(String email);
	
	void resetPassword(ResetPasswordForm form);
	Long countNearbyMatches(Double latitude, Double longitude, Double distance);
	Long countNearbyMates(Double latitude,Double longitude, Double distance);
	Long countNearbyTeachers(Double latitude, Double longitude, Double distance);
	List<TeacherSelect> findNearbyTennisTeachers(FindByLocationArgs args);
	List<TeacherSelect> searchTennisTeachers(SearchTennisTeachersArgs args);
	Long countSearchTennisTeachers(SearchTennisTeachersArgs args);
	List<TeacherSelect> searchTennisTeachers(SearchByNameOrEmailArgs args);
	Long countTennisTeachers(SearchByNameOrEmailArgs args);
	
	List<Match> searchMatches(SearchMatchesArgs args);
	List<MatchSelect> searchMatchItems(SearchMatchesArgs args);
	MatchSelect findMatch(Long matchId);
	List<MatchMember> findMatchMembers(Long matchId);
	MatchMember joinMatch(Long matchId, Long userAccountId);
	Long countSearchMatchItems(SearchMatchesArgs args);
	List<MateSelect> searchMateItems(SearchMatesArgs args);
	List<MateSelect> searchMateItems(SearchMatesByNameArgs args);
	Long countSearchMateByName(String usernameOrEmail);
	
	List<?> test(SearchMatchesArgs args);
	Long testScalar();
//	List<MatchSelect> scrollPlayerMatches(ScrollMatchesArgs args);
//	List<TennisCourt> findNearbyCourts(Double latitude, Double longitude, Double distance);
	List<Address> findNearbyAddresses(Double latitude, Double longitude, Double distance);
	
	List<TennisCourt> findCourts(Long tennisCenterId);
	List<CourtSelect> searchTennisCenters(SearchTennisCourtsArgs args);
	Long countSearchTennisCenters(SearchTennisCourtsArgs args);
	
	TennisCenter findTennisCourt(Double latitude, Double longitude);
	List<CourtSelect> nearbyTennisCenters(FindByLocationArgs args);
	long countNearbyTennisCenters(Double latitude, Double longitude, Double distance);

	TennisCenter createTennisCenter(CreateTennisCenterArgs f);
	
	LatitudeLongitude userLatLng(String userEmail);
	LatitudeLongitude userLatLng(Long userAccountId);
	
	Match createMatch(CreateMatchArgs args);
	boolean matchMemberExists(Long matchId, Long userAccountId);
	void flush();
	
	
	//----------------------- MOBILE --------
	List<Mate> scrollUserMates(ScrollByUserAccountIdArgs args);
	Long countUserMates(Long userAccountId);
	TennisPlayerProfile createPlayerProfile(CreatePlayerProfileArgs args);
	TennisPlayerProfile updateTennisDetails(UpdateTennisDetailsArgs args);
	UserAccount updateAccountPrimaryFields(UserAccountPrimaryArgs args);
	Mate addMate(Long userAccountId, Long mateAccountId);
	Mate findMateByUserAndMateIds(Long userAccountId, Long mateAccountId);
	
	List<TeacherSelect> findFavoriteTeachers(ScrollByUserAccountIdArgs args);
	FavoriteTeacher createFavoriteTeacher(CreateFavoriteTeacherArgs args);	
	int removeFavoriteTeacher(Long userAccountId, Long teacherProfileId);
	int deleteFavoriteTeachers(Long userAccountId);
	
	//---
//	List<MatchSelect> scrollPlayerInitiatedMatches(PlayerCalendarArgs args);
//	Long countPlayerInitiatedMatches(Long userAccountId);
//	List<MatchSelect> scrollMatchMembers(PlayerCalendarArgs args);
//	Long countMatchMembers(Long userAccountId);
//	
//	List<MatchSelect> scrollPlayerMatches(PlayerCalendarArgs args);
//	Long countPlayerMatches(Long userAccountId);
	
	//---
	
	List<CalendarEventSelect> getCalendarEvents(CalendarEventsArgs args);
	CalendarEventSelect getNextCalendarEvent(CalendarEventsArgs args);
}