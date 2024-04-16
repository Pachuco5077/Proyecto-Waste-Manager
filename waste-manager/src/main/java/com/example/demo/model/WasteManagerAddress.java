package com.example.demo.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Data 
public class WasteManagerAddress {
	private Long id; 
	private String direccion; 
	private Boolean isEnabled = Boolean.TRUE;
	private Long version = 0L; 
	private Date createdDate; 
	private Date lastModifiedDate;
}
