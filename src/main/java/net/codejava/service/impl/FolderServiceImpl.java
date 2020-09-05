package net.codejava.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.converter.FolderConverter;
import net.codejava.dto.FolderDTO;
import net.codejava.model.Content;
import net.codejava.model.Folder;
import net.codejava.model.SharingFolder;
import net.codejava.repository.ContentRepository;
import net.codejava.repository.FolderRepository;
import net.codejava.repository.SharingFolderRepository;
import net.codejava.service.FolderService;
import net.codejava.service.GenericService;

@Service
public class FolderServiceImpl implements FolderService, GenericService<FolderDTO> {
	
	@Autowired
	private FolderRepository folderRepository;
	
	@Autowired
	private FolderConverter folderConverter;
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private SharingFolderRepository sharingFolderRepo;

	@Override
	public List<FolderDTO> findAll() {
		return folderConverter.toDTOList(folderRepository.findAll());
	}

	@Override
	public FolderDTO save(FolderDTO dto) {
		dto.setCreatedDate(new Date());
		dto.setStatus(1);
		Folder entity = folderRepository.save(folderConverter.toEntity(dto));
		return folderConverter.toDTO(entity);
	}

	@Override
	public FolderDTO findOne(Long id) {
		return folderConverter.toDTO(folderRepository.findById(id).get());
	}

	@Override
	public String delete(Long id) {
		List<Content> contentList = contentRepository.findByFolderid(id);
		if (contentList != null) {
			for (int i = 0; i < contentList.size(); i++) contentList.get(i).setStatus(0);
		}
		
		List<Folder> folderList = folderRepository.findByFolderId(id);
		for (int i = 0; i < folderList.size(); i++) {
			delete(folderList.get(i).getId());
		}
		
		Folder folder = folderRepository.findById(id).get();
		folder.setStatus(0);
		folderRepository.save(folder);
		return null;
	}

	@Override
	public List<FolderDTO> findSharingFolderByUser(Long id) {
		List<SharingFolder> sharingFolder = sharingFolderRepo.findByUserid(id);
		List<FolderDTO> dtos = new ArrayList<FolderDTO>();
		for (SharingFolder item : sharingFolder) {
			FolderDTO dto = folderConverter.toDTO(folderRepository.findById(item.getFolderid()).get());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<FolderDTO> findByFolderIdAndUserId(Long folderId, Long userId) {
		return folderConverter.toDTOList(folderRepository.findByFolderIdAndUserid(folderId, userId));
	}

	@Override
	public List<FolderDTO> findByFolderId(Long folderId) {
		return folderConverter.toDTOList(folderRepository.findByFolderId(folderId));
	}

}
