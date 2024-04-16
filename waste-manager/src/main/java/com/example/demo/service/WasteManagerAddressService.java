package com.example.demo.service;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.example.demo.dto.WasteManagerAddressDto;

public interface WasteManagerAddressService {
	
	ResponseEntity<?> create(WasteManagerAddressDto wasteManagerAddressDto);

    ResponseEntity<?> update(WasteManagerAddressDto wasteManagerAddressDto, Long id);

    ResponseEntity<?> findById(long wasteManagerAddressId);
}