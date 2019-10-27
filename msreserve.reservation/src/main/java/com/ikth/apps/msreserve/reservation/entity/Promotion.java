package com.ikth.apps.msreserve.reservation.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author taehoonkim
 *
 */
@Entity
public class Promotion {

	@Id
	private long id;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="productId", referencedColumnName = "id")
	private Product product;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
