package org.example.repository;

import org.example.entity.EmplType;
import org.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkersTypeRepo extends JpaRepository<EmplType, Integer> {

  @Query(
      value = "select\n" +
          "  id_type, Typename \n" +
          "from \n" +
          "  empltype \n",
      nativeQuery = true
  )
  List<EmplType> getTypes();
}
