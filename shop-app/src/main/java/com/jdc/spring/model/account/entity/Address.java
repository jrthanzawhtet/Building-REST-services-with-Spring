package com.jdc.spring.model.account.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	private String name;
	
	@ManyToOne(optional = false)
	private Customer customer;
	
	@Column(nullable = false)
	private String building;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private String quarter;
	
	@Column(nullable = false)
	private String township;
	
	@Column(nullable = false)
	private String region;

}
