package org.example.service;

import org.example.payload.OrderData;
import org.example.repository.OrderRepo;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

  @Autowired
  private OrderRepo userRepo;

  @Autowired
  @PersistenceContext
  private EntityManager em;

  public boolean createOrder(
      int productid,
      int userid,
      int statusid,
      int emplid,
      int vehicleid,
      String departure,
      String destination,
      int distance
  ) {
    try {
      em.createNamedStoredProcedureQuery("EmplkDistribution")
          .setParameter("productid",productid)
          .setParameter("userid",userid)
          .setParameter("statusid",statusid)
          .setParameter("emplid",emplid)
          .setParameter("vehicleid",vehicleid)
          .setParameter("departure",departure)
          .setParameter("destination",destination)
          .setParameter("distance",distance)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean finishOrder(int id) {
    try {
      em.createNamedStoredProcedureQuery("CompleteTheOrder")
          .setParameter("orderid",id)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean deleteOrder(int id) {
    try {
      em.createNamedStoredProcedureQuery("OrderDelete")
          .setParameter("orderid",id)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }
  public float getTotalSum() {
    List results = em.createNamedStoredProcedureQuery("SumCount").getResultList();
    float totalSum = 0;
    for(int i = 0; i < results.size(); ++i) {
      Object[] obj = (Object[]) results.get(i);
      totalSum += (float) obj[1];
    }
    return totalSum;
  }

  public List<OrderData> getOrdersOnTheWay() {
    List queryResults = em.createNamedStoredProcedureQuery("OrderOnTheWay").getResultList();
    List<OrderData> orderList = this.toOrderData(queryResults);
    return orderList;
  }

  public List<OrderData> toOrderData(List data) {
    List<OrderData> result = new ArrayList<>();

    for(int i = 0; i < data.size(); ++i) {
      Object[] obj = (Object[]) data.get(i);
      result.add(
          new OrderData(
              (Integer) obj[0],
              (String) obj[1],
              (String) obj[2],
              (String) obj[3],
              (Float) obj[4],
              (String) obj[5],
              (String) obj[6],
              (Float) obj[7]
          )
      );
    }

    return result;
  }
}
