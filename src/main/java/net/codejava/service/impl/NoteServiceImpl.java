package net.codejava.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.converter.NoteConverter;
import net.codejava.dto.NoteDTO;
import net.codejava.model.Note;
import net.codejava.repository.NoteRepository;
import net.codejava.service.GenericService;
import net.codejava.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService, GenericService<NoteDTO> {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private NoteConverter noteConverter;

	@Override
	public List<NoteDTO> findAll() {		
		return noteConverter.toDTOList(noteRepository.findAll());
	}

	@Override
	public NoteDTO save(NoteDTO dto) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = format.format(new Date());
		dto.setDate(dateString);
		Note note = noteRepository.save(noteConverter.toEntity(dto));
		return noteConverter.toDTO(note);
	}

	@Override
	public NoteDTO findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteDTO> findByFolder() {
		List<NoteDTO> dtos = noteConverter.toDTOList(noteRepository.findAll());
		List<NoteDTO> noteList = new ArrayList<NoteDTO>();
		for (NoteDTO dto : dtos) {
			if (dto.getFolderId() != null) {				
				noteList.add(dto);
			}
		}	
		return noteList;
	}

	@Override
	public List<NoteDTO> findByContent() {
		List<NoteDTO> dtos = noteConverter.toDTOList(noteRepository.findAll());
		List<NoteDTO> noteList = new ArrayList<NoteDTO>();
		for (NoteDTO dto : dtos) {
			if (dto.getContentId() != null) {			
				noteList.add(dto);
			}
		}	
		return noteList;
	}

}
