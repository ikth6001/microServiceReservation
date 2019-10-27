package com.ikth.apps.msreserve.reservation.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.ikth.apps.msreserve.reservation.entity.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, QueryByExampleExecutor<Product> {

	@Query("SELECT tbl FROM Product tbl WHERE category_id=:categoryId")
	public List<Product> findAllByCategory(@Param("categoryId") int categoryId, Pageable pageable);
}
