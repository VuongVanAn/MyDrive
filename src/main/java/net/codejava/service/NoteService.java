package net.codejava.service;

import java.util.List;

import net.codejava.dto.NoteDTO;

public interface NoteService {
	
	List<NoteDTO> findByFolder();
	
	List<NoteDTO> findByContent();

}
