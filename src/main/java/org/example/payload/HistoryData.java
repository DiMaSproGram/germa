package org.example.payload;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class HistoryData {

  public String username;
  public String description;
  public Date timeAdd;
  public String additional;

  public HistoryData(String username, String description, Date timeAdd, String additional) {
    this.username = username;
    this.description = description;
    this.timeAdd = timeAdd;
    this.additional = additional;
  }
}
