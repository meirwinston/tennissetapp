package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tennissetapp.args.AddressArgs;
import com.tennissetapp.forms.AddressForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.dao.DaoManagerImpl;
import com.tennissetapp.persistence.entities.Address;
import com.tennissetapp.persistence.entities.LatitudeLongitude;


@Controller
public class TestService {
	Logger logger = Logger.getLogger(getClass());
	
	@Inject DaoManager daoManager;
	
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/util/storeaddress"}, method = {RequestMethod.POST})
	@Transactional
	public void address(AddressForm form, HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		AddressArgs args = new AddressArgs();
		form.initArgs(args);
		Address address = DaoManagerImpl.extractAddress(args);
		address.setCreatedOn(new Date());
		Address dbAddress = daoManager.find(Address.class, new LatitudeLongitude(Double.valueOf(form.getLatitude()), Double.valueOf(form.getLongitude())));
		if(dbAddress != null){
			logger.debug("----++>found duplicate " + dbAddress);
		}
		else{
			daoManager.persist(address);
			logger.debug("--->success! " + address);
		}
		
		
	}
}
