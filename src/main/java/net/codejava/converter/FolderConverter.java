package net.codejava.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.codejava.dto.FolderDTO;
import net.codejava.model.Folder;

@Component
public class FolderConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FolderDTO toDTO(Folder entity) {	
		return modelMapper.map(entity, FolderDTO.class);
	}
	
	public Folder toEntity(FolderDTO dto) {
		return modelMapper.map(dto, Folder.class);
	}
	
	public List<FolderDTO> toDTOList(List<Folder> listEntity) {
		return listEntity.stream().map(entity -> modelMapper.map(entity, FolderDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<Folder> toEntityList(List<FolderDTO> listDTOS) {
		return listDTOS.stream().map(dto -> modelMapper.map(dto, Folder.class))
                .collect(Collectors.toList());
	}

}
