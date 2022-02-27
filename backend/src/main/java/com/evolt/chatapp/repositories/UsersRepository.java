package com.evolt.chatapp.repositories;

import com.evolt.chatapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user.username from User user")
    List<String> getAllUsernames();

    @Modifying
    @Transactional
    @Query ("DELETE FROM User user WHERE user.username=:username")
    void removeUserByUsername(@Param("username") String username);
}
