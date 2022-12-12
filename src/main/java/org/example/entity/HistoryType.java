package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "historytype")
public class HistoryType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_historytype")
  public int historyTypeId;

  @Column(name = "discription")
  private String description;
}
