package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "history")
public class History {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_history")
  public int historyId;

  @Column(name = "id_user")
  public int userId;

  @Column(name = "id_historytype")
  public int historyTypeId;

  @Column(name = "Additional")
  private String additional;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "AddTime")
  private Date addTime;

  public History(int historyId, int userId, int historyTypeId, String additional, Date addTime) {
    this.historyId = historyId;
    this.userId = userId;
    this.historyTypeId = historyTypeId;
    this.additional = additional;
    this.addTime = addTime;
  }
}
