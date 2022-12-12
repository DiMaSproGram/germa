package org.example.repository;

import org.example.entity.History;
import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<History, Integer> {

  @Query(
      value = "select\n" +
          "  dbUser.userName, historytype.discription, AddTime, Additional \n" +
          "from history \n" +
          "  join dbUser on dbUser.id_user = history.id_user \n" +
          "  join historytype on historytype.id_historytype = history.id_historytype \n",
      nativeQuery = true
  )
  List<Object> getAll();
}
