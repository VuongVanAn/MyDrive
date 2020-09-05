package net.codejava.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.codejava.dto.SharingContentDTO;
import net.codejava.model.SharingContent;

@Component
public class SharingContentConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SharingContentDTO toDTO(SharingContent entity) {	
		return modelMapper.map(entity, SharingContentDTO.class);
	}
	
	public SharingContent toEntity(SharingContentDTO dto) {
		return modelMapper.map(dto, SharingContent.class);
	}
	
	public List<SharingContentDTO> toDTOList(List<SharingContent> listEntity) {
		return listEntity.stream().map(entity -> modelMapper.map(entity, SharingContentDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<SharingContent> toEntityList(List<SharingContentDTO> listDTOS) {
		return listDTOS.stream().map(dto -> modelMapper.map(dto, SharingContent.class))
                .collect(Collectors.toList());
	}

}
