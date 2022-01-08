package com.inderjeet.noteapp.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.inderjeet.noteapp.dataBase.NoteDataBase
import com.inderjeet.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

class Repository(context: Context) {

    private val notesDao= NoteDataBase.getNotesDatabase(context)?.NoteDao()

    fun getNotes():Flow<List<Note>>{
        return notesDao!!.getAllNote()
    }
    suspend fun insertNote(note:Note){
        notesDao?.saveNote(note)
    }

    suspend fun deleteNote(id:Int){
        notesDao?.deleteNote(id)
    }

}