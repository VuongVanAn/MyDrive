package net.codejava.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.codejava.dto.UserDTO;
import net.codejava.util.ApiExchangeService;

@Controller
public class UserController {
	
	private String URL = "http://localhost:5000/api/admin/";
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@GetMapping("/users")
	public String index(Model model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		String fullName = (String) request.getSession().getAttribute("MY_FULL_NAME");
		UserDTO[] dtos = exchangeService.get(URL, UserDTO[].class, token);
		List<UserDTO> userList = Arrays.asList(dtos);
		model.addAttribute("userList", userList);
		model.addAttribute("MY_USER", fullName);
		return "admin/index";
	}
	
	@PostMapping("/users/save")
	public void createOrUpdateUser(@RequestBody UserDTO user, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		if (user.getId() == null) {
			@SuppressWarnings("unused")
			UserDTO dto = exchangeService.post(URL, user, UserDTO.class, token);
		} else {
			exchangeService.put(URL, user, token);			
		}		
	}
	
	@GetMapping("/users/findOne/{id}")
	public @ResponseBody UserDTO findOne(@PathVariable Integer id, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		UserDTO dto = exchangeService.get(URL + id, UserDTO.class, token);
		return dto;
	}
	
	@RequestMapping(value = "/users/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteUser(@PathVariable Long id, HttpServletRequest request,
			                 RedirectAttributes redirectAttributes) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		exchangeService.delete(URL + id, token);
		redirectAttributes.addFlashAttribute("SuccessMessage", "Delete User Successfully!");
		return "redirect:/users";
	}

}
