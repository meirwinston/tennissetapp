package com.tennissetapp.service;

import org.springframework.transaction.annotation.Transactional;

import com.tennissetapp.forms.SignupForm;
import com.tennissetapp.persistence.dao.AuthDao;
import com.tennissetapp.persistence.entities.UserAccount;

@Transactional
public class TestServiceImpl implements TestService{

	protected AuthDao authDAO;
	
	public AuthDao getAuthDAO() {
		return authDAO;
	}

	public void setAuthDAO(AuthDao authDAO) {
		this.authDAO = authDAO;
	}

	@Override
	public String test(String param) {
		return "This is test(" + param + ") method";
	}
	
//	@Override
//	public UserAccount newUserAccount(SignupForm signupForm){
//		UserAccount a = authDAO.createUserAccount(signupForm);
//		return a;
//	}
}
