package org.example.repository;

import org.example.entity.Employee;
import org.example.entity.Product;
import org.example.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

  @Query(
      value = "select\n" +
          "  id_product, ProductName AS product_name, ProductWeight AS product_weight \n" +
          "from \n" +
          "  product \n",
      nativeQuery = true
  )
  List<Product> getAll();

  @Query(
      value = "select\n" +
          "  id_product, ProductName AS product_name, ProductWeight AS product_weight \n" +
          "from \n" +
          "  product where id_product = ?1 \n",
      nativeQuery = true
  )
  Product findById(int id);
}
