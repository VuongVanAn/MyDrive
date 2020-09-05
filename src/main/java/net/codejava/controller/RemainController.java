package net.codejava.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.codejava.dto.ContentDTO;
import net.codejava.dto.FolderDTO;
import net.codejava.dto.RemainDTO;
import net.codejava.util.ApiExchangeService;

@Controller
@RequestMapping("/user")
public class RemainController {
	
	private String REMAIN_URL = "http://localhost:5000/api/remain/";
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@RequestMapping("/shared/diary")
	public String diary(Model model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		String fullName = (String) request.getSession().getAttribute("MY_FULL_NAME");
		Long userId = (Long) request.getSession().getAttribute("MY_LOGIN");
		
		RemainDTO[] remainDtos = exchangeService.get(REMAIN_URL + "findAll/" + userId, RemainDTO[].class, token);
		List<RemainDTO> diaryList = Arrays.asList(remainDtos);
		
		model.addAttribute("MY_USER", fullName);
		model.addAttribute("diaryList", diaryList);
		return "user/diary";
	}
	
	@RequestMapping("/trash")
	public String trash(Model model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		String fullName = (String) request.getSession().getAttribute("MY_FULL_NAME");
		Long userId = (Long) request.getSession().getAttribute("MY_LOGIN");
		
		FolderDTO[] folderDtos = exchangeService.get(REMAIN_URL + "folder/" + userId, FolderDTO[].class, token);
		List<FolderDTO> folderList = Arrays.asList(folderDtos);
		
		ContentDTO[] contentDtos = exchangeService.get(REMAIN_URL + "content/" + userId, ContentDTO[].class, token);
		List<ContentDTO> contentList = Arrays.asList(contentDtos);
		
		model.addAttribute("MY_USER", fullName);
		model.addAttribute("folderList", folderList);
		model.addAttribute("contentList", contentList);
		return "user/trash";
	}
	
	@RequestMapping("/restore/folder/{id}")
	public String restoreFolder(HttpServletRequest request, @PathVariable Long id) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		@SuppressWarnings("unused")
		String result = exchangeService.get(REMAIN_URL + "restore/folder/" + id, String.class, token);
		return "redirect:/user/trash";
	}
	
	@RequestMapping("/restore/content/{id}")
	public String restoreContent(HttpServletRequest request, @PathVariable Long id) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		@SuppressWarnings("unused")
		String result = exchangeService.get(REMAIN_URL + "restore/content/" + id, String.class, token);	
		return "redirect:/user/trash";
	}

}
