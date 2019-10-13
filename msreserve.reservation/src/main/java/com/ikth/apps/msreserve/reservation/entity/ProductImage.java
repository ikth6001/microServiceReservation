package com.ikth.apps.msreserve.reservation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="PRODUCT_IMAGE")
public class ProductImage {

	@Id
	private long id;
	private long productId;
	private String type;
	private long fileId;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
}
