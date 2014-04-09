package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tennissetapp.args.CreateFavoriteTeacherArgs;
import com.tennissetapp.args.FindByLocationArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.args.SearchByNameOrEmailArgs;
import com.tennissetapp.args.SearchTennisTeachersArgs;
import com.tennissetapp.auth.AppUserDetails;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.forms.CreateFavoriteTeacherForm;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.forms.SearchByNameOrEmailForm;
import com.tennissetapp.forms.SearchTennisTeachersForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.FavoriteTeacher;
import com.tennissetapp.persistence.entities.TeacherSelect;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.TeachersServiceValidator;

//@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class TeachersService extends AbstractService{
	protected Logger logger = Logger.getLogger(getClass());
	
//	@Inject
//	private PlatformTransactionManager platformTransactionManager;
	
	@Inject
	protected SessionFactory sessionFactory;
	
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new TeachersServiceValidator());
    }
	
	@Transactional
	@ResponseBody 
	@RequestMapping(value = {"service/teachers/nearby"}, method = {RequestMethod.GET})
	public ServiceResponse nearbyTennisTeachers(@Valid FindByLocationForm form, BindingResult result)
	throws IOException {
		logger.debug("nearbyTennisTeachers "+ form + ", dao: " + daoManager);
		ServiceResponse response = new ServiceResponse(result);
		if(result.getErrorCount() > 0){
			return response;
		}
		FindByLocationArgs args = form.getArguments(FindByLocationArgs.class);
		List<TeacherSelect> list = daoManager.findNearbyTennisTeachers(args);
		
		Long count = daoManager.countNearbyTeachers(args.latitude,args.longitude,args.maxDistance);
		logger.debug("count is " + count);
		response.put("list", list);
		response.put("count", count);
		
		logger.debug("RETURNING RESULT " + response);
		return response;
	}
	
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/teachers/search"}, method = {RequestMethod.GET})
	public ServiceResponse search(@Valid SearchTennisTeachersForm form, BindingResult result)
	throws IOException {
		logger.debug("search: "  + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		SearchTennisTeachersArgs args = form.getArguments(SearchTennisTeachersArgs.class);
		logger.debug("search, args: " + args);

		List<TeacherSelect> list = daoManager.searchTennisTeachers(args);
		Long count = daoManager.countSearchTennisTeachers(args);
		response.put("list", list);
		response.put("count", count);
		return response;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/teachers/searchByNameOrEmail"}, method = {RequestMethod.GET})
	public ServiceResponse search(@Valid SearchByNameOrEmailForm form,BindingResult result)
	throws IOException {
		logger.debug("search form: " + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.getErrorCount() > 0){
			return response;
		}
		SearchByNameOrEmailArgs args = form.getArguments(SearchByNameOrEmailArgs.class);
		logger.debug("search by name args: " + args);

		List<TeacherSelect> list = daoManager.searchTennisTeachers(args);
		Long count = daoManager.countTennisTeachers(args);
		
		logger.debug(list + ", count is " + count);
		response.put("list", list);
		response.put("count", count);
		
		return response;
	}

	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/teachers/addToFavorites"}, method = {RequestMethod.GET})
	public ServiceResponse addToFavorites(@Valid CreateFavoriteTeacherForm form, BindingResult result)
	throws IOException {
		logger.debug("addToFavorites: "  + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		CreateFavoriteTeacherArgs args = form.getArguments(CreateFavoriteTeacherArgs.class);
		logger.debug("addToFavorites, args: " + args);

		FavoriteTeacher t = daoManager.createFavoriteTeacher(args);
		
		response.put("teacherProfileId", t.getTeacherProfileId());
		return response;
	}

	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/teachers/removeFromFavorites"}, method = {RequestMethod.GET})
	public ServiceResponse removeFromFavorites(@RequestParam("teacherProfileId") String teacherProfileIdParam, BindingResult result, Principal principal)
	throws IOException {
		logger.debug("removeFromFavorites: "  + teacherProfileIdParam);
		AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
		if(userDetails == null){
			throw new com.tennissetapp.exception.NotAuthorizedException();
		}
		Long teacherProfileId = null;
		try {
			teacherProfileId = Long.valueOf(teacherProfileIdParam);
		} catch (Exception exp) {
			result.rejectValue("teacherProfileId", "Invalid teacher profile ID");
		}
		
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		
		int removedCount = daoManager.removeFavoriteTeacher(userDetails.getUserAccountId(), teacherProfileId);
		
		response.put("removedCount", removedCount);
		return response;
	}

	
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/teachers/favorites"}, method = {RequestMethod.GET})
	public ServiceResponse findFavorites(@Valid ScrollForm form, BindingResult result, Principal principal)
	throws IOException {
		logger.debug("findFavorites: "  + form);
		AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
		if(userDetails == null){
			throw new com.tennissetapp.exception.NotAuthorizedException();
		}
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		ScrollByUserAccountIdArgs args = form.getArguments(ScrollByUserAccountIdArgs.class);
		args.userAccountId = userDetails.getUserAccountId();
		logger.debug("findFavorites, args: " + args);

		List<TeacherSelect> list = daoManager.findFavoriteTeachers(args);
		
		response.put("list", list);
		response.put("count", list.size());
		return response;
	}

}
