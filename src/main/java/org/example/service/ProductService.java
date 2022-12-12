package org.example.service;

import org.example.entity.Employee;
import org.example.entity.Product;
import org.example.entity.Vehicle;
import org.example.repository.ProductRepo;
import org.example.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductRepo productRepo;

  @Autowired
  @PersistenceContext
  private EntityManager em;

  public List<Product> getAll() {
    return productRepo.getAll();
  }

  public Product findById(int id) {
    return productRepo.findById(id);
  }
  public boolean createProduct(
      String name,
      Float weight
  ) {
    try {
      em.createNamedStoredProcedureQuery("ProductAdd")
          .setParameter("productname",name)
          .setParameter("productweight",weight)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean updateProduct(
      int productId,
      String name,
      Float weight
  ) {
    try {
      em.createNamedStoredProcedureQuery("ProductUpdate")
          .setParameter("productid", productId)
          .setParameter("productname",name)
          .setParameter("productweight",weight)
          .execute();
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
