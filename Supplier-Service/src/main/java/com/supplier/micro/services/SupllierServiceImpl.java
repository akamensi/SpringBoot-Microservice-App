package com.supplier.micro.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplier.micro.entities.Supplier;
import com.supplier.micro.entities.SupplierDTO;
import com.supplier.micro.repositories.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupllierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    
    
    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public SupplierDTO getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }
    
    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = convertToEntity(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return convertToDTO(savedSupplier);
    }
    
    
    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        // Find the existing supplier
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            throw new EntityNotFoundException("Supplier not found with id: " + id);
        }

        Supplier existingSupplier = optionalSupplier.get();

        // Update only the fields that are not null in the DTO
        if (supplierDTO.name() != null) {
            existingSupplier.setName(supplierDTO.name());
        }
        if (supplierDTO.address() != null) {
            existingSupplier.setAddress(supplierDTO.address());
        }
        if (supplierDTO.email() != null) {
            existingSupplier.setEmail(supplierDTO.email());
        }

        // Save the updated supplier
        Supplier updatedSupplier = supplierRepository.save(existingSupplier);

        // Convert to DTO and return
        return new SupplierDTO(updatedSupplier.getId(), updatedSupplier.getName(), updatedSupplier.getAddress(), updatedSupplier.getEmail());
    }
    
    
    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
    
    
    //converting methodes
    
    private SupplierDTO convertToDTO(Supplier supplier) {
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getEmail());
    }

    private Supplier convertToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.name());
        supplier.setAddress(supplierDTO.address());
        supplier.setEmail(supplierDTO.email());
        return supplier;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
