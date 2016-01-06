package com.homeAutomation.validator;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.validation.ValidatorAdapter;

@Provider
public class UserValidatorContextResolver implements ContextResolver<ValidatorAdapter> {

	@Override
	public ValidatorAdapter getContext(Class<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
