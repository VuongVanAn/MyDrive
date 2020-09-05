package net.codejava.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.codejava.dto.ContentDTO;
import net.codejava.service.ContentService;
import net.codejava.util.ApiExchangeService;

@Controller
@RequestMapping("/user")
public class ContentController {
	
	private String URL = "http://localhost:5000/api/content/";
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@PostMapping("/upload")
	public void multipleUpload(@RequestParam("files") MultipartFile[] files, @RequestParam("id") Long id,
			 HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (files.length > 5) {
            throw new RuntimeException("Too many files!");
        }
        Long userId = (Long) request.getSession().getAttribute("MY_LOGIN");
        
        Arrays.asList(files)
                .stream()
                .forEach(file -> {
                	String name = StringUtils.cleanPath(file.getOriginalFilename());
                	String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                             .path("/user/download/").path(name).toUriString();               	 
                	contentService.saveContent(url, name, file, id, userId);                                                     
                });
        response.sendRedirect("/user/my-drive/" + id);
    }

	
	@GetMapping("/download/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
        ContentDTO dto = contentService.findByName(fileName);
        String mimeType = request.getServletContext().getMimeType(dto.getName());
        mimeType = mimeType == null ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mimeType;
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + dto.getName())
                .body(dto.getDocFile());
    }
    
    @RequestMapping(value = "/delete/content/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteContent(@PathVariable Long id, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		ContentDTO dto = exchangeService.get(URL + id, ContentDTO.class, token);
		exchangeService.delete(URL + id, token);
		return "redirect:/user/my-drive/" + dto.getFolderId();
	}

}
