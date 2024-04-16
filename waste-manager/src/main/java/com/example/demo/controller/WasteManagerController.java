package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.WasteManagerDto;
import com.example.demo.service.WasteManagerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wasteManagers")
public class WasteManagerController {

	private final WasteManagerService wasteManagerService;

	@Autowired
	public WasteManagerController(WasteManagerService wasteManagerService) {
		this.wasteManagerService = wasteManagerService;
	}

	@PostMapping
	public ResponseEntity<?> createWasteManager(@Valid @RequestBody WasteManagerDto wasteManagerDto, BindingResult bindingResult) {
		try {
			return wasteManagerService.create(wasteManagerDto, bindingResult);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Waste Manager: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateWasteManager(@PathVariable Long id, @Valid @RequestBody WasteManagerDto wasteManagerDto, BindingResult bindingResult) {
		try {
			return wasteManagerService.update(wasteManagerDto, bindingResult, id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Waste Manager: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getWasteManagerById(@PathVariable Long id) {
		try {
			return wasteManagerService.findById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving Waste Manager: " + e.getMessage());
		}
	}

}
