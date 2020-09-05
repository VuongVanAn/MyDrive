package net.codejava.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.codejava.dto.JwtRequest;
import net.codejava.model.User;
import net.codejava.repository.UserRepository;
import net.codejava.util.ApiExchangeService;

@Controller
public class LoginController {
	
    private String URL = "http://localhost:5000/api/login";
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new JwtRequest());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@ModelAttribute JwtRequest loginForm, HttpServletResponse response, 
			          HttpServletRequest request) throws IOException { 
		String token = exchangeService.postNonToken(URL, loginForm, String.class);
		request.getSession().setAttribute("MY_TOKEN", token);
		
		User user = userRepository.findByUserName(loginForm.getUserName());		
		request.getSession().setAttribute("MY_LOGIN", user.getId());
		request.getSession().setAttribute("AUTHORITY", user.getPermission());
		request.getSession().setAttribute("MY_FULL_NAME", user.getFullName());
		
		if (user.getPermission() == 2) {
			response.sendRedirect("/user/my-drive");
		} else {
			response.sendRedirect("/users");
		}
	}
	
	@RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        request.getSession().removeAttribute("MY_TOKEN");
        request.getSession().removeAttribute("MY_LOGIN");
        request.getSession().removeAttribute("AUTHORITY");
        request.getSession().removeAttribute("MY_FULL_NAME");
    }
	
	@RequestMapping(value = "/authorize")
	public String authorize() {
		return "authorize";
	}

}
