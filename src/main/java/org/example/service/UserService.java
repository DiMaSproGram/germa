package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepo;
import org.example.repository.UserTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Date;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserTypeRepo userTypeRepo;

    @Autowired
    @PersistenceContext
    private EntityManager em;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void activateUser(int userId) {
        userRepo.activeUser(userId);
    }

    public void deactivateUser(int userId) {
        userRepo.deactiveUser(userId);
    }

    public boolean register(User user, Model model) {
        User userFromDb = loadUserByUsername(user.getUsername());

        if(userFromDb != null) {
            model.addAttribute("message", "Пользователь уже зарегистрирован");
            model.addAttribute("user", user);
            return false;
        } else if (user.getUsername().length() < 4 || user.getPassword().length() < 4) {
            model.addAttribute("message", "Логин и пароль должны быть длиною не менее 4 символов");
            model.addAttribute("user", user);
            return false;
        }
        user.setEnabled(true);
        user.setTypeId(userTypeRepo.findById(2).get());
        user.setPassword(
            new BCryptPasswordEncoder(11)
                .encode(user.getPassword())
        );
//        em.createNamedStoredProcedureQuery("SignUp")
//            .setParameter("login", user.getUsername())
//            .setParameter("pasword", user.getPassword())
//            .execute();
        userRepo.signUp(user.getUsername(), user.getPassword());
        return true;
    }

    public boolean logIn(String username, String password, Model model) {
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        User userFromDB = userRepo.findByUsername(username);
        if (passEncoder.encode(password).equals(userFromDB.getPassword())) {
            return true;
        }
        return false;
    }

//    public List<User> getAllBySearching(String search) {
//        ArrayList<User> arrayList = (ArrayList<User>) userRepo.findAll();
//        arrayList.removeIf(
//            elem -> !elem.getUsername().replaceAll(" ", "").toLowerCase().contains(
//                search.replaceAll(" ", "").toLowerCase()
//            )
//        );
//        return arrayList;
//    }
}
