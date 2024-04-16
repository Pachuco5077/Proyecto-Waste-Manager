package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.WasteManagerAddressDto;
import com.example.demo.service.WasteManagerAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wasteManagerAddresses")
public class WasteManagerAddressController {

	private final WasteManagerAddressService wasteManagerAddressService;

    @Autowired
    public WasteManagerAddressController(WasteManagerAddressService wasteManagerAddressService) {
        this.wasteManagerAddressService = wasteManagerAddressService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWasteManagerAddress(@PathVariable Long id, @Valid @RequestBody WasteManagerAddressDto wasteManagerAddressDto) {
        return wasteManagerAddressService.update(wasteManagerAddressDto, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWasteManagerAddressById(@PathVariable Long id) {
        return wasteManagerAddressService.findById(id);
    }
}
