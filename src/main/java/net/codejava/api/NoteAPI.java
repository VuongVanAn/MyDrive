package net.codejava.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.dto.NoteDTO;
import net.codejava.service.GenericService;
import net.codejava.service.NoteService;

@RestController
@RequestMapping("/api/note")
public class NoteAPI implements Resource<NoteDTO> {
	
	@Autowired
	private GenericService<NoteDTO> service;
	
	@Autowired
	private NoteService noteService;

	@Override
	public ResponseEntity<List<NoteDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NoteDTO> findById(Long id) {
		return null;
	}
	
	@RequestMapping("/folder")
	public ResponseEntity<List<NoteDTO>> findByFolder() {
		return new ResponseEntity<>(noteService.findByFolder(), HttpStatus.OK);
	}
	
	@RequestMapping("/content")
	public ResponseEntity<List<NoteDTO>> findByContent() {
		return new ResponseEntity<>(noteService.findByContent(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NoteDTO> save(NoteDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NoteDTO> update(NoteDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
