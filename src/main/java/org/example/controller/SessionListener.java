package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class SessionListener implements HttpSessionListener {

//  @Override
//  public void sessionCreated(HttpSessionEvent se) {
//    System.out.println(se);
//  }
  @Autowired
  private UserService userService;

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {

    if (se.getSession().getAttribute("SPRING_SECURITY_CONTEXT") == null) {
      return;
    }
    User accountDetails = ((User) ((SecurityContextImpl) se.getSession().getAttribute("SPRING_SECURITY_CONTEXT"))
        .getAuthentication()
        .getPrincipal());
    userService.deactivateUser(accountDetails.getUserId());
    //    userService.activateUser();
  }


}
