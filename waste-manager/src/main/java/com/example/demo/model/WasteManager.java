package com.example.demo.model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.WasteCenterAuthorizationEntity;
import com.example.demo.entity.WasteManagerAddressEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter 
public class WasteManager{
	private Long id; 
	private String nombre; 
	private String nif; 
	private WasteManagerAddressEntity wasteManagerAddressEntity; 
	private List<WasteCenterAuthorizationEntity>listOfWasteCenterAuthorization = new ArrayList<>(); 
	private Boolean isEnabled = Boolean.TRUE; 
	private Long version = 0L; 
	private Date createdDate; 
	private Date lastModifiedDate; 
}
