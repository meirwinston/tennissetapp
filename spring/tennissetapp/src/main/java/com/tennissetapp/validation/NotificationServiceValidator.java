package com.tennissetapp.validation;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

import com.tennissetapp.args.MessageArgs;
import com.tennissetapp.args.ScrollByMateAccountIdArgs;
import com.tennissetapp.args.ScrollMessagesArgs;
import com.tennissetapp.forms.MessageForm;
import com.tennissetapp.forms.ScrollByMateAccountIdForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.validation.Validator.ErrorCode;

public class NotificationServiceValidator extends Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return MessageForm.class.equals(clazz) ||
				ScrollForm.class.equals(clazz) ||
				ScrollByMateAccountIdForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof MessageForm){
			validate((MessageForm)target,errors);
		}
		else if(target instanceof ScrollByMateAccountIdForm){
			validate((ScrollByMateAccountIdForm)target,errors);
		}
		else if(target instanceof ScrollForm){
			validate((ScrollForm)target,errors);
		}
	}
	
	public void validate(ScrollByMateAccountIdForm form, Errors errors) {
		ScrollByMateAccountIdArgs args = new ScrollByMateAccountIdArgs();
		try {
			args.firstResult = Long.valueOf(form.getFirstResult());
			args.maxResults = Integer.valueOf(form.getMaxResults());
			args.mateAccountId = Long.valueOf(form.getMateAccountId());
			
			if(StringUtils.isNotEmpty(form.getStartDate())){
				args.startDate = new Date(Long.valueOf(form.getStartDate()));
			}
		} catch (Exception exp) {
			errors.rejectValue("firstResult", ErrorCode.INVALID_EXPRESSION, "Invalid request");
		}
		
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}
	
	public void validate(ScrollForm form, Errors errors) {
		ScrollMessagesArgs args = new ScrollMessagesArgs();
		try {
			args.firstResult = Long.valueOf(form.getFirstResult());
			args.maxResults = Integer.valueOf(form.getMaxResults());
		} catch (Exception exp) {
			errors.rejectValue("firstResult", ErrorCode.INVALID_EXPRESSION, "Invalid request");
		}
		
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}
	
	protected void validate(MessageForm form, Errors errors) {
		MessageArgs args = new MessageArgs();
		try {
			args.toUserAccountId = Long.valueOf(form.getToUserAccountId());
		} catch (Exception exp) {
			errors.rejectValue("toUserAccountId", ErrorCode.INVALID_EXPRESSION, "Invalid request");
		}
		if(StringUtils.isNotEmpty(form.getMessage())){
			args.message = form.getMessage();
		}
		else{
			errors.rejectValue("message", ErrorCode.EMPTY_FIELD, "Message cannot be empty");
		}
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}
}
