package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.example.demo.dto.WasteCenterAuthorizationDto;
import com.example.demo.dto.WasteManagerAddressDto;
import com.example.demo.dto.WasteManagerDto;
import com.example.demo.entity.WasteCenterAuthorizationEntity;
import com.example.demo.entity.WasteManagerAddressEntity;
import com.example.demo.entity.WasteManagerEntity;
import com.example.demo.repository.WasteManagerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

@Service
public class WasteManagerServiceImpl implements WasteManagerService{

	private final WasteManagerRepository wasteManagerRepository;
	private final WasteManagerAddressService wasteManagerAddressService;

	@Autowired
	public WasteManagerServiceImpl(WasteManagerRepository wasteManagerRepository, WasteManagerAddressService wasteManagerAddressService) {
		this.wasteManagerRepository = wasteManagerRepository;
		this.wasteManagerAddressService = wasteManagerAddressService;
	}

	public ResponseEntity<?> create(WasteManagerDto wasteManagerDto, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorMessages(bindingResult));
		}
		try {
			WasteManagerEntity wasteManagerEntity = convertToEntity(wasteManagerDto);
			wasteManagerEntity.setCreatedDate(new Date());
			wasteManagerEntity.setLastModifiedDate(new Date());
			WasteManagerEntity savedWasteManager = wasteManagerRepository.save(wasteManagerEntity);
			ResponseEntity<?> addressResponse = wasteManagerAddressService.create(wasteManagerDto.getWasteManagerAddressDto());

			if (addressResponse.getStatusCode().isError()) {
				throw new Exception("Error saving Waste Manager Address: " + addressResponse.getBody());
			}

			savedWasteManager.setWasteManagerAddressEntity((WasteManagerAddressEntity) addressResponse.getBody());
			wasteManagerRepository.save(savedWasteManager);

			return ResponseEntity.ok(savedWasteManager);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Waste Manager: " + ex.getMessage());
		}
	}

	@Transactional
	public ResponseEntity<?> update(WasteManagerDto dto, BindingResult bindingResult, Long id) throws Exception {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorMessages(bindingResult));
		}
		try {
			WasteManagerEntity existingEntity = wasteManagerRepository.findById(id)
					.orElseThrow(() -> new Exception("Waste Manager not found with ID: " + id));

			existingEntity.setNombre(dto.getNombre());
			existingEntity.setNif(dto.getNif());
			existingEntity.setLastModifiedDate(new Date());

			List<WasteCenterAuthorizationEntity> authorizationEntities = dto.getListOfWasteCenterAuthorization().stream()
					.map(this::convertToEntity)
					.collect(Collectors.toList());
			existingEntity.setListOfWasteCenterAuthorization(authorizationEntities);

			ResponseEntity<?> addressResponse = wasteManagerAddressService.update(dto.getWasteManagerAddressDto(), existingEntity.getWasteManagerAddressEntity().getId());
			if (addressResponse.getStatusCode().isError()) {
				throw new Exception("Error updating Waste Manager Address: " + addressResponse.getBody());
			}
			existingEntity.setWasteManagerAddressEntity((WasteManagerAddressEntity) addressResponse.getBody());
			wasteManagerRepository.save(existingEntity);

			return ResponseEntity.ok(existingEntity);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Waste Manager: " + ex.getMessage());
		}
	}

	public ResponseEntity<?> findById(long wasteManagerId) throws Exception {
		WasteManagerEntity wasteManagerEntity = wasteManagerRepository.findById(wasteManagerId)
				.orElseThrow(() -> new Exception("Waste Manager not found with ID: " + wasteManagerId));

		WasteManagerDto wasteManagerDto = wasteManagerEntity.toDto();
		return ResponseEntity.ok(wasteManagerDto);
	}

	// Método para convertir WasteManagerDto a WasteManagerEntity
	private WasteManagerEntity convertToEntity(WasteManagerDto dto) {
		WasteManagerEntity wasteManagerEntity = new WasteManagerEntity();
		wasteManagerEntity.setNombre(dto.getNombre());
		wasteManagerEntity.setNif(dto.getNif());

		// Convertir la lista de WasteCenterAuthorizationDto a una lista de WasteCenterAuthorizationEntity
		List<WasteCenterAuthorizationEntity> authorizationEntities = dto.getListOfWasteCenterAuthorization().stream()
				.map(this::convertToEntity)
				.collect(Collectors.toList());
		wasteManagerEntity.setListOfWasteCenterAuthorization(authorizationEntities);

		return wasteManagerEntity;
	}

	// Método para convertir WasteCenterAuthorizationDto a WasteCenterAuthorizationEntity
	private WasteCenterAuthorizationEntity convertToEntity(WasteCenterAuthorizationDto dto) {
		WasteCenterAuthorizationEntity authorizationEntity = new WasteCenterAuthorizationEntity();
		authorizationEntity.setAuthorizationNumber(dto.getAuthorizationNumber());
		return authorizationEntity;
	}

	private String getErrorMessages(BindingResult bindingResult) {
		StringBuilder errorMessage = new StringBuilder();
		for (ObjectError error : bindingResult.getAllErrors()) {
			errorMessage.append(error.getDefaultMessage()).append("\n");
		}
		return errorMessage.toString();
	}

}

