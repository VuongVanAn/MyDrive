package net.codejava.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.codejava.converter.ContentConverter;
import net.codejava.dto.ContentDTO;
import net.codejava.model.Content;
import net.codejava.model.SharingContent;
import net.codejava.repository.ContentRepository;
import net.codejava.repository.SharingContentRepository;
import net.codejava.service.ContentService;
import net.codejava.service.GenericService;

@Service
public class ContentServiceImpl implements ContentService, GenericService<ContentDTO> {
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private ContentConverter contentConveter;
	
	@Autowired
	private SharingContentRepository sharingContentRepo;

	@Override
	public List<ContentDTO> findAll() {
		List<ContentDTO> contentList = contentConveter.toDTOList(contentRepository.findAll());
		for (int i = 0; i < contentList.size(); i++) {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = format.format(contentList.get(i).getCreatedDate());
			contentList.get(i).setDate(dateString);
		}
		return contentList;
	}

	@Override
	public ContentDTO save(ContentDTO dto) {
		dto.setStatus(1);
		Content entity = contentRepository.save(contentConveter.toEntity(dto));
		return contentConveter.toDTO(entity);
	}

	@Override
	public ContentDTO findOne(Long id) {
		return contentConveter.toDTO(contentRepository.findById(id).get());
	}

	@Override
	public String delete(Long id) {
		Content content = contentRepository.findById(id).get();
		content.setStatus(0);
		contentRepository.save(content);
		return null;
	}

	@Override
	public ContentDTO findByName(String name) {
		return contentConveter.toDTO(contentRepository.findByName(name));
	}

	@Override
	public void saveContent(String url, String name, MultipartFile file, Long id, Long userId) {
		Content entity = new Content();		
        try {
        	entity.setName(name);
        	entity.setDocFile(file.getBytes());
        	entity.setFormat(FilenameUtils.getExtension(file.getOriginalFilename()));
            entity.setCreatedDate(new Date());
            entity.setFolderid(id);
            entity.setUrl(url);
            entity.setUserId(userId);
            entity.setStatus(1);
            contentRepository.save(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}       	
	}

	@Override
	public List<ContentDTO> findByFolderAndUser(Long id, Long userId) {		
		return contentConveter.toDTOList(contentRepository.findByFolderIdAndUserId(id, userId));
	}

	@Override
	public List<ContentDTO> findSharingContentByUser(Long id) {
		List<SharingContent> sharingContent = sharingContentRepo.findByUserid(id);
		List<ContentDTO> dtos = new ArrayList<ContentDTO>();
		for (SharingContent item : sharingContent) {
			ContentDTO dto = contentConveter.toDTO(contentRepository.findById(item.getContentid()).get());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<ContentDTO> findByFolderId(Long folderId) {
		return contentConveter.toDTOList(contentRepository.findByFolderid(folderId));
	}

}
