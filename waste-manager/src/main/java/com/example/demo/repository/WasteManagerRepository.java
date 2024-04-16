package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.WasteManagerEntity;

public interface WasteManagerRepository  extends JpaRepository<WasteManagerEntity, Long> {

}

