package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tennissetapp.args.CreateMatchArgs;
import com.tennissetapp.args.FindByLocationArgs;
import com.tennissetapp.args.ScrollArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.args.SearchMatchesArgs;
import com.tennissetapp.auth.AppUserDetails;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.DuplicateRecordException;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.forms.CreateMatchForm;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.forms.SearchMatchesForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.Match;
import com.tennissetapp.persistence.entities.MatchMember;
import com.tennissetapp.persistence.entities.MatchSelect;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.MatchesServiceValidator;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class MatchesService extends AbstractService{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject
	private PlatformTransactionManager platformTransactionManager;
	
	@Inject
	protected SessionFactory sessionFactory;
	
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new MatchesServiceValidator());
    }
	
	@Transactional
	@ResponseBody 
	@RequestMapping(value = {"service/matches/nearby"}, method = {RequestMethod.GET})
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
			SearchMatchesArgs newArgs = new SearchMatchesArgs();
			newArgs.maxResults = args.maxResults;
			newArgs.firstResult = args.firstResult;
			newArgs.distance = args.maxDistance;
			newArgs.latitude = args.latitude;
			newArgs.longitude = args.longitude;
			newArgs.playDoubles = userAccount.getPlayerProfile().getPlayDoubles();
			newArgs.playFullMatch = userAccount.getPlayerProfile().getPlayFullMatch();
			newArgs.playHittingAround = userAccount.getPlayerProfile().getPlayHittingAround();
			newArgs.playPoints = userAccount.getPlayerProfile().getPlayPoints();
			newArgs.playSingles = userAccount.getPlayerProfile().getPlaySingles();
			newArgs.levelOfPlayMin = userAccount.getPlayerProfile().getLevelOfPlay();
			newArgs.levelOfPlayMax = userAccount.getPlayerProfile().getLevelOfPlay();
			
			List<MatchSelect> list = daoManager.searchMatchItems(newArgs);
			Long count = daoManager.countNearbyMatches(newArgs.latitude,newArgs.longitude,newArgs.distance);
			logger.debug("count is " + count);
			response.put("list", list);
			response.put("count", count);
		}
		
		logger.debug("RETURNING RESULT " + response);
		return response;
	}
	
	
	@Transactional
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/matches/create"}, method = {RequestMethod.POST})
	public ServiceResponse createMatch(@Valid CreateMatchForm form, BindingResult result,Principal principal)
	throws IOException {
		logger.debug("createMatch " + form);
		ServiceResponse response = new ServiceResponse(result);
		
		if(result.getErrorCount() == 0){
			CreateMatchArgs args = form.getArguments(CreateMatchArgs.class);
			AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
			args.orgenizerId = userDetails.getUserAccountId();
			Match match = daoManager.createMatch(args);
			if(match != null){
				response.put("matchId", match.getMatchId());
			}
		}
		return response;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/matches/search"}, method = {RequestMethod.GET})
	public ServiceResponse search(@Valid SearchMatchesForm form, BindingResult result)
	throws IOException {
		logger.debug("search: "  + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		SearchMatchesArgs args = form.getArguments(SearchMatchesArgs.class);
		logger.debug("search, args: " + args);
//		args.distance = 50d;
//		
//		//if location criteria is not specified, use the user's location
//		if(args.latitude == null || args.longitude == null){
//			UserAccount userAccount = daoManager.find(UserAccount.class, WebUserDetails.userDetailsOf(principal).getUserAccountId());
//			args.latitude = userAccount.getAddressLatitude();
//			args.longitude = userAccount.getAddressLongitude();
//		}
		List<MatchSelect> list = daoManager.searchMatchItems(args);
		Long count = daoManager.countSearchMatchItems(args);
		response.put("list", list);
		response.put("count", count);
		return response;
	}
	
//	@Transactional
//	@ResponseBody
//	@RequestMapping(value = {"/service/matches/scroll"}, method = {RequestMethod.GET})
//	public ServiceResponse scroll(@Valid ScrollForm form, BindingResult result)
//	throws IOException {
//		logger.debug("scroll: "  + form);
//		ServiceResponse response = new ServiceResponse(result);
//		if(result.hasErrors()){
//			return response;
//		}
//		
//		ScrollArgs args = form.getArguments(ScrollArgs.class);
//		logger.debug("search, args: " + args);
////		List<MatchSelect> list = daoManager.searchMatchItems(args);
////		Long count = daoManager.countSearchMatchItems(args);
////		response.put("list", list);
////		response.put("count", count);
//		return response;
//	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/matches/find"}, method = {RequestMethod.GET})
	public ServiceResponse find(@RequestParam("matchId") String matchIdParam)
	throws IOException {
		
		logger.debug("find: "  + matchIdParam);
		Long matchId = null;
		ServiceResponse response = new ServiceResponse();
		if(matchIdParam == null){
			response.putError("matchId", "Problem processing request");
			return response;
		}
		else{
			try {
				matchId = Long.valueOf(matchIdParam);
			} catch (Exception exp) {
				response.putError("matchId", "Problem processing request");
			}
		}
		
		MatchSelect match = daoManager.findMatch(matchId);
		response.put("item", match);
		return response;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/matches/findMembers"}, method = {RequestMethod.GET})
	public ServiceResponse findMembers(@RequestParam("matchId") String matchIdParam)
	throws IOException {
		
		logger.debug("findMembers: "  + matchIdParam);
		Long matchId = null;
		ServiceResponse response = new ServiceResponse();
		if(matchIdParam == null){
			response.putError("matchId", "Problem processing request");
			return response;
		}
		else{
			try {
				matchId = Long.valueOf(matchIdParam);
			} catch (Exception exp) {
				response.putError("matchId", "Problem processing request");
			}
		}
		
		List<MatchMember> members = daoManager.findMatchMembers(matchId);
		logger.debug("THE MEMBERS ARE " + members);
		response.put("list", members);
		return response;
	}

	@ResponseBody
	@RequestMapping(value = {"/service/matches/join"}, method = {RequestMethod.POST})
	public ServiceResponse join(@RequestParam("matchId") final String matchIdParam, final Principal principal)
	throws IOException {
		logger.debug("join: "  + matchIdParam + ", " + platformTransactionManager);
		TransactionTemplate tx = new TransactionTemplate(platformTransactionManager);
		final ServiceResponse response = new ServiceResponse();
		final AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
		
		final Long matchId;
		if(matchIdParam == null){
			response.putError("matchId", "Problem processing request, did not get mathId parameter");
			return response;
		}
		else{
			try {
				matchId = Long.valueOf(matchIdParam);
			} catch (Exception exp) {
				response.putError("matchId", "Problem processing request, invalid matchId");
				return response;
			}
		}
		try {
			MatchMember member = tx.execute(new TransactionCallback<MatchMember>() {
				@Override
				public MatchMember doInTransaction(TransactionStatus status) {
					MatchMember member = null;
					if(daoManager.matchMemberExists(matchId,userDetails.getUserAccountId())){
						response.putError("userAccountId", "You have already joined this match");
					}
					else{
						member = daoManager.joinMatch(matchId, userDetails.getUserAccountId());
					}
					return member;
				}
			});
			if(member != null){
				response.put("matchId", member.getMatchId());
				response.put("userAccountId", member.getUserAccountId());
			}
			
		} 
		catch(org.springframework.dao.DataIntegrityViolationException exp){
			throw new DuplicateRecordException("You have already joined this match");
		}
		catch (Exception exp) {
			logger.error(exp.getMessage(),exp);
		}
//		tx.execute(new TransactionCallbackWithoutResult() {
//		    public void doInTransactionWithoutResult(TransactionStatus status) { 
//		        // Perform data access here
//		    }
//		});
		
		return response;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/matches/all"}, method = {RequestMethod.GET})
	public ServiceResponse findAllMatches(@Valid ScrollForm form, BindingResult result, Principal principal)
	throws IOException {
		logger.debug("findAllMatches: "  + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		AppUserDetails user = TennisSetAppUtils.cast(principal);
		
//		ScrollByUserAccountIdArgs args = form.getArguments(ScrollByUserAccountIdArgs.class);
//		logger.debug("search, args: " + args);
//		List<MatchSelect> list = daoManager.scrollPlayerMatches(args);
//		Long count = daoManager.countPlayerInitiatedMatches(args.userAccountId);
//		response.put("list", list);
//		response.put("count", count);
		return response;
	}
}
