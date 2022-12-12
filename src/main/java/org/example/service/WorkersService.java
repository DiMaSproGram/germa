package org.example.service;

import org.example.entity.EmplType;
import org.example.entity.Employee;
import org.example.payload.EmployeeData;
import org.example.payload.HistoryData;
import org.example.repository.WorkersRepo;
import org.example.repository.WorkersTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkersService {

  @Autowired
  private WorkersRepo workersRepo;

  @Autowired
  private WorkersTypeRepo workersTypeRepo;

  @Autowired
  @PersistenceContext
  private EntityManager em;

  public List<Employee> getAll() {
    return workersRepo.getAll();
  }

  public Employee findById(int id) {
    return workersRepo.findById(id);
  }
  public boolean createWorker(
      String name,
      String position,
      String passportData,
      int typeId
  ) {
    try {
      em.createNamedStoredProcedureQuery("EmplAdd")
          .setParameter("typeid",typeId)
          .setParameter("my_name",name)
          .setParameter("class",position)
          .setParameter("passport",passportData)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean updateWorker(
      int id,
      String name,
      String position,
      String passportData,
      int typeId
  ) {
    try {
      em.createNamedStoredProcedureQuery("EmplUpdate")
          .setParameter("id",id)
          .setParameter("typeid",typeId)
          .setParameter("my_name",name)
          .setParameter("class",position)
          .setParameter("passport",passportData)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public List<EmplType> getTypes() {
    return workersTypeRepo.getTypes();
  }

  public boolean finishOrder(int id) {
    try {
      em.createNamedStoredProcedureQuery("CompleteTheOrder")
          .setParameter("orderid",id)
          .getResultList();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean deleteEmpl(int id) {
    try {
      em.createNamedStoredProcedureQuery("EmplRemove")
          .setParameter("emplid",id)
          .getResultList();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public List<EmployeeData> getTopDrivers() {
    List queryResults = workersRepo.getTopDrivers();
    List<EmployeeData> topDrivers = this.toEmployee(queryResults);
    return topDrivers;
  }

  public List<EmployeeData> toEmployee(List data) {
    List<EmployeeData> result = new ArrayList<>();

    for(int i = 0; i < data.size(); ++i) {
      Object[] obj = (Object[]) data.get(i);
      result.add(
          new EmployeeData(
              (String) obj[0],
              (BigInteger) obj[1]
          )
      );
    }

    return result;
  }
}
