package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);
	
	List<User> findByPermission(int id);
	
}
