package net.codejava.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.dto.ContentDTO;
import net.codejava.dto.FolderDTO;
import net.codejava.dto.RemainDTO;
import net.codejava.service.RemainService;

@RestController
@RequestMapping("/api/remain")
public class RemainAPI {
	
	@Autowired
	private RemainService service;
	
	@RequestMapping("/findAll/{id}")
	public ResponseEntity<List<RemainDTO>> findAll(@PathVariable Long id) {
		return new ResponseEntity<>(service.findAllByUserId(id), HttpStatus.OK);
	}
	
	@RequestMapping("/folder/{id}")
	public ResponseEntity<List<FolderDTO>> findAllFolder(@PathVariable Long id) {
		return new ResponseEntity<>(service.findAllFolderTrash(id, 0), HttpStatus.OK);
	}
	
	@RequestMapping("/content/{id}")
	public ResponseEntity<List<ContentDTO>> findAllContent(@PathVariable Long id) {
		return new ResponseEntity<>(service.findAllContentTrash(id, 0), HttpStatus.OK);
	}
	
	@RequestMapping("/restore/folder/{id}")
	public ResponseEntity<String> restoreFolder(@PathVariable Long id) {
		return new ResponseEntity<>(service.restoreFolder(id), HttpStatus.OK);
	}
	
	@RequestMapping("/restore/content/{id}")
	public ResponseEntity<String> restoreContent(@PathVariable Long id) {
		return new ResponseEntity<>(service.restoreContent(id), HttpStatus.OK);
	}

}
