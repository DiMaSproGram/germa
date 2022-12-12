package org.example.service;

import org.example.entity.History;
import org.example.payload.HistoryData;
import org.example.payload.OrderData;
import org.example.repository.HistoryRepo;
import org.example.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoryService {

  @Autowired
  private HistoryRepo historyRepo;

  @Autowired
  @PersistenceContext
  private EntityManager em;

  public List<HistoryData> getAll() {
    List queryResults = historyRepo.getAll();
    List<HistoryData> orderList = this.toHistoryData(queryResults);
    return orderList;
  }

  public List<HistoryData> toHistoryData(List data) {
    List<HistoryData> result = new ArrayList<>();

    for(int i = 0; i < data.size(); ++i) {
      Object[] obj = (Object[]) data.get(i);
      result.add(
          new HistoryData(
              (String) obj[0],
              (String) obj[1],
              (Date) obj[2],
              (String) obj[3]
          )
      );
    }

    return result;
  }
}
