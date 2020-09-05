package net.codejava.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.dto.SharingContentDTO;
import net.codejava.service.GenericService;
import net.codejava.service.SharingContentService;

@RestController
@RequestMapping("/api/sharingcontent")
public class SharingContentAPI implements Resource<SharingContentDTO> {
	
	@Autowired
	private GenericService<SharingContentDTO> service;
	
	@Autowired
	private SharingContentService sharingContentService;

	@Override
	public ResponseEntity<List<SharingContentDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping("/{folderId}/{userId}")
	public ResponseEntity<List<SharingContentDTO>> findAllByUser(@PathVariable Long folderId, @PathVariable Long userId) {
		return new ResponseEntity<>(sharingContentService.findAllByFolderIdAndUser(folderId, userId), HttpStatus.OK);
	}
	
	@RequestMapping("/find/{userId}")
	public ResponseEntity<List<SharingContentDTO>> findAllByUserId(@PathVariable Long userId) {
		return new ResponseEntity<>(sharingContentService.findAllByUserId(userId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharingContentDTO> findById(Long id) {
		return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharingContentDTO> save(SharingContentDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharingContentDTO> update(SharingContentDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}

}
