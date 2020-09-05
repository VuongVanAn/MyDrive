package net.codejava.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.codejava.dto.NoteDTO;
import net.codejava.model.Note;

@Component
public class NoteConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public NoteDTO toDTO(Note entity) {	
		return modelMapper.map(entity, NoteDTO.class);
	}
	
	public Note toEntity(NoteDTO dto) {
		return modelMapper.map(dto, Note.class);
	}
	
	public List<NoteDTO> toDTOList(List<Note> listEntity) {
		return listEntity.stream().map(entity -> modelMapper.map(entity, NoteDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<Note> toEntityList(List<NoteDTO> listDTOS) {
		return listDTOS.stream().map(dto -> modelMapper.map(dto, Note.class))
                .collect(Collectors.toList());
	}

}
