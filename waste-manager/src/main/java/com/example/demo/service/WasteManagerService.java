package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.example.demo.dto.WasteManagerDto;

public interface WasteManagerService {
	ResponseEntity<?> create ( WasteManagerDto wasteManagerDto, BindingResult bindingResult ) throws Exception; 
	ResponseEntity<?> update( WasteManagerDto dto, BindingResult bindingResult, Long id ) throws Exception; 
	ResponseEntity<?> findById(long wasteManagerId) throws Exception; 
}
