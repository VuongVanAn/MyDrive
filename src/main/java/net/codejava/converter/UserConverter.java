package net.codejava.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.codejava.dto.UserDTO;
import net.codejava.model.User;

@Component
public class UserConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO toDTO(User entity) {	
		return modelMapper.map(entity, UserDTO.class);
	}
	
	public User toEntity(UserDTO dto) {
		return modelMapper.map(dto, User.class);
	}
	
	public List<UserDTO> toDTOList(List<User> listEntity) {
		return listEntity.stream().map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<User> toEntityList(List<UserDTO> listDTOS) {
		return listDTOS.stream().map(dto -> modelMapper.map(dto, User.class))
                .collect(Collectors.toList());
	}
	
}
