package com.ikth.apps.msreserve.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikth.apps.msreserve.reservation.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
