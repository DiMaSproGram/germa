package org.example.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class VehicleData {

  public String plateNumber;
  public BigInteger finishedOrders;

  public VehicleData(String plateNumber, BigInteger finishedOrders) {
    this.plateNumber = plateNumber;
    this.finishedOrders = finishedOrders;
  }
}
