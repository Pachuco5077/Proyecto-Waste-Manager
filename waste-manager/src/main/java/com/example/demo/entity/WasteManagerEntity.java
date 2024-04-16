package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.WasteManagerDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class WasteManagerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 	

	private String nombre;
	
	private String nif; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private WasteManagerAddressEntity wasteManagerAddressEntity; 
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<WasteCenterAuthorizationEntity> listOfWasteCenterAuthorization = new ArrayList<>(); 
	
	private Boolean isEnabled = Boolean.TRUE; 
	
	private Long version = 0L; 
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate; 
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_modified_date")
	private Date lastModifiedDate; 
	
	public WasteManagerDto toDto() {
        WasteManagerDto dto = new WasteManagerDto();
        dto.setNombre(this.nombre);
        dto.setNif(this.nif);
        dto.setCreatedDate(this.createdDate);
        dto.setLastModifiedDate(this.lastModifiedDate);
        dto.setWasteManagerAddressDto(this.wasteManagerAddressEntity != null ? this.wasteManagerAddressEntity.toDto() : null);
        dto.setListOfWasteCenterAuthorization(this.listOfWasteCenterAuthorization.stream()
                .map(WasteCenterAuthorizationEntity::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
}