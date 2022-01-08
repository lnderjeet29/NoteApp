package com.inderjeet.noteapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inderjeet.noteapp.model.Note

import com.inderjeet.noteapp.repo.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class HomeViewModel:ViewModel() {
    private lateinit var repo:Repository

    fun initDB(applicationContext: Context){
        repo= Repository(applicationContext)
    }

    fun getNotes(): Flow<List<Note>> {
        return repo.getNotes()
    }

    fun insertNotes(note:Note){
        viewModelScope.launch {
            repo.insertNote(note)
        }
    }
    fun deleteNote(id:Int){
       viewModelScope.launch {
           repo.deleteNote(id)
       }
    }

}