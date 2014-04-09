package com.tennissetapp.validation;

import java.util.Date;

import org.springframework.validation.Errors;

import com.tennissetapp.args.CalendarEventsArgs;
import com.tennissetapp.forms.CalendarEventsForm;
import com.tennissetapp.forms.NextCalendarEventForm;

public class CalendarServiceValidator extends Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return CalendarEventsForm.class.equals(clazz) ||
				NextCalendarEventForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof CalendarEventsForm){
			validate((CalendarEventsForm)target,errors);
		}
		if(target instanceof NextCalendarEventForm){
			validate((NextCalendarEventForm)target,errors);
		}
		
	}
	
	protected void validate(CalendarEventsForm form, Errors errors) {
		CalendarEventsArgs args = new CalendarEventsArgs();
		try {
			args.fromDate = new Date(Long.valueOf(form.getFromDate()));
		} catch (Exception exp) {
			errors.rejectValue("fromDate", ErrorCode.INVALID_EXPRESSION, "Invalid request");
		}
		try {
			args.toDate = new Date(Long.valueOf(form.getToDate()));
		} catch (Exception exp) {
			errors.rejectValue("toDate", ErrorCode.INVALID_EXPRESSION, "Invalid request");
		}
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}
	
	protected void validate(NextCalendarEventForm form, Errors errors) {
		CalendarEventsArgs args = new CalendarEventsArgs();
		try {
			args.fromDate = new Date(Long.valueOf(form.getFromDate()));
		} catch (Exception exp) {
			errors.rejectValue("fromDate", ErrorCode.INVALID_EXPRESSION, "Invalid request");
		}
		
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}
	
}
