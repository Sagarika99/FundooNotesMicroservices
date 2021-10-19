package com.bridgelabz.Fundoonotemicro.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import com.bridgelabz.Fundoonotemicro.dto.*;
import com.bridgelabz.Fundoonotemicro.entity.*;
import com.bridgelabz.Fundoonotemicro.repository.*;
import com.bridgelabz.Fundoonotemicro.exception.*;
import com.bridgelabz.Fundoonotemicro.utils.*;

@Service
public class NoteServiceImp implements NoteService {

	
	@Autowired
	private TokenService tokenService;
	
//	@Autowired
//	private UserRepository userRepository;
	
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
//	@Autowired
//	private S3service s3service;
	

	
	@Autowired
	private NoteImageRepository noteImageRepository;
	
	@Override
	public Note createNote(NoteDTO noteDTO, String token) {
		long userid=tokenService.decodeToken(token);
		System.out.println(token);
		//User user=userRepository.findById(tokenService.decodeToken(token)).orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found"));
		boolean isuserpresent=restTemplate.getForObject("http://USER-SERVER/user/isuserpresent/"+token,Boolean.class);
		System.out.println(isuserpresent);
		if(isuserpresent)
		{
			Note note= new Note();
			BeanUtils.copyProperties(noteDTO, note);
			note.setUserId(userid);
			note=noteRepository.save(note);
//			user.getNotes().add(note);
//			userRepository.save(user);
			return note;	
		}
		throw new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found");		
	}

	@Override
	public List<Note> getNotes(String token) {
		// TODO Auto-generated method stub
		//User user=userRepository.findById(tokenService.decodeToken(token)).orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found"));
		long userid=tokenService.decodeToken(token);
		//User user=userRepository.findById(tokenService.decodeToken(token)).orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found"));
		boolean isuserpresent=restTemplate.getForObject("http://localhost:8081/user/isuserpresent/"+token,boolean.class);
		if(isuserpresent)
			return noteRepository.findAll().stream().filter(note -> note.getIsTrashed().equals(false) && note.getUserId()==userid).collect(Collectors.toList());
		throw new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found");
		//return user.getNotes().stream().filter(note -> note.getIsTrashed().equals(false)).collect(Collectors.toList());
		
	}

//	@Override
//	public Note pinNote(String token, Long noteId) {
//		// TODO Auto-generated method stub
//		User user=userRepository.findById(tokenService.decodeToken(token)).orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found"));
//		Note isNotePresent=user.getNotes().stream().filter(note->note.getId().equals(noteId)).findAny().orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"Note Not Found"));
//		if(isNotePresent.getIsPinned().equals(true)) {
//			isNotePresent.setIsPinned(false);
//		}
//		else {
//			isNotePresent.setIsPinned(true);
//		}
//		return noteRepository.save(isNotePresent);
//	}
//
//	@Override
//	public Note archiveNote(String token, Long noteId) {
//		// TODO Auto-generated method stub
//		User user=userRepository.findById(tokenService.decodeToken(token)).orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found"));
//		Note isNotePresent=user.getNotes().stream().filter(note->note.getId().equals(noteId)).findAny().orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"Note Not Found"));
//		if(isNotePresent.getIsArchived().equals(true)) {
//			isNotePresent.setIsArchived(false);
//		}
//		else {
//			isNotePresent.setIsArchived(true);
//		}
//		return noteRepository.save(isNotePresent);
//	}
//
//	@Override
//	public Note trashNote(String token, Long noteId) {
//		// TODO Auto-generated method stub
//		User user=userRepository.findById(tokenService.decodeToken(token)).orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found"));
//		Note isNotePresent=user.getNotes().stream().filter(note->note.getId().equals(noteId)).findAny().orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"Note Not Found"));
//		if(isNotePresent.getIsTrashed().equals(true)) {
//			isNotePresent.setIsTrashed(false);
//		}
//		else {
//			isNotePresent.setIsTrashed(true);
//		}
//		return noteRepository.save(isNotePresent); 
//	}
//
//	@Override
//	public Note deleteNote(String token, Long noteId) {
//		// TODO Auto-generated method stub4
//		User user=userRepository.findById(tokenService.decodeToken(token)).orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found"));
//		Note isNotePresent=user.getNotes().stream().filter(note->note.getId().equals(noteId)).findAny().orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"Note Not Found"));
//		noteRepository.delete(isNotePresent);
//		return isNotePresent;
//	}
//
//	@Override
//	public Note getNote(String token, Long noteId) {
//		// TODO Auto-generated method stub
//		User user=userRepository.findById(tokenService.decodeToken(token)).orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"User Not Found"));
//		return user.getNotes().stream().filter(note->note.getId().equals(noteId)).findAny().orElseThrow(()-> new FundooException(HttpStatus.NOT_FOUND.value(),"Note Not Found"));
//		
//			
//	}
//
//	@Override
//	public NoteImage addImage(String token, MultipartFile file, Long noteId) {
//		// TODO Auto-generated method stub
//		Note note=getNote(token,noteId);
//		String url=s3service.fileUpload(file,"note images",note.getId().toString());
//		NoteImage noteImage=new NoteImage();
//		noteImage.setUrl(url);
//		NoteImage savedNote=noteImageRepository.save(noteImage);
//		note.getNoteImage().add(savedNote);
//		noteRepository.save(note);
//		return savedNote;
//	}

	
}
