package net.codejava.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.converter.ContentConverter;
import net.codejava.converter.FolderConverter;
import net.codejava.dto.ContentDTO;
import net.codejava.dto.FolderDTO;
import net.codejava.dto.RemainDTO;
import net.codejava.model.Content;
import net.codejava.model.Folder;
import net.codejava.model.SharingContent;
import net.codejava.model.SharingFolder;
import net.codejava.model.User;
import net.codejava.repository.ContentRepository;
import net.codejava.repository.FolderRepository;
import net.codejava.repository.SharingContentRepository;
import net.codejava.repository.SharingFolderRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.RemainService;

@Service
public class RemainServiceImpl implements RemainService {
	
	@Autowired
	private SharingFolderRepository sharingFolderRepo;
	
	@Autowired
	private SharingContentRepository sharingContentRepo;
	
	@Autowired
	private FolderRepository folderRepository;
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FolderConverter folderConverter;
	
	@Autowired
	private ContentConverter contentConverter;

	@Override
	public List<RemainDTO> findAllByUserId(Long userId) {
		List<RemainDTO> dtos = new ArrayList<>();
		
		List<SharingFolder> folderList = sharingFolderRepo.findByUserid(userId);		
		for (SharingFolder folderSharing : folderList) {
			Folder folder = folderRepository.findById(folderSharing.getFolderid()).get();
			User user = userRepository.findById(folder.getUserid()).get();
			RemainDTO dto = new RemainDTO();
			dto.setFileName(folder.getName());
			dto.setFullName(user.getFullName());
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = format.format(folderSharing.getCreatedDate());
			dto.setDate(dateString);
			dtos.add(dto);
		}
		
		List<SharingContent> contentList = sharingContentRepo.findByUserid(userId);
		for (SharingContent contentSharing : contentList) {
			Content content = contentRepository.findById(contentSharing.getContentid()).get();
			User user = userRepository.findById(content.getUserId()).get();
			RemainDTO dto = new RemainDTO();
			dto.setFileName(content.getName());
			dto.setFullName(user.getFullName());
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = format.format(contentSharing.getCreatedDate());
			dto.setDate(dateString);
			dtos.add(dto);
		}	
		return dtos;
	}

	@Override
	public List<FolderDTO> findAllFolderTrash(Long userId, int status) {
		List<FolderDTO> folderList = folderConverter.toDTOList(folderRepository.findAllByUserAndStatus(userId, status));
		List<FolderDTO> dtos = new ArrayList<>();
		for (FolderDTO dto : folderList) {
			Folder folder = folderRepository.findById(dto.getFolderId()).get();
			if (folder.getStatus() == 1) {
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public List<ContentDTO> findAllContentTrash(Long userId, int status) {
		List<ContentDTO> contentList = contentConverter.toDTOList(contentRepository.findAllByUserAndStatus(userId, status));
		List<ContentDTO> dtos = new ArrayList<>();
		for (ContentDTO dto : contentList) {
			Folder folder = folderRepository.findById(dto.getFolderId()).get();
			if (folder.getStatus() == 1) {
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public String restoreFolder(Long folderId) {
		List<Content> contentList = contentRepository.findByFolderid(folderId);
		if (contentList != null) {
			for (int i = 0; i < contentList.size(); i++) {
				contentList.get(i).setStatus(1);
			}
		}
		
		List<Folder> folderList = folderRepository.findByFolderId(folderId);
		for (int i = 0; i < folderList.size(); i++) {
			restoreFolder(folderList.get(i).getId());
		}
		
		Folder folder = folderRepository.findById(folderId).get();
		folder.setStatus(1);
		folderRepository.save(folder); 
		return null;
	}

	@Override
	public String restoreContent(Long contentId) {
		Content content = contentRepository.findById(contentId).get();
		content.setStatus(1);
		contentRepository.save(content);
		return null;
	}

}
