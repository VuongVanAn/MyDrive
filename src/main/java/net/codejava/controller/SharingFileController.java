package net.codejava.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.codejava.dto.NoteDTO;
import net.codejava.dto.SharingContentDTO;
import net.codejava.dto.SharingFolderDTO;
import net.codejava.util.ApiExchangeService;

@Controller
@RequestMapping("/user")
public class SharingFileController {
	
    private String SHARING_CONTENT_URL = "http://localhost:5000/api/sharingcontent/";
	
	private String SHARING_FOLDER_URL = "http://localhost:5000/api/sharingfolder/";
    
    private String NOTE_FOLDER_URL = "http://localhost:5000/api/note/folder";
	
	private String NOTE_CONTENT_URL = "http://localhost:5000/api/note/content";
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@GetMapping("/sharing")
	public String sharing(Model model,  HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		String fullName = (String) request.getSession().getAttribute("MY_FULL_NAME");
		Long userId = (Long) request.getSession().getAttribute("MY_LOGIN");
		
		SharingFolderDTO[] dtos = exchangeService.get(SHARING_FOLDER_URL + "1/" + userId, SharingFolderDTO[].class, token);
		List<SharingFolderDTO> folderList = Arrays.asList(dtos);
		
		SharingContentDTO[] contentDtos = exchangeService.get(SHARING_CONTENT_URL + "1/" + userId, SharingContentDTO[].class, token);
		List<SharingContentDTO> contentList = Arrays.asList(contentDtos);
		
		SharingFolderDTO[] folderSharingDtos = exchangeService.get(SHARING_FOLDER_URL + "find/" + userId, SharingFolderDTO[].class, token);
		List<SharingFolderDTO> folderSharingList = Arrays.asList(folderSharingDtos);
		
		SharingContentDTO[] contentSharingDtos = exchangeService.get(SHARING_CONTENT_URL + "find/" + userId, SharingContentDTO[].class, token);
		List<SharingContentDTO> contentSharingList = Arrays.asList(contentSharingDtos);
		
		NoteDTO[] noteDtos = exchangeService.get(NOTE_FOLDER_URL, NoteDTO[].class, token);
		List<NoteDTO> noteFolderList = Arrays.asList(noteDtos);
		
		NoteDTO[] noteDTOS = exchangeService.get(NOTE_CONTENT_URL, NoteDTO[].class, token);
		List<NoteDTO> noteContentList = Arrays.asList(noteDTOS);
				
		model.addAttribute("MY_USER", fullName);
		model.addAttribute("folderList", folderList);
		model.addAttribute("contentList", contentList);
		
		model.addAttribute("noteFolderList", noteFolderList);
		model.addAttribute("noteContentList", noteContentList);
		
		model.addAttribute("folderSharingList", folderSharingList);
		model.addAttribute("contentSharingList", contentSharingList);
		
		return "user/share";
	}
	
	@GetMapping("/sharing/{id}")
	public String sharingFile(Model model,  HttpServletRequest request, @PathVariable Long id) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		String fullName = (String) request.getSession().getAttribute("MY_FULL_NAME");
		Long userId = (Long) request.getSession().getAttribute("MY_LOGIN");		
		
		SharingFolderDTO[] dtos = exchangeService.get(SHARING_FOLDER_URL + id + "/" + userId, SharingFolderDTO[].class, token);
		List<SharingFolderDTO> folderList = Arrays.asList(dtos);
		
		SharingContentDTO[] contentDtos = exchangeService.get(SHARING_CONTENT_URL + id + "/" + userId, SharingContentDTO[].class, token);
		List<SharingContentDTO> contentList = Arrays.asList(contentDtos);
		
		SharingFolderDTO[] folderSharingDtos = exchangeService.get(SHARING_FOLDER_URL + "find/" + userId, SharingFolderDTO[].class, token);
		List<SharingFolderDTO> folderSharingList = Arrays.asList(folderSharingDtos);
		
		SharingContentDTO[] contentSharingDtos = exchangeService.get(SHARING_CONTENT_URL + "find/" + userId, SharingContentDTO[].class, token);
		List<SharingContentDTO> contentSharingList = Arrays.asList(contentSharingDtos);
		
		NoteDTO[] noteDtos = exchangeService.get(NOTE_FOLDER_URL, NoteDTO[].class, token);
		List<NoteDTO> noteFolderList = Arrays.asList(noteDtos);
		
		NoteDTO[] noteDTOS = exchangeService.get(NOTE_CONTENT_URL, NoteDTO[].class, token);
		List<NoteDTO> noteContentList = Arrays.asList(noteDTOS);
				
		model.addAttribute("MY_USER", fullName);
		model.addAttribute("folderList", folderList);
		model.addAttribute("contentList", contentList);
		
		model.addAttribute("noteFolderList", noteFolderList);
		model.addAttribute("noteContentList", noteContentList);
		
		model.addAttribute("folderSharingList", folderSharingList);
		model.addAttribute("contentSharingList", contentSharingList);
		
		return "user/share";
	}
	
	@PostMapping("/save/sharing/content")
	public void saveSharingContent(@ModelAttribute SharingContentDTO sharingDto, HttpServletRequest request, 
			                       HttpServletResponse response) throws IOException {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		if (sharingDto.getId() == null) {
			SharingContentDTO dto = exchangeService.post(SHARING_CONTENT_URL, sharingDto, SharingContentDTO.class, token);
			response.sendRedirect("/user/my-drive/" + dto.getFolderId());
		} else {					
			exchangeService.put(SHARING_CONTENT_URL, sharingDto, token);
			response.sendRedirect("/user/sharing/" + sharingDto.getFolderId());
		}	
	}
	
	@PostMapping("/save/sharing/folder")
	public void saveSharingFolder(@ModelAttribute SharingFolderDTO sharingDto, HttpServletRequest request,
			                      HttpServletResponse response) throws IOException {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		if (sharingDto.getId() == null) {
			SharingFolderDTO dto = exchangeService.post(SHARING_FOLDER_URL, sharingDto, SharingFolderDTO.class, token);
			response.sendRedirect("/user/my-drive/" + dto.getFolderParentId());
		} else {			
			exchangeService.put(SHARING_FOLDER_URL, sharingDto, token);
			response.sendRedirect("/user/sharing/" + sharingDto.getFolderParentId());
		}		
	}
	
	@RequestMapping(value = "/delete/sharing/content/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public void deleteSharingContent(@PathVariable Long id, HttpServletRequest request, 
			                           HttpServletResponse response) throws IOException {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		SharingContentDTO dto = exchangeService.get(SHARING_CONTENT_URL + id, SharingContentDTO.class, token);
		exchangeService.delete(SHARING_CONTENT_URL + id, token);
		response.sendRedirect("/user/sharing/" + dto.getFolderId());
	}
	
	@RequestMapping(value = "/delete/sharing/folder/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public void deleteSharingFolder(@PathVariable Long id, HttpServletRequest request, 
			                        HttpServletResponse response) throws IOException {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		SharingFolderDTO dto = exchangeService.get(SHARING_FOLDER_URL + id, SharingFolderDTO.class, token);
		exchangeService.delete(SHARING_FOLDER_URL + id, token);
		response.sendRedirect("/user/sharing/" + dto.getFolderParentId());
	}

}
