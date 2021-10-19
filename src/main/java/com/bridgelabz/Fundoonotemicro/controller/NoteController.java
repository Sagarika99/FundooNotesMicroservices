package com.bridgelabz.Fundoonotemicro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.bridgelabz.fundoonotes.dto.NoteDTO;
//import com.bridgelabz.fundoonotes.entity.Note;
//import com.bridgelabz.fundoonotes.entity.NoteImage;
//import com.bridgelabz.fundoonotes.response.Response;
//import com.bridgelabz.fundoonotes.service.NoteService;


import com.bridgelabz.Fundoonotemicro.dto.*;
import com.bridgelabz.Fundoonotemicro.entity.*;
import com.bridgelabz.Fundoonotemicro.response.*;
import com.bridgelabz.Fundoonotemicro.service.*;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	@PostMapping
	public ResponseEntity<Response> createNote(@RequestHeader String token,@Valid @RequestBody NoteDTO noteDTO,BindingResult result){
		
		if(result.hasErrors()) {
			return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(),result.getAllErrors().get(0).getDefaultMessage(),null),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		Note note=noteService.createNote(noteDTO, token);
		return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(),"Note Created",note),HttpStatus.CREATED);
		
	}
	
//	@GetMapping("/{noteId}")
//	public ResponseEntity<Response> getNote(@RequestHeader String token, @PathVariable Long noteId){
//		
//		Note note = noteService.getNote(token, noteId);
//		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Note Retrived Successfully", note),HttpStatus.OK);	
//	}
	
	@GetMapping
	public ResponseEntity<Response> getNotes(@RequestHeader String token){
		
	
		List<Note> notes=noteService.getNotes(token);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"Response retrived successfully",notes),HttpStatus.OK);
		
	}
	
//	@PutMapping(value = {"/pin/{noteId}","/un-pin/{noteId}"})
//	public ResponseEntity<Response> changePinStatus(@RequestHeader String token,@PathVariable Long noteId){
//		
//	Note note = noteService.pinNote(token, noteId);
//		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Note Pin status is changed Successfully", note),HttpStatus.OK);	
//	}
//	
//	@PutMapping(value = {"/archive/{noteId}","/un-archive/{noteId}"})
//	public ResponseEntity<Response> changeArchiveStatus(@RequestHeader String token,@PathVariable Long noteId){	
//	Note note = noteService.archiveNote(token, noteId);
//		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Note Archive status is changed Successfully", note),HttpStatus.OK);	
//	}
//	
//	@PutMapping(value = {"/trash/{noteId}","/restore/{noteId}"})
//	public ResponseEntity<Response> changetrashStatus(@RequestHeader String token,@PathVariable Long noteId){	
//	Note note = noteService.trashNote(token, noteId);
//		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Note Trash status is changed Successfully", note),HttpStatus.OK);	
//	}
//	
//	@DeleteMapping("/{noteId}")
//	public ResponseEntity<Response> deleteNote(@RequestHeader String token,@PathVariable Long noteId){	
//	Note note = noteService.deleteNote(token, noteId);
//		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Note deleted Successfully", note),HttpStatus.OK);	
//	}
//	
//
//	@PostMapping("/image/{noteId}")
//	public ResponseEntity<Response> uploadNoteImage(@RequestHeader String token,@PathVariable Long noteId,@RequestParam MultipartFile file){
//		NoteImage noteImage = noteService.addImage(token, file, noteId);
//		
//		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Image added to note Successfully", noteImage),HttpStatus.OK);	
//
//	}
	
	
}
