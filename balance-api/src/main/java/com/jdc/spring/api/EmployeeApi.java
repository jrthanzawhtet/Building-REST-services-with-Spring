package com.jdc.spring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.api.input.EmployeeForm;
import com.jdc.spring.api.input.EmployeeSearch;
import com.jdc.spring.api.input.EmployeeStatusForm;
import com.jdc.spring.api.output.EmployeeInfo;
import com.jdc.spring.api.output.EmployeeInfoDetails;
import com.jdc.spring.model.service.EmployeeService;

@RestController
@RequestMapping("employee")
@PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
public class EmployeeApi {
	
	@Autowired
	private EmployeeService service;
	
	Page<EmployeeInfo> search(EmployeeSearch search,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){

		return service.search(search,page,size);
		
	}
	
	
	@GetMapping("{id}")
	EmployeeInfoDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@GetMapping("{id}/edit")
	EmployeeForm findByIdForEdit(@PathVariable int id) {
		return service.findByIdForEdit(id);
	}
	
	
	@PostMapping
	EmployeeInfo create(
			@Validated @RequestBody EmployeeForm form, 
			BindingResult result) {
		
		return service.create(form);
		
	}
	
	@PutMapping("{id}")
	EmployeeInfo update(@PathVariable int id,
			@Validated @RequestBody EmployeeStatusForm form, BindingResult result) {
		return service.update(id,form);
	}
	
	@PutMapping("{id}/status")
	EmployeeInfo updateStatus(@PathVariable int id, 
			@Validated @RequestBody EmployeeStatusForm form, BindingResult result) {
		return service.update(id, form);	
	}

}
