package com.tennissetapp.rest;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.tennissetapp.controller.ControllerBase;
import com.tennissetapp.persistence.dao.DaoManager;

@Controller
public class ScrollTennisCourtsService extends ControllerBase{
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(new SubmitPostFormValidator());
    }
	
//	@Transactional
//	@ResponseBody
//	@RequestMapping(value = {"/service/courts/scrollnearby"}, method = {RequestMethod.GET})
//	public ServiceResponse scrollnearby(ScrollForm form)
//	throws IOException {
////		if(principal == null){
////			throw new NotAuthorizedException("Not authorized");
////		}
////		form.setEmail(principal.getName());
////		System.out.println("--->HomeController.submitPost " + form);
////		daoManager.createUserPost(form);
//		logger.debug("--->scrollnearby");
//		ServiceResponse response = new ServiceResponse();
//		return response;
//	}
	
//	@Transactional
//	@ResponseBody
//	@RequestMapping(value = {"/service/courts/findnearby"}, method = {RequestMethod.GET})
//	public ServiceResponse scrollnearby(FindNearbyCourtsForm form)
//	throws IOException {
////		if(principal == null){
////			throw new NotAuthorizedException("Not authorized");
////		}
////		form.setEmail(principal.getName());
////		System.out.println("--->HomeController.submitPost " + form);
////		daoManager.createUserPost(form);
//		logger.debug("--->scrollnearby");
//		ServiceResponse response = new ServiceResponse();
//		
//		List<CourtSelect> list = daoManager.findNearbyCourts(form);
//		
//		response.put("list", list);
//		return response;
//	}
}
