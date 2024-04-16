package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class WasteManagerDto {
	private String nombre;
    private String nif;
    private WasteManagerAddressDto wasteManagerAddressDto;
    private List<WasteCenterAuthorizationDto> listOfWasteCenterAuthorization;

    private Date createdDate; 
	private Date lastModifiedDate;
}