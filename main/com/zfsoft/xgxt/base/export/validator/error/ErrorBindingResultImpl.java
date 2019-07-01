package com.zfsoft.xgxt.base.export.validator.error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.validator.ValidatorUtils;

/**
 * <li>´íÎóÐÅÏ¢°ó¶¨Æ÷</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ErrorBindingResultImpl implements IErrorBindingResult {
	private Object target;
	private List<FieldError> errors = new ArrayList<FieldError>();

	public ErrorBindingResultImpl(Object target) {
		if (target == null) {
			throw new IllegalArgumentException("target must not be null!");
		}
		this.target = target;
	}

	public void addErrors(IErrorBindingResult result) {
		if (result == null || result.getAllErrors().size() < 1) {
			return;
		}
		if (!result.getTargetName().equals(getTargetName())) {
			throw new IllegalArgumentException(
					"the targets don't match each other!");
		}
		for (FieldError error : result.getAllErrors()) {
			rejectValue(error.getField(), error.getErrMessage());
		}
	}

	public List<FieldError> getAllErrors() {
		return Collections.unmodifiableList(errors);
	}

	public String getFieldErrorMessage(String field) {
		List<FieldError> fieldErrors = getFieldErrors(field);
		String result = "";
		for (FieldError e : fieldErrors) {
			result += e.getErrMessage()
					+ ValidatorUtils.VALIDATE_INFO_SPLIT_SYMBAL;
		}
		return result.endsWith(ValidatorUtils.VALIDATE_INFO_SPLIT_SYMBAL) ? result
				.substring(0, result.length() - 1)
				: result;
	}

	public List<FieldError> getFieldErrors(String field) {
		if (StringUtils.isNull(field)) {
			throw new IllegalArgumentException("field must not be null!");
		}
		List<FieldError> fieldErrors = new ArrayList<FieldError>();
		for (FieldError e : getAllErrors()) {
			if (field.equals(e.getField())) {
				fieldErrors.add(e);
			}
		}
		return fieldErrors;
	}

	public Object getTarget() {
		return this.target;
	}

	public String getTargetName() {
		return this.target.getClass().getName();
	}

	public boolean hasErrors() {
		return errors.size() > 0;
	}

	public void rejectValue(String field, String errMessage) {
		FieldError error = new FieldError(this.target, field, errMessage);
		if (!errors.contains(error) && StringUtils.isNotNull(errMessage)) {
			errors.add(error);
		}
	}

}
