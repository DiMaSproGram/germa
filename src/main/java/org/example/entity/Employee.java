package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "employee")
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
        name = "EmplRemove",
        procedureName = "EmplRemove",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "emplid",type=Integer.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "EmplAdd",
        procedureName = "EmplAdd",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "typeid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "my_name",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "class",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "passport",type=String.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "EmplUpdate",
        procedureName = "EmplUpdate",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "id",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "typeid",type=Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "my_name",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "class",type=String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "passport",type=String.class)
        }
    )
})
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_empl")
  public int emplId;

  @Column(name = "id_type")
  public int typeId;

  @Column(name = "Emplname")
  private String emplName;

  @Column(name = "Classiness")
  private String classiness;

  @Column(name = "PassportData")
  private String passportData;

  @Column(name = "Statuss")
  private boolean status;
}
