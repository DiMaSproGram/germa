package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
@NamedStoredProcedureQueries({
//    @NamedStoredProcedureQuery(
//        name = "SumCount",
//        procedureName = "SumCount"
//    ),
//    @NamedStoredProcedureQuery(
//        name = "OrderOnTheWay",
//        procedureName = "OrderOnTheWay"
//    ),
    @NamedStoredProcedureQuery(
        name = "VehicleRemove",
        procedureName = "VehicleRemove",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "vehicleid",type=Integer.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "VehicleAdd",
        procedureName = "VehicleAdd",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "firmname",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "brand",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "platenumber",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "trcost",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "trailerlenght",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "liftingcapacity",type=Integer.class),
        }
    ),
    @NamedStoredProcedureQuery(
        name = "VehicleUpdate",
        procedureName = "VehicleUpdate",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "vehicleid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "firmname",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "brand",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "platenumber",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "trcost",type=Float.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "trailerlenght",type=Float.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "liftingcapacity",type=Float.class)
        }
    )
})
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_vehicle")
  public int vehicleId;

  @Column(name = "Firmname")
  private String firmName;

  @Column(name = "Brand")
  private String brand;

  @Column(name = "PlateNumber")
  private String plateNumber;

  @Column(name = "TransportationCost")
  private float transportationCost;

  @Column(name = "TrailerLenght")
  private float trailerLenght;

  @Column(name = "LiftingCapacity")
  private float liftingCapacity;
}
