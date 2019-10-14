package com.ikth.apps.msreserve.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikth.apps.msreserve.reservation.dto.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
