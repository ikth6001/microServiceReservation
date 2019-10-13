package com.ikth.apps.msreserve.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikth.apps.msreserve.reservation.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
