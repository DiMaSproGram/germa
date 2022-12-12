package org.example.service;

import org.example.entity.Employee;
import org.example.entity.Vehicle;
import org.example.payload.EmployeeData;
import org.example.payload.OrderData;
import org.example.payload.VehicleData;
import org.example.repository.OrderRepo;
import org.example.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

  @Autowired
  private VehicleRepo vehicleRepo;

  @Autowired
  @PersistenceContext
  private EntityManager em;

  public List<Vehicle> getAll() {
    return vehicleRepo.getAll();
  }

  public Vehicle findById(int id) {
    return vehicleRepo.findById(id);
  }
  public boolean createTransport(
      String plateNumber,
      String mark,
      String firmName,
      int capacity,
      int length,
      int cost
  ) {
    try {
      em.createNamedStoredProcedureQuery("VehicleAdd")
          .setParameter("firmname",firmName)
          .setParameter("brand",mark)
          .setParameter("platenumber",plateNumber)
          .setParameter("trcost",capacity)
          .setParameter("trailerlenght",length)
          .setParameter("liftingcapacity",cost)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean updateVehicle(
      int vehicleId,
      String firmName,
      String brand,
      String plateNumber,
      Float trCost,
      Float trailerLenght,
      Float liftingCapacity
  ) {
    try {
      em.createNamedStoredProcedureQuery("VehicleUpdate")
          .setParameter("vehicleid",vehicleId)
          .setParameter("firmname",firmName)
          .setParameter("brand",brand)
          .setParameter("platenumber",plateNumber)
          .setParameter("trcost",trCost)
          .setParameter("trailerlenght",trailerLenght)
          .setParameter("liftingcapacity",liftingCapacity)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean finishTransport(int id) {
    try {
      em.createNamedStoredProcedureQuery("CompleteTheOrder")
          .setParameter("orderid",id)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean deleteTransport(int id) {
    try {
      em.createNamedStoredProcedureQuery("VehicleRemove")
          .setParameter("vehicleid",id)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public List<Vehicle> getTopLifting() {
    List<Vehicle> topDrivers = vehicleRepo.getTopLifting();
    return topDrivers;
  }

  public List<VehicleData> getTopVehicles() {
    List queryResults = vehicleRepo.getBestVehicle();
    List<VehicleData> topDrivers = this.toVehicleData(queryResults);
    return topDrivers;
  }

  public List<VehicleData> toVehicleData(List data) {
    List<VehicleData> result = new ArrayList<>();

    for(int i = 0; i < data.size(); ++i) {
      Object[] obj = (Object[]) data.get(i);
      result.add(
          new VehicleData(
              (String) obj[0],
              (BigInteger) obj[1]
          )
      );
    }

    return result;
  }
}
