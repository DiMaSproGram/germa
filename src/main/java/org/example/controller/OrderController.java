package org.example.controller;

import org.example.entity.Employee;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.entity.Vehicle;
import org.example.payload.OrderData;
import org.example.service.*;
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
public class OrderController {

  @Autowired
  private UserService userService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private WorkersService workersService;
  @Autowired
  private VehicleService vehicleService;
  @Autowired
  private ProductService productService;


  @GetMapping("/orders")
  public String main(@AuthenticationPrincipal User user, Model model) {
    List<OrderData> orderList = orderService.getOrdersOnTheWay();
    model.addAttribute("orders", orderList);
    if (orderList.isEmpty()) {
      model.addAttribute("error", "Нет заказов в пути");
    }
    return "orders";
  }

  @PostMapping("/orders/search")
  public String searchByWeight(
      @RequestParam String search,
      @RequestParam String type,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    List<OrderData> orderList = orderService.getOrdersOnTheWay();
    if (!search.isEmpty()) {
      orderList = orderList.stream().filter(orderData -> {
        if (type.equals("weight")) {
          return orderData.getProductWeight() == Integer.parseInt(search);
        } else {
          return orderData.getDistance() == Integer.parseInt(search);
        }
      }).collect(Collectors.toList());
    }
    model.addAttribute("orders", orderList);
    if (orderList.isEmpty()) {
      model.addAttribute("error", "Нет заказов в пути");
    }
    return "orders";
  }

  @PostMapping("/orders/finish/{id}")
  public String finishOrder(
      @PathVariable("id") String id,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !orderService.finishOrder(Integer.parseInt(id));
    List<OrderData> orderList = orderService.getOrdersOnTheWay();

    model.addAttribute("orders", orderList);
    if (isError) {
      model.addAttribute("error", "Ошибка при завершении заказа");
    }
    if (orderList.isEmpty() && !isError) {
      model.addAttribute("error", "Нет заказов в пути");
    }
    return "orders";
  }

  @PostMapping("/orders/delete/{id}")
  public String deleteOrder(
      @PathVariable("id") String id,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !orderService.deleteOrder(Integer.parseInt(id));
    List<OrderData> orderList = orderService.getOrdersOnTheWay();

    model.addAttribute("orders", orderList);
    if (isError) {
      model.addAttribute("error", "Ошибка при удалении заказа");
    }
    if (orderList.isEmpty() && !isError) {
      model.addAttribute("error", "Нет заказов в пути");
    }
    return "orders";
  }

  @GetMapping("/orders/add")
  public String add(@AuthenticationPrincipal User user, Model model) {
    List<Employee> workersList = workersService.getAll();
    List<Vehicle> vehicleList = vehicleService.getAll();
    List<Product> productList = productService.getAll();

    model.addAttribute("workerDrivers", workersList.stream().filter(worker -> worker.getTypeId() == 1).collect(Collectors.toList()));
    model.addAttribute("transport", vehicleList);
    model.addAttribute("products", productList);
    model.addAttribute("workerHelpers", workersList.stream().filter(worker -> worker.getTypeId() == 2).collect(Collectors.toList()));

    return "order-add";
  }

  @PostMapping("/orders/add")
  public String createOrder(
      @RequestParam int productId,
      @RequestParam int emplId,
      @RequestParam int vehicleId,
      @RequestParam String departure,
      @RequestParam String destination,
      @RequestParam int distance,
      @AuthenticationPrincipal User user,
      Model model
  ) {

    boolean isError = !orderService.createOrder(
        productId,
        user.getUserId(),
        1,
        emplId,
        vehicleId,
        departure,
        destination,
        distance
    );
    if (isError) {
      model.addAttribute("error", "Ошибка при добавлении заказа");
      return "order-add";
    }
    return "redirect:/orders";
  }
}
