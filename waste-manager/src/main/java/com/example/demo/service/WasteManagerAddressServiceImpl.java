package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.demo.dto.WasteManagerAddressDto;
import com.example.demo.dto.WasteManagerDto;
import com.example.demo.entity.WasteManagerAddressEntity;
import com.example.demo.repository.WasteManagerAddressRepository;

@Service
public class WasteManagerAddressServiceImpl implements WasteManagerAddressService {

	private final WasteManagerAddressRepository wasteManagerAddressRepository;

	@Autowired
	public WasteManagerAddressServiceImpl(WasteManagerAddressRepository wasteManagerAddressRepository) {
		this.wasteManagerAddressRepository = wasteManagerAddressRepository;
	}

	public ResponseEntity<?> create(WasteManagerAddressDto wasteManagerAddressDto) {
		try {
			WasteManagerAddressEntity wasteManagerAddressEntity = convertToEntity(wasteManagerAddressDto);
			wasteManagerAddressEntity.setCreatedDate(new Date());
			wasteManagerAddressEntity.setLastModifiedDate(new Date());
			wasteManagerAddressRepository.save(wasteManagerAddressEntity);

			return ResponseEntity.ok(wasteManagerAddressEntity);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Waste Manager Address: " + ex.getMessage());
		}
	}

	public ResponseEntity<?> update(WasteManagerAddressDto wasteManagerAddressDto, Long id) {
		try {
			WasteManagerAddressEntity existingEntity = wasteManagerAddressRepository.findById(id)
					.orElse(null);

			if (existingEntity == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Waste Manager Address not found with ID: " + id);
			}
			existingEntity.setDireccion(wasteManagerAddressDto.getDireccion());
			existingEntity.setIsEnabled(wasteManagerAddressDto.getIsEnabled());
			existingEntity.setVersion(wasteManagerAddressDto.getVersion());
			existingEntity.setLastModifiedDate(new Date());
			wasteManagerAddressRepository.save(existingEntity);

			return ResponseEntity.ok(existingEntity);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Waste Manager Address: " + ex.getMessage());
		}
	}

	public ResponseEntity<?> findById(long wasteManagerAddressId) {
		WasteManagerAddressEntity wasteManagerAddressEntity = wasteManagerAddressRepository.findById(wasteManagerAddressId)
				.orElse(null);
		if (wasteManagerAddressEntity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Waste Manager Address not found with ID: " + wasteManagerAddressId);
		}
		
		WasteManagerAddressDto wasteManagerAddressDto = wasteManagerAddressEntity.toDto();
		return ResponseEntity.ok(wasteManagerAddressDto);
	}

	private WasteManagerAddressEntity convertToEntity(WasteManagerAddressDto dto) {
		WasteManagerAddressEntity entity = new WasteManagerAddressEntity();
		entity.setDireccion(dto.getDireccion());
		entity.setIsEnabled(dto.getIsEnabled());
		entity.setVersion(dto.getVersion());
		return entity;
	}

}
