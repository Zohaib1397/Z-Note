package com.example.z_note.feature.domain.repository

import androidx.lifecycle.LiveData
import com.example.z_note.feature.data.data_source.NoteDao
import com.example.z_note.feature.domain.model.Note

class NoteRepository(
    private val dao:NoteDao
) {
    fun getAllNotes():LiveData<List<Note>> = dao.getAllNotes()

    suspend fun addNote(note:Note) = dao.addNote(note)
}