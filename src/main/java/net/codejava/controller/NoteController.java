package net.codejava.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.codejava.dto.ContentDTO;
import net.codejava.dto.FolderDTO;
import net.codejava.dto.NoteDTO;
import net.codejava.util.ApiExchangeService;

@Controller
@RequestMapping("/user")
public class NoteController {
	
	private String URL = "http://localhost:5000/api/note/";
	
	private String FOLDER_URL = "http://localhost:5000/api/folder/";
	
	private String CONTENT_URL = "http://localhost:5000/api/content/";
	
	@Autowired
	private ApiExchangeService exchangeService; 
	
	@PostMapping("/save/note/folder/{folderId}")
	public void saveNoteFolder(@RequestParam("noteFolder") String noteFolder, @PathVariable Long folderId, 
			                   HttpServletResponse response, HttpServletRequest request) throws IOException {
		Long id = (Long) request.getSession().getAttribute("MY_LOGIN");
		NoteDTO dto = new NoteDTO();	
		dto.setUserId(id);
		dto.setNote(noteFolder);
		dto.setFolderId(folderId);
		
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		@SuppressWarnings("unused")
		NoteDTO noteDto = exchangeService.post(URL, dto, NoteDTO.class, token);
		FolderDTO folderDto = exchangeService.get(FOLDER_URL + folderId, FolderDTO.class, token);
		response.sendRedirect("/user/my-drive/" + folderDto.getFolderId());
	}
	
	@PostMapping("/save/note/content/{contentId}")
	public void saveNoteContent(@RequestParam("noteContent") String noteContent, @PathVariable Long contentId, 
			                   HttpServletResponse response, HttpServletRequest request) throws IOException {
		Long id = (Long) request.getSession().getAttribute("MY_LOGIN");
		NoteDTO dto = new NoteDTO();	
		dto.setUserId(id);
		dto.setNote(noteContent);
		dto.setContentId(contentId);
		
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		@SuppressWarnings("unused")
		NoteDTO noteDto = exchangeService.post(URL, dto, NoteDTO.class, token);
		ContentDTO contentDto = exchangeService.get(CONTENT_URL + contentId, ContentDTO.class, token);
		response.sendRedirect("/user/my-drive/" + contentDto.getFolderId());
	}

}
