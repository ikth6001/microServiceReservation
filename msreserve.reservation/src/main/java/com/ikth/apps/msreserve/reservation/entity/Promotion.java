package com.ikth.apps.msreserve.reservation.entity;

import javax.persistence.Column;
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
	@Column(name = "product_id")
	private long productId;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Product product;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
