package com.ikth.apps.msreserve.reservation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * items field 에 전시상품 (Product) 들을 담는다. totalCount 는 카테고리에 해당하는 전체 상품 수이다.
 */
@ApiModel(description = "items field 에 전시상품 (Product) 들을 담는다. totalCount 는 카테고리에 해당하는 전체 상품 수이다.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-05T12:37:37.915+09:00")

public class ProductResponse   {
  @JsonProperty("items")
  @Valid
  private List<Product> items = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  public ProductResponse items(List<Product> items) {
    this.items = items;
    return this;
  }

  public ProductResponse addItemsItem(Product itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<Product>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Product> getItems() {
    return items;
  }

  public void setItems(List<Product> items) {
    this.items = items;
  }

  public ProductResponse totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Get totalCount
   * @return totalCount
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductResponse productResponse = (ProductResponse) o;
    return Objects.equals(this.items, productResponse.items) &&
        Objects.equals(this.totalCount, productResponse.totalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, totalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductResponse {\n");
    
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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

