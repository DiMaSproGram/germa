package org.example.controller;

import org.example.entity.EmplType;
import org.example.entity.Employee;
import org.example.entity.User;
import org.example.entity.Vehicle;
import org.example.payload.OrderData;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.example.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;


  @GetMapping("/transport")
  public String main(@AuthenticationPrincipal User user, Model model) {
    List<Vehicle> transportList = vehicleService.getAll();

    model.addAttribute("transport", transportList);
    if (transportList.isEmpty()) {
      model.addAttribute("error", "Нет транспорта");
    }
    return "transport";
  }

  @PostMapping("/transport/search")
  public String search(
      @RequestParam String search,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    List<Vehicle> transportList = vehicleService.getAll();
    if (!search.isEmpty()) {
      transportList = transportList.stream()
          .filter(transportData -> transportData.getPlateNumber().contains(search))
          .collect(Collectors.toList());
    }
    model.addAttribute("transport", transportList);
    if (transportList.isEmpty()) {
      model.addAttribute("error", "Нет транспорта");
    }
    return "transport";
  }

//  @PostMapping("/transport/edit/{id}")
//  public String finishTransport(
//      @PathVariable("id") String id,
//      @AuthenticationPrincipal User user,
//      Model model
//  ) {
//    boolean isError = !vehicleService.finishTransport(Integer.parseInt(id));
//    List<Vehicle> transportList = vehicleService.getAll();
//
//    model.addAttribute("transport", transportList);
//    if (isError) {
//      model.addAttribute("error", "Ошибка при завершении заказа");
//    }
//    if (transportList.isEmpty() && !isError) {
//      model.addAttribute("error", "Нет транспорта");
//    }
//    return "transport";
//  }

  @PostMapping("/transport/delete/{id}")
  public String deleteTransport(
      @PathVariable("id") String id,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !vehicleService.deleteTransport(Integer.parseInt(id));
    List<Vehicle> transportList = vehicleService.getAll();

    model.addAttribute("transport", transportList);
    if (isError) {
      model.addAttribute("error", "Ошибка при удалении заказа");
    }
    if (transportList.isEmpty() && !isError) {
      model.addAttribute("error", "Нет транспорта");
    }
    return "transport";
  }

  @GetMapping("/transport/add")
  public String addVehiclePage(@AuthenticationPrincipal User user, Model model) {
    return "transport-add";
  }

  @PostMapping("/transport/add")
  public String addVehicle(
      @RequestParam String plateNumber,
      @RequestParam String mark,
      @RequestParam String firmName,
      @RequestParam int capacity,
      @RequestParam int length,
      @RequestParam int cost,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !vehicleService.createTransport(
        plateNumber,
        mark,
        firmName,
        capacity,
        length,
        cost
    );
    if (isError) {
      model.addAttribute("message", "Неполучилось добавить транспорт");
      return "redirect:/transport/add";
    }
    return "redirect:/transport";
  }

  @GetMapping("/transport/edit/{id}")
  public String transportEditPage(
      @PathVariable("id") String id,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    Vehicle employee = vehicleService.findById(Integer.parseInt(id));

    model.addAttribute("transport", employee);

    return "transport-edit";
  }

  @PostMapping("/transport/edit/{id}")
  public String transportEdit(
      @PathVariable("id") String id,
      @RequestParam String plateNumber,
      @RequestParam String mark,
      @RequestParam String firmName,
      @RequestParam String liftingCapacity,
      @RequestParam String trailerLenght,
      @RequestParam String trCost,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !vehicleService.updateVehicle(
        Integer.parseInt(id),
        firmName,
        mark,
        plateNumber,
        Float.parseFloat(trCost),
        Float.parseFloat(trailerLenght),
        Float.parseFloat(liftingCapacity)
    );

    if (isError) {
      model.addAttribute("message", "Неполучилось внести изменения в транспорт");
      return "redirect:/transport/edit/" + id;
    }
    return "redirect:/transport";
  }
}
