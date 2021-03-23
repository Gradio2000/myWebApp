package ru.laskin.myWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.laskin.myWebApp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
