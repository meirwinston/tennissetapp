package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tennissetapp.args.CreatePlayerProfileArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.args.UpdateTennisDetailsArgs;
import com.tennissetapp.auth.AppUserDetails;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.forms.CreatePlayerProfileForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.forms.UpdateTennisDetailsForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.Address;
import com.tennissetapp.persistence.entities.Mate;
import com.tennissetapp.persistence.entities.MateSelect;
import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.PlayerProfileServiceValidator;

//MOBILE
@Controller
public class PlayerProfileService extends AbstractService{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@Inject
	protected AuthenticationManager authenticationManager;
	
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PlayerProfileServiceValidator());
    }
	
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/profile/mates"}, method = {RequestMethod.GET})
	@Transactional
	public ServiceResponse userMates(@Valid ScrollForm form, Principal principal)
	throws IOException {
		ServiceResponse response = new ServiceResponse();
		
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		
		ScrollByUserAccountIdArgs args = form.getArguments(ScrollByUserAccountIdArgs.class);
		args.userAccountId = TennisSetAppUtils.cast(principal).getUserAccountId();
		
		List<Mate> mateList = daoManager.scrollUserMates(args);
		
		List<MateSelect> list = Lists.newArrayListWithCapacity(mateList.size());
		for(Mate mate : mateList){
			MateSelect m = new MateSelect();
			m.setFirstName(mate.getMateAccount().getFirstName());
			m.setLastName(mate.getMateAccount().getLastName());
			if(mate.getMateAccount().getAddress() != null){
				m.setCountry(mate.getMateAccount().getAddress().getCountry());
				m.setAdministrativeAreaLevel1ShortName(mate.getMateAccount().getAddress().getAdministrativeAreaLevel1ShortName());
			}
			m.setLevelOfPlay(mate.getMateAccount().getPlayerProfile().getLevelOfPlay());
			m.setUserAccountId(mate.getMateAccount().getUserAccountId());
			m.setProfilePhoto(TennisSetAppUtils.extractProfileImageUrl(mate.getMateAccount().getPlayerProfile()));
			list.add(m);
		}
		response.put("list", list);
		
		return response;
	}
	
//	static class MateSelect{
//		public String firstName;
//		public String lastName;
//		public Long userAccountId;
//		public String country;
//		public String administrativeAreaLevel1;
//		public Float levelOfPlay;
//		public String profileImageUrl;
//	}
	
	/**
	 * here you must be logged in
	 * during the process of signing up perform a complete login,
	 * if the user has no accounts at all direct him to create account only 
	 * @param principal
	 * @return
	 * @throws IOException
	 */
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/profile/player"}, method = {RequestMethod.GET})
	@Transactional
	public ServiceResponse playerProfile(@RequestParam(required=false, value="userAccountId") String playerProfileIdParam, Principal principal)
	throws IOException {
		ServiceResponse response = new ServiceResponse();
//		logger.debug("playerProfile---------------" + playerProfileIdParam);
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		UserAccount userAccount = null;
		if(playerProfileIdParam != null){
			try {
				Long playerProfileId = Long.valueOf(playerProfileIdParam);
				userAccount = daoManager.find(UserAccount.class, playerProfileId);
			} catch (Exception exp) {
			}
		}
		else{
			AppUserDetails auth = TennisSetAppUtils.cast(principal);
			userAccount = daoManager.find(UserAccount.class, auth.getUserAccountId());	
		}
//		logger.debug("playerProfile " + principal);
		if(userAccount != null){
			TennisPlayerProfile profile = userAccount.getPlayerProfile();
			Address address = userAccount.getAddress();
			if(userAccount == null || profile == null){
				throw new NotAuthorizedException("No player profile exists under your name");
			}
			Map<String,Object> model = Maps.newHashMap();
			model.put("firstName", userAccount.getFirstName());
			model.put("lastName", userAccount.getLastName());
			
			model.put("attendance", profile.getAttendance());
			model.put("punctuality", profile.getPunctuality());
			model.put("tennisLevel", profile.getTennisLevel());
			model.put("country", address.getCountry());
			model.put("locality", address.getLocality());
			model.put("administrativeAreaLevel1", address.getAdministrativeAreaLevel1());
			model.put("playSingles", profile.getPlaySingles());
			model.put("playDoubles", profile.getPlayDoubles());
			model.put("playFullMatch", profile.getPlayFullMatch());
			model.put("playHittingAround", profile.getPlayHittingAround());
			model.put("playPoints", profile.getPlayPoints());
			model.put("availableWeekendMorning", profile.getAvailableWeekendMorning());
			model.put("availableWeekendAfternoon", profile.getAvailableWeekendAfternoon());
			model.put("availableWeekendEvening", profile.getAvailableWeekendEvening());
			model.put("availableWeekdayMorning", profile.getAvailableWeekdayMorning());
			model.put("availableWeekdayAfternoon", profile.getAvailableWeekdayAfternoon());
			model.put("availableWeekdayEvening", profile.getAvailableWeekdayEvening());
			model.put("levelOfPlay", profile.getLevelOfPlay());
			String profileUrl = TennisSetAppUtils.extractProfileImageUrl(profile);
			if(profileUrl != null){
				model.put("profileImageUrl",profileUrl);
			}
			response.put("profile", model);	
		}
		
		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/profile/player/create"}, method = {RequestMethod.POST})
	public ServiceResponse createProfile(@Valid CreatePlayerProfileForm form, BindingResult result,HttpServletRequest request,Principal principal)
	throws IOException {
		logger.debug("createProfile: " + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		CreatePlayerProfileArgs args = form.getArguments(CreatePlayerProfileArgs.class);
		AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
		args.userAccountId = userDetails.getUserAccountId();
		if(form.getProfileFileItemId() != null){
			args.fileItem = (FileItem)request.getSession().getAttribute(form.getProfileFileItemId());
		}
		
		logger.debug("Create Player Profile Args: " + args);
		TennisPlayerProfile profile = daoManager.createPlayerProfile(args);
		request.getSession().removeAttribute(form.getProfileFileItemId());

		response.put("userAccountId", profile.getUserAccountId());		
		
		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/profile/player/updateTennisDetails"}, method = {RequestMethod.POST})
	public ServiceResponse updateTennisDetails(@Valid UpdateTennisDetailsForm form, BindingResult result,Principal principal)
	throws IOException {
		logger.debug("updateTennisDetails: " + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		UpdateTennisDetailsArgs args = form.getArguments(UpdateTennisDetailsArgs.class);
		AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
		args.userAccountId = userDetails.getUserAccountId();
		
		logger.debug("Create Player Profile Args: " + args);
		TennisPlayerProfile profile = daoManager.updateTennisDetails(args);

		response.put("userAccountId", profile.getUserAccountId());		
		
		return response;
	}

	
	/**
	 * check if player profile exists
	 * @param principal
	 * @return
	 * @throws IOException
	 */
	@ResponseBody 
	@RequestMapping(value = {"/service/profile/player/id"}, method = {RequestMethod.GET})
	@Transactional
	public ServiceResponse playerProfile(Principal principal)
	throws IOException {
		ServiceResponse response = new ServiceResponse();
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		AppUserDetails auth = TennisSetAppUtils.cast(principal);
		UserAccount userAccount = daoManager.find(UserAccount.class, auth.getUserAccountId());
		
		if(userAccount != null){
			TennisPlayerProfile profile = userAccount.getPlayerProfile();
			if(profile != null){
				response.put("playerProfileId", userAccount.getUserAccountId());
			}
		}
		return response;
	}
}
