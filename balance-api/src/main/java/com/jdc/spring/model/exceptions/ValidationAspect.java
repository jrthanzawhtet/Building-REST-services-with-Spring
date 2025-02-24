package com.jdc.spring.model.exceptions;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ValidationAspect {

	@Pointcut("")
	public void apiMethod() {
		
	}
	
	@Before(value = "apiMethods() && args(.., result)", argNames = "result")
	public void handle(BindingResult result) {
		if(result.hasErrors()) {
			var messages = result.getFieldErrors()
					.stream().map(a -> a.getDefaultMessage())
					.toList();
			
			throw new ApiValidationException(messages);
		}
	}

}
