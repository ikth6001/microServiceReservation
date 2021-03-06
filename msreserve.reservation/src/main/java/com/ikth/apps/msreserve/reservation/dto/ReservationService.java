package com.ikth.apps.msreserve.reservation.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ikth.apps.msreserve.reservation.entity.FileInfo;
import com.ikth.apps.msreserve.reservation.entity.Promotion;
import com.ikth.apps.msreserve.reservation.repository.CategoryRepository;
import com.ikth.apps.msreserve.reservation.repository.ProductRepository;
import com.ikth.apps.msreserve.reservation.repository.PromotionRepository;

@Service
public class ReservationService {

	@SuppressWarnings("unused")
	private Logger logger= LoggerFactory.getLogger(ReservationService.class);
	
	@Autowired
	private PromotionRepository promotionRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	private Function<com.ikth.apps.msreserve.reservation.entity.Product, Product> fncProductAdapt= (p) -> {
		Product product= new Product();
		product.setProductContent(p.getContent());
		product.setProductDescription(p.getDescription());
		FileInfo img= p.getProductImage() == null || p.getProductImage().size() < 1 ? null
						: p.getProductImage().get(0).getFileInfo();
		product.setProductImageUrl(img == null ? "" : "image/" + img.getFileName());
		
		return product;
	};
	
	public List<Product> getPromotionList() {
		List<Promotion> promotions= this.promotionRepo.findAll();
		return promotions.stream()
						 .map(Promotion::getProduct)
						 .map(this.fncProductAdapt)
						 .collect(Collectors.toList());
	}
	
	public List<Category> getCategories() {
		return this.categoryRepo.findAll();
	}
	
	private final int PAGE_SIZE= 4;
	
	public List<Product> getProducts(int categoryId, int start) {
		PageRequest pageRequest= PageRequest.of(start / PAGE_SIZE, PAGE_SIZE);
		
		Iterable<com.ikth.apps.msreserve.reservation.entity.Product> products= null;
		if(categoryId == 0) {
			products= productRepo.findAll(pageRequest);
		} else {
			products= productRepo.findAllByCategory(categoryId, pageRequest);
		}
		return StreamSupport.stream(products.spliterator(), false)
							.map(fncProductAdapt)
							.collect(Collectors.toList());
	}
}
