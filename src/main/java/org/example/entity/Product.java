package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "ProductAdd",
        procedureName = "ProductAdd",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "productname",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "productweight",type=Float.class),
        }
    ),
    @NamedStoredProcedureQuery(
        name = "ProductUpdate",
        procedureName = "ProductUpdate",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "productid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "productname",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "productweight",type=Float.class)
        }
    )
})
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_product")
  public int id_product;

  @Column(name = "ProductName")
  private String productName;

  @Column(name = "ProductWeight")
  private float productWeight;
}
