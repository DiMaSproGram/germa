package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.payload.OrderData;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orderr")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "SumCount",
        procedureName = "SumCount"
    ),
    @NamedStoredProcedureQuery(
        name = "OrderOnTheWay",
        procedureName = "OrderOnTheWay"
    ),
    @NamedStoredProcedureQuery(
        name = "OrderDelete",
        procedureName = "OrderDelete",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid",type=Integer.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "CompleteTheOrder",
        procedureName = "CompleteTheOrder",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid",type=Integer.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "EmplkDistribution",
        procedureName = "EmplkDistribution",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "productid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "userid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "statusid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "emplid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "vehicleid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "departure",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "destination",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "distance",type=Integer.class),
        }
    )
})
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_order")
  public int orderId;

  @Column(name = "id_vehicle")
  public int vehicleId;

  @Column(name = "id_product")
  public int productId;

  @Column(name = "id_empl")
  public int emplId;

  @Column(name = "id_user")
  public int userId;

  @Column(name = "id_status")
  public int statusId;

  @Column(name = "Departure")
  private String departure;

  @Column(name = "Destination")
  private String destination;

  @Column(name = "Distance")
  private float distance;
}
