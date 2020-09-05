package net.codejava.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.dto.ContentDTO;
import net.codejava.service.ContentService;
import net.codejava.service.GenericService;

@RestController
@RequestMapping("/api/content")
public class ContentAPI implements Resource<ContentDTO> {
	
	@Autowired
	private GenericService<ContentDTO> service;
	
	@Autowired
	private ContentService contentService;

	@Override
	public ResponseEntity<List<ContentDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}	
	
	@RequestMapping("/{id}/{userId}")
	public ResponseEntity<List<ContentDTO>> findByFolderAndUser(@PathVariable Long id, @PathVariable Long userId) {
		return new ResponseEntity<>(contentService.findByFolderAndUser(id, userId), HttpStatus.OK);
	}
	
	@RequestMapping("/find/{id}")
	public ResponseEntity<List<ContentDTO>> findAllByUserId(@PathVariable Long id) {
		return new ResponseEntity<>(contentService.findSharingContentByUser(id), HttpStatus.OK);
	}
	
	@RequestMapping("/find/folder/{id}")
	public ResponseEntity<List<ContentDTO>> findAllByFolderId(@PathVariable Long id) {
		return new ResponseEntity<>(contentService.findByFolderId(id), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ContentDTO> findById(Long id) {
		return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ContentDTO> save(ContentDTO dto) {
		return null;
	}

	@Override
	public ResponseEntity<ContentDTO> update(ContentDTO dto) {
		return null;
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}
	
}
