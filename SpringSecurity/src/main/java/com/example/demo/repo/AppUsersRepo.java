package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.AppUsers;

public interface AppUsersRepo extends JpaRepository<AppUsers, Long> {
	Optional<AppUsers> findByUsername(String username);
}
