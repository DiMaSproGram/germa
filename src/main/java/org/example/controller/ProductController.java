package org.example.controller;

import org.example.entity.Product;
import org.example.entity.User;
import org.example.entity.Vehicle;
import org.example.service.ProductService;
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
public class ProductController {

  @Autowired
  private ProductService productService;


  @GetMapping("/products")
  public String main(@AuthenticationPrincipal User user, Model model) {
    List<Product> productList = productService.getAll();

    model.addAttribute("products", productList);
    if (productList.isEmpty()) {
      model.addAttribute("error", "Нет товаров");
    }
    return "products";
  }

  @PostMapping("/products/search")
  public String search(
      @RequestParam String search,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    List<Product> productList = productService.getAll();
    if (!search.isEmpty()) {
      productList = productList.stream()
          .filter(transportData -> transportData.getProductName().contains(search))
          .collect(Collectors.toList());
    }
    model.addAttribute("products", productList);
    if (productList.isEmpty()) {
      model.addAttribute("error", "Нет товаров");
    }
    return "products";
  }

  @GetMapping("/product/add")
  public String addVehiclePage(@AuthenticationPrincipal User user, Model model) {
    return "product-add";
  }

  @PostMapping("/product/add")
  public String addVehicle(
      @RequestParam String name,
      @RequestParam String weight,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !productService.createProduct(name, Float.parseFloat(weight));
    if (isError) {
      model.addAttribute("message", "Неполучилось добавить товар");
      return "redirect:/product/add";
    }
    return "redirect:/products";
  }

  @GetMapping("/product/edit/{id}")
  public String transportEditPage(
      @PathVariable("id") String id,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    Product product = productService.findById(Integer.parseInt(id));

    model.addAttribute("product", product);

    return "product-edit";
  }

  @PostMapping("/product/edit/{id}")
  public String transportEdit(
      @PathVariable("id") String id,
      @RequestParam String name,
      @RequestParam String weight,
      @AuthenticationPrincipal User user,
      Model model
  ) {
    boolean isError = !productService.updateProduct(Integer.parseInt(id), name, Float.parseFloat(weight));

    if (isError) {
      model.addAttribute("message", "Неполучилось внести изменения в товар");
      return "redirect:/product/edit/" + id;
    }
    return "redirect:/products";
  }
}
