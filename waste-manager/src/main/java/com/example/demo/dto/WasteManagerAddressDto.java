package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class WasteManagerAddressDto {
    private String direccion;
    private Boolean isEnabled = Boolean.TRUE;
	private Long version = 0L;
}
