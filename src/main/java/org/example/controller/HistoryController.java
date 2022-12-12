package org.example.controller;

import org.example.entity.History;
import org.example.entity.User;
import org.example.payload.HistoryData;
import org.example.service.HistoryService;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HistoryController {

  @Autowired
  private UserService userService;
  @Autowired
  private HistoryService historyService;


  @GetMapping("/journal")
  public String history(@AuthenticationPrincipal User user, Model model) {
    if (user.getTypeId().typeId != 1) {
      return "redirect:/main";
    }
    List<HistoryData> historyList = historyService.getAll();
    model.addAttribute("historyList", historyList);
    return "history";
  }
}
