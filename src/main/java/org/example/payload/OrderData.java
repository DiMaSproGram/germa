package org.example.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderData {
  public int id;
  public String emplName;
  public String plateNumber;
  public String productName;
  public Float productWeight;
  public String departure;
  public String destination;
  public Float distance;

  public OrderData() {
  }

  public OrderData(int id, String emplName, String plateNumber, String productName, Float productWeight, String departure, String destination, Float distance) {
    this.id = id;
    this.emplName = emplName;
    this.plateNumber = plateNumber;
    this.productName = productName;
    this.productWeight = productWeight;
    this.departure = departure;
    this.destination = destination;
    this.distance = distance;
  }
}
