package com.tennissetapp.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tennissetapp.args.CreateTennisCenterArgs;
import com.tennissetapp.args.FindByLocationArgs;
import com.tennissetapp.args.SearchTennisCourtsArgs;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.forms.CreateTennisCenterForm;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.SearchTennisCourtsForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.TennisCenter;
import com.tennissetapp.validation.TennisCourtServiceValidator;

//@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class TennisCourtsService extends AbstractService{
	Logger logger = Logger.getLogger(getClass());
	
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new TennisCourtServiceValidator());
    }
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	@ResponseBody //return the body only
	@RequestMapping(value = {"service/courts/create"}, method = {RequestMethod.POST})
	public ServiceResponse createTennisCenter(@Valid CreateTennisCenterForm form, BindingResult result)
	throws IOException {
		logger.debug("createTennisCourt "+ form);
		ServiceResponse response = new ServiceResponse(result);
		if(!result.hasErrors()){
			TennisCenter t = daoManager.createTennisCenter(form.getArguments(CreateTennisCenterArgs.class));
			response.put("tennisCenterId", t.getTennisCenterId());
		}
		
		return response;
	}
	
//	
	@Transactional
	@ResponseBody 
	@RequestMapping(value = {"service/courts/nearby"}, method = {RequestMethod.GET})
	public ServiceResponse nearbyTennisCenters(@Valid FindByLocationForm form, BindingResult result)
	throws IOException {
//		logger.debug("nearbyTennisCenters "+ form + ", principal: " + principal + ", dao: " + daoManager);
		ServiceResponse response = new ServiceResponse(result);
		if(result.getErrorCount() > 0){
			return response;
		}
		FindByLocationArgs args = form.getArguments(FindByLocationArgs.class);
//		if(StringUtils.isEmpty(form.getLatitude()) || StringUtils.isEmpty(form.getLongitude())){
//			//find the location of the logged in user
//			AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
//			LatitudeLongitude l = daoManager.userLatLng(userDetails.getUserAccountId());
//			args.latitude = l.getLatitude();
//			args.longitude = l.getLongitude();
//		}
			
		response.put("list", daoManager.nearbyTennisCenters(args));
		response.put("count", daoManager.countNearbyTennisCenters(args.latitude,args.longitude,args.maxDistance));
		logger.debug("RETURNING RESULT " + response);
		return response;
	}
	
	@Transactional
	@ResponseBody 
	@RequestMapping(value = {"service/courts/getCourts"}, method = {RequestMethod.GET})
	public ServiceResponse getCourts(@RequestParam("tennisCenterId") String tennisCenterIdParam){
		ServiceResponse response = new ServiceResponse();
		logger.debug("getCourts::: " + tennisCenterIdParam);
		Long tennisCenterId = null;
		try {
			tennisCenterId = Long.valueOf(tennisCenterIdParam);
		} 
		catch (Exception exp) {
			response.putError("tennisCenterId", "Invalid tennis center id");
		}
		logger.info("getCourts " + tennisCenterId);
		
		if(tennisCenterId != null){
			response.put("list", daoManager.findCourts(tennisCenterId));
		}
		else{
			logger.warn("getCourts VALIDATION FAILED ");
		}
//		logger.debug("RETURNING RESULT " + response);
		return response;
	}
	
	@Transactional
	@ResponseBody 
	@RequestMapping(value = {"service/courts/search"}, method = {RequestMethod.GET})
	public ServiceResponse searchTennisCenters(@Valid SearchTennisCourtsForm form, BindingResult result)
	throws IOException {
//		logger.debug("nearbyTennisCenters "+ form + ", principal: " + principal + ", dao: " + daoManager);
		ServiceResponse response = new ServiceResponse(result);
		if(result.getErrorCount() > 0){
			return response;
		}
		SearchTennisCourtsArgs args = form.getArguments(SearchTennisCourtsArgs.class);
		logger.info("the ARGS are " + args);
//		if(StringUtils.isEmpty(form.getLatitude()) || StringUtils.isEmpty(form.getLongitude())){
			//find the location of the logged in user
//			AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
//			LatitudeLongitude l = daoManager.userLatLng(userDetails.getUserAccountId());
//			args.latitude = l.getLatitude();
//			args.longitude = l.getLongitude();
//		}
		if(args != null){
			response.put("list", daoManager.searchTennisCenters(args));
			response.put("count", daoManager.countSearchTennisCenters(args));
		}
		else{
			logger.warn("VALIDATION FAILED " + result.getErrorCount() + ", " + result.getAllErrors());
		}
		logger.debug("RETURNING RESULT " + response);
		return response;
	}
}
