package com.ikth.apps.msreserve.reservation.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 상품 (Product) 모델
 */
@ApiModel(description = "상품 (Product) 모델")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-05T12:37:37.915+09:00")

public class Product   {
  @JsonProperty("displayInfoId")
  private Integer displayInfoId = null;

  @JsonProperty("placeName")
  private String placeName = null;

  @JsonProperty("productContent")
  private String productContent = null;

  @JsonProperty("productDescription")
  private String productDescription = null;

  @JsonProperty("productId")
  private Integer productId = null;

  @JsonProperty("productImageUrl")
  private String productImageUrl = null;

  public Product displayInfoId(Integer displayInfoId) {
    this.displayInfoId = displayInfoId;
    return this;
  }

  /**
   * 전시 (display_info) Id
   * @return displayInfoId
  **/
  @ApiModelProperty(value = "전시 (display_info) Id")


  public Integer getDisplayInfoId() {
    return displayInfoId;
  }

  public void setDisplayInfoId(Integer displayInfoId) {
    this.displayInfoId = displayInfoId;
  }

  public Product placeName(String placeName) {
    this.placeName = placeName;
    return this;
  }

  /**
   * 전시 장소 명
   * @return placeName
  **/
  @ApiModelProperty(value = "전시 장소 명")


  public String getPlaceName() {
    return placeName;
  }

  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }

  public Product productContent(String productContent) {
    this.productContent = productContent;
    return this;
  }

  /**
   * 상품 상세 설명
   * @return productContent
  **/
  @ApiModelProperty(value = "상품 상세 설명")


  public String getProductContent() {
    return productContent;
  }

  public void setProductContent(String productContent) {
    this.productContent = productContent;
  }

  public Product productDescription(String productDescription) {
    this.productDescription = productDescription;
    return this;
  }

  /**
   * 상품 설명
   * @return productDescription
  **/
  @ApiModelProperty(value = "상품 설명")


  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public Product productId(Integer productId) {
    this.productId = productId;
    return this;
  }

  /**
   * 상품 (product) Id
   * @return productId
  **/
  @ApiModelProperty(value = "상품 (product) Id")


  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Product productImageUrl(String productImageUrl) {
    this.productImageUrl = productImageUrl;
    return this;
  }

  /**
   * 상품 썸네일 이미지 URL
   * @return productImageUrl
  **/
  @ApiModelProperty(value = "상품 썸네일 이미지 URL")


  public String getProductImageUrl() {
    return productImageUrl;
  }

  public void setProductImageUrl(String productImageUrl) {
    this.productImageUrl = productImageUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.displayInfoId, product.displayInfoId) &&
        Objects.equals(this.placeName, product.placeName) &&
        Objects.equals(this.productContent, product.productContent) &&
        Objects.equals(this.productDescription, product.productDescription) &&
        Objects.equals(this.productId, product.productId) &&
        Objects.equals(this.productImageUrl, product.productImageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayInfoId, placeName, productContent, productDescription, productId, productImageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    displayInfoId: ").append(toIndentedString(displayInfoId)).append("\n");
    sb.append("    placeName: ").append(toIndentedString(placeName)).append("\n");
    sb.append("    productContent: ").append(toIndentedString(productContent)).append("\n");
    sb.append("    productDescription: ").append(toIndentedString(productDescription)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    productImageUrl: ").append(toIndentedString(productImageUrl)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

