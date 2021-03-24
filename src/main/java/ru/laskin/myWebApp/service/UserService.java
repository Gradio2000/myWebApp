package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers(){
        return (List<User>) repository.findAll();
    }
}
