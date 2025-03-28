package com.jdc.spring.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.spring.controller.input.SignUpForm;
import com.jdc.spring.model.account.service.SignUpService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("public/signup")
public class SignUpController {
	
	private final AuthenticationManager authenticationManager;
	private final SignUpService customerService;
	private final SecurityContextRepository securityContextRepository;
	
	
	@GetMapping
	String index() {
		return "signup";
	}
	
	@PostMapping
	String signUp(@Validated @ModelAttribute(name = "form") SignUpForm singUpForm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {

		if (result.hasErrors()) {
			return "signup";
		}

		var authentication = customerService.signUp(singUpForm);

		authentication = authenticationManager.authenticate(authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);

		return "redirect:/";
	}
	
	@ModelAttribute(name = "form")
	SignUpForm signUpForm() {
		return new SignUpForm();
	}
	
}
