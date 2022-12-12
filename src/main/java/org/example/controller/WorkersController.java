package org.example.controller;

import org.example.entity.EmplType;
import org.example.entity.Employee;
import org.example.entity.User;
import org.example.service.WorkersService;
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
public class WorkersController {

  @Autowired
  private WorkersService workersService;


  @GetMapping("/workers")
  public String main(@AuthenticationPrincipal User user, Model model) {
    List<Employee> employeeList = workersService.getAll();

    model.addAttribute("workers", employeeList);
    if (employeeList.isEmpty()) {
      model.addAttribute("error", "Нет транспорта");
    }
    return "workers";
  }

  @PostMapping("/workers/search")
  public String search(
      @RequestParam String search,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    List<Employee> employeeList = workersService.getAll();
    if (!search.isEmpty()) {
      employeeList = employeeList.stream()
          .filter(transportData -> transportData.getEmplName().toLowerCase().contains(search.toLowerCase()))
          .collect(Collectors.toList());
    }
    model.addAttribute("workers", employeeList);
    if (employeeList.isEmpty()) {
      model.addAttribute("error", "Нет транспорта");
    }
    return "workers";
  }

//  @GetMapping("/worker/delete/{id}")
//  public String finishTransport(
//      @PathVariable("id") String id,
//      @AuthenticationPrincipal User user,
//      Model model
//  ) {
//    boolean isError = !workersService.finishOrder(Integer.parseInt(id));
//    List<Employee> employeeList = workersService.getAll();
//
//    model.addAttribute("workers", employeeList);
//    if (isError) {
//      model.addAttribute("error", "Ошибка при завершении заказа");
//    }
//    if (employeeList.isEmpty() && !isError) {
//      model.addAttribute("error", "Нет транспорта");
//    }
//    return "workers";
//  }

  @PostMapping("/worker/delete/{id}")
  public String deleteEmpl(
      @PathVariable("id") String id,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !workersService.deleteEmpl(Integer.parseInt(id));
    List<Employee> employeeList = workersService.getAll();

    model.addAttribute("workers", employeeList);
    if (isError) {
      model.addAttribute("error", "Ошибка при удалении заказа");
    }
    if (employeeList.isEmpty() && !isError) {
      model.addAttribute("error", "Нет транспорта");
    }
    return "workers";
  }

  @GetMapping("/worker/add")
  public String workerAddPage(@AuthenticationPrincipal User user, Model model) {
    List<EmplType> employeeTypeList = workersService.getTypes();

    model.addAttribute("types", employeeTypeList);

    return "worker-add";
  }

  @PostMapping("/worker/add")
  public String workerAdd(
      @RequestParam String name,
      @RequestParam String position,
      @RequestParam String passportData,
      @RequestParam int typeId,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !workersService.createWorker(name, position, passportData, typeId);

    if (isError) {
      model.addAttribute("message", "Неполучилось добавить работника");
      return "redirect:/worker/add";
    }
    return "redirect:/workers";
  }

  @GetMapping("/worker/edit/{id}")
  public String workerEditPage(
      @PathVariable("id") String id,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    Employee employee = workersService.findById(Integer.parseInt(id));
    List<EmplType> employeeTypeList = workersService.getTypes();

    model.addAttribute("types", employeeTypeList);
    model.addAttribute("employee", employee);

    return "worker-edit";
  }

  @PostMapping("/worker/edit/{id}")
  public String workerEdit(
      @PathVariable("id") String id,
      @RequestParam String name,
      @RequestParam String position,
      @RequestParam String passportData,
      @RequestParam int typeId,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !workersService.updateWorker(Integer.parseInt(id), name, position, passportData, typeId);

    if (isError) {
      model.addAttribute("message", "Неполучилось внести изменения в работника");
      return "redirect:/worker/edit/" + id;
    }
    return "redirect:/workers";
  }
}
