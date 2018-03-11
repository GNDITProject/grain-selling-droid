package com.prayasj.gndit.dto;

import java.math.BigDecimal;

public class CropRequestDto {
  private String cropName;
  private BigDecimal price;
  private BigDecimal quantity;

  public CropRequestDto(String cropName, String quantity, String price) {
    this.cropName =  cropName;
    this.quantity = new BigDecimal(quantity);
    this.price = new BigDecimal(price);
  }
}
