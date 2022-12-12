package org.example.controller;

import org.example.entity.User;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  private UserService userService;
  @Autowired
  private OrderService orderService;


  @GetMapping("/")
  public String main(@AuthenticationPrincipal User user, Model model) {
    if (user == null)
      return "redirect:/login";
    userService.activateUser(user.getUserId());
    return "redirect:/main";
  }

  @GetMapping("/login")
  public String login(@RequestParam(value = "error", required = false) String error, Model model) {
    if (error != null) {
      model.addAttribute("message", "Неправильное имя пользователя или пароль");
    }
    model.addAttribute("user", new User());
    return "login";
  }

//  @PostMapping("/authentication")
//  public String login(String username, String password, Model model) {
//    boolean result = userService.logIn(username, password, model);
//    if (!result) {
//      model.addAttribute("message", "Неправильное имя пользователя или пароль");
//    }
//    return result
//        ? "redirect:/main-stat"
//        : "/login";
//  }

  @GetMapping("/registration")
  public String registration( Model model) {
    model.addAttribute("user", new User());
    return "registration";
  }

  @PostMapping("/registration")
  public String register(User user, Model model) {
    return userService.register(user, model)
        ? "redirect:/login"
        : "/registration";
  }

  @GetMapping("/main")
  public String main2(@AuthenticationPrincipal User user, Model model) {
    model.addAttribute("totalPrice", orderService.getTotalSum());
    model.addAttribute("isAdmin", user.getTypeId().typeId == 1);
    return "/main";
  }
}
