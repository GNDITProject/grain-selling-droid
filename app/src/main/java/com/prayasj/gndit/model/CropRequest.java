package com.prayasj.gndit.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CropRequest {
  private Crop crop;
  private BigDecimal price;
  private BigDecimal quantity;
  private Long createdAt;

  public BigDecimal getPrice(){
    return price;
  }

  public BigDecimal getQuantity(){
    return quantity;
  }

  public Crop getCrop(){
    return crop;
  }

  public String getCreatedAt() {
    Date createAtInDateObject = new Date(createdAt);
    return new SimpleDateFormat("dd LLL, yyyy").format(createAtInDateObject);
  }
}

