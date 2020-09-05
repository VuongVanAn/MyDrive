package net.codejava.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.dto.SharingFolderDTO;
import net.codejava.service.GenericService;
import net.codejava.service.SharingFolderService;

@RestController
@RequestMapping("/api/sharingfolder")
public class SharingFolderAPI implements Resource<SharingFolderDTO> {
	
	@Autowired
	private GenericService<SharingFolderDTO> service;
	
	@Autowired
	private SharingFolderService sharingFolderService;

	@Override
	public ResponseEntity<List<SharingFolderDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping("/{folderId}/{userId}")
	public ResponseEntity<List<SharingFolderDTO>> findAllByUser(@PathVariable Long folderId, @PathVariable Long userId) {
		return new ResponseEntity<>(sharingFolderService.findAllByFolderAndUser(folderId, userId), HttpStatus.OK);
	}
	
	@RequestMapping("/find/{userId}")
	public ResponseEntity<List<SharingFolderDTO>> findAllByUserId(@PathVariable Long userId) {
		return new ResponseEntity<>(sharingFolderService.findAllByUserId(userId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharingFolderDTO> findById(Long id) {
		return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharingFolderDTO> save(SharingFolderDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharingFolderDTO> update(SharingFolderDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}

}
