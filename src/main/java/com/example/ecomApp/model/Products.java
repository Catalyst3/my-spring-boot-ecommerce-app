package com.example.ecomApp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productID;
	
	private String productName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catagory_ID", referencedColumnName = "catagory_ID") 
    private Category category;
	
	private double productPrice;
	
	private  double productWeight;
	
	private String productDescription;
	
	private String productImageName;

}
