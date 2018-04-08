package com.prayasj.gndit.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CropRequest {
  private String cropName;
  private BigDecimal price;
  private BigDecimal quantity;
  private Long createdAt;
  private String requestId;

  public BigDecimal getPrice(){
    return price;
  }

  public BigDecimal getQuantity(){
    return quantity;
  }

  public String getCropName(){
    return cropName;
  }

  public String getCreatedAt() {
    Date createAtInDateObject = new Date(createdAt);
    return new SimpleDateFormat("dd LLL, yyyy").format(createAtInDateObject);
  }

  public String getRequestId() {
    return requestId;
  }
}

