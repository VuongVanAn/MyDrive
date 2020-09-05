package net.codejava.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.dto.UserDTO;
import net.codejava.service.GenericService;
import net.codejava.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class UserAPI implements Resource<UserDTO> {
	
	@Autowired
	private GenericService<UserDTO> service;
	
	@Autowired
	private UserService userService;

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping("/permission/{userId}")
	public ResponseEntity<List<UserDTO>> findAllByUser(@PathVariable Long userId) {
		return new ResponseEntity<>(userService.findByPermission(2, userId), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> findById(Long id) {
		return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> save(UserDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> update(UserDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteById(Long id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}

}
