package net.codejava.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.converter.SharingFolderConverter;
import net.codejava.dto.SharingFolderDTO;
import net.codejava.model.Content;
import net.codejava.model.Folder;
import net.codejava.model.SharingContent;
import net.codejava.model.SharingFolder;
import net.codejava.repository.ContentRepository;
import net.codejava.repository.FolderRepository;
import net.codejava.repository.SharingContentRepository;
import net.codejava.repository.SharingFolderRepository;
import net.codejava.service.GenericService;
import net.codejava.service.SharingFolderService;

@Service
public class SharingFolderServiceImpl implements SharingFolderService, GenericService<SharingFolderDTO> {
	
	@Autowired
	private SharingFolderRepository sharingFolderRepo;
	
	@Autowired
	private SharingFolderConverter sharingFolderConverter;
	
	@Autowired
	private SharingContentRepository sharingContentRepo;
	
	@Autowired
	private FolderRepository folderRepository;
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Override
	public List<SharingFolderDTO> findAll() {
		return sharingFolderConverter.toDTOList(sharingFolderRepo.findAll()); 
	}

	@Override
	public SharingFolderDTO save(SharingFolderDTO dto) {
		if (dto.getId() == null) {
			List<Content> contentList = contentRepository.findByFolderid(dto.getFolderId());
			if (contentList != null) {
				for (Content content : contentList) {
					SharingContent sharingContent = new SharingContent();
					sharingContent.setContentid(content.getId());
					sharingContent.setCreatedDate(new Date());
					sharingContent.setFolderid(content.getFolderid());
					sharingContent.setUserid(dto.getUserId());
					sharingContent.setStatus(1);
					sharingContentRepo.save(sharingContent);
				}
			}	
			
			List<Folder> folderList = folderRepository.findByFolderId(dto.getFolderId());
			for (Folder folder : folderList) {
				saveSharingFile(folder.getId(), dto.getUserId());
				
				SharingFolder sharingFolder = new SharingFolder();
				sharingFolder.setCreatedDate(new Date());
				sharingFolder.setUserid(dto.getUserId());
				sharingFolder.setFolderid(folder.getId());
				sharingFolder.setFolderParentId(folder.getFolderId());
				sharingFolder.setStatus(1);
				sharingFolderRepo.save(sharingFolder);
			}
			
			dto.setFolderParentId((long) 1);
			dto.setStatus(1);
			dto.setCreatedDate(new Date());
		} else {
			SharingFolder folder = sharingFolderRepo.findById(dto.getId()).get();
			dto.setUserId(folder.getUserid());
			dto.setFolderId(folder.getFolderid());
			dto.setCreatedDate(folder.getCreatedDate());
			dto.setStatus(folder.getStatus());
		}
		SharingFolder entity = sharingFolderRepo.save(sharingFolderConverter.toEntity(dto));
		return sharingFolderConverter.toDTO(entity);
	}
	
	private void saveSharingFile(Long id, Long userId) {
		List<Content> contentList = contentRepository.findByFolderid(id);
		if (contentList != null) {
			for (Content content : contentList) {
				SharingContent sharingContent = new SharingContent();
				sharingContent.setContentid(content.getId());
				sharingContent.setCreatedDate(new Date());
				sharingContent.setFolderid(content.getFolderid());
				sharingContent.setUserid(userId);
				sharingContent.setStatus(1);
				sharingContentRepo.save(sharingContent);
			}
		}	
		
		List<Folder> folderList = folderRepository.findByFolderId(id);
		for (Folder folder : folderList) {
			SharingFolder sharingFolder = new SharingFolder();
			sharingFolder.setCreatedDate(new Date());
			sharingFolder.setUserid(userId);
			sharingFolder.setFolderid(folder.getId());
			sharingFolder.setFolderParentId(folder.getFolderId());
			sharingFolder.setStatus(1);
			sharingFolderRepo.save(sharingFolder);
		}
	}

	@Override
	public SharingFolderDTO findOne(Long id) {
		return sharingFolderConverter.toDTO(sharingFolderRepo.findById(id).get());
	}

	@Override
	public String delete(Long id) {
		SharingFolder sharingFolder = sharingFolderRepo.findById(id).get();
		List<SharingContent> sharingListContent = sharingContentRepo.findByFolderid(sharingFolder.getFolderid());
		if (sharingListContent != null) {
			for (int i = 0; i < sharingListContent.size(); i++) sharingListContent.get(i).setStatus(0);
		}
		
		List<SharingFolder> sharingFolderList = sharingFolderRepo.findByFolderParentId(sharingFolder.getFolderid());
		for (int i = 0; i < sharingFolderList.size(); i++) {
			delete(sharingFolderList.get(i).getId());
		}
		
		sharingFolder.setStatus(0);
		sharingFolderRepo.save(sharingFolder);
		return null;
	}

	@Override
	public List<SharingFolderDTO> findAllByFolderAndUser(Long folderId, Long userId) {
		List<SharingFolder> sharingFolderList = sharingFolderRepo.findByFolderIdAndUserid(folderId, userId);
		List<SharingFolderDTO> dtos = new ArrayList<>();
		for (SharingFolder folderSharing : sharingFolderList) {
			SharingFolderDTO dto = mapperList(folderSharing);
			dtos.add(dto);
		}
		return dtos;	
	}

	@Override
	public List<SharingFolderDTO> findAllByUserId(Long userId) {
		List<SharingFolder> sharingFolderList = sharingFolderRepo.findByUserid(userId);
		List<SharingFolderDTO> dtos = new ArrayList<>();
		for (SharingFolder folderSharing : sharingFolderList) {
			SharingFolderDTO dto = mapperList(folderSharing);
			dtos.add(dto);
		}
		return dtos;
	}
	
	private SharingFolderDTO mapperList(SharingFolder folderSharing) {
		SharingFolderDTO dto = new SharingFolderDTO();
		Folder folder = folderRepository.findById(folderSharing.getFolderid()).get();
		dto.setId(folderSharing.getId());
		dto.setName(folder.getName());
		dto.setFolderParentId(folderSharing.getFolderParentId());
		dto.setFolderId(folderSharing.getFolderid());
		dto.setUserId(folderSharing.getUserid());
		dto.setStatus(folderSharing.getStatus());
		return dto;
	}

	@Override
	public List<SharingFolderDTO> findAllByFolderId(Long folderId) {
		List<SharingFolderDTO> sharingFolderList = sharingFolderConverter.toDTOList(sharingFolderRepo.findByFolderid(folderId));
		return sharingFolderList;
	}

}
