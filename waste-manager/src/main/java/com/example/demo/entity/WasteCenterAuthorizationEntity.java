package com.example.demo.entity;

import com.example.demo.dto.WasteCenterAuthorizationDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class WasteCenterAuthorizationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String authorizationNumber; 
	
	public WasteCenterAuthorizationDto toDto() {
        WasteCenterAuthorizationDto dto = new WasteCenterAuthorizationDto();
        dto.setAuthorizationNumber(this.authorizationNumber);
        return dto;
    }
}
