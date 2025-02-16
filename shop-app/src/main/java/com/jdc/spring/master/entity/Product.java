package com.jdc.spring.master.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(indexes = {
		@Index(columnList = "category_id,name",unique = true)
})
@EqualsAndHashCode(callSuper = false)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy = "product")
	private ProductStock stock;
	
	@ManyToOne(optional = false)
	private Category category;
	
	@Column(nullable = false)
	private String name;
	private int salePrice;
	
	@Column(columnDefinition = "Text")
	private String image;
	private String description;
	
	@ElementCollection
	@MapKeyColumn(name = "property")
	@CollectionTable(name = "product_properties")
	private Map<String, String> properties = new HashMap<>();
	
	private List<ProductStockHistory> stockHistory;
}
