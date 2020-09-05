package net.codejava.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.dto.FolderDTO;
import net.codejava.service.FolderService;
import net.codejava.service.GenericService;

@RestController
@RequestMapping("/api/folder")
public class FolderAPI implements Resource<FolderDTO> {
	
	@Autowired
	private GenericService<FolderDTO> service;
	
	@Autowired
	private FolderService folderService;

	@Override
	public ResponseEntity<List<FolderDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FolderDTO> findById(Long id) {
		return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping("/{folderId}/{userId}")
	public ResponseEntity<List<FolderDTO>> findAllFolder(@PathVariable Long folderId, @PathVariable Long userId) {
		return new ResponseEntity<>(folderService.findByFolderIdAndUserId(folderId, userId), HttpStatus.OK);
	}
	
	@RequestMapping("/find/{id}")
	public ResponseEntity<List<FolderDTO>> findAllByUserId(@PathVariable Long id) {
		return new ResponseEntity<>(folderService.findSharingFolderByUser(id), HttpStatus.OK);
	}
	
	@RequestMapping("/find/folder/{id}")
	public ResponseEntity<List<FolderDTO>> findAllByFolderId(@PathVariable Long id) {
		return new ResponseEntity<>(folderService.findByFolderId(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FolderDTO> save(FolderDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FolderDTO> update(FolderDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}

}
