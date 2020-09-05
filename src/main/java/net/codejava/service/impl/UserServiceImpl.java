package net.codejava.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.codejava.converter.UserConverter;
import net.codejava.dto.UserDTO;
import net.codejava.model.User;
import net.codejava.repository.UserRepository;
import net.codejava.service.GenericService;
import net.codejava.service.UserService;

@Service
public class UserServiceImpl implements UserService, GenericService<UserDTO> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	 
	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> userList = userConverter.toDTOList(userRepository.findAll());
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getPermission() == 1) {
				userList.get(i).setRoleName("ADMIN");
			} else {
				userList.get(i).setRoleName("USER");
			}
		}
		return userList;
	}

	@Override
	public UserDTO save(UserDTO dto) {
		if (dto.getId() == null) {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = format.format(new Date());
			dto.setCreatedDate(dateString);
			dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
		} else {
			User user = userRepository.findById(dto.getId()).get();
			dto.setCreatedDate(user.getCreatedDate());
		}
		User entity = userRepository.save(userConverter.toEntity(dto));
		return userConverter.toDTO(entity);
	}

	@Override
	public UserDTO findOne(Long id) {
		return userConverter.toDTO(userRepository.findById(id).get());
	}

	@Override
	public String delete(Long id) {
		userRepository.deleteById(id);
		return null;
	}

	@Override
	public List<UserDTO> findByPermission(int id, Long userId) {
		List<UserDTO> userDtos = userConverter.toDTOList(userRepository.findByPermission(id));
		List<UserDTO> userList = new ArrayList<UserDTO>();
		for (UserDTO dtos : userDtos) {
			if (dtos.getId() != userId) userList.add(dtos);
		}
		return userList;
	}

}
