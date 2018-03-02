package com.prayasj.gndit.model;

import java.math.BigDecimal;

public class CropRequest {
  private Crop crop;
  private BigDecimal price;
  private BigDecimal quantity;

  public BigDecimal getPrice(){
    return price;
  }

  public BigDecimal getQuantity(){
    return quantity;
  }

  public Crop getCrop(){
    return crop;
  }
}

