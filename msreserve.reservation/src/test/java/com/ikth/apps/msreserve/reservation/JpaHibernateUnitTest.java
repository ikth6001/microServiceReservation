package com.ikth.apps.msreserve.reservation;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ikth.apps.msreserve.reservation.entity.Promotion;
import com.ikth.apps.msreserve.reservation.repository.PromotionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ReservationApplication.class })
public class JpaHibernateUnitTest {
	
	private final Logger logger= LoggerFactory.getLogger(JpaHibernateUnitTest.class);
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Test
	public void selectPromotionTest() {
		
		List<Promotion> promotions= promotionRepository.findAll();
		logger.debug("promotion count [{}]", promotions.size());
		for(Promotion promotion : promotions) {
			logger.debug("product id [{}]", promotion.getProduct() == null ? "null" : promotion.getProduct().getId());
		}
		assertEquals(true, promotions.size() > 0);
	}
}
