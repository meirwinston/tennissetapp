package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.tennissetapp.args.FindByLocationArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.args.SearchMatesArgs;
import com.tennissetapp.args.SearchMatesByNameArgs;
import com.tennissetapp.auth.AppUserDetails;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.forms.SearchByNameOrEmailForm;
import com.tennissetapp.forms.SearchMatesForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.Mate;
import com.tennissetapp.persistence.entities.MateSelect;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.TennisMatesServiceValidator;

@Controller
public class MatesService extends AbstractService{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new TennisMatesServiceValidator());
    }
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	@ResponseBody 
	@RequestMapping(value = {"service/mates/nearby"}, method = {RequestMethod.GET})
	public ServiceResponse nearbyTennisMates(@Valid FindByLocationForm form, BindingResult result,Principal principal)
	throws IOException {
//		logger.debug("nearbyTennisMates "+ form + ", principal: " + principal + ", dao: " + daoManager);
		ServiceResponse response = new ServiceResponse(result);
		if(result.getErrorCount() > 0){
			return response;
		}
		AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
		UserAccount userAccount = daoManager.find(UserAccount.class, userDetails.getUserAccountId());
		if(userAccount != null){
			FindByLocationArgs args = form.getArguments(FindByLocationArgs.class);
			SearchMatesArgs newArgs = new SearchMatesArgs();
			newArgs.maxResults = args.maxResults;
			newArgs.firstResult = args.firstResult;
			newArgs.distance = args.maxDistance;
			newArgs.latitude = args.latitude;
			newArgs.longitude = args.longitude;
			//on create new account we jump to mates
			//before the user has a player profile
			if(userAccount.getPlayerProfile() != null){
				newArgs.playDoubles = userAccount.getPlayerProfile().getPlayDoubles();
				newArgs.playFullMatch = userAccount.getPlayerProfile().getPlayFullMatch();
				newArgs.playHittingAround = userAccount.getPlayerProfile().getPlayHittingAround();
				newArgs.playPoints = userAccount.getPlayerProfile().getPlayPoints();
				newArgs.playSingles = userAccount.getPlayerProfile().getPlaySingles();
				newArgs.levelOfPlayMin = userAccount.getPlayerProfile().getLevelOfPlay();
				newArgs.levelOfPlayMax = userAccount.getPlayerProfile().getLevelOfPlay();
			}
			else{
				newArgs.playDoubles = true;
				newArgs.playFullMatch = true;
				newArgs.playHittingAround = true;
				newArgs.playPoints = true;
				newArgs.playSingles = true;
				newArgs.levelOfPlayMin = 1f;
				newArgs.levelOfPlayMax = 7f;
			}
			
			
			List<MateSelect> list = daoManager.searchMateItems(newArgs);
			for(MateSelect m : list){
				if(StringUtils.isNotEmpty(m.getProfilePhoto())){
					daoManager.evict(m);
					m.setProfilePhoto(TennisSetAppUtils.profileImageFileUrl(m.getProfilePhoto()));
				}
			}
			Long count = daoManager.countNearbyMates(newArgs.latitude,newArgs.longitude,newArgs.distance);
			logger.debug("count is " + count);
			response.put("list", list);
			response.put("count", count);
		}
		
		logger.debug("RETURNING RESULT " + response);
		return response;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/mates/search"}, method = {RequestMethod.GET})
	public ServiceResponse search(@Valid SearchMatesForm form,BindingResult result)
	throws IOException {
//		logger.debug("search form: " + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.getErrorCount() > 0){
			return response;
		}
		SearchMatesArgs args = form.getArguments(SearchMatesArgs.class);
		logger.debug("search args: " + args);

		List<MateSelect> list = daoManager.searchMateItems(args);
		for(MateSelect m : list){
			if(StringUtils.isNotEmpty(m.getProfilePhoto())){
				daoManager.evict(m);
				m.setProfilePhoto(TennisSetAppUtils.profileImageFileUrl(m.getProfilePhoto()));
			}
		}
		Long count = daoManager.countNearbyMates(args.latitude,args.longitude,args.distance);
//		logger.debug("count is " + count);
		response.put("list", list);
		response.put("count", count);
		
		return response;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/mates/searchByNameOrEmail"}, method = {RequestMethod.GET})
	public ServiceResponse search(@Valid SearchByNameOrEmailForm form,BindingResult result)
	throws IOException {
//		logger.debug("search form: " + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.getErrorCount() > 0){
			return response;
		}
		SearchMatesByNameArgs args = form.getArguments(SearchMatesByNameArgs.class);
		logger.debug("search by name args: " + args);

		List<MateSelect> list = daoManager.searchMateItems(args);
		for(MateSelect m : list){
			if(StringUtils.isNotEmpty(m.getProfilePhoto())){
				daoManager.evict(m);
				m.setProfilePhoto(TennisSetAppUtils.profileImageFileUrl(m.getProfilePhoto()));
			}
		}
		Long count = daoManager.countSearchMateByName(args.nameOrEmail);
//		logger.debug("count is " + count);
		response.put("list", list);
		response.put("count", count);
		
		return response;
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/service/mates/findByUser"}, method = {RequestMethod.GET})
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
				m.setLocality(mate.getMateAccount().getAddress().getLocality());
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

	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/mates/add"}, method = {RequestMethod.POST})
	@Transactional
	public ServiceResponse userMates(@RequestParam("mateAccountId") String mateAccountIdParam, Principal principal)
	throws IOException {
		ServiceResponse response = new ServiceResponse();
		
		Long mateAccountId;
		try {
			mateAccountId = Long.valueOf(mateAccountIdParam);
		} catch (Exception exp) {
			response.putError("mateAccountId", "Invalid user account");
			return response;
		}
		AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
		Mate mate = daoManager.findMateByUserAndMateIds(userDetails.getUserAccountId(), mateAccountId);
		if(mate == null){
			mate = daoManager.addMate(userDetails.getUserAccountId(), mateAccountId);
		}
		
		response.put("mateAccountId", mate.getMateAccountId());
		
		return response;
	}

	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/mates/get"}, method = {RequestMethod.POST})
	@Transactional
	public ServiceResponse getMate(@RequestParam("mateAccountId") String mateAccountIdParam, Principal principal)
	throws IOException {
		ServiceResponse response = new ServiceResponse();
		
		Long mateAccountId;
		try {
			mateAccountId = Long.valueOf(mateAccountIdParam);
		} catch (Exception exp) {
			response.putError("mateAccountId", "Invalid user account");
			return response;
		}
		AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
		Mate mate = daoManager.findMateByUserAndMateIds(userDetails.getUserAccountId(), mateAccountId);
		if(mate != null){
			response.put("item", mate);
		}
		return response;
	}
}
