package com.evolt.chatapp.services;

import com.evolt.chatapp.models.User;
import com.evolt.chatapp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServices {
    @Autowired
    private UsersRepository usersRepository;

    public void addUser(User user) {
        usersRepository.save(user);
    }

    public void removeUser(String username) {
        usersRepository.removeUserByUsername(username);
    }

    public List<String> fetchAllUsers() {
        return usersRepository.getAllUsernames();
    }
}
