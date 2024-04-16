package com.example.demo.entity;


import java.util.Date;

import com.example.demo.dto.WasteManagerAddressDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Data 
@Table
public class WasteManagerAddressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private String direccion; 
	
	private Boolean isEnabled = Boolean.TRUE;
	
	private Long version = 0L; 
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate; 
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_modified_date")
	private Date lastModifiedDate;

	public WasteManagerAddressDto toDto() {
        WasteManagerAddressDto dto = new WasteManagerAddressDto();
        dto.setDireccion(this.direccion);
        dto.setVersion(this.version);
        dto.setIsEnabled(this.isEnabled);
        return dto;
    }
}
