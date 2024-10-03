package com.supplier.micro.services;

import java.util.List;

import com.supplier.micro.entities.SupplierDTO;

public interface ISupplierService {
	
    List<SupplierDTO> getAllSuppliers();
    
    SupplierDTO getSupplierById(Long id);
    
    SupplierDTO createSupplier(SupplierDTO supplierDTO);
    
    SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO);
    
    void deleteSupplier(Long id);

}
