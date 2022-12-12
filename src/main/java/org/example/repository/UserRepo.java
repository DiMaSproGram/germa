package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(
        value = "select\n" +
            "  id_user, userName AS user_name, userPass AS user_pass, enabled, id_status, id_type \n" +
            "from \n" +
            "  dbuser \n" +
            "where \n" +
            "  dbuser.userName = ?1 \n",
        nativeQuery = true
    )
    User findByUsername(String username);


    @Procedure("ActiveUser")
    void activeUser(int userid);

    @Procedure("DeactiveUser")
    void deactiveUser(int userid);

    @Procedure("SignUp")
    void signUp(String login, String pasword);
}
