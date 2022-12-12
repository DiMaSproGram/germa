package org.example.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class EmployeeData {

  public String name;
  public BigInteger count;

  public EmployeeData(String name, BigInteger count) {
    this.name = name;
    this.count = count;
  }
}
