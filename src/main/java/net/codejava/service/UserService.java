package net.codejava.service;

import java.util.List;

import net.codejava.dto.UserDTO;

public interface UserService {
	
	List<UserDTO> findByPermission(int id, Long userId);
	
}
