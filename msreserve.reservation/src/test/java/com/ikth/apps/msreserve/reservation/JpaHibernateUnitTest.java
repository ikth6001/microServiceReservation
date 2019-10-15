package com.ikth.apps.msreserve.reservation;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ikth.apps.msreserve.reservation.entity.Product;
import com.ikth.apps.msreserve.reservation.entity.Promotion;
import com.ikth.apps.msreserve.reservation.repository.ProductRepository;
import com.ikth.apps.msreserve.reservation.repository.PromotionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ReservationApplication.class })
public class JpaHibernateUnitTest {
	
	private final Logger logger= LoggerFactory.getLogger(JpaHibernateUnitTest.class);
	
	@Autowired
	private PromotionRepository promotionRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Test
	public void selectPromotionTest() {
		
		List<Promotion> promotions= promotionRepo.findAll();
		logger.debug("promotion count [{}]", promotions.size());
		for(Promotion promotion : promotions) {
			logger.debug("product id [{}]", promotion.getProduct() == null ? "null" : promotion.getProduct().getId());
		}
		assertEquals(true, promotions.size() > 0);
	}
	
	@Test
	public void selectProductByCategoryTest() {
//		ExampleMatcher categoryEq= ExampleMatcher.matchingAny()
//												 .withMatcher("category_id", ExampleMatcher.GenericPropertyMatchers.exact());
//		Product sample= new Product();
//		sample.setCategoryId(1L);
//		Example<Product> example= Example.of(sample, categoryEq);
		
		final int pageSize= 5;
		
		PageRequest pageReq= PageRequest.of(0, pageSize);
		Iterator<Product> productsByCategory= productRepo.findAllByCategory(1, pageReq).iterator();
		
		int count= 0;
		while(productsByCategory.hasNext()) {
			Product product= productsByCategory.next();
			logger.debug("product category is [{}]", product.getCategoryId());
			logger.debug("product id is [{}], product image's product id is [{}]", product.getId(), product.getProductImage().get(0).getProductId());
			count++;
		}

		assertEquals(true, count == pageSize);
	}
}
