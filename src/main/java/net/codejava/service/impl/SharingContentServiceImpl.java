package net.codejava.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.converter.SharingContentConverter;
import net.codejava.dto.SharingContentDTO;
import net.codejava.model.Content;
import net.codejava.model.SharingContent;
import net.codejava.repository.ContentRepository;
import net.codejava.repository.SharingContentRepository;
import net.codejava.service.GenericService;
import net.codejava.service.SharingContentService;

@Service
public class SharingContentServiceImpl implements SharingContentService, GenericService<SharingContentDTO> {
	
	@Autowired
	private SharingContentRepository sharingContentRepo;
	
	@Autowired
	private SharingContentConverter sharingContentConverter;
	
	@Autowired
	private ContentRepository contentRepsitory;

	@Override
	public List<SharingContentDTO> findAll() {
		return sharingContentConverter.toDTOList(sharingContentRepo.findAll());
	}

	@Override
	public SharingContentDTO save(SharingContentDTO dto) {
		if (dto.getId() == null) {
			dto.setCreatedDate(new Date());
			dto.setFolderId((long) 1);
			dto.setStatus(1);
		} else {
			SharingContent sharingContent = sharingContentRepo.findById(dto.getId()).get();
			dto.setCreatedDate(sharingContent.getCreatedDate());
			dto.setUserId(sharingContent.getUserid());
			dto.setContentId(sharingContent.getContentid());
			dto.setStatus(sharingContent.getStatus());
		}
		SharingContent entity = sharingContentRepo.save(sharingContentConverter.toEntity(dto));
		return sharingContentConverter.toDTO(entity);
	}

	@Override
	public SharingContentDTO findOne(Long id) {
		return sharingContentConverter.toDTO(sharingContentRepo.findById(id).get());
	}

	@Override
	public String delete(Long id) {
		SharingContent sharingContent = sharingContentRepo.findById(id).get();
		sharingContent.setStatus(0);
		sharingContentRepo.save(sharingContent);
		return null;
	}

	@Override
	public List<SharingContentDTO> findAllByFolderIdAndUser(Long folderId, Long userId) {
		List<SharingContent> sharingContentList = sharingContentRepo.findByFolderIdAndUserid(folderId, userId);
		List<SharingContentDTO> dtos = new ArrayList<>();
		for (SharingContent sharingContent : sharingContentList) {
			SharingContentDTO dto = mapperList(sharingContent);		
			dtos.add(dto);	
		}
		return dtos;
	}

	@Override
	public List<SharingContentDTO> findAllByUserId(Long userId) {
		List<SharingContent> sharingContentList = sharingContentRepo.findByUserid(userId);
		List<SharingContentDTO> dtos = new ArrayList<>();
		for (SharingContent contentSharing : sharingContentList) {
			SharingContentDTO dto = mapperList(contentSharing);
			dtos.add(dto);
		}
		return dtos;
	}
	
	private SharingContentDTO mapperList(SharingContent contentSharing) {
		SharingContentDTO dto = new SharingContentDTO();
		Content content = contentRepsitory.findById(contentSharing.getContentid()).get();
		dto.setName(content.getName());
		dto.setContentId(contentSharing.getContentid());
		dto.setFolderId(contentSharing.getFolderid());
		dto.setUserId(contentSharing.getUserid());
		dto.setFormat(content.getFormat());
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss");
		String dateString = format.format(content.getCreatedDate());
		dto.setDate(dateString);
		dto.setUrl(content.getUrl());
		dto.setId(contentSharing.getId());
		dto.setStatus(contentSharing.getStatus());
		return dto;
	}

}
