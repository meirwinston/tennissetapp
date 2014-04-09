package com.tennissetapp.controller;

import java.io.IOException;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.social.NotAuthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.tennissetapp.Constants;


public abstract class ControllerBase {
	protected Logger logger = Logger.getLogger(getClass());

}
