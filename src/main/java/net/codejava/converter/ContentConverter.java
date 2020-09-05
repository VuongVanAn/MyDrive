package net.codejava.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.codejava.dto.ContentDTO;
import net.codejava.model.Content;

@Component
public class ContentConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ContentDTO toDTO(Content entity) {	
		return modelMapper.map(entity, ContentDTO.class);
	}
	
	public Content toEntity(ContentDTO dto) {
		return modelMapper.map(dto, Content.class);
	}
	
	public List<ContentDTO> toDTOList(List<Content> listEntity) {
		return listEntity.stream().map(entity -> modelMapper.map(entity, ContentDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<Content> toEntityList(List<ContentDTO> listDTOS) {
		return listDTOS.stream().map(dto -> modelMapper.map(dto, Content.class))
                .collect(Collectors.toList());
	}

}
