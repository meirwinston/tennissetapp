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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tennissetapp.args.CalendarEventsArgs;
import com.tennissetapp.auth.AppUserDetails;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.forms.CalendarEventsForm;
import com.tennissetapp.forms.NextCalendarEventForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.CalendarEventSelect;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.CalendarServiceValidator;
import com.tennissetapp.validation.MatchesServiceValidator;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class CalnedarService extends AbstractService{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject
	private PlatformTransactionManager platformTransactionManager;
	
	@Inject
	protected SessionFactory sessionFactory;
	
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CalendarServiceValidator());
    }
		
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/calendar/events"}, method = {RequestMethod.GET})
	public ServiceResponse getCalendarEvents(@Valid CalendarEventsForm form, BindingResult result, Principal principal)
	throws IOException {
		logger.debug("getCalendarEvents: "  + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		AppUserDetails user = TennisSetAppUtils.cast(principal);
		CalendarEventsArgs args = form.getArguments(CalendarEventsArgs.class);
		args.userAccountId = user.getUserAccountId();
		
		logger.debug("search, args: " + args);
		List<CalendarEventSelect> list = daoManager.getCalendarEvents(args);
		response.put("list", list);
		return response;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/calendar/nextEvent"}, method = {RequestMethod.GET})
	public ServiceResponse getNextCalendarEvent(@Valid NextCalendarEventForm form, BindingResult result, Principal principal)
	throws IOException {
		logger.debug("getNextCalendarEvent: "  + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		AppUserDetails user = TennisSetAppUtils.cast(principal);
		CalendarEventsArgs args = form.getArguments(CalendarEventsArgs.class);
		args.userAccountId = user.getUserAccountId();
		
		logger.debug("search, args: " + args);
		CalendarEventSelect item = daoManager.getNextCalendarEvent(args);
		response.put("item", item);
		return response;
	}
	
	
}
