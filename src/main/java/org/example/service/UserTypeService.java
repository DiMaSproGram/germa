package org.example.service;

import org.example.repository.UserRepo;
import org.example.repository.UserTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTypeService {

  @Autowired
  private UserTypeRepo userTypeRepo;


}
