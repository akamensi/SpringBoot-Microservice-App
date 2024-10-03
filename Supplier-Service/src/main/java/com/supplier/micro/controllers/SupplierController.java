package com.supplier.micro.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.supplier.micro.entities.SupplierDTO;
import com.supplier.micro.services.ISupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
	
    @Autowired
    private ISupplierService supplierService;
    
    
    @GetMapping
    public List<SupplierDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierDTO getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.createSupplier(supplierDTO);
    }

    @PutMapping("/{id}")
    public SupplierDTO updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        return supplierService.updateSupplier(id, supplierDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }

}
