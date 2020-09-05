package net.codejava.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.codejava.dto.SharingFolderDTO;
import net.codejava.model.SharingFolder;

@Component
public class SharingFolderConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SharingFolderDTO toDTO(SharingFolder entity) {	
		return modelMapper.map(entity, SharingFolderDTO.class);
	}
	
	public SharingFolder toEntity(SharingFolderDTO dto) {
		return modelMapper.map(dto, SharingFolder.class);
	}
	
	public List<SharingFolderDTO> toDTOList(List<SharingFolder> listEntity) {
		return listEntity.stream().map(entity -> modelMapper.map(entity, SharingFolderDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<SharingFolder> toEntityList(List<SharingFolderDTO> listDTOS) {
		return listDTOS.stream().map(dto -> modelMapper.map(dto, SharingFolder.class))
                .collect(Collectors.toList());
	}

}
