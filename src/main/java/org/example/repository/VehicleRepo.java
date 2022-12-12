package org.example.repository;

import org.example.entity.Employee;
import org.example.entity.User;
import org.example.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

  @Query(
      value = "select\n" +
          "  id_vehicle, Firmname, Brand, PlateNumber AS plate_number, TransportationCost AS transportation_cost, TrailerLenght AS trailer_lenght, LiftingCapacity AS lifting_capacity \n" +
          "from \n" +
          "  vehicle \n",
      nativeQuery = true
  )
  List<Vehicle> getAll();

  @Query(
      value = "select\n" +
          "  id_vehicle, Firmname, Brand, PlateNumber AS plate_number, TransportationCost AS transportation_cost, TrailerLenght AS trailer_lenght, LiftingCapacity AS lifting_capacity \n" +
          "from \n" +
          "  vehicle where id_vehicle = ?1 \n",
      nativeQuery = true
  )
  Vehicle findById(int id);

  @Query(
      value = "select `vehicle`.`PlateNumber`, count(`orderr`.`id_vehicle`) \n \n" +
          "from (`orderr` join `vehicle` on((`vehicle`.`id_vehicle` = `orderr`.`id_vehicle`))) \n" +
          "where ((`orderr`.`id_status` = 2) and `orderr`.`id_empl` in (select `employee`.`id_empl` from `employee` where (`employee`.`id_type` = 1)))  \n" +
          "group by `vehicle`.`PlateNumber` order by count(`orderr`.`id_order`) desc \n",
      nativeQuery = true
  )
  List<Object> getBestVehicle();

  @Query(
      value = "select \n" +
          "  id_vehicle, Firmname, Brand, PlateNumber AS plate_number, TransportationCost AS transportation_cost, TrailerLenght AS trailer_lenght, LiftingCapacity AS lifting_capacity \n" +
          "from vehicle \n" +
          "ORDER BY vehicle.LiftingCapacity DESC LIMIT 5 \n",
      nativeQuery = true
  )
  List<Vehicle> getTopLifting();
}
