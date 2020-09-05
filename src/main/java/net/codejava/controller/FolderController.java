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

import net.codejava.dto.ContentDTO;
import net.codejava.dto.FolderDTO;
import net.codejava.dto.NoteDTO;
import net.codejava.dto.UserDTO;
import net.codejava.util.ApiExchangeService;

@Controller
@RequestMapping("/user")
public class FolderController {
	
	private String FOLDER_URL = "http://localhost:5000/api/folder/";
	
	private String CONTENT_URL = "http://localhost:5000/api/content/"; 
	
	private String USER_URL = "http://localhost:5000/api/admin/permission/";
	
	private String NOTE_FOLDER_URL = "http://localhost:5000/api/note/folder";
	
	private String NOTE_CONTENT_URL = "http://localhost:5000/api/note/content";
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@GetMapping("/my-drive")
	public String index(Model model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		String fullName = (String) request.getSession().getAttribute("MY_FULL_NAME");
		Long userId = (Long) request.getSession().getAttribute("MY_LOGIN");
		
		FolderDTO[] folderDtos = exchangeService.get(FOLDER_URL + "1/" + userId, FolderDTO[].class, token);
		List<FolderDTO> folderList = Arrays.asList(folderDtos);
		
		ContentDTO[] contentDtos = exchangeService.get(CONTENT_URL + "1/" + userId, ContentDTO[].class, token);
		List<ContentDTO> contentList = Arrays.asList(contentDtos);	

		UserDTO[] userDtos = exchangeService.get(USER_URL + userId, UserDTO[].class, token);
		List<UserDTO> userList = Arrays.asList(userDtos);
		
		NoteDTO[] noteDtos = exchangeService.get(NOTE_FOLDER_URL, NoteDTO[].class, token);
		List<NoteDTO> noteFolderList = Arrays.asList(noteDtos);
		
		NoteDTO[] noteDTOS = exchangeService.get(NOTE_CONTENT_URL, NoteDTO[].class, token);
		List<NoteDTO> noteContentList = Arrays.asList(noteDTOS);
		
		model.addAttribute("userId", userId);
		model.addAttribute("MY_USER", fullName);
		model.addAttribute("userList", userList);
		
		model.addAttribute("folderList", folderList);
		model.addAttribute("contentList", contentList);
		
		model.addAttribute("noteFolderList", noteFolderList);
		model.addAttribute("noteContentList", noteContentList);
					
		return "user/index";
	}
	
	@GetMapping("/my-drive/{id}")
	public String dashboard(Model model, HttpServletRequest request, @PathVariable Long id) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		String fullName = (String) request.getSession().getAttribute("MY_FULL_NAME");
		Long userId = (Long) request.getSession().getAttribute("MY_LOGIN");
		
		FolderDTO[] folderDtos = exchangeService.get(FOLDER_URL + id + "/" + userId, FolderDTO[].class, token);
		List<FolderDTO> folderList = Arrays.asList(folderDtos);
		
		ContentDTO[] contentDtos = exchangeService.get(CONTENT_URL + id + "/" + userId, ContentDTO[].class, token);
		List<ContentDTO> contentList = Arrays.asList(contentDtos);
			
		UserDTO[] userDtos = exchangeService.get(USER_URL + userId, UserDTO[].class, token);
		List<UserDTO> userList = Arrays.asList(userDtos);
		
		NoteDTO[] noteDtos = exchangeService.get(NOTE_FOLDER_URL, NoteDTO[].class, token);
		List<NoteDTO> noteFolderList = Arrays.asList(noteDtos);
		
		NoteDTO[] noteDTOS = exchangeService.get(NOTE_CONTENT_URL, NoteDTO[].class, token);
		List<NoteDTO> noteContentList = Arrays.asList(noteDTOS);
		
		model.addAttribute("userId", userId);
		model.addAttribute("MY_USER", fullName);
		model.addAttribute("userList", userList);
		
		model.addAttribute("folderList", folderList);
		model.addAttribute("contentList", contentList);
		
		model.addAttribute("noteFolderList", noteFolderList);
		model.addAttribute("noteContentList", noteContentList);
			
		return "user/index";
	}
	
	@PostMapping("/save/folder")
	public void createFolder(@RequestBody FolderDTO folder, HttpServletRequest request) {
		Long id = (Long) request.getSession().getAttribute("MY_LOGIN");
		folder.setUserId(id);
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		@SuppressWarnings("unused")
		FolderDTO dto = exchangeService.post(FOLDER_URL, folder, FolderDTO.class, token);
	}
	
	@RequestMapping(value = "/delete/folder/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteFolder(@PathVariable Long id, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		FolderDTO dto = exchangeService.get(FOLDER_URL + id, FolderDTO.class, token);
		exchangeService.delete(FOLDER_URL + id, token);
		return "redirect:/user/my-drive/" + dto.getFolderId();
	}

}
