package com.tennissetapp.persistence.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tennissetapp.forms.SubmitPostForm;
import com.tennissetapp.persistence.entities.UserPost;

@Deprecated
public class UserPostDaoImpl implements UserPostDao{
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession(){
		try {
			return sessionFactory.getCurrentSession();
		}
		catch (Exception exp) {
			exp.printStackTrace();
			sessionFactory.close();
		}
		return null;
	}

//	@Override
//	public UserPost create(SubmitPostForm form) {
//		UserPost userPost = new UserPost();
//		userPost.setContent(form.getContent());
//		userPost.setPostedOn(new Date());
//		userPost.setEmail(form.getEmail());
//		getSession().persist(userPost);
//		
//		return userPost;
//	}

}
