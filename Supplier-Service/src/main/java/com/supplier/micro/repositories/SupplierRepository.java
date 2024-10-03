package com.supplier.micro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplier.micro.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
