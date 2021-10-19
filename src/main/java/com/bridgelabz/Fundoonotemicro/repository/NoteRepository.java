package com.bridgelabz.Fundoonotemicro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.Fundoonotemicro.entity.*;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

}
