package org.example.controller;

import org.example.entity.Employee;
import org.example.entity.User;
import org.example.entity.Vehicle;
import org.example.payload.EmployeeData;
import org.example.payload.HistoryData;
import org.example.payload.VehicleData;
import org.example.service.HistoryService;
import org.example.service.UserService;
import org.example.service.VehicleService;
import org.example.service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TopListController {

  @Autowired
  private WorkersService workersService;
  @Autowired
  private VehicleService vehicleService;


  @GetMapping("/lists")
  public String history(@AuthenticationPrincipal User user, Model model) {
    List<EmployeeData> topDrivers = workersService.getTopDrivers();
    List<Vehicle> topLifting = vehicleService.getTopLifting();
    List<VehicleData> topVehicle = vehicleService.getTopVehicles();

    model.addAttribute("topDrivers", topDrivers);
    model.addAttribute("topLifting", topLifting);
    model.addAttribute("topVehicle", topVehicle);
    return "lists";
  }
}
