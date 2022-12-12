package org.example.repository;

import org.example.entity.EmplType;
import org.example.entity.Employee;
import org.example.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface WorkersRepo extends JpaRepository<Employee, Integer> {

  @Query(
      value = "select\n" +
          "  id_empl, id_type, Emplname, Classiness, PassportData AS passport_data, Statuss \n" +
          "from \n" +
          "  employee \n",
      nativeQuery = true
  )
  List<Employee> getAll();

  @Query(
      value = "select\n" +
          "  id_empl, id_type, Emplname, Classiness, PassportData AS passport_data, Statuss \n" +
          "from \n" +
          "  employee where id_empl = ?1 \n",
      nativeQuery = true
  )
  Employee findById(int id);

  @Query(
      value = "select `employee`.`Emplname`, count(`orderr`.`id_order`) \n" +
          "from (`orderr` join `employee` on((`employee`.`id_empl` = `orderr`.`id_empl`)))  \n" +
          "where ((`orderr`.`id_status` = 2) and `orderr`.`id_empl` in (select `employee`.`id_empl` from `employee` where (`employee`.`id_type` = 1)))  \n" +
          "group by `employee`.`Emplname` order by count(`orderr`.`id_order`) desc limit 100  \n",
      nativeQuery = true
  )
  List<Object> getTopDrivers();
}
